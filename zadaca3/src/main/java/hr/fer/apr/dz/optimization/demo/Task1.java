package hr.fer.apr.dz.optimization.demo;

import hr.fer.apr.dz.optimization.GradientDescent;
import hr.fer.apr.dz.optimization.functions.DerivationGenerator;
import hr.fer.apr.dz.optimization.functions.FunctionGenerator;
import hr.fer.apr.lu.matrix.Vector;
import hr.fer.apr.optimization.EvaluationCounter;

public class Task1 {
    public static void main(String[] args) {
        EvaluationCounter<Vector, Double> f3 = new EvaluationCounter<>(FunctionGenerator.f3());
        EvaluationCounter<Vector, Vector> gradientF3 = new EvaluationCounter<>(DerivationGenerator.f3());
        EvaluationCounter<Vector, Double> f3Golden = new EvaluationCounter<>(FunctionGenerator.f3());
        EvaluationCounter<Vector, Vector> gradientF3Golden = new EvaluationCounter<>(DerivationGenerator.f3());

        System.out.println("Gradijentni spust: ");
        Vector startingPoint = new Vector(2, new double[]{0, 0});
        System.out.println(GradientDescent.of(f3, gradientF3, startingPoint, 1e-6));
        System.out.println("Br evaluacija funkcije: " + f3.getNumberOfEvaluations());
        System.out.println("Br evaluacija gradijenta: " + gradientF3.getNumberOfEvaluations());
        System.out.println("\n\nGradijentni spust sa zlatnim rezom: ");
        System.out.println(GradientDescent.withGoldenSection(f3Golden, gradientF3Golden, startingPoint, 1e-6));
        System.out.println("Br evaluacija funkcije: " + f3Golden.getNumberOfEvaluations());
        System.out.println("Br evaluacija gradijenta: " + gradientF3Golden.getNumberOfEvaluations());
    }
}
