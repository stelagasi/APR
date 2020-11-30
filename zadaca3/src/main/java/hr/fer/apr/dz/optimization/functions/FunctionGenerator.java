package hr.fer.apr.dz.optimization.functions;

import hr.fer.apr.lu.matrix.Vector;
import java.util.function.Function;

import static java.lang.Math.pow;

public class FunctionGenerator {

    private FunctionGenerator() {
    }

    public static Function<Vector, Double> f1() {
        return vector -> 100 * pow((vector.getElementAt(1) - pow(vector.getElementAt(0), 2)), 2)
                + pow(1 - vector.getElementAt(0), 2);
    }

    public static Function<Vector, Double> f2() {
        return vector -> pow(vector.getElementAt(0) - 4, 2)
                + 4 * pow(vector.getElementAt(1) - 2, 2);
    }

    public static Function<Vector, Double> f3(){
        return vector -> pow(vector.getElementAt(0) - 2, 2) + pow(vector.getElementAt(1) + 3, 2);
    }

    public static Function<Vector, Double> f4(){
        return vector -> pow(vector.getElementAt(0) - 3, 2) + pow(vector.getElementAt(1), 2);
    }

    public static Function<Vector, Double> getLambdaFunction(Function<Vector, Double> function, Vector v, Vector point) {
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