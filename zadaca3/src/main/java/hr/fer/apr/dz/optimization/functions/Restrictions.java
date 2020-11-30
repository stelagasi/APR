package hr.fer.apr.dz.optimization.functions;

import hr.fer.apr.lu.matrix.Vector;
import java.util.function.Function;

public class Restrictions {

    private Restrictions(){}

    public static Function<Vector, Boolean> differenceBiggerThanZero(){
        return vector -> vector.getElementAt(1) - vector.getElementAt(0) >= 0;
    }

    public static Function<Vector, Boolean> twoMinusFirst(){
        return vector -> 2 - vector.getElementAt(0) >= 0;
    }

    public static Function<Vector, Boolean> threeMinusFirstMinusSecond(){
        return vector -> 3 - vector.getElementAt(0) - vector.getElementAt(1) >= 0;
    }

    public static Function<Vector, Boolean> threePlusOnePointFiveFirstMinusSecond(){
        return vector -> 3 + 1.5 * vector.getElementAt(0) - vector.getElementAt(1) >= 0;
    }

    public static Function<Vector, Boolean> secondMinusOne(){
        return vector -> vector.getElementAt(1) - 1 >= 0;
    }

//    public static Function<Vector, Boolean> fromMinusHundredToHundred(){
//        return vector -> vector.getElementAt(0) > -100 && vector.getElementAt(0) < 100
//                && vector.getElementAt(1) > -100 && vector.getElementAt(1) < 100;
//    }
}
