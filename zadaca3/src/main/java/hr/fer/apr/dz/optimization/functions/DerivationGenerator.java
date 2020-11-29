package hr.fer.apr.dz.optimization.functions;

import hr.fer.apr.lu.matrix.Vector;
import java.util.function.Function;

import static java.lang.Math.pow;

public class DerivationGenerator {

    private DerivationGenerator(){}

    public static Function<Vector, Vector> f1() {
        return vector -> {
            double x1 = -400 * vector.getElementAt(0) * vector.getElementAt(1)
                    + 400 * pow(vector.getElementAt(0), 3) + 2 * vector.getElementAt(0) - 2;
            double x2 = 200 * (vector.getElementAt(1) - pow(vector.getElementAt(0), 2));
            return new Vector(2, new double[]{x1, x2});
        };
    }

    public static Function<Vector, Vector> f2(){
        return vector -> {
            double x1 = 2 * (vector.getElementAt(0) - 4);
            double x2 = 8 * (vector.getElementAt(1) - 2);
            return new Vector(2, new double[]{x1, x2});
        };
    }

    public static Function<Vector, Vector> f3(){
        return vector -> {
            double x1 = 2 * (vector.getElementAt(0) - 2);
            double x2 = 2 * (vector.getElementAt(1) + 3);
            return new Vector(2, new double[]{x1, x2});
        };
    }

    public static Function<Vector, Vector> f4(){
        return vector -> {
            double x1 = 2 * (vector.getElementAt(0) - 3);
            double x2 = 2 * vector.getElementAt(1);
            return new Vector(2, new double[]{x1, x2});
        };
    }
}