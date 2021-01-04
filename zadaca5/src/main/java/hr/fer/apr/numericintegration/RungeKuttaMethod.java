package hr.fer.apr.numericintegration;

import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.Vector;

import static hr.fer.apr.lu.matrix.Vector.addition;
import static hr.fer.apr.numericintegration.MethodHelper.getDeltaX;

public class RungeKuttaMethod implements ExplicitMethod {

    @Override
    public Vector apply(Matrix A, Vector x0, Matrix B, String[] rt, double T, double tMax) {
        Vector xCurrent = x0;
        Vector xNext;

        for (double i = T; i < tMax + 0.0001; i = i + T) {

            Vector m1 = getDeltaX(A, xCurrent, B, rt, i - T);
            Vector m2 = getDeltaX(A, addition(xCurrent, m1.multiplicationWithScalar(T / 2)), B, rt, (i - T) + T / 2);
            Vector m3 = getDeltaX(A, addition(xCurrent, m2.multiplicationWithScalar(T / 2)), B, rt, (i - T) + T / 2);
            Vector m4 = getDeltaX(A, addition(xCurrent, m3.multiplicationWithScalar(T / 2)), B, rt, (i - T) + T / 2);

            xNext = addition(xCurrent, addition(addition(addition(m1, m2.multiplicationWithScalar(2)), m3.multiplicationWithScalar(2)), m4));
            xCurrent = xNext;
        }

        return xCurrent;
    }
}
