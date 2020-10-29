package hr.fer.apr.optimization;

import hr.fer.apr.lu.matrix.Vector;

import java.util.function.Function;

import static java.lang.Math.pow;

public class Main {
    public static void main(String[] args) {
        Function<Vector, Double> function = vector -> {
            Double value = 0.0;
            for (int i = 0; i < vector.getNumberOfRows(); i++) {
                value += pow(vector.getElementAt(i) - (i + 1), 2);
            }
            return value;
        };
      //  Function<Vector, Double> function1 = x -> pow(x.getElementAt(0) - 4,2);
      //  ArrayList<Vector> unimodal = UnimodalIntervalSearch.of(function1, 1, new Vector(1, new double[]{1}), 0);
       // System.out.println(unimodal);
        System.out.print(CoordinateAxisSearch.of(function, new Vector(3, new double[3]), new Vector(3, new double[]{1e-6, 1e-6, 1e-6}), 1));
       // Vector r = GoldenSectionSearch.of(function1, 0, new Vector(1, new double[]{2}), new Vector(1, new double[]{8}), new Vector(1, new double[]{1}));
//        Vector centroid = NelderMeadSimplex.of(function, new Vector(3, new double[3]), 1, 1, 0.5, 2, 1e-6, 0.5);
//        System.out.print(centroid);
       // Vector result = HookeJeevesSearch.of(function, new Vector(3, new double[3]), new Vector(3, new double[]{0.5, 0.5, 0.5}), new Vector(3, new double[]{1e-6, 1e-6, 1e-6}));
       // System.out.print(r);
    }
}
