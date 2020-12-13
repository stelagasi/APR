package hr.fer.apr.geneticalgorithm.initializer;

import hr.fer.apr.geneticalgorithm.individual.IIndividual;

public abstract class PopulationInitializer<T extends IIndividual<?>> implements IPopulationInitializer<T> {
    private final int populationSize;
    private final int numberOfChromosomes;

    public PopulationInitializer(int populationSize, int numberOfChromosomes) {
        this.populationSize = populationSize;
        this.numberOfChromosomes = numberOfChromosomes;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public int getNumberOfChromosomes() {
        return numberOfChromosomes;
    }
}
