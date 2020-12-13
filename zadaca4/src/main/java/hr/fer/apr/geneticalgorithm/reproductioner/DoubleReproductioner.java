package hr.fer.apr.geneticalgorithm.reproductioner;

import hr.fer.apr.geneticalgorithm.individual.DoubleIndividual;

import java.util.ArrayList;
import java.util.List;

public class DoubleReproductioner extends Reproductioner<DoubleIndividual> {
    @Override
    public DoubleIndividual reproduce(List<DoubleIndividual> parents) {
        List<Double> firstParentChromosomes = parents.get(0).getChromosomes();
        List<Double> secondParentChromosomes = parents.get(1).getChromosomes();

        int childSize = firstParentChromosomes.size();

        List<Double> childChromosomes = new ArrayList<>();

        for (int i = 0; i < childSize; i++) {
            childChromosomes.add((firstParentChromosomes.get(i) + secondParentChromosomes.get(i)) / 2);
        }
        return new DoubleIndividual(childChromosomes);
    }
}
