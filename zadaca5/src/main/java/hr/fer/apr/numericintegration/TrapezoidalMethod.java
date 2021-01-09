package hr.fer.apr.numericintegration;

import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.SquareMatrix;
import hr.fer.apr.lu.matrix.Vector;

import static hr.fer.apr.lu.TaskSolver.inverseOfMatrix;
import static hr.fer.apr.lu.matrix.Matrix.matrixMultiplication;
import static hr.fer.apr.lu.matrix.Matrix.subtraction;
import static hr.fer.apr.lu.matrix.Vector.addition;
import static hr.fer.apr.numericintegration.MethodHelper.getDeltaX;
import static hr.fer.apr.numericintegration.MethodHelper.getRt;

public class TrapezoidalMethod implements ImplicitMethod {
    private final MethodHelper methodHelper;
    private Matrix R;
    private Matrix S;

    public TrapezoidalMethod(MethodHelper methodHelper) {
        this.methodHelper = methodHelper;
    }

    @Override
    public void transformToExplicit(Matrix A, Matrix B, double T) {
        if (!(A instanceof SquareMatrix)) throw new IllegalArgumentException("Matrix should be SquareMatrix.");

        SquareMatrix U = new SquareMatrix(A.getNumberOfRows(), new double[A.getNumberOfRows() * A.getNumberOfColumns()]);

        for (int i = 0; i < U.getNumberOfRows(); i++) {
            U.setElementAt(i, i, 1);
        }

        SquareMatrix expression = new SquareMatrix(subtraction(U, A.multiplicationWithScalar(T / 2)));
        this.R = matrixMultiplication(inverseOfMatrix(expression), addition(U, A.multiplicationWithScalar(T / 2)));
        this.S = matrixMultiplication(inverseOfMatrix(expression).multiplicationWithScalar(T / 2), B);
    }

    @Override
    public Vector apply(Vector x0, String[] rt, double T, double tMax, boolean calculateError) {
        Vector x = new Vector(x0);

        for (double i = T; i <= tMax; i += T) {
            x = addition(new Vector(matrixMultiplication(R, x)), new Vector(matrixMultiplication(S, addition(getRt(rt, i - T), getRt(rt, i)))));

            methodHelper.addToHistory(x);

            if (calculateError) methodHelper.calculateError(x, i);
        }

        return x;
    }

    @Override
    public Vector correct(Matrix A, Matrix B, Vector x0, Vector xApprox, String[] rt, double T, double t) {
        return addition(x0, addition(getDeltaX(A, x0, B, rt, t - T), getDeltaX(A, xApprox, B, rt, t)).multiplicationWithScalar(T / 2));
    }

    public MethodHelper getMethodHelper() {
        return methodHelper;
    }

}
