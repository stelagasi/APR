package hr.fer.apr.geneticalgorithm.mutator;

import hr.fer.apr.geneticalgorithm.individual.IIndividual;

public abstract class Mutator<T extends IIndividual<?>> implements IMutator<T> {
    private final double mutationProbability; // 0.01;
    private final int mutationRange; // 1;

    public Mutator(double mutationProbability, int mutationRange) {
        this.mutationProbability = mutationProbability;
        this.mutationRange = mutationRange;
    }

    public double getMutationProbability() {
        return mutationProbability;
    }

    public int getMutationRange() {
        return mutationRange;
    }
}
