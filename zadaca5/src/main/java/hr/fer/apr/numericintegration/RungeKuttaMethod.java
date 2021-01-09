package hr.fer.apr.numericintegration;

import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.Vector;

import static hr.fer.apr.lu.matrix.Vector.addition;
import static hr.fer.apr.numericintegration.MethodHelper.getDeltaX;

public class RungeKuttaMethod implements ExplicitMethod {
    private final MethodHelper methodHelper;
    private double[] x1;
    private double[] x2;

    public RungeKuttaMethod(MethodHelper methodHelper) {
        this.methodHelper = methodHelper;
    }

    @Override
    public Vector apply(Matrix A, Vector x0, Matrix B, String[] rt, double T, double tMax, int numberOfPrintingIteration, boolean calculateError) {
        Vector x = new Vector(x0);
        int iterationNumber = 1;
        x1 = new double[(int) (tMax/T)+1];
        x2 = new double[(int) (tMax/T)+1];

        for (double i = T; i <= tMax; i = i + T) {

            Vector m1 = getDeltaX(A, x, B, rt, i - T);
            Vector m2 = getDeltaX(A, addition(x, m1.multiplicationWithScalar(T / 2)), B, rt, (i - T) + T / 2);
            Vector m3 = getDeltaX(A, addition(x, m2.multiplicationWithScalar(T / 2)), B, rt, (i - T) + T / 2);
            Vector m4 = getDeltaX(A, addition(x, m3.multiplicationWithScalar(T)), B, rt, i);

            x = addition(x, addition(addition(addition(m1, m2.multiplicationWithScalar(2)), m3.multiplicationWithScalar(2)), m4).multiplicationWithScalar(T / 6));
            if(numberOfPrintingIteration != 0 && iterationNumber++ % numberOfPrintingIteration == 0) {
                methodHelper.getStringBuilder().append(x).append(System.lineSeparator());
                x1[iterationNumber-1] = x.getElementAt(0);
                x2[iterationNumber-1] = x.getElementAt(1);
            }
            if(calculateError) methodHelper.calculateError(x, i);
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
