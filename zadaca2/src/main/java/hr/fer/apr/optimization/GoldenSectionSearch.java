package hr.fer.apr.optimization;

import hr.fer.apr.lu.matrix.Vector;

import java.util.ArrayList;
import java.util.function.Function;

import static java.lang.Math.sqrt;

public class GoldenSectionSearch {
    private static final double k = 0.5 * (sqrt(5) - 1);

    private GoldenSectionSearch(){}

    public static Vector of(Function<Vector, Double> function, int dimension, Vector a, Vector b, Vector e){
        Vector c = new Vector(a);
        Vector d = new Vector(b);

        c.setElementAt(dimension,  b.getElementAt(dimension) - k * (b.getElementAt(dimension) - a.getElementAt(dimension)));
        d.setElementAt(dimension, a.getElementAt(dimension) + k * (b.getElementAt(dimension) - a.getElementAt(dimension)));

        double fc = function.apply(c);
        double fd = function.apply(d);

        while((b.getElementAt(dimension)) - a.getElementAt(dimension) > e.getElementAt(dimension)){
            if(fc < fd){
                b.setElementAt(dimension, d.getElementAt(dimension));
                d.setElementAt(dimension, c.getElementAt(dimension));
                c.setElementAt(dimension, b.getElementAt(dimension) - k * (b.getElementAt(dimension) - a.getElementAt(dimension)));
                fd = fc;
                fc = function.apply(c);
            } else {
                a.setElementAt(dimension, c.getElementAt(dimension));
                c.setElementAt(dimension, d.getElementAt(dimension));
                d.setElementAt(dimension, a.getElementAt(dimension) + k * (b.getElementAt(dimension) - a.getElementAt(dimension)));
                fc = fd;
                fd = function.apply(d);
            }
        }

        Vector result = new Vector(a);
        result.setElementAt(dimension, (a.getElementAt(dimension) + b.getElementAt(dimension))/2);
        return result;
    }

    public static Vector ofDirection(Function<Vector, Double> function, Vector a, Vector b, double e){
        Vector c = Vector.subtraction(b, Vector.subtraction(b, a).multiplicationWithScalar(k));
        Vector d = Vector.addition(a, Vector.subtraction(b, a).multiplicationWithScalar(k));

        double fc = function.apply(c);
        double fd = function.apply(d);

        while(Vector.subtraction(b, a).getEuclideanNorm() > e){
            if(fc < fd){
                b = d;
                d = c;
                c = Vector.subtraction(b, Vector.subtraction(b, a).multiplicationWithScalar(k));
                fd = fc;
                fc = function.apply(c);
            } else {
                a = c;
                c = d;
                d = Vector.addition(a, Vector.subtraction(b, a).multiplicationWithScalar(k));
                fc = fd;
                fd = function.apply(d);
            }
        }
        return Vector.addition(a, b).multiplicationWithScalar(1./2);
    }

//    private static boolean compare(Vector v1, Vector v2){
//        if(v1.getNumberOfRows() != v2.getNumberOfRows()) throw new IllegalArgumentException("Vectors are not the same size");
//
//        for(int i = 0; i < v1.getNumberOfRows(); i++){
//            if(v2.getElementAt(i) >= v1.getElementAt(i)) return false;
//        }
//
//        return true;
//    }

    public static Vector of(Function<Vector, Double> function, int dimension, Vector startingPoint, Vector e, double h){
        ArrayList<Vector> unimodalInterval = UnimodalIntervalSearch.of(function, h, startingPoint, dimension);
        Vector a = unimodalInterval.get(0);
        Vector b = unimodalInterval.get(1);

        return of(function, dimension, a, b, e);
    }

    public static Vector ofDirection(Function<Vector, Double> function, Vector direction, Vector startingPoint, double e, double h){
        ArrayList<Vector> unimodalInterval = UnimodalIntervalSearch.ofDirection(function, h, startingPoint, direction);
        Vector a = unimodalInterval.get(0);
        Vector b = unimodalInterval.get(1);

        return ofDirection(function, a, b, e);
    }
}
