package hr.fer.apr.lu;

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

    //todo koliki h treba biti??
    public static Vector of(Function<Vector, Double> function, int dimension, Vector startingPoint, Vector e){
        ArrayList<Vector> unimodalInterval = UnimodalIntervalSearch.of(function, 10e-6, startingPoint, dimension);
        Vector a = unimodalInterval.get(0);
        Vector b = unimodalInterval.get(1);

        return of(function, dimension, a, b, e);
    }
}
