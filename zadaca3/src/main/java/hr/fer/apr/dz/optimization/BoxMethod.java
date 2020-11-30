package hr.fer.apr.dz.optimization;

import hr.fer.apr.dz.optimization.restriction.ImplicitRestriction;
import hr.fer.apr.lu.matrix.Vector;
import hr.fer.apr.optimization.ValueSaver;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.lang.Math.*;
import static java.lang.Math.pow;

public class BoxMethod {
    private static final int NUMBER_OF_VARIABLES = 2;

    private BoxMethod() {
    }

    public static Vector of(Function<Vector, Double> function, Vector startingPoint, Double explicitRestriction, List<ImplicitRestriction> restrictions, double alpha, double epsilon) {
        if (explicitRestriction != null) {
            for (int i = 0; i < startingPoint.getNumberOfRows(); i++) {
                if (startingPoint.getElementAt(i) > explicitRestriction
                        || startingPoint.getElementAt(i) < -explicitRestriction) {
                    throw new IllegalArgumentException("Starting point is violating explicit restriction.");
                }
            }
        }

        if (!checkImplicitRestrictions(startingPoint, restrictions)) {
            throw new IllegalArgumentException("Starting point is violating implicit restriction.");
        }

        ValueSaver centroid = new ValueSaver(startingPoint, function.apply(startingPoint));
        List<ValueSaver> points = new ArrayList<>();
        points.add(centroid);

        for (int i = 0; i < 2 * NUMBER_OF_VARIABLES; i++) {
            Vector point = new Vector(NUMBER_OF_VARIABLES, new double[NUMBER_OF_VARIABLES]);
            for (int j = 0; j < NUMBER_OF_VARIABLES; j++) {
                point.setElementAt(j, random() * (2 * explicitRestriction) - explicitRestriction);
            }
            boolean violating = true;
            while (violating) {
                violating = false;
                for (ImplicitRestriction restriction : restrictions) {
                    if (!restriction.getCondition().apply(point)) {
                        point = Vector.addition(point, centroid.getPoint()).multiplicationWithScalar(1. / 2);
                        violating = true;
                        break;
                    }
                }
            }
            ValueSaver pointValue = new ValueSaver(point, function.apply(point));
            points.add(pointValue);
            centroid = calculateCentroid(function, points, findWorstPoint(points));
        }
        int numberOfIterations = 0;
        do {
            ValueSaver worst = findWorstPoint(points);
            List<ValueSaver> pointsWithoutWorst = new ArrayList<>(points);
            pointsWithoutWorst.remove(worst);
            ValueSaver secondWorst = findWorstPoint(pointsWithoutWorst);

            centroid = calculateCentroid(function, points, worst);

            Vector xr = Vector.subtraction(centroid.getPoint().multiplicationWithScalar(1 + alpha), worst.getPoint().multiplicationWithScalar(alpha));

            for (int j = 0; j < NUMBER_OF_VARIABLES; j++) {
                if (xr.getElementAt(j) < -explicitRestriction) {
                    xr.setElementAt(j, -explicitRestriction);
                } else if (xr.getElementAt(j) > explicitRestriction) {
                    xr.setElementAt(j, explicitRestriction);
                }
            }
            while (!checkImplicitRestrictions(xr, restrictions)) {
                xr = Vector.addition(xr, centroid.getPoint()).multiplicationWithScalar(1. / 2);
            }
            if (function.apply(xr) > secondWorst.getValue()) {
                xr = Vector.addition(xr, centroid.getPoint()).multiplicationWithScalar(1. / 2);
            }
            points.remove(worst);
            points.add(new ValueSaver(xr, function.apply(xr)));
        } while (calculateStoppingCondition(points, centroid.getValue()) > epsilon && numberOfIterations++ < 10000);

        return centroid.getPoint();
    }

    private static ValueSaver calculateCentroid(Function<Vector, Double> function, List<ValueSaver> points, ValueSaver worstPoint) {
        Vector centroid = new Vector(worstPoint.getPoint().getNumberOfRows(), new double[worstPoint.getPoint().getNumberOfRows()]);
        for (ValueSaver point : points) {
            if (point.equals(worstPoint)) continue;
            centroid = Vector.addition(centroid, point.getPoint());
        }
        centroid = centroid.multiplicationWithScalar(1. / (points.size() - 1));
        return new ValueSaver(centroid, function.apply(centroid));
    }

    private static ValueSaver findWorstPoint(List<ValueSaver> points) {
        if (points.isEmpty()) throw new IllegalArgumentException("No given points");
        ValueSaver worst = points.get(0);
        for (int i = 1; i < points.size(); i++) {
            if (points.get(i).getValue() > worst.getValue()) worst = points.get(i);
        }
        return worst;
    }

    private static double calculateStoppingCondition(List<ValueSaver> points, Double centroidValue) {
        double sum = 0.0;
        for (ValueSaver point : points) {
            sum += pow(point.getValue() - centroidValue, 2);
        }
        sum /= points.size();
        return sqrt(sum);
    }

    private static boolean checkImplicitRestrictions(Vector point, List<ImplicitRestriction> restrictions) {
        for (ImplicitRestriction restriction : restrictions) {
            if (!restriction.getCondition().apply(point)) {
                return false;
            }
        }
        return true;
    }
}

