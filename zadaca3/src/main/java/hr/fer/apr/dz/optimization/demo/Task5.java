package hr.fer.apr.dz.optimization.demo;

import hr.fer.apr.dz.optimization.TransformationMethod;
import hr.fer.apr.dz.optimization.functions.FunctionGenerator;
import hr.fer.apr.lu.matrix.Vector;
import hr.fer.apr.optimization.EvaluationCounter;

import java.util.List;

import static hr.fer.apr.dz.optimization.restriction.Restrictions.*;

public class Task5 {
    public static void main(String[] args) {
        EvaluationCounter<Vector, Double> f4 = new EvaluationCounter<>(FunctionGenerator.f4());
        Vector startingPoint = new Vector(2, new double[]{5, 5});
        Vector dx = new Vector(2, new double[]{0.5, 0.5});
        System.out.println(TransformationMethod.of(f4, List.of(), List.of(threeMinusFirstMinusSecond, threePlusOnePointFiveFirstMinusSecond, secondMinusOne), startingPoint, dx, new Vector(2, new double[]{1e-6, 1e-6}), 1));
        System.out.println("Br evaluacija fje: " + f4.getNumberOfEvaluations());
    }
}
