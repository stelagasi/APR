package hr.fer.apr.geneticalgorithm.mutator;

import hr.fer.apr.geneticalgorithm.individual.IIndividual;

import java.util.List;

public interface IMutator<T extends IIndividual<?>> {
    void mutate(List<T> children);
}
