package hr.fer.apr.geneticalgorithm.mutator;

import hr.fer.apr.geneticalgorithm.individual.IIndividual;

public abstract class Mutator<T extends IIndividual<?>> implements IMutator<T> {
    protected final double mutationProbability;
    protected final int mutationRange;

    public Mutator(double mutationProbability, int mutationRange) {
        this.mutationProbability = mutationProbability;
        this.mutationRange = mutationRange;
    }
}
