package hr.fer.apr.numericintegration;

import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.Vector;

public interface ExplicitMethod {

    Vector apply(Matrix A, Vector x0, Matrix B, String[] rt, double T, double tMax, boolean calculateError);



    MethodHelper getMethodHelper();

}
