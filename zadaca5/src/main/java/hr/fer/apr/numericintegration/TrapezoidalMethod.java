package hr.fer.apr.numericintegration;

import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.SquareMatrix;
import hr.fer.apr.lu.matrix.Vector;

import static hr.fer.apr.lu.TaskSolver.inverseOfMatrix;
import static hr.fer.apr.lu.matrix.Matrix.matrixMultiplication;
import static hr.fer.apr.lu.matrix.Matrix.subtraction;
import static hr.fer.apr.lu.matrix.Vector.addition;
import static hr.fer.apr.numericintegration.MethodHelper.getRt;

public class TrapezoidalMethod implements ImplicitMethod {
    private Matrix R;
    private Matrix S;

    @Override
    public void transformToExplicit(Matrix A, Matrix B, double T) {
        if (!(A instanceof SquareMatrix)) throw new IllegalArgumentException("Matrix should be SquareMatrix.");

        SquareMatrix U = new SquareMatrix(A.getNumberOfRows(), new double[A.getNumberOfRows() * A.getNumberOfColumns()]);

        for (int i = 0; i < U.getNumberOfRows(); i++) {
            U.setElementAt(i, i, 1);
        }

        SquareMatrix expression = (SquareMatrix) subtraction(U, A.multiplicationWithScalar(T / 2));
        this.R = matrixMultiplication(inverseOfMatrix(expression), expression);
        this.S = matrixMultiplication(inverseOfMatrix(expression).multiplicationWithScalar(T / 2), B);
    }

    @Override
    public Vector apply(Vector x0, String[] rt, double T, double tMax) {
        Vector xCurrent = x0;
        Vector xNext;

        for (double i = T; i < tMax + 0.0001; i = i + T) {
            xNext = addition(new Vector(matrixMultiplication(R, xCurrent)), new Vector(matrixMultiplication(S, addition(getRt(rt, i - T), getRt(rt, i)))));
            xCurrent = xNext;
        }

        return xCurrent;
    }
}
