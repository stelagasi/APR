package hr.fer.apr.numericintegration;

import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.SquareMatrix;
import hr.fer.apr.lu.matrix.Vector;

import static hr.fer.apr.lu.TaskSolver.inverseOfMatrix;
import static hr.fer.apr.lu.matrix.Matrix.matrixMultiplication;
import static hr.fer.apr.lu.matrix.Matrix.subtraction;
import static hr.fer.apr.lu.matrix.Vector.addition;
import static hr.fer.apr.numericintegration.MethodHelper.getRt;

public class BackwardEulerMethod implements ImplicitMethod {
    private final MethodHelper methodHelper;
    private double[] x1;
    private double[] x2;
    private Matrix P;
    private Matrix Q;

    public BackwardEulerMethod(MethodHelper methodHelper) {
        this.methodHelper = methodHelper;
    }

    @Override
    public void transformToExplicit(Matrix A, Matrix B, double T) {
        if (!(A instanceof SquareMatrix) || !(B instanceof SquareMatrix))
            throw new IllegalArgumentException("Matrix should be SquareMatrix.");

        SquareMatrix U = new SquareMatrix(A.getNumberOfRows(), new double[A.getNumberOfRows() * A.getNumberOfColumns()]);

        for (int i = 0; i < U.getNumberOfRows(); i++) {
            U.setElementAt(i, i, 1);
        }

        this.P = inverseOfMatrix(new SquareMatrix(subtraction(U, A.multiplicationWithScalar(T))));
        this.Q = matrixMultiplication(P.multiplicationWithScalar(T), B);
    }

    @Override
    public Vector apply(Vector x0, String[] rt, double T, double tMin, double tMax, int numberOfPrintingIteration, boolean calculateError) {
        methodHelper.resetError();
        int iterationNumber = 1;
        Vector x = new Vector(x0);
        x1 = new double[(int) (tMax/T)+1];
        x2 = new double[(int) (tMax/T)+1];

        for (double i = tMin; i <= tMax; i = i + T) {
            x = addition(new Vector(matrixMultiplication(P, x)), new Vector(matrixMultiplication(Q, getRt(rt, i))));
            if(numberOfPrintingIteration !=0 && iterationNumber++ % numberOfPrintingIteration == 0) {
                methodHelper.getStringBuilder().append(i).append(" ").append(x).append(System.lineSeparator());
                x1[iterationNumber-1] = x.getElementAt(0);
                x2[iterationNumber-1] = x.getElementAt(1);
            }
            if(calculateError) methodHelper.calculateError(x, i);
        }

        return x;
    }

    @Override
    public Vector apply2(Matrix A, Matrix B, Vector x0, Vector xApprox, String[] rt, double T, double tMin, double tMax, int numberOfPrintingIteration, boolean calculateError) {
        return null;
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
