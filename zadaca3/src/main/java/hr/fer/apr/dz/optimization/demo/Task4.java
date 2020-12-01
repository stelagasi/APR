package hr.fer.apr.dz.optimization.demo;

import hr.fer.apr.dz.optimization.TransformationMethod;
import hr.fer.apr.dz.optimization.functions.FunctionGenerator;
import hr.fer.apr.lu.matrix.Vector;
import hr.fer.apr.optimization.EvaluationCounter;

import java.util.List;

import static hr.fer.apr.dz.optimization.restriction.Restrictions.differenceBiggerThanZero;
import static hr.fer.apr.dz.optimization.restriction.Restrictions.twoMinusFirst;

public class Task4 {
    public static void main(String[] args) {
        EvaluationCounter<Vector, Double> f1 = new EvaluationCounter<>(FunctionGenerator.f1());
        EvaluationCounter<Vector, Double> f2 = new EvaluationCounter<>(FunctionGenerator.f2());

        Vector startingPoint1 = new Vector(2, new double[]{-1.9, 2});
        Vector startingPoint2 = new Vector(2, new double[]{0.1, 0.3});

        Vector dx = new Vector(2, new double[]{0.5, 0.5});

        System.out.println("f1:");
        System.out.println(TransformationMethod.of(f1, List.of(), List.of(differenceBiggerThanZero, twoMinusFirst), startingPoint1, dx, new Vector(2, new double[]{1e-6, 1e-6}), 1));
        System.out.println("Br evaluacija fje: " + f1.getNumberOfEvaluations());
        System.out.println("\nf2:");
        System.out.println(TransformationMethod.of(f2, List.of(), List.of(differenceBiggerThanZero, twoMinusFirst), startingPoint2, dx, new Vector(2, new double[]{1e-6, 1e-6}), 1));
        System.out.println("Br evaluacija fje: " + f2.getNumberOfEvaluations());
    }
}
