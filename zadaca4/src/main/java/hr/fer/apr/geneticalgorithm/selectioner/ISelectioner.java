package hr.fer.apr.geneticalgorithm.selectioner;

import hr.fer.apr.geneticalgorithm.individual.IIndividual;

import java.util.List;

public interface ISelectioner<T extends IIndividual<?>> {
    T select(List<T> population);
}
