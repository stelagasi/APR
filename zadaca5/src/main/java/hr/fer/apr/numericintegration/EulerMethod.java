package hr.fer.apr.numericintegration;

import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.Vector;

import static hr.fer.apr.lu.matrix.Vector.*;
import static hr.fer.apr.numericintegration.MethodHelper.*;

public class EulerMethod implements ExplicitMethod {

    @Override
    public Vector apply(Matrix A, Vector x0, Matrix B, String[] rt, double T, double tMax) {
        Vector xCurrent = x0;
        Vector xNext;

        for (double i = T; i < tMax + 0.0001; i = i + T) {
            Vector deltaX = getDeltaX(A, xCurrent, B, rt, i - T);
            xNext = addition(xCurrent, deltaX.multiplicationWithScalar(T));
            xCurrent = xNext;
        }

        return xCurrent;
    }
}
