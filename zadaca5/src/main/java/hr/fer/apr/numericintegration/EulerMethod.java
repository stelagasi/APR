package hr.fer.apr.numericintegration;

import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.Vector;

import static hr.fer.apr.lu.matrix.Vector.*;
import static hr.fer.apr.numericintegration.MethodHelper.*;

public class EulerMethod implements ExplicitMethod {

    @Override
    public Vector apply(Matrix A, Vector x0, Matrix B, String[] rt, double T, double tMax) {
        Vector x = new Vector(x0);

        for (double i = T; i <= tMax; i = i + T) {
            Vector deltaX = getDeltaX(A, x, B, rt, i - T);
            x = addition(x, deltaX.multiplicationWithScalar(T));
        }

        return x;
    }
}
