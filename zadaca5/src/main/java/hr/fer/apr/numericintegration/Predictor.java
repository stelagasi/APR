package hr.fer.apr.numericintegration;

import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.Vector;

public interface Predictor extends ExplicitMethod {

    Vector predict(Matrix A, Vector x0, Matrix B, String[] rt, double T, double t);

}
