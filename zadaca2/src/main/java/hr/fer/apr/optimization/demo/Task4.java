package hr.fer.apr.optimization.demo;

import hr.fer.apr.lu.matrix.Vector;
import hr.fer.apr.optimization.EvaluationCounter;
import hr.fer.apr.optimization.FunctionGenerator;
import hr.fer.apr.optimization.NelderMeadSimplex;

public class Task4 {
    public static void main(String[] args) {
        Vector startingPoint = new Vector(2, new double[]{0.5, 0.5});
        for (int i = 1; i <= 20; i++) {
            EvaluationCounter function1 = new EvaluationCounter(FunctionGenerator.f1());
            System.out.print(NelderMeadSimplex.of(function1, startingPoint, i, 1, 0.5, 2, 1e-6, 0.5));
            System.out.printf("Broj evaluacija funkcije: %d\n\n", function1.getNumberOfEvaluations());
            System.out.println("----------------------------------------------------------");
        }

        startingPoint = new Vector(2, new double[]{20, 20});
        for (int i = 1; i <= 20; i++) {
            EvaluationCounter function1 = new EvaluationCounter(FunctionGenerator.f1());
            System.out.print(NelderMeadSimplex.of(function1, startingPoint, i, 1, 0.5, 2, 1e-6, 0.5));
            System.out.printf("Broj evaluacija funkcije: %d\n\n", function1.getNumberOfEvaluations());
            System.out.println("----------------------------------------------------------");
        }
    }
}
