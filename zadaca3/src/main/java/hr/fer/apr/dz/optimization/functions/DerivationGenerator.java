package hr.fer.apr.dz.optimization.functions;

import hr.fer.apr.lu.matrix.Vector;
import java.util.function.Function;

import static java.lang.Math.pow;

public class DerivationGenerator {

    private DerivationGenerator(){}

    public static Function<Vector, Double> f1(int numberOfVariable){
        if(numberOfVariable > 1 || numberOfVariable < 0) {
            throw new IllegalArgumentException("Function has only two variables.");
        }
        if(numberOfVariable == 0){
            return vector -> -400 * vector.getElementAt(0) * vector.getElementAt(1)
                    + 400 * pow(vector.getElementAt(0), 3) + 2 * vector.getElementAt(0) - 2;
        } else return vector -> 200 * (vector.getElementAt(1) - pow(vector.getElementAt(0), 2));
    }

    public static Function<Vector, Double> f2(int numberOfVariable){
        if(numberOfVariable > 1 || numberOfVariable < 0) {
            throw new IllegalArgumentException("Function has only two variables.");
        }
        if(numberOfVariable == 0){
            return vector -> 2 * (vector.getElementAt(0) - 4);
        } else return vector -> 8 * (vector.getElementAt(1) - 2);
    }

    public static Function<Vector, Double> f3(int numberOfVariable){
        if(numberOfVariable > 1 || numberOfVariable < 0) {
            throw new IllegalArgumentException("Function has only two variables.");
        }
        if(numberOfVariable == 0){
            return vector -> 2 * (vector.getElementAt(0) - 2);
        } else return vector -> 2 * (vector.getElementAt(1) + 3);
    }

    public static Function<Vector, Double> f4(int numberOfVariable){
        if(numberOfVariable > 1 || numberOfVariable < 0) {
            throw new IllegalArgumentException("Function has only two variables.");
        }
        if(numberOfVariable == 0){
            return vector -> 2 * (vector.getElementAt(0) - 3);
        } else return vector -> 2 * vector.getElementAt(1);
    }
}