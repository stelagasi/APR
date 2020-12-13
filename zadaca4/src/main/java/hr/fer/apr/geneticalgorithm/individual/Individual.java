package hr.fer.apr.geneticalgorithm.individual;

import java.util.List;

public interface Individual<T> {
    List<T> getChromosomes();

    double getPenalty();

    void setPenalty(double penalty);
}
