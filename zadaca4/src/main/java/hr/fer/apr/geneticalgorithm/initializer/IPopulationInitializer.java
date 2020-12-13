package hr.fer.apr.geneticalgorithm.initializer;

import hr.fer.apr.geneticalgorithm.individual.IIndividual;

import java.util.List;

public interface IPopulationInitializer<T extends IIndividual<?>> {
    List<T> initialize();

    int getPopulationSize();

    int getNumberOfChromosomes();
}
