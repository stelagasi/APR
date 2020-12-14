package hr.fer.apr.geneticalgorithm.evaluator;

import hr.fer.apr.geneticalgorithm.function.IFunction;
import hr.fer.apr.geneticalgorithm.individual.IIndividual;

import java.util.List;

public interface IPopulationEvaluator<T extends IIndividual<?>> {
    double evaluatePenalty(List<T> population, IFunction<Double> goalFunction);

    double evaluateFitness(List<T> population, IFunction<Double> goalFunction);

    T getBestIndividual();

    T getWorstIndividual();

    double evaluatePenaltyOfIndividual(T individual, IFunction<Double> goalFunction);
}
