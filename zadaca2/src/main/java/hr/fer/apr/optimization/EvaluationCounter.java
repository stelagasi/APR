package hr.fer.apr.optimization;

import hr.fer.apr.lu.matrix.Vector;

import java.util.function.Function;

public class EvaluationCounter implements Function<Vector, Double> {

    private final Function<Vector, Double> function;
    private int numberOfEvaluations = 0;

    public EvaluationCounter(Function<Vector, Double> function) {
        this.function = function;
    }

    public int getNumberOfEvaluations() {
        return numberOfEvaluations;
    }

    @Override
    public Double apply(Vector point) {
        numberOfEvaluations++;
        return function.apply(point);
    }
}