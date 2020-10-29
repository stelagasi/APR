package hr.fer.apr.optimization;

import hr.fer.apr.lu.matrix.Vector;
import java.util.function.Function;

public class CoordinateAxisSearch {

    private CoordinateAxisSearch(){}

    public static Vector of(Function<Vector, Double> function, Vector startingPoint, Vector e, double h){
        Vector x = new Vector(startingPoint);
        Vector xs;
        do {
            xs = new Vector(x);
            for (int i = 0; i < startingPoint.getNumberOfRows(); i++) {
                x = GoldenSectionSearch.of(function, i, x, e, h);
            }
        } while(condition(x, xs, e));
    return x;
    }

    private static boolean condition(Vector x, Vector xs, Vector e){
        for (int i = 0; i < x.getNumberOfRows(); i++) {
            if(Math.abs(x.getElementAt(i) - xs.getElementAt(i)) > e.getElementAt(i)){
                return true;
            }
        }
        return false;
    }
}
