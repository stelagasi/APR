package hr.fer.apr.numericintegration;

import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.Vector;

public interface ImplicitMethod {

    void transformToExplicit(Matrix A, Matrix B, double T);

    Vector apply(Vector x0, String[] rt, double T, double tMax, int numberOfPrintingIteration, boolean calculateError);

    MethodHelper getMethodHelper();

}
