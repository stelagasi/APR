package hr.fer.apr.dz.optimization.functions;

import hr.fer.apr.lu.matrix.SquareMatrix;
import hr.fer.apr.lu.matrix.Vector;

import java.util.function.Function;

import static java.lang.Math.pow;

public class SecondDerivationGenerator {

    public static Function<Vector, SquareMatrix> f1(){
        return vector -> {
            double element1 = -400 * vector.getElementAt(1) + 1200 * pow(vector.getElementAt(0), 2) + 2;
            double element2 = -400 * vector.getElementAt(0);
            double element3 = 200;
            double element4 = -400 * vector.getElementAt(0);
            return new SquareMatrix(2, new double[]{element1, element2, element3, element4});
        };
    }

    public static Function<Vector, SquareMatrix> f2(){ return vector -> new SquareMatrix(2, new double[]{2, 0, 0, 8}); }

    public static Function<Vector, SquareMatrix> f3(){ return vector -> new SquareMatrix(2, new double[]{2, 0, 0, 2}); }

    public static Function<Vector, SquareMatrix> f4(){ return vector -> new SquareMatrix(2, new double[]{2, 0, 0, 2}); }
}
