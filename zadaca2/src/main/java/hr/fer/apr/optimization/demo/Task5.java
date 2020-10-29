package hr.fer.apr.optimization.demo;

import hr.fer.apr.lu.matrix.Vector;
import hr.fer.apr.optimization.EvaluationCounter;
import hr.fer.apr.optimization.FunctionGenerator;
import hr.fer.apr.optimization.NelderMeadSimplex;

public class Task5 {
    public static void main(String[] args) {
        int n = 10;
        double min = -50, max = 50;

        for (int i = 0; i < n; i++) {
            Vector startingPoint = new Vector(2, new double[2]);
            startingPoint.setElementAt(0, (Math.random() * (max - min)) + min);
            EvaluationCounter function = new EvaluationCounter(FunctionGenerator.ShafferFunction());
            System.out.print(NelderMeadSimplex.of(function, startingPoint, 1, 1, 0.5, 2, 1e-6, 0.5));
            System.out.printf("Broj evaluacija funkcije: %d\n\n", function.getNumberOfEvaluations());
            System.out.println("----------------------------------------------------------");
        }
    }
}