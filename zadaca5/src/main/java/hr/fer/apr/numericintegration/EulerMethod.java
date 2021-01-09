package hr.fer.apr.numericintegration;

import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.Vector;

import static hr.fer.apr.lu.matrix.Vector.*;
import static hr.fer.apr.numericintegration.MethodHelper.*;

public class EulerMethod implements ExplicitMethod {
    private final MethodHelper methodHelper;
    private double[] x1;
    private double[] x2;

    public EulerMethod(MethodHelper methodHelper) {
        this.methodHelper = methodHelper;
    }

    @Override
    public Vector apply(Matrix A, Vector x0, Matrix B, String[] rt, double T, double tMin, double tMax, int numberOfPrintingIteration, boolean calculateError) {
        Vector x = new Vector(x0);
        int iterationNumber = 0;
        x1 = new double[(int) (tMax/T)+1];
        x2 = new double[(int) (tMax/T)+1];

        for (double i = tMin; i <= tMax; i = i + T) {
            Vector deltaX = getDeltaX(A, x, B, rt, i - T);
            x = addition(x, deltaX.multiplicationWithScalar(T));
            if(numberOfPrintingIteration != 0 && iterationNumber++ % numberOfPrintingIteration == 0) {
                methodHelper.getStringBuilder().append(x).append(System.lineSeparator());
                x1[iterationNumber-1] = x.getElementAt(0);
                x2[iterationNumber-1] = x.getElementAt(1);
            }
            if (calculateError) methodHelper.calculateError(x, i);
        }

        return x;
    }

    public MethodHelper getMethodHelper() {
        return methodHelper;
    }

    public double[] getX1() {
        return x1;
    }

    public double[] getX2() {
        return x2;
    }
}
