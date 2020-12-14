package hr.fer.apr.geneticalgorithm.mutator;

import hr.fer.apr.geneticalgorithm.individual.BinaryIndividual;

import java.util.List;
import java.util.Random;

public class BinaryMutator extends Mutator<BinaryIndividual> {

    public BinaryMutator(double mutationProbability, int mutationRange) {
        super(mutationProbability, mutationRange);
    }

    @Override
    public void mutate(List<BinaryIndividual> children) {
        Random random = new Random();

        for (BinaryIndividual child : children) {
            List<List<Boolean>> childChromosomes = child.getChromosomes();
            for (List<Boolean> childChromosome : childChromosomes) {
                if (random.nextDouble() < mutationProbability) {
                    int index = random.nextInt(childChromosome.size());
                    childChromosome.set(index, !childChromosome.get(index));
                }
            }
        }
    }
}
