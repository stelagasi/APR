package hr.fer.apr.dz.optimization.demo;

import hr.fer.apr.dz.optimization.GradientDescent;
import hr.fer.apr.dz.optimization.functions.DerivationGenerator;
import hr.fer.apr.dz.optimization.functions.FunctionGenerator;
import hr.fer.apr.lu.matrix.Vector;

import java.util.function.Function;

public class Task1 {
    public static void main(String[] args) {
        Function<Vector, Double> f3 = FunctionGenerator.f3();
        Function<Vector, Vector> gradientF3 = DerivationGenerator.f3();
        Vector startingPoint = new Vector(2, new double[]{0, 0});
        System.out.println(GradientDescent.of(f3, gradientF3, startingPoint, 1e-6));
        System.out.println(GradientDescent.withGoldenSection(f3, gradientF3, startingPoint, 1e-6));
    }
}
