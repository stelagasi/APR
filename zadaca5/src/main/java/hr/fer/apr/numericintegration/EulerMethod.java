package hr.fer.apr.numericintegration;

import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.Vector;

import static hr.fer.apr.lu.matrix.Vector.*;
import static hr.fer.apr.numericintegration.MethodHelper.*;

public class EulerMethod implements Predictor {
    private final MethodHelper methodHelper;

    public EulerMethod(MethodHelper methodHelper) {
        this.methodHelper = methodHelper;
    }

    @Override
    public Vector apply(Matrix A, Vector x0, Matrix B, String[] rt, double T, double tMax, boolean calculateError) {
        Vector x = new Vector(x0);

        for (double i = T; i <= tMax; i = i + T) {
            Vector deltaX = getDeltaX(A, x, B, rt, i - T);
            x = addition(x, deltaX.multiplicationWithScalar(T));

            methodHelper.addToHistory(x);

            if (calculateError) methodHelper.calculateError(x, i);
        }

        return x;
    }

    public Vector predict(Matrix A, Vector x0, Matrix B, String[] rt, double T, double t){ return addition(x0, getDeltaX(A, x0, B, rt, t - T).multiplicationWithScalar(T));   }

    public MethodHelper getMethodHelper() {
        return methodHelper;
    }

}
