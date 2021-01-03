package hr.fer.apr.numericintegration;

import hr.fer.apr.lu.TaskSolver;
import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.SquareMatrix;
import hr.fer.apr.lu.matrix.Vector;

import static hr.fer.apr.lu.matrix.Matrix.*;

public class TrapezoidalMethod implements ImplicitMethod {
    private Matrix R;
    private Matrix S;

    @Override
    public void transformToExplicit(Matrix A, Matrix B, double T) {
        if(!(A instanceof SquareMatrix)) throw new IllegalArgumentException("Matrix should be SquareMatrix.");

        SquareMatrix U = new SquareMatrix(A.getNumberOfRows(), new double[A.getNumberOfRows()*A.getNumberOfColumns()]);

        for (int i = 0; i < U.getNumberOfRows(); i++) {
            U.setElementAt(i, i, 1);
        }

        SquareMatrix expression = (SquareMatrix) subtraction(U, A.multiplicationWithScalar(T/2));
        this.R = matrixMultiplication(TaskSolver.inverseOfMatrix(expression), expression);
        this.S = matrixMultiplication(TaskSolver.inverseOfMatrix(expression).multiplicationWithScalar(T/2), B);
    }

    @Override
    public Vector apply(Vector x0, String[] rt, double T, double tMax) {
        Vector xCurrent = x0;
        Vector xNext;

        for (double i = T; i < tMax + 0.0001; i = i + T) {
            xNext = Vector.addition(new Vector(matrixMultiplication(R, xCurrent)), new Vector(matrixMultiplication(S, Vector.addition(getRt(rt, i - T), getRt(rt, i)))));
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
