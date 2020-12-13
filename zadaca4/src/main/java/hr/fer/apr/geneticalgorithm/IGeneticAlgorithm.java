package hr.fer.apr.geneticalgorithm;

import hr.fer.apr.geneticalgorithm.individual.IIndividual;

import java.util.List;

public interface IGeneticAlgorithm<T extends IIndividual<?>> {
    List<T> execute(int numberOfIterations, int numberOfEvaluations);
}
