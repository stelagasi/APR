package hr.fer.apr.numericintegration;

import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.Vector;

public class EulerMethod implements ExplicitMethod {

    @Override
    public Vector apply(Matrix A, Vector x0, Matrix B, String[] rt, double T, double tMax) {
        Vector xCurrent = x0;
        Vector xNext;

        for (double i = T; i < tMax + 0.0001; i = i + T) {
            Vector deltaX = getDeltaX(A, xCurrent, B, rt, i - T);
            xNext = Vector.addition(xCurrent, deltaX.multiplicationWithScalar(T));
            xCurrent = xNext;
        }

        return xCurrent;
    }

    private Vector getDeltaX(Matrix A, Vector x, Matrix B, String[] rt, double t) {
        Vector deltaX;

        if (B == null || rt == null) {
            deltaX = new Vector(Matrix.matrixMultiplication(A, x));
        } else {
            Vector vectorRt = getRt(rt, t);
            deltaX = Vector.addition(new Vector(Matrix.matrixMultiplication(A, x)), new Vector(Matrix.matrixMultiplication(B, vectorRt)));
        }

        return deltaX;
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
