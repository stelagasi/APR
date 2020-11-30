package hr.fer.apr.optimization;

import hr.fer.apr.lu.matrix.Vector;

import java.util.ArrayList;
import java.util.function.Function;

public class UnimodalIntervalSearch {

    private UnimodalIntervalSearch(){}

    public static ArrayList<Vector> of(Function<Vector, Double> goalFunction, double h, Vector point, int dimension){
        double m = point.getElementAt(dimension);
        double l = point.getElementAt(dimension) - h;
        double r = point.getElementAt(dimension) + h;
        int step = 1;

        Vector a = new Vector(point);
        a.setElementAt(dimension, l);
        Vector b = new Vector(point);
        b.setElementAt(dimension, r);

        double fm = goalFunction.apply(point);
        double fa = goalFunction.apply(a);
        double fb = goalFunction.apply(b);

        ArrayList<Vector> result = new ArrayList<>();

        if(fm < fb && fm < fa) {
            result.add(a);
            result.add(b);
            return result;
        } else if(fm > fb) {
            do {
                a.setElementAt(dimension, m);
                m = r;
                fm = fb;
                step *= 2;
                r = point.getElementAt(dimension) + h * step;
                b.setElementAt(dimension, r);
                fb = goalFunction.apply(b);
            } while(fm > fb);
            result.add(a);
            result.add(b);
            return result;
        } else {
            do {
                b.setElementAt(dimension, m);
                m = l;
                fm = fa;
                step *= 2;
                l = point.getElementAt(dimension) - h * step;
                a.setElementAt(dimension, l);
                fa = goalFunction.apply(a);
            } while(fm > fa);
            result.add(a);
            result.add(b);
            return result;
        }
    }

    public static ArrayList<Vector> ofDirection(Function<Vector, Double> goalFunction, double h, Vector point, Vector direction){
        Vector normalizedDirection = direction.multiplicationWithScalar(1/direction.getEuclideanNorm());
        Vector a = Vector.subtraction(point, normalizedDirection.multiplicationWithScalar(h));
        Vector b = Vector.addition(point, normalizedDirection.multiplicationWithScalar(h));
        int step = 1;

        double fPoint = goalFunction.apply(point);
        double fa = goalFunction.apply(a);
        double fb = goalFunction.apply(b);

        ArrayList<Vector> result = new ArrayList<>();

        if(fPoint < fb && fPoint < fa) {
            result.add(a);
            result.add(b);
            return result;
        } else if(fPoint > fb) {
            do {
                a = point;
                point = b;
                fPoint = fb;
                step *= 2;
                b = Vector.addition(point, normalizedDirection.multiplicationWithScalar(h * step));
                fb = goalFunction.apply(b);
            } while(fPoint > fb);
            result.add(a);
            result.add(b);
            return result;
        } else {
            do {
                b = point;
                point = a;
                fPoint = fa;
                step *= 2;
                a = Vector.subtraction(point, normalizedDirection.multiplicationWithScalar(h * step));
                fa = goalFunction.apply(a);
            } while(fPoint > fa);
            result.add(a);
            result.add(b);
            return result;
        }
    }
}
