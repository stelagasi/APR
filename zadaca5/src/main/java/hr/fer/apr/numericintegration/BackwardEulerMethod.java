package hr.fer.apr.numericintegration;

import hr.fer.apr.lu.TaskSolver;
import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.SquareMatrix;
import hr.fer.apr.lu.matrix.Vector;

public class BackwardEulerMethod implements ImplicitMethod {
    private Matrix P;
    private Matrix Q;

    @Override
    public void transformToExplicit(Matrix A, Matrix B, double T) {
        if(!(A instanceof SquareMatrix) || !(B instanceof SquareMatrix)) throw new IllegalArgumentException("Matrix should be SquareMatrix.");

        SquareMatrix U = new SquareMatrix(A.getNumberOfRows(), new double[A.getNumberOfRows()* A.getNumberOfColumns()]);

        for (int i = 0; i < U.getNumberOfRows(); i++) {
            U.setElementAt(i, i, 1);
        }

        this.P = TaskSolver.inverseOfMatrix((SquareMatrix) Matrix.subtraction(U, A.multiplicationWithScalar(T)));
        this.Q = Matrix.matrixMultiplication(P.multiplicationWithScalar(T), B);
    }

    @Override
    public Vector apply(Vector x0, String[] rt, double T, double tMax) {
        Vector xCurrent = x0;
        Vector xNext;

        for (double i = T; i < tMax + 0.0001; i = i + T) {
           xNext = Vector.addition(new Vector(Matrix.matrixMultiplication(P, xCurrent)), new Vector(Matrix.matrixMultiplication(Q, getRt(rt, i))));
           xCurrent = xNext;
        }

        return xCurrent;
    }

    private Vector getRt(String[] rt, double t){
        Vector vectorRt = new Vector(rt.length, new double[rt.length]);

        for (int i = 0; i < rt.length; i++) {
            if(rt[i].matches("-?\\d+(\\.\\d+)?")){
                vectorRt.setElementAt(i, Double.parseDouble(rt[i]));
            } else {
                vectorRt.setElementAt(i, t);
            }
        }

        return vectorRt;
    }
}
