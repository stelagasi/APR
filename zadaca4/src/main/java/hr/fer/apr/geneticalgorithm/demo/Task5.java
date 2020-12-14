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

public class Task5 {
    public static void main(String[] args) {
        List<Integer> k = List.of(3, 5, 7);
        List<List<Double>> lists = new ArrayList<>();
        for (int i = 0; i < k.size(); i++) {
            lists.add(new ArrayList<>());
            for (int j = 0; j < 10; j++) {
                DoublePopulationInitializer doublePopulationInitializer = new DoublePopulationInitializer(1000, 3, -50, 150);
                GaussianDoubleMutator doubleMutator = new GaussianDoubleMutator(0.01, 1);
                EliminationGeneticAlgorithm<DoubleIndividual> ega = new EliminationGeneticAlgorithm<>(doublePopulationInitializer, doubleMutator, new DoubleReproductioner(), new RandomSelectioner<DoubleIndividual>(), FunctionGenerator.f6(3), new DoublePopulationEvaluator(), k.get(i));
                lists.get(i).add(ega.execute(100000, 100000).getPenalty());
            }
        }
        System.out.println("3,5,7");
        outputInAcceptableFormat(lists.get(0), lists.get(1), lists.get(2));
    }

    public static void outputInAcceptableFormat(List<Double> l1, List<Double> l2, List<Double> l3) {
        for (int i = 0; i < l1.size(); i++) {
            System.out.println(l1.get(i) + "," +
                    l2.get(i) + "," +
                    l3.get(i));
        }
    }
}
