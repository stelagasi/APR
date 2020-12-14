package hr.fer.apr.geneticalgorithm.demo;

import hr.fer.apr.geneticalgorithm.EliminationGeneticAlgorithm;
import hr.fer.apr.geneticalgorithm.evaluator.BinaryPopulationEvaluator;
import hr.fer.apr.geneticalgorithm.evaluator.DoublePopulationEvaluator;
import hr.fer.apr.geneticalgorithm.function.FunctionGenerator;
import hr.fer.apr.geneticalgorithm.individual.BinaryIndividual;
import hr.fer.apr.geneticalgorithm.individual.DoubleIndividual;
import hr.fer.apr.geneticalgorithm.initializer.BinaryPopulationInitializer;
import hr.fer.apr.geneticalgorithm.initializer.DoublePopulationInitializer;
import hr.fer.apr.geneticalgorithm.mutator.GaussianDoubleMutator;
import hr.fer.apr.geneticalgorithm.mutator.BinaryMutator;
import hr.fer.apr.geneticalgorithm.reproductioner.DoubleReproductioner;
import hr.fer.apr.geneticalgorithm.reproductioner.HeuristicBinaryReproductioner;
import hr.fer.apr.geneticalgorithm.selectioner.RandomSelectioner;

import java.util.ArrayList;
import java.util.List;

