package hr.fer.apr.geneticalgorithm;

import hr.fer.apr.geneticalgorithm.individual.IIndividual;

public interface IGeneticAlgorithm<T extends IIndividual<?>> {
    T execute(int numberOfIterations, int numberOfEvaluations);
}
