package hr.fer.apr.geneticalgorithm;

import hr.fer.apr.geneticalgorithm.evaluator.IPopulationEvaluator;
import hr.fer.apr.geneticalgorithm.function.IFunction;
import hr.fer.apr.geneticalgorithm.individual.Individual;
import hr.fer.apr.geneticalgorithm.initializer.IPopulationInitializer;
import hr.fer.apr.geneticalgorithm.mutator.IMutator;
import hr.fer.apr.geneticalgorithm.reproductioner.IReproductioner;
import hr.fer.apr.geneticalgorithm.selectioner.ISelectioner;

import java.util.ArrayList;
import java.util.List;

public class GenerationalGeneticAlgorithm<T extends Individual<K>, K> extends GeneticAlgorithm<T> {
    private final boolean elitism;

    public GenerationalGeneticAlgorithm(IPopulationInitializer<T> populationInitializer, IMutator<T> mutator, IReproductioner<T> reproductioner, ISelectioner<T> selectioner, IFunction<Double> goalFunction, IPopulationEvaluator<T> populationEvaluator, boolean elitism) {
        super(populationInitializer, mutator, reproductioner, selectioner, goalFunction, populationEvaluator);
        this.elitism = elitism;
    }

    //todo iskoristi numberOfEvaluations za uvjet isto
    @Override
    public T execute(int numberOfIterations, int numberOfEvaluations) {
        this.setPopulation(this.populationInitializer.initialize());
        IPopulationEvaluator<T> populationEvaluator = this.populationEvaluator;

        for (int i = 0; i < numberOfIterations; i++) {
            if (goalFunction.getNumberOfEvaluations() >= numberOfEvaluations) break;
            int individualsNeeded = this.populationInitializer.getPopulationSize();
            List<T> nextPopulation = new ArrayList<>(individualsNeeded);
            populationEvaluator.evaluatePenalty(this.population, this.goalFunction);
            System.out.println(i + " " + populationEvaluator.getBestIndividual());
            if (elitism) {
                nextPopulation.add(populationEvaluator.getBestIndividual());
                individualsNeeded--;
            }
            List<T> parents = selection(2 * individualsNeeded);
            List<T> children = reproductioner.reproduceMultiple(parents);
            mutator.mutate(children);
            nextPopulation.addAll(children);
            this.setPopulation(nextPopulation);
        }

        populationEvaluator.evaluatePenalty(this.population, goalFunction);
        return populationEvaluator.getBestIndividual();
    }

    private List<T> selection(int parentsNeeded) {
        List<T> parents = new ArrayList<>(parentsNeeded);
        List<T> copy = new ArrayList<>(population.size());
//        for (int i = 0; i < population.size(); i++) {
//            copy.add(new T(population.get(i).getChromosomes()));
//        }
        populationEvaluator.evaluateFitness(copy, goalFunction);

        for (int i = 0; i < parentsNeeded; i++) {
            parents.add(selectioner.select(copy));
        }
        return parents;
    }
}