public class Task3 {
    public static void main(String[] args) {
        List<Double> penaltyF6Double3 = new ArrayList<>();
        List<Double> penaltyF6Double6 = new ArrayList<>();
        List<Double> penaltyF6Binary3 = new ArrayList<>();
        List<Double> penaltyF6Binary6 = new ArrayList<>();
        List<Double> penaltyF7Double3 = new ArrayList<>();
        List<Double> penaltyF7Double6 = new ArrayList<>();
        List<Double> penaltyF7Binary3 = new ArrayList<>();
        List<Double> penaltyF7Binary6 = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            DoublePopulationInitializer doublePopulationInitializer3 = new DoublePopulationInitializer(1000, 3, -50, 150);
            GaussianDoubleMutator doubleMutator3 = new GaussianDoubleMutator(0.01, 1);
            EliminationGeneticAlgorithm<DoubleIndividual> ega3 = new EliminationGeneticAlgorithm<>(doublePopulationInitializer3, doubleMutator3, new DoubleReproductioner(), new RandomSelectioner<DoubleIndividual>(), FunctionGenerator.f6(3), new DoublePopulationEvaluator(), 3);
            penaltyF6Double3.add(ega3.execute(100000, 100000).getPenalty());

            DoublePopulationInitializer doublePopulationInitializer4 = new DoublePopulationInitializer(1000, 3, -50, 150);
            GaussianDoubleMutator doubleMutator4 = new GaussianDoubleMutator(0.01, 1);
            EliminationGeneticAlgorithm<DoubleIndividual> ega4 = new EliminationGeneticAlgorithm<>(doublePopulationInitializer4, doubleMutator4, new DoubleReproductioner(), new RandomSelectioner<DoubleIndividual>(), FunctionGenerator.f7(3), new DoublePopulationEvaluator(), 3);
            penaltyF7Double3.add(ega4.execute(100000, 100000).getPenalty());

            BinaryPopulationInitializer binaryPopulationInitializer3 = new BinaryPopulationInitializer(1000, 3, 4, -50, 150);
            BinaryMutator binaryMutator3 = new BinaryMutator(0.01, 1);
            EliminationGeneticAlgorithm<BinaryIndividual> binaryEGA3 = new EliminationGeneticAlgorithm<>(binaryPopulationInitializer3, binaryMutator3, new HeuristicBinaryReproductioner(), new RandomSelectioner<BinaryIndividual>(), FunctionGenerator.f6(3), new BinaryPopulationEvaluator(-50, 150), 3);
            penaltyF6Binary3.add(binaryEGA3.execute(100000, 100000).getPenalty());

            BinaryPopulationInitializer binaryPopulationInitializer4 = new BinaryPopulationInitializer(1000, 3, 4, -50, 150);
            BinaryMutator binaryMutator4 = new BinaryMutator(0.01, 1);
            EliminationGeneticAlgorithm<BinaryIndividual> binaryEGA4 = new EliminationGeneticAlgorithm<>(binaryPopulationInitializer4, binaryMutator4, new HeuristicBinaryReproductioner(), new RandomSelectioner<BinaryIndividual>(), FunctionGenerator.f7(3), new BinaryPopulationEvaluator(-50, 150), 3);
            penaltyF7Binary3.add(binaryEGA4.execute(100000, 100000).getPenalty());

            DoublePopulationInitializer doublePopulationInitializer5 = new DoublePopulationInitializer(1000, 6, -50, 150);
            GaussianDoubleMutator doubleMutator5 = new GaussianDoubleMutator(0.01, 1);
            EliminationGeneticAlgorithm<DoubleIndividual> ega5 = new EliminationGeneticAlgorithm<>(doublePopulationInitializer5, doubleMutator5, new DoubleReproductioner(), new RandomSelectioner<DoubleIndividual>(), FunctionGenerator.f6(6), new DoublePopulationEvaluator(), 3);
            penaltyF6Double6.add(ega5.execute(100000, 100000).getPenalty());

            DoublePopulationInitializer doublePopulationInitializer6 = new DoublePopulationInitializer(1000, 6, -50, 150);
            GaussianDoubleMutator doubleMutator6 = new GaussianDoubleMutator(0.01, 1);
            EliminationGeneticAlgorithm<DoubleIndividual> ega6 = new EliminationGeneticAlgorithm<>(doublePopulationInitializer6, doubleMutator6, new DoubleReproductioner(), new RandomSelectioner<DoubleIndividual>(), FunctionGenerator.f7(6), new DoublePopulationEvaluator(), 3);
            penaltyF7Double6.add(ega6.execute(1000000, 100000).getPenalty());

            BinaryPopulationInitializer binaryPopulationInitializer7 = new BinaryPopulationInitializer(1000, 6, 4, -50, 150);
            BinaryMutator binaryMutator7 = new BinaryMutator(0.01, 1);
            EliminationGeneticAlgorithm<BinaryIndividual> binaryEGA7 = new EliminationGeneticAlgorithm<>(binaryPopulationInitializer7, binaryMutator7, new HeuristicBinaryReproductioner(), new RandomSelectioner<BinaryIndividual>(), FunctionGenerator.f6(6), new BinaryPopulationEvaluator(-50, 150), 3);
            penaltyF6Binary6.add(binaryEGA7.execute(100000, 100000).getPenalty());

            BinaryPopulationInitializer binaryPopulationInitializer8 = new BinaryPopulationInitializer(1000, 6, 4, -50, 150);
            BinaryMutator binaryMutator8 = new BinaryMutator(0.01, 1);
            EliminationGeneticAlgorithm<BinaryIndividual> binaryEGA8 = new EliminationGeneticAlgorithm<>(binaryPopulationInitializer8, binaryMutator8, new HeuristicBinaryReproductioner(), new RandomSelectioner<BinaryIndividual>(), FunctionGenerator.f7(6), new BinaryPopulationEvaluator(-50, 150), 3);
            penaltyF7Binary6.add(binaryEGA8.execute(100000, 100000).getPenalty());
        }

        outputInAcceptableFormat(penaltyF6Binary3, penaltyF6Binary6, penaltyF6Double3, penaltyF6Double6);
        System.out.println("\n\n");
        outputInAcceptableFormat(penaltyF7Binary3, penaltyF7Binary6, penaltyF7Double3, penaltyF7Double6);
    }

    public static void outputInAcceptableFormat(List<Double> penaltyBinary3, List<Double> penaltyBinary6, List<Double> penaltyDouble3, List<Double> penaltyDouble6) {
        System.out.println("PB3,PB6,PD3,PD6");
        for (int i = 0; i < penaltyBinary3.size(); i++) {
            System.out.println(penaltyBinary3.get(i) + "," +
                    penaltyBinary6.get(i) + "," +
                    penaltyDouble3.get(i) + "," +
                    penaltyDouble6.get(i));
        }
    }
}
