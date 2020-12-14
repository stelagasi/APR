package hr.fer.apr.geneticalgorithm.demo;

import hr.fer.apr.geneticalgorithm.EliminationGeneticAlgorithm;
import hr.fer.apr.geneticalgorithm.evaluator.DoublePopulationEvaluator;
import hr.fer.apr.geneticalgorithm.function.FunctionGenerator;
import hr.fer.apr.geneticalgorithm.individual.DoubleIndividual;
import hr.fer.apr.geneticalgorithm.initializer.DoublePopulationInitializer;
import hr.fer.apr.geneticalgorithm.mutator.GaussianDoubleMutator;
import hr.fer.apr.geneticalgorithm.reproductioner.DoubleReproductioner;
import hr.fer.apr.geneticalgorithm.selectioner.RandomSelectioner;

import java.util.ArrayList;
import java.util.List;

public class Task4 {
    public static void main(String[] args) {
        List<Integer> populationSize = List.of(30, 50, 100, 200);
        List<Double> mutationProbability = List.of(0.1, 0.3, 0.6, 0.9);
        List<List<Double>> prob = new ArrayList<>();
        List<List<Double>> pop = new ArrayList<>();

        for (int i = 0; i < mutationProbability.size(); i++) {
            prob.add(new ArrayList<>());
            for (int j = 0; j < 10; j++) {
                DoublePopulationInitializer doublePopulationInitializer = new DoublePopulationInitializer(1000, 3, -50, 150);
                GaussianDoubleMutator doubleMutator = new GaussianDoubleMutator(mutationProbability.get(i), 1);
                EliminationGeneticAlgorithm<DoubleIndividual> ega = new EliminationGeneticAlgorithm<>(doublePopulationInitializer, doubleMutator, new DoubleReproductioner(), new RandomSelectioner<DoubleIndividual>(), FunctionGenerator.f6(3), new DoublePopulationEvaluator(), 3);
                prob.get(i).add(ega.execute(100000, 100000).getPenalty());
            }
        }

        for (int i = 0; i < populationSize.size(); i++) {
            pop.add(new ArrayList<>());
            for (int j = 0; j < 10; j++) {
                DoublePopulationInitializer doublePopulationInitializer = new DoublePopulationInitializer(populationSize.get(i), 3, -50, 150);
                GaussianDoubleMutator doubleMutator = new GaussianDoubleMutator(0.01, 1);
                EliminationGeneticAlgorithm<DoubleIndividual> ega = new EliminationGeneticAlgorithm<>(doublePopulationInitializer, doubleMutator, new DoubleReproductioner(), new RandomSelectioner<DoubleIndividual>(), FunctionGenerator.f6(3), new DoublePopulationEvaluator(), 3);
                //System.out.println(ega.execute(100000, 100000));
                pop.get(i).add(ega.execute(100000, 100000).getPenalty());
            }
        }

        System.out.println("0.1,0.3,0.6,0.9");
        outputInAcceptableFormat(prob.get(0), prob.get(1), prob.get(2), prob.get(3));
        System.out.println("\n\n");
        System.out.println("30,50,100,200");
        outputInAcceptableFormat(pop.get(0), pop.get(1), pop.get(2), pop.get(3));
    }
        public static void outputInAcceptableFormat(List<Double> l1, List<Double> l2, List<Double> l3, List<Double> l4) {
            for (int i = 0; i < l1.size(); i++) {
                System.out.println(l1.get(i) + "," +
                        l2.get(i) + "," +
                        l3.get(i) + "," +
                        l4.get(i));
            }
        }

}
