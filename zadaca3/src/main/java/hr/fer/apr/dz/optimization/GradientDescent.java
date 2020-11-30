package hr.fer.apr.dz.optimization;

import hr.fer.apr.lu.matrix.Vector;
import hr.fer.apr.optimization.GoldenSectionSearch;

import java.util.function.Function;

public class GradientDescent {

    private GradientDescent() {
    }

    public static Vector of(Function<Vector, Double> function, Function<Vector, Vector> functionGradient, Vector startingPoint, double epsilon) {
        Vector nextPoint = new Vector(startingPoint);
        while (true) {
            Vector point = nextPoint;
            Vector v = functionGradient.apply(point);
            v = v.multiplicationWithScalar(-1);
            System.out.println(v);
            if(v.getEuclideanNorm() < epsilon) return nextPoint;

            for (int i = 0; i < point.getNumberOfRows(); i++) {
                nextPoint.setElementAt(i, point.getElementAt(i) + v.getElementAt(i));
            }
        }
    }

    public static Vector withGoldenSection(Function<Vector, Double> function, Function<Vector, Vector> functionGradient, Vector startingPoint, double epsilon) {
        Vector nextPoint = new Vector(startingPoint);
        while (true) {
            Vector point = nextPoint;
            Vector v = functionGradient.apply(point);
            v = v.multiplicationWithScalar(-1);

            if (v.getEuclideanNorm() < epsilon) return nextPoint;

            Function<Vector, Double> lambdaFunction = getLambdaFunction(function, v, point);
            nextPoint = GoldenSectionSearch.of(lambdaFunction, 0, point,
                    new Vector(1, new double[]{epsilon}), 1);
        }
    }

    private static Function<Vector, Double> getLambdaFunction(Function<Vector, Double> function, Vector v, Vector point) {
        return lambda -> {
            Vector lambdaVector = new Vector(2, new double[2]);
            lambdaVector.setElementAt(0, point.getElementAt(0) +
                    v.getElementAt(0) * lambda.getElementAt(0));
            lambdaVector.setElementAt(1, point.getElementAt(1) +
                    v.getElementAt(1) * lambda.getElementAt(0));
            return function.apply(lambdaVector);
        };
    }
}
