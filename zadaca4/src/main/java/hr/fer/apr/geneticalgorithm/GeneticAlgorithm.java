package hr.fer.apr.geneticalgorithm;

import hr.fer.apr.geneticalgorithm.evaluator.IPopulationEvaluator;
import hr.fer.apr.geneticalgorithm.function.IFunction;
import hr.fer.apr.geneticalgorithm.individual.IIndividual;
import hr.fer.apr.geneticalgorithm.initializer.IPopulationInitializer;
import hr.fer.apr.geneticalgorithm.mutator.IMutator;
import hr.fer.apr.geneticalgorithm.reproductioner.IReproductioner;
import hr.fer.apr.geneticalgorithm.selectioner.ISelectioner;

import java.util.List;

public abstract class GeneticAlgorithm<T extends IIndividual<?>> implements IGeneticAlgorithm<T> {
    protected List<T> population;
    protected final IPopulationInitializer<T> populationInitializer;
    protected final IMutator<T> mutator;
    protected final IReproductioner<T> reproductioner;
    protected final ISelectioner<T> selectioner;
    protected final IFunction<Double> goalFunction;
    protected final IPopulationEvaluator<T> populationEvaluator;

    public GeneticAlgorithm(IPopulationInitializer<T> populationInitializer, IMutator<T> mutator, IReproductioner<T> reproductioner, ISelectioner<T> selectioner, IFunction<Double> goalFunction, IPopulationEvaluator<T> populationEvaluator) {
        this.populationInitializer = populationInitializer;
        this.mutator = mutator;
        this.reproductioner = reproductioner;
        this.selectioner = selectioner;
        this.goalFunction = goalFunction;
        this.populationEvaluator = populationEvaluator;
    }

    public void setPopulation(List<T> population) {
        this.population = population;
    }
}
