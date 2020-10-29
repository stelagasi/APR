package hr.fer.apr.optimization.demo;

import hr.fer.apr.lu.matrix.Vector;
import hr.fer.apr.optimization.*;

public class Task1 {
    public static void main(String[] args) {
        int n = 10;
        Vector startingPoint = new Vector(3, new double[]{10, 0, 0});

        for(int i = 0; i < n; i++){
            EvaluationCounter function1 = new EvaluationCounter(FunctionGenerator.f3(3));
            EvaluationCounter function2 = new EvaluationCounter(FunctionGenerator.f3(3));
            EvaluationCounter function3 = new EvaluationCounter(FunctionGenerator.f3(3));

            Vector e = new Vector(3, new double[]{1e-6, 1e-6, 1e-6});
            Vector dx = new Vector(3, new double[]{0.5, 0.5, 0.5});
            startingPoint = Vector.addition(startingPoint, new Vector(3, new double[]{10, 10, 10}));

            System.out.println("Pretraživanje po koordinatnim osima: ");
            System.out.print(CoordinateAxisSearch.of(function1, startingPoint, e, 1));
            System.out.printf("Broj evaluacija funkcije: %d\n\n", function1.getNumberOfEvaluations());
            System.out.println("Simpleks postupak po Nelderu i Meadu: ");
            System.out.print(NelderMeadSimplex.of(function2, startingPoint, 1, 1, 0.5, 2, 1e-6, 0.5));
            System.out.printf("Broj evaluacija funkcije: %d\n\n", function2.getNumberOfEvaluations());
            System.out.println("Postupak Hooke-Jeeves: ");
            System.out.print(HookeJeevesSearch.of(function3, startingPoint, dx, e));
            System.out.printf("Broj evaluacija funkcije: %d\n\n", function3.getNumberOfEvaluations());
            System.out.println("----------------------------------------------------------");
        }
    }
}
