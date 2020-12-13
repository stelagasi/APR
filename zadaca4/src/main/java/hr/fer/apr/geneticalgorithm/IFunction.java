package hr.fer.apr.geneticalgorithm;

import java.util.List;

public interface IFunction {

    double valueAt(double x, double y, List<Double> chromosomes);

}
