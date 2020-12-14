package hr.fer.apr.geneticalgorithm.mutator;

import hr.fer.apr.geneticalgorithm.individual.DoubleIndividual;

import java.util.List;
import java.util.Random;

public class GaussianDoubleMutator extends Mutator<DoubleIndividual> {

    public GaussianDoubleMutator(double mutationProbability, int mutationRange) {
        super(mutationProbability, mutationRange);
    }

    @Override
    public void mutate(List<DoubleIndividual> children) {
        Random random = new Random();

        for (DoubleIndividual child : children) {
            List<Double> childChromosomes = child.getChromosomes();
            for (int i = 0; i < childChromosomes.size(); i++) {
                if (random.nextDouble() < mutationProbability) {
                    childChromosomes.set(i, childChromosomes.get(i) + random.nextGaussian() * 0.5);
                }
            }
        }
    }
}
