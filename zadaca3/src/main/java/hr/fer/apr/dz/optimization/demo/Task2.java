package hr.fer.apr.dz.optimization.demo;

import hr.fer.apr.dz.optimization.GradientDescent;
import hr.fer.apr.dz.optimization.NewtonRaphsonMethod;
import hr.fer.apr.dz.optimization.functions.DerivationGenerator;
import hr.fer.apr.dz.optimization.functions.FunctionGenerator;
import hr.fer.apr.dz.optimization.functions.SecondDerivationGenerator;
import hr.fer.apr.lu.matrix.Vector;

public class Task2 {
    public static void main(String[] args) {
        Vector startingPoint1 = new Vector(2, new double[]{-1.9, 2});
        Vector startingPoint2 = new Vector(2, new double[]{0.1, 0.3});
        System.out.println("Za f1:");
        System.out.println("Gradijentni spust: ");
        System.out.println(GradientDescent.withGoldenSection(FunctionGenerator.f1(), DerivationGenerator.f1(), startingPoint1, 1e-6));
        System.out.println("Newton-Raphson: ");
        System.out.println(NewtonRaphsonMethod.withGoldenSection(FunctionGenerator.f1(), DerivationGenerator.f1(), SecondDerivationGenerator.f1(), startingPoint1, 1e-6));
        System.out.println("-------------------------------------------------");
        System.out.println("Za f2:");
        System.out.println("Gradijentni spust: ");
        System.out.println(GradientDescent.withGoldenSection(FunctionGenerator.f2(), DerivationGenerator.f2(), startingPoint2, 1e-6));
        System.out.println("Newton-Raphson: ");
        System.out.println(NewtonRaphsonMethod.withGoldenSection(FunctionGenerator.f2(), DerivationGenerator.f2(), SecondDerivationGenerator.f2(), startingPoint2, 1e-6));

    }
}
