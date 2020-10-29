package hr.fer.apr.optimization.demo;

import hr.fer.apr.lu.matrix.Vector;
import hr.fer.apr.optimization.*;

public class Task2 {
    public static void main(String[] args) {
        Vector startingPoint1 = new Vector(2, new double[]{-1.9, 2});
        Vector startingPoint2 = new Vector(2, new double[]{0.1, 0.3});
        Vector startingPoint3 = new Vector(5, new double[5]);
        Vector startingPoint4 = new Vector(2, new double[]{5.1, 1.1});

        Vector e = new Vector(3, new double[]{1e-6, 1e-6});
        Vector dx = new Vector(3, new double[]{0.5, 0.5});

        for(int i = 0; i < 3; i++) {
            EvaluationCounter function1 = new EvaluationCounter(FunctionGenerator.f1());
            EvaluationCounter function2 = new EvaluationCounter(FunctionGenerator.f2());
            EvaluationCounter function3 = new EvaluationCounter(FunctionGenerator.f3(5));
            EvaluationCounter function4 = new EvaluationCounter(FunctionGenerator.JakobovicFunction());

            if(i == 0){
                System.out.println("Pretraživanje po koordinatnim osima: ");
                System.out.println("f1: ");
                System.out.print(CoordinateAxisSearch.of(function1, startingPoint1, e, 1));
                System.out.printf("Broj evaluacija funkcije: %d\n\n", function1.getNumberOfEvaluations());
                System.out.println("f2: ");
                System.out.print(CoordinateAxisSearch.of(function2, startingPoint2, e, 1));
                System.out.printf("Broj evaluacija funkcije: %d\n\n", function2.getNumberOfEvaluations());
                System.out.println("f3: ");
                System.out.print(CoordinateAxisSearch.of(function3, startingPoint3, new Vector(5, new double[]{1e-6, 1e-6, 1e-6, 1e-6, 1e-6}), 1));
                System.out.printf("Broj evaluacija funkcije: %d\n\n", function3.getNumberOfEvaluations());
                System.out.println("f4: ");
                System.out.print(CoordinateAxisSearch.of(function4, startingPoint4, e, 1));
                System.out.printf("Broj evaluacija funkcije: %d\n\n", function4.getNumberOfEvaluations());
            } else if (i == 1) {
                System.out.println("----------------------------------------------------------");
                System.out.println("Simpleks postupak po Nelderu i Meadu: ");
                System.out.println("f1: ");
                System.out.print(NelderMeadSimplex.of(function1, startingPoint1, 1, 1, 0.5, 2, 1e-6, 0.5));
                System.out.printf("Broj evaluacija funkcije: %d\n\n", function1.getNumberOfEvaluations());
                System.out.println("f2: ");
                System.out.print(NelderMeadSimplex.of(function2, startingPoint2, 1, 1, 0.5, 2, 1e-6, 0.5));
                System.out.printf("Broj evaluacija funkcije: %d\n\n", function2.getNumberOfEvaluations());
                System.out.println("f3: ");
                System.out.print(NelderMeadSimplex.of(function3, startingPoint3, 1, 1, 0.5, 2, 1e-6, 0.5));
                System.out.printf("Broj evaluacija funkcije: %d\n\n", function3.getNumberOfEvaluations());
                System.out.println("f4: ");
                System.out.print(NelderMeadSimplex.of(function4, startingPoint4, 1, 1, 0.5, 2, 1e-6, 0.5));
                System.out.printf("Broj evaluacija funkcije: %d\n\n", function4.getNumberOfEvaluations());
            } else {
                System.out.println("----------------------------------------------------------");
                System.out.println("Postupak Hooke-Jeeves: ");
                System.out.println("f1: ");
                System.out.print(HookeJeevesSearch.of(function1, startingPoint1, dx, e));
                System.out.printf("Broj evaluacija funkcije: %d\n\n", function1.getNumberOfEvaluations());
                System.out.println("f2: ");
                System.out.print(HookeJeevesSearch.of(function2, startingPoint2, dx, e));
                System.out.printf("Broj evaluacija funkcije: %d\n\n", function2.getNumberOfEvaluations());
                System.out.println("f3: ");
                System.out.print(HookeJeevesSearch.of(function3, startingPoint3, new Vector(5, new double[]{0.5, 0.5, 0.5, 0.5, 0.5}), new Vector(5, new double[]{1e-6, 1e-6, 1e-6, 1e-6, 1e-6})));
                System.out.printf("Broj evaluacija funkcije: %d\n\n", function3.getNumberOfEvaluations());
                System.out.println("f4: ");
                System.out.print(HookeJeevesSearch.of(function4, startingPoint4, dx, e));
                System.out.printf("Broj evaluacija funkcije: %d\n\n", function4.getNumberOfEvaluations());
                System.out.println("----------------------------------------------------------");
            }
        }
    }
}
