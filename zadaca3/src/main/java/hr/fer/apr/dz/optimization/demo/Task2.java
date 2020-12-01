package hr.fer.apr.dz.optimization.demo;

import hr.fer.apr.dz.optimization.GradientDescent;
import hr.fer.apr.dz.optimization.NewtonRaphsonMethod;
import hr.fer.apr.dz.optimization.functions.DerivationGenerator;
import hr.fer.apr.dz.optimization.functions.FunctionGenerator;
import hr.fer.apr.dz.optimization.functions.SecondDerivationGenerator;
import hr.fer.apr.lu.matrix.SquareMatrix;
import hr.fer.apr.lu.matrix.Vector;
import hr.fer.apr.optimization.EvaluationCounter;

public class Task2 {
    public static void main(String[] args) {
        Vector startingPoint1 = new Vector(2, new double[]{-1.9, 2});
        Vector startingPoint2 = new Vector(2, new double[]{0.1, 0.3});

        EvaluationCounter<Vector, Double> f1Grad = new EvaluationCounter<>(FunctionGenerator.f1());
        EvaluationCounter<Vector, Vector> gradientF1Grad = new EvaluationCounter<>(DerivationGenerator.f1());
        EvaluationCounter<Vector, Double> f1GoldenNewton = new EvaluationCounter<>(FunctionGenerator.f1());
        EvaluationCounter<Vector, Vector> gradientF1Newton = new EvaluationCounter<>(DerivationGenerator.f1());
        EvaluationCounter<Vector, SquareMatrix> f1Hesse = new EvaluationCounter<>(SecondDerivationGenerator.f1());

        EvaluationCounter<Vector, Double> f2Grad = new EvaluationCounter<>(FunctionGenerator.f2());
        EvaluationCounter<Vector, Vector> gradientF2Grad = new EvaluationCounter<>(DerivationGenerator.f2());
        EvaluationCounter<Vector, Double> f2GoldenNewton = new EvaluationCounter<>(FunctionGenerator.f2());
        EvaluationCounter<Vector, Vector> gradientF2Newton = new EvaluationCounter<>(DerivationGenerator.f2());
        EvaluationCounter<Vector, SquareMatrix> f2Hesse = new EvaluationCounter<>(SecondDerivationGenerator.f2());

        System.out.println("Za f1:");
        System.out.println("Gradijentni spust: ");
        System.out.println(GradientDescent.withGoldenSection(f1Grad, gradientF1Grad, startingPoint1, 1e-6));
        System.out.println("Br evaluacija fje: " + f1Grad.getNumberOfEvaluations());
        System.out.println("Br evaluacija gradijenta: " + gradientF1Grad.getNumberOfEvaluations());
        System.out.println("\nNewton-Raphson: ");
        System.out.println(NewtonRaphsonMethod.withGoldenSection(f1GoldenNewton, gradientF1Newton, f1Hesse, startingPoint1, 1e-6));
        System.out.println("Br evaluacija fje: " + f1GoldenNewton.getNumberOfEvaluations());
        System.out.println("Br evaluacija gradijenta: " + gradientF1Newton.getNumberOfEvaluations());
        System.out.println("Br evaluacija Hesseove matrice: " + f1Hesse.getNumberOfEvaluations());
        System.out.println("-------------------------------------------------");
        System.out.println("Za f2:");
        System.out.println("Gradijentni spust: ");
        System.out.println(GradientDescent.withGoldenSection(f2Grad, gradientF2Grad, startingPoint2, 1e-6));
        System.out.println("Br evaluacija fje: " + f2Grad.getNumberOfEvaluations());
        System.out.println("Br evaluacija gradijenta: " + gradientF2Grad.getNumberOfEvaluations());
        System.out.println("\nNewton-Raphson: ");
        System.out.println(NewtonRaphsonMethod.withGoldenSection(f2GoldenNewton, gradientF2Newton, f2Hesse, startingPoint2, 1e-6));
        System.out.println("Br evaluacija fje: " + f2GoldenNewton.getNumberOfEvaluations());
        System.out.println("Br evaluacija gradijenta: " + gradientF2Newton.getNumberOfEvaluations());
        System.out.println("Br evaluacija Hesseove matrice: " + f2Hesse.getNumberOfEvaluations());
    }
}
