package hr.fer.apr.geneticalgorithm.function;

import java.util.List;

import static java.lang.Math.*;

public class FunctionGenerator {

    private FunctionGenerator() {
    }

    public static IFunction<Double> f1() {
        return new IFunction<>() {
            private int numberOfEvaluations = 0;
            @Override
            public double valueAt(List<Double> chromosomes) {
                numberOfEvaluations++;
                return 100 * pow(chromosomes.get(1) - pow(chromosomes.get(0), 2), 2)
                      + pow(1 - chromosomes.get(0), 2);
            }

            @Override
            public int getNumberOfEvaluations() {
                return numberOfEvaluations;
            }
        };
    }

    public static IFunction<Double> f3(int n) {
        return new IFunction<>() {
            private int numberOfEvaluations = 0;
            @Override
            public double valueAt(List<Double> chromosomes) {
                numberOfEvaluations++;
                double value = 0;
                for (int i = 0; i < n; i++) {
                    value += pow(chromosomes.get(i) - i, 2);
                }
                return value;
            }

            @Override
            public int getNumberOfEvaluations() {
                return numberOfEvaluations;
            }
        };
    }

    public static IFunction<Double> f6(int n) {
        return new IFunction<>() {
            private int numberOfEvaluations = 0;
            @Override
            public double valueAt(List<Double> chromosomes) {
                numberOfEvaluations++;
                double sum = 0;
                for (int i = 0; i < n; i++) {
                    sum += pow(chromosomes.get(i), 2);
                }
                return 0.5 + (pow(sin(sqrt(sum)), 2) - 0.5) / pow(1 + 0.001 * sum, 2);
            }

            @Override
            public int getNumberOfEvaluations() {
                return numberOfEvaluations;
            }
        };
    }

    public static IFunction<Double> f7(int n) {
        return new IFunction<>() {
            private int numberOfEvaluations = 0;
            @Override
            public double valueAt(List<Double> chromosomes) {
                numberOfEvaluations++;
                double sum = 0;
                for (int i = 0; i < n; i++) {
                    sum += pow(chromosomes.get(i), 2);
                }
                return pow(sum, 0.25) * (1 + pow(sin(50 * pow(sum, 0.1)), 2));
            }

            @Override
            public int getNumberOfEvaluations() {
                return numberOfEvaluations;
            }
        };
    }
}
