package hr.fer.apr.optimization;

import java.util.function.Function;

public class EvaluationCounter<T, K> implements Function<T, K> {

    private final Function<T, K> function;
    private int numberOfEvaluations = 0;

    public EvaluationCounter(Function<T, K> function) {
        this.function = function;
    }

    public int getNumberOfEvaluations() {
        return numberOfEvaluations;
    }

    @Override
    public K apply(T point) {
        numberOfEvaluations++;
        return function.apply(point);
    }
}