package hr.fer.apr.optimization;

import hr.fer.apr.lu.matrix.Vector;

import java.util.function.Function;

import static java.lang.Math.*;

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

    public static Function<Vector, Double> f3(int index) {
        return vector -> {
            if (vector.getNumberOfRows() != index) {
                throw new IllegalArgumentException("Point doesn't have the same number of arguments as function.");
            }
            double value = 0.0;
            for (int i = 0; i < index; i++){
                value += pow(vector.getElementAt(i) - i, 2);
            }
            return value;
        };
    }

    public static Function<Vector, Double> JakobovicFunction(){
        return vector -> abs((vector.getElementAt(0) - vector.getElementAt(1)) *
                (vector.getElementAt(0) + vector.getElementAt(1)))
                + sqrt(pow(vector.getElementAt(0), 2) + pow(vector.getElementAt(1), 2));
    }

    public static Function<Vector, Double> ShafferFunction(){
        return vector -> {
            double sum = 0.0;
            for (int i = 0; i < vector.getNumberOfRows(); i++) {
                sum += vector.getElementAt(i) * vector.getElementAt(i);
            }
            return 0.5 + (pow(sin(sqrt(sum)), 2) - 0.5) / pow(1 + 0.001 * sum, 2);
        };
    }

}
