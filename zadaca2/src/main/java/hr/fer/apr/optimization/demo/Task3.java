package hr.fer.apr.optimization.demo;

import hr.fer.apr.lu.matrix.Vector;
import hr.fer.apr.optimization.*;

public class Task3 {
    public static void main(String[] args) {
        EvaluationCounter function1 = new EvaluationCounter(FunctionGenerator.JakobovicFunction());
        EvaluationCounter function2 = new EvaluationCounter(FunctionGenerator.JakobovicFunction());

        Vector e = new Vector(3, new double[]{1e-6, 1e-6});
        Vector dx = new Vector(3, new double[]{0.5, 0.5});
        Vector startingPoint = new Vector(2, new double[]{5, 5});

        System.out.println("Nelder-Mead:");
        System.out.print(NelderMeadSimplex.of(function1, startingPoint, 1, 1, 0.5, 2, 1e-6, 0.5));
        System.out.printf("Broj evaluacija funkcije: %d\n\n", function1.getNumberOfEvaluations());
        System.out.println("----------------------------------------------------------");
        System.out.println("Hooke-Jeeves:");
        System.out.print(HookeJeevesSearch.of(function2, startingPoint, dx, e));
        System.out.printf("Broj evaluacija funkcije: %d\n\n", function2.getNumberOfEvaluations());
    }
}
