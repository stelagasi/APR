package hr.fer.apr.lu;

import hr.fer.apr.lu.matrix.Vector;

import java.util.ArrayList;
import java.util.function.Function;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class NelderMeadSimplex {

    private NelderMeadSimplex() {
    }

    public static Vector of(Function<Vector, Double> function, Vector startingPoint, double offset, double alpha, double beta, double gamma, double epsilon, double sigma) {
        ArrayList<ValueSaver> points = new ArrayList<>();
        int maxIndex = 0, minIndex = 0;

        Double startingPointValue = function.apply(startingPoint);
        points.add(new ValueSaver(startingPoint, startingPointValue));

        Double maxValue = startingPointValue;
        Double minValue = startingPointValue;

        for (int i = 0; i < startingPoint.getNumberOfRows(); i++) {
            Vector point = new Vector(startingPoint);
            point.setElementAt(i, point.getElementAt(i) + offset);
            Double value = function.apply(point);
            points.add(new ValueSaver(point, value));
            if (maxValue < value) {
                maxIndex = i + 1;
                maxValue = value;
            }
            if (minValue > value) {
                minIndex = i + 1;
                minValue = value;
            }
        }

        double sum;
        Vector centroid;
        do {
            centroid = new Vector(startingPoint.getNumberOfRows(), new double[]{0});
            for (int i = 0; i < points.size(); i++) {
                if (i == maxIndex) continue;
                centroid.selfAddition(points.get(i).getPoint());
            }
            centroid = centroid.multiplicationWithScalar((double) 1 / (points.size() - 1));
            Vector xr = Vector.subtraction(centroid.multiplicationWithScalar(1 + alpha), points.get(maxIndex).getPoint());
            Double xrValue = function.apply(xr);
            if (xrValue < points.get(minIndex).getValue()) {
                Vector xe = Vector.addition(centroid.multiplicationWithScalar(1 - gamma), xr.multiplicationWithScalar(gamma));
                Double xeValue = function.apply(xe);
                if (xeValue < points.get(minIndex).getValue()) {
                    points.get(maxIndex).setPoint(xe);
                    points.get(maxIndex).setValue(xeValue);
                } else {
                    points.get(maxIndex).setPoint(xr);
                    points.get(maxIndex).setValue(xrValue);
                }
            } else {
                boolean xrBigger = true;
                for (int i = 0; i < points.size(); i++) {
                    if (i == maxIndex) continue;
                    if (xrValue <= points.get(i).getValue()) {
                        xrBigger = false;
                        break;
                    }
                }
                if (xrBigger) {
                    if (xrValue < points.get(maxIndex).getValue()) {
                        points.get(maxIndex).setPoint(xr);
                        points.get(maxIndex).setValue(xrValue);
                    }
                    Vector xk = Vector.addition(centroid.multiplicationWithScalar(1 - beta),
                            points.get(maxIndex).getPoint().multiplicationWithScalar(beta));
                    Double xk_value = function.apply(xk);
                    if (xk_value < points.get(maxIndex).getValue()) {
                        points.get(maxIndex).setPoint(xk);
                        points.get(maxIndex).setValue(xk_value);
                    } else {
                        for (int i = 0; i < points.size(); i++) {
                            if (i == minIndex) continue;
                            Vector point = Vector.addition(points.get(minIndex).getPoint(), points.get(i).getPoint()).multiplicationWithScalar(sigma);
                            Double value = function.apply(point);
                            points.get(i).setPoint(point);
                            points.get(i).setValue(value);
                        }
                    }
                } else {
                    points.get(maxIndex).setPoint(xr);
                    points.get(maxIndex).setValue(xrValue);
                }
            }
            Double centroidValue = function.apply(centroid);
            sum = 0.0;
            for (ValueSaver point : points) {
                sum += pow(point.getValue() - centroidValue, 2);
            }
            sum /= points.size();
            sum = sqrt(sum);

            minIndex = findMinIndex(points);
            maxIndex = findMaxIndex(points);
        } while (sum > epsilon);
        return centroid;
    }

    private static int findMinIndex(ArrayList<ValueSaver> points) {
        int minIndex = -1;
        double minValue = Double.MAX_VALUE;

        for (int i = 0; i < points.size(); i++) {
            double value = points.get(i).getValue();
            if (value < minValue) {
                minIndex = i;
                minValue = value;
            }
        }
        return minIndex;
    }

    private static int findMaxIndex(ArrayList<ValueSaver> points) {
        int maxIndex = -1;
        double maxValue = -Double.MAX_VALUE;

        for (int i = 0; i < points.size(); i++) {
            double value = points.get(i).getValue();
            if (value > maxValue) {
                maxIndex = i;
                maxValue = value;
            }
        }
        return maxIndex;
    }
}
