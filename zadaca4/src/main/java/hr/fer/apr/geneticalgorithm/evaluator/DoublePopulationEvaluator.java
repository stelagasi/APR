package hr.fer.apr.geneticalgorithm.evaluator;

import hr.fer.apr.geneticalgorithm.function.IFunction;
import hr.fer.apr.geneticalgorithm.individual.DoubleIndividual;

import java.util.List;

import static java.lang.Double.MAX_VALUE;

public class DoublePopulationEvaluator implements IPopulationEvaluator<DoubleIndividual> {
    private DoubleIndividual worstIndividual;
    private DoubleIndividual bestIndividual;


    public double evaluatePenalty(List<DoubleIndividual> population, IFunction<Double> goalFunction) {
        double populationPenalty = 0.0;

        DoubleIndividual worstIndividual = population.get(0);
        DoubleIndividual bestIndividual = population.get(0);

        worstIndividual.setPenalty(-MAX_VALUE);
        bestIndividual.setPenalty(MAX_VALUE);

        for (DoubleIndividual individual : population) {
            double individualPenalty = evaluatePenaltyOfIndividual(individual, goalFunction);

            if (worstIndividual.getPenalty() < individualPenalty) {
                worstIndividual = individual;
            }
            if (bestIndividual.getPenalty() > individualPenalty) {
                bestIndividual = individual;
            }

            individual.setPenalty(individualPenalty);
            populationPenalty += individualPenalty;
        }

        this.setWorstIndividual(worstIndividual);
        this.setBestIndividual(bestIndividual);

        return populationPenalty;
    }

    public double evaluateFitness(List<DoubleIndividual> population, IFunction<Double> goalFunction) {
        evaluatePenalty(population, goalFunction);
        double worstPenalty = getWorstIndividual().getPenalty();
        double populationFitness = 0.0;
        for (DoubleIndividual individual : population) {
            double fitness = worstPenalty - individual.getPenalty();
            populationFitness += fitness;
            individual.setPenalty(fitness);
        }
        return populationFitness;
    }

    public double evaluatePenaltyOfIndividual(DoubleIndividual individual, IFunction<Double> goalFunction) {
        var result = Math.abs(goalFunction.valueAt(individual.getChromosomes()));
        individual.setPenalty(result);
        return result;
    }

    public DoubleIndividual getWorstIndividual() {
        return worstIndividual;
    }

    public void setWorstIndividual(DoubleIndividual worstIndividual) {
        this.worstIndividual = worstIndividual;
    }

    public DoubleIndividual getBestIndividual() {
        return bestIndividual;
    }

    public void setBestIndividual(DoubleIndividual bestIndividual) {
        this.bestIndividual = bestIndividual;
    }
}
