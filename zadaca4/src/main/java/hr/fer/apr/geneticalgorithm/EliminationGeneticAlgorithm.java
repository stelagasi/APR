package hr.fer.apr.geneticalgorithm;

import hr.fer.apr.geneticalgorithm.evaluator.IPopulationEvaluator;
import hr.fer.apr.geneticalgorithm.function.IFunction;
import hr.fer.apr.geneticalgorithm.individual.IIndividual;
import hr.fer.apr.geneticalgorithm.individual.Individual;
import hr.fer.apr.geneticalgorithm.initializer.IPopulationInitializer;
import hr.fer.apr.geneticalgorithm.mutator.IMutator;
import hr.fer.apr.geneticalgorithm.reproductioner.IReproductioner;
import hr.fer.apr.geneticalgorithm.selectioner.ISelectioner;

import java.util.ArrayList;
import java.util.List;

public class EliminationGeneticAlgorithm<T extends Individual<?>> extends GeneticAlgorithm<T> {
    private static final double MORTALITY = 0.5;
    private final int numberOfIndividualsToSelect;

    public EliminationGeneticAlgorithm(IPopulationInitializer<T> populationInitializer, IMutator<T> mutator, IReproductioner<T> reproductioner, ISelectioner<T> selectioner, IFunction<Double> goalFunction, IPopulationEvaluator<T> populationEvaluator, int numberOfIndividualsToSelect) {
        super(populationInitializer, mutator, reproductioner, selectioner, goalFunction, populationEvaluator);
        this.numberOfIndividualsToSelect = numberOfIndividualsToSelect;
    }

    @Override
    public List<T> execute(int numberOfIterations, int numberOfEvaluations) {
        this.setPopulation(this.populationInitializer.initialize());
        IPopulationEvaluator<T> populationEvaluator = this.populationEvaluator;
        int neededIndividuals = (int) (MORTALITY * population.size());

        for (int i = 0; i < numberOfIterations; i++) {
            if(goalFunction.getNumberOfEvaluations() == numberOfEvaluations) break;
            populationEvaluator.evaluatePenalty(this.population, this.goalFunction);
            System.out.println(i + " " + populationEvaluator.getBestIndividual());

            for (int j = 0; j < neededIndividuals; j++) {
                List<T> tournament = selectK();
                T worst = findWorst(tournament);
                tournament.remove(worst);
                T child = reproductioner.reproduce(tournament);
                mutator.mutate(List.of(child));
                if (populationEvaluator.evaluatePenaltyOfIndividual(child, goalFunction) < worst.getPenalty()) {
                    population.remove(worst);
                    population.add(child);
                }
            }
        }
        return this.population;
    }

    private List<T> selectK() {
        List<T> triple = new ArrayList<>(numberOfIndividualsToSelect);
        for (int i = 0; i < numberOfIndividualsToSelect; i++) {
            triple.add(selectioner.select(population));
        }
        return triple;
    }

    private T findWorst(List<T> tournament) {
        T worst = tournament.get(0);
        for (int i = 1; i < numberOfIndividualsToSelect; i++) {
            T individual = tournament.get(i);
            if (individual.getPenalty() > worst.getPenalty()) {
                worst = individual;
            }
        }
        return worst;
    }
}
