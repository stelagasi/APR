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
        for (int i = 0; i < 100; i++) {
            Vector point = nextPoint;
            Vector v = functionGradient.apply(point);
            v = v.multiplicationWithScalar(-1);
            if(v.getEuclideanNorm() < epsilon) return nextPoint;

            nextPoint = Vector.addition(point, v);
        }
        return nextPoint;
    }

    public static Vector withGoldenSection(Function<Vector, Double> function, Function<Vector, Vector> functionGradient, Vector startingPoint, double epsilon) {
        Vector nextPoint = new Vector(startingPoint);
        for (int i = 0; i < 10000; i++) {
            Vector point = nextPoint;
            Vector v = functionGradient.apply(point);

            if (v.getEuclideanNorm() < epsilon) return nextPoint;

            nextPoint = GoldenSectionSearch.ofDirection(function, v, point, epsilon, 1);
        }
        return nextPoint;
    }
}
