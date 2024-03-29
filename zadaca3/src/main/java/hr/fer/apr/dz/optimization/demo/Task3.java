package hr.fer.apr.dz.optimization.demo;

import hr.fer.apr.dz.optimization.BoxMethod;
import hr.fer.apr.dz.optimization.functions.FunctionGenerator;
import hr.fer.apr.lu.matrix.Vector;
import hr.fer.apr.optimization.EvaluationCounter;

import java.util.List;

import static hr.fer.apr.dz.optimization.restriction.Restrictions.*;

public class Task3 {
    public static void main(String[] args) {
        EvaluationCounter<Vector, Double> f1 = new EvaluationCounter<>(FunctionGenerator.f1());
        EvaluationCounter<Vector, Double> f2 = new EvaluationCounter<>(FunctionGenerator.f2());

        Vector startingPoint1 = new Vector(2, new double[]{-1.9, 2});
        Vector startingPoint2 = new Vector(2, new double[]{0.1, 0.3});
        System.out.println("f1:");
        System.out.println(BoxMethod.of(f1, startingPoint1, 100.0, List.of(differenceBiggerThanZero, twoMinusFirst), 1.3, 1e-6));
        System.out.println("Br evaluacija fje: " + f1.getNumberOfEvaluations());
        System.out.println("\nf2:");
        System.out.println(BoxMethod.of(f2, startingPoint2, 100.0, List.of(differenceBiggerThanZero, twoMinusFirst), 1.3, 1e-6));
        System.out.println("Br evaluacija fje: " + f2.getNumberOfEvaluations());
    }
}
