package hr.fer.apr.geneticalgorithm.mutator;

import hr.fer.apr.geneticalgorithm.individual.DoubleIndividual;

import java.util.List;
import java.util.Random;

public class DoubleMutator extends Mutator<DoubleIndividual> {

    public DoubleMutator(double mutationProbability, int mutationRange) {
        super(mutationProbability, mutationRange);
    }

    @Override
    public void mutate(List<DoubleIndividual> children) {
        Random random = new Random();

        for (DoubleIndividual child : children) {
            List<Double> childChromosomes = child.getChromosomes();
            for (int i = 0; i < childChromosomes.size(); i++) {
                if (random.nextDouble() < mutationProbability) {
                    double mutation = random.nextDouble() * (mutationRange + mutationRange) - mutationRange;
                    childChromosomes.set(i, childChromosomes.get(i) + mutation);
                }
            }
        }
    }
}
