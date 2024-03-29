package hr.fer.apr.geneticalgorithm.reproductioner;

import hr.fer.apr.geneticalgorithm.individual.IIndividual;

import java.util.List;

public interface IReproductioner<T extends IIndividual<?>> {
    T reproduce(T firstParent, T secondParent);

    List<T> reproduceMultiple(List<T> parents);
}
