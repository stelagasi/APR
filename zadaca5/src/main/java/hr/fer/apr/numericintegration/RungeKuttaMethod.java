package hr.fer.apr.numericintegration;

import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.Vector;

import static hr.fer.apr.lu.matrix.Vector.addition;
import static hr.fer.apr.numericintegration.MethodHelper.getDeltaX;

public class RungeKuttaMethod implements ExplicitMethod {
    private final MethodHelper methodHelper;

    public RungeKuttaMethod(MethodHelper methodHelper) {
        this.methodHelper = methodHelper;
    }

    @Override
    public Vector apply(Matrix A, Vector x0, Matrix B, String[] rt, double T, double tMax, boolean calculateError) {
        Vector x = new Vector(x0);

        for (double i = T; i <= tMax; i = i + T) {

            Vector m1 = getDeltaX(A, x, B, rt, i - T);
            Vector m2 = getDeltaX(A, addition(x, m1.multiplicationWithScalar(T / 2)), B, rt, (i - T) + T / 2);
            Vector m3 = getDeltaX(A, addition(x, m2.multiplicationWithScalar(T / 2)), B, rt, (i - T) + T / 2);
            Vector m4 = getDeltaX(A, addition(x, m3.multiplicationWithScalar(T)), B, rt, i);

            x = addition(x, addition(addition(addition(m1, m2.multiplicationWithScalar(2)), m3.multiplicationWithScalar(2)), m4).multiplicationWithScalar(T / 6));

            methodHelper.addToHistory(x);

            if(calculateError) methodHelper.calculateError(x, i);
        }

        return x;
    }

    public MethodHelper getMethodHelper() {
        return methodHelper;
    }
}
