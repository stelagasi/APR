package hr.fer.apr.geneticalgorithm.individual;

import java.util.List;

public interface IIndividual<T> {
    List<T> getChromosomes();

    double getPenalty();

    void setPenalty(double penalty);
}
