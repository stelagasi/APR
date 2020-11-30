package hr.fer.apr.dz.optimization;

import hr.fer.apr.dz.optimization.functions.FunctionGenerator;
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

            nextPoint = Vector.addition(point, v);
        }
    }

    public static Vector withGoldenSection(Function<Vector, Double> function, Function<Vector, Vector> functionGradient, Vector startingPoint, double epsilon) {
        Vector nextPoint = new Vector(startingPoint);
        while (true) {
            Vector point = nextPoint;
            Vector v = functionGradient.apply(point);
            v = v.multiplicationWithScalar(-1);

            if (v.getEuclideanNorm() < epsilon) return nextPoint;

            Function<Vector, Double> lambdaFunction = FunctionGenerator.getLambdaFunction(function, v, point);
            Vector shift = GoldenSectionSearch.ofDirection(lambdaFunction, v, point, epsilon, 1);
            nextPoint = Vector.addition(point, shift);
        }
    }
}
