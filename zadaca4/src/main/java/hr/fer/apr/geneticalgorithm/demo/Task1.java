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

public class Task1 {
    public static void main(String[] args) {
        DoublePopulationInitializer doublePopulationInitializer1 = new DoublePopulationInitializer(1000, 2, -50, 150);
        GaussianDoubleMutator doubleMutator1 = new GaussianDoubleMutator(0.01, 1);
        EliminationGeneticAlgorithm<DoubleIndividual> ega1 = new EliminationGeneticAlgorithm<>(doublePopulationInitializer1, doubleMutator1, new DoubleReproductioner(), new RandomSelectioner<DoubleIndividual>(), FunctionGenerator.f1(), new DoublePopulationEvaluator(), 3);
        System.out.println("double f1: ");
        System.out.println(ega1.execute(100000, 100000));

        DoublePopulationInitializer doublePopulationInitializer2 = new DoublePopulationInitializer(1000, 5, -50, 150);
        GaussianDoubleMutator doubleMutator2 = new GaussianDoubleMutator(0.01, 1);
        EliminationGeneticAlgorithm<DoubleIndividual> ega2 = new EliminationGeneticAlgorithm<>(doublePopulationInitializer2, doubleMutator2, new DoubleReproductioner(), new RandomSelectioner<DoubleIndividual>(), FunctionGenerator.f3(5), new DoublePopulationEvaluator(), 3);
        System.out.println("\ndouble f3: ");
        System.out.println(ega2.execute(100000, 100000));

        DoublePopulationInitializer doublePopulationInitializer3 = new DoublePopulationInitializer(1000, 2, -50, 150);
        GaussianDoubleMutator doubleMutator3 = new GaussianDoubleMutator(0.01, 1);
        EliminationGeneticAlgorithm<DoubleIndividual> ega3 = new EliminationGeneticAlgorithm<>(doublePopulationInitializer3, doubleMutator3, new DoubleReproductioner(), new RandomSelectioner<DoubleIndividual>(), FunctionGenerator.f6(2), new DoublePopulationEvaluator(), 3);
        System.out.println("\ndouble f6: ");
        System.out.println(ega3.execute(100000, 100000));

        DoublePopulationInitializer doublePopulationInitializer4 = new DoublePopulationInitializer(1000, 2, -50, 150);
        GaussianDoubleMutator doubleMutator4 = new GaussianDoubleMutator(0.01, 1);
        EliminationGeneticAlgorithm<DoubleIndividual> ega4 = new EliminationGeneticAlgorithm<>(doublePopulationInitializer4, doubleMutator4, new DoubleReproductioner(), new RandomSelectioner<DoubleIndividual>(), FunctionGenerator.f7(2), new DoublePopulationEvaluator(), 3);
        System.out.println("\ndouble f7: ");
        System.out.println(ega4.execute(100000, 100000));

        BinaryPopulationInitializer binaryPopulationInitializer1 = new BinaryPopulationInitializer(1000, 2, 4, -50, 150);
        BinaryMutator binaryMutator1 = new BinaryMutator(0.01, 1);
        BinaryPopulationEvaluator binaryPopulationEvaluator = new BinaryPopulationEvaluator(-50, 150);
        EliminationGeneticAlgorithm<BinaryIndividual> binaryEGA1 = new EliminationGeneticAlgorithm<>(binaryPopulationInitializer1, binaryMutator1, new HeuristicBinaryReproductioner(), new RandomSelectioner<BinaryIndividual>(), FunctionGenerator.f1(), new BinaryPopulationEvaluator(-50, 150), 3);
        System.out.println("\n\nbinary f1: ");
        System.out.println(binaryPopulationEvaluator.getDoubleRepresentation(binaryEGA1.execute(100000, 100000)));

        BinaryPopulationInitializer binaryPopulationInitializer2 = new BinaryPopulationInitializer(1000, 5, 4, -50, 150);
        BinaryMutator binaryMutator2 = new BinaryMutator(0.01, 1);
        BinaryPopulationEvaluator binaryPopulationEvaluator2 = new BinaryPopulationEvaluator(-50, 150);
        EliminationGeneticAlgorithm<BinaryIndividual> binaryEGA2 = new EliminationGeneticAlgorithm<>(binaryPopulationInitializer2, binaryMutator2, new HeuristicBinaryReproductioner(), new RandomSelectioner<BinaryIndividual>(), FunctionGenerator.f3(5), binaryPopulationEvaluator2, 3);
        System.out.println("\nbinary f3: ");
        System.out.println(binaryPopulationEvaluator2.getDoubleRepresentation(binaryEGA2.execute(100000, 100000)));

        BinaryPopulationInitializer binaryPopulationInitializer3 = new BinaryPopulationInitializer(1000, 2, 4, -50, 150);
        BinaryMutator binaryMutator3 = new BinaryMutator(0.01, 1);
        BinaryPopulationEvaluator binaryPopulationEvaluator3 = new BinaryPopulationEvaluator(-50, 150);
        EliminationGeneticAlgorithm<BinaryIndividual> binaryEGA3 = new EliminationGeneticAlgorithm<>(binaryPopulationInitializer3, binaryMutator3, new HeuristicBinaryReproductioner(), new RandomSelectioner<BinaryIndividual>(), FunctionGenerator.f6(2), binaryPopulationEvaluator3, 3);
        System.out.println("\nbinary f6: ");
        System.out.println(binaryPopulationEvaluator3.getDoubleRepresentation(binaryEGA3.execute(100000, 100000)));

        BinaryPopulationInitializer binaryPopulationInitializer4 = new BinaryPopulationInitializer(1000, 2, 4, -50, 150);
        BinaryMutator binaryMutator4 = new BinaryMutator(0.01, 1);
        BinaryPopulationEvaluator binaryPopulationEvaluator4 = new BinaryPopulationEvaluator(-50, 150);
        EliminationGeneticAlgorithm<BinaryIndividual> binaryEGA4 = new EliminationGeneticAlgorithm<>(binaryPopulationInitializer4, binaryMutator4, new HeuristicBinaryReproductioner(), new RandomSelectioner<BinaryIndividual>(), FunctionGenerator.f7(2), binaryPopulationEvaluator4, 3);
        System.out.println("\nbinary f7: ");
        System.out.println(binaryPopulationEvaluator4.getDoubleRepresentation(binaryEGA4.execute(100000, 100000)));
    }
}
