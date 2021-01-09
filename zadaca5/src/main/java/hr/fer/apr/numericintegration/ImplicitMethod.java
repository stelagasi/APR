package hr.fer.apr.numericintegration;

import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.Vector;

public interface ImplicitMethod {

    void transformToExplicit(Matrix A, Matrix B, double T);

    Vector apply(Vector x0, String[] rt, double T, double tMin, double tMax, int numberOfPrintingIteration, boolean calculateError);

    Vector apply2(Matrix A, Matrix B, Vector x0, Vector xApprox, String[] rt, double T, double tMin, double tMax, int numberOfPrintingIteration, boolean calculateError);

    MethodHelper getMethodHelper();

    double[] getX1();

    double[] getX2();
}
