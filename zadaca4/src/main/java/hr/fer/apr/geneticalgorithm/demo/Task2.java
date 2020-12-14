package hr.fer.apr.geneticalgorithm.demo;

import hr.fer.apr.geneticalgorithm.EliminationGeneticAlgorithm;
import hr.fer.apr.geneticalgorithm.evaluator.DoublePopulationEvaluator;
import hr.fer.apr.geneticalgorithm.function.FunctionGenerator;
import hr.fer.apr.geneticalgorithm.individual.DoubleIndividual;
import hr.fer.apr.geneticalgorithm.initializer.DoublePopulationInitializer;
import hr.fer.apr.geneticalgorithm.mutator.GaussianDoubleMutator;
import hr.fer.apr.geneticalgorithm.reproductioner.DoubleReproductioner;
import hr.fer.apr.geneticalgorithm.selectioner.RandomSelectioner;

import java.util.List;

public class Task2 {
    public static void main(String[] args) {
        List<Integer> dimensions = List.of(1, 3, 6, 10);

        for(Integer d : dimensions) {
            System.out.println(d + " dimenzija: ");
            DoublePopulationInitializer doublePopulationInitializer1 = new DoublePopulationInitializer(1000, d, -50, 150);
            GaussianDoubleMutator doubleMutator1 = new GaussianDoubleMutator(0.01, 1);
            EliminationGeneticAlgorithm<DoubleIndividual> ega1 = new EliminationGeneticAlgorithm<>(doublePopulationInitializer1, doubleMutator1, new DoubleReproductioner(), new RandomSelectioner<DoubleIndividual>(), FunctionGenerator.f6(d), new DoublePopulationEvaluator(), 3);
            System.out.println(ega1.execute(100000, 10000));

            DoublePopulationInitializer doublePopulationInitializer2 = new DoublePopulationInitializer(1000, d, -50, 150);
            GaussianDoubleMutator doubleMutator2 = new GaussianDoubleMutator(0.01, 1);
            EliminationGeneticAlgorithm<DoubleIndividual> ega2 = new EliminationGeneticAlgorithm<>(doublePopulationInitializer2, doubleMutator2, new DoubleReproductioner(), new RandomSelectioner<DoubleIndividual>(), FunctionGenerator.f6(d), new DoublePopulationEvaluator(), 3);
            System.out.println(ega2.execute(100000, 10000));
            System.out.println("\n");
        }
    }
}