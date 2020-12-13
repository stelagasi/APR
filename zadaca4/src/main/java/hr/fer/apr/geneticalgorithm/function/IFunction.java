package hr.fer.apr.geneticalgorithm.function;

import java.util.List;

public interface IFunction<T> {

    double valueAt(List<T> chromosomes);

    int getNumberOfEvaluations();
}
