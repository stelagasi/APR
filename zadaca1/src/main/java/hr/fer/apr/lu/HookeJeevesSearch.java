package hr.fer.apr.lu;

import hr.fer.apr.lu.matrix.Vector;

import java.util.function.Function;

public class HookeJeevesSearch {

    private HookeJeevesSearch(){}

    public static Vector of(Function<Vector, Double> function, Vector startingPoint, Vector Dx, Vector epsilon){
        Vector basePoint = new Vector(startingPoint);
        Vector startingSearchPoint = new Vector(startingPoint);

        do {
            Vector resultPoint = search(function, startingSearchPoint, Dx);
            if(function.apply(resultPoint) < function.apply(basePoint)){
                startingSearchPoint = Vector.subtraction(resultPoint.multiplicationWithScalar(2), basePoint);
                basePoint = new Vector(resultPoint);
            } else {
                Dx = Dx.multiplicationWithScalar((double) 1/2);
                startingSearchPoint = new Vector(basePoint);
            }
        } while(vectorComparison(Dx, epsilon.multiplicationWithScalar(0.5))>= 0);

        return basePoint;
    }

    private static Vector search(Function<Vector, Double> function, Vector startingSearchPoint, Vector Dx){
        Vector x = new Vector(startingSearchPoint);
        for (int i = 0; i < startingSearchPoint.getNumberOfRows(); i++) {
            double P = function.apply(x);
            x.setElementAt(i, x.getElementAt(i) + Dx.getElementAt(i));
            double N = function.apply(x);
            if(N > P){
                x.setElementAt(i, x.getElementAt(i) - 2 * Dx.getElementAt(i));
                N = function.apply(x);
                if (N > P){
                    x.setElementAt(i, x.getElementAt(i) + Dx.getElementAt(i));
                }
            }
        }
        return x;
    }

    private static int vectorComparison(Vector v1, Vector v2){
        if(v1.getNumberOfRows() != v2.getNumberOfRows())
            throw new IllegalArgumentException("Vectors are not the same size");
        for (int i = 0; i < v1.getNumberOfRows(); i++) {
            if(v1.getElementAt(i) < v2.getElementAt(i)) return -1;
            if(v1.getElementAt(i) > v2.getElementAt(i)) return 1;
        }
        return 0;
    }
}

