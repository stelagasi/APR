package hr.fer.apr.geneticalgorithm.reproductioner;

import hr.fer.apr.geneticalgorithm.individual.DoubleIndividual;

import java.util.ArrayList;
import java.util.List;

public class AverageDoubleReproduce implements IReproductioner<DoubleIndividual> {
    @Override
    public DoubleIndividual reproduce(DoubleIndividual firstParent, DoubleIndividual secondParent) {
        List<Double> firstParentChromosomes = firstParent.getChromosomes();
        List<Double> secondParentChromosomes = secondParent.getChromosomes();

        int childSize = firstParentChromosomes.size();

        List<Double> childChromosomes = new ArrayList<>();

        for (int i = 0; i < childSize; i++) {
            childChromosomes.add((firstParentChromosomes.get(i) + secondParentChromosomes.get(i)) / 2);
        }
        return new DoubleIndividual(childChromosomes);
    }

    @Override
    public List<DoubleIndividual> reproduceMultiple(List<DoubleIndividual> parents) {
        List<DoubleIndividual> children = new ArrayList<>();
        for (int i = 0; i < parents.size(); i += 2) {
            children.add(reproduce(parents.get(i), parents.get(i + 1)));
        }
        return children;
    }
}
