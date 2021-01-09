package hr.fer.apr.numericintegration;

import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.Vector;

import java.util.function.Function;

public class MethodHelper {
    private final Function<Double, Vector> function;
    private final Vector error = new Vector(2, new double[2]);
    private final StringBuilder stringBuilder = new StringBuilder();

    public MethodHelper(Function<Double, Vector> function){
        this.function = function;
    }

    public static Vector getDeltaX(Matrix A, Vector x, Matrix B, String[] rt, double t) {
        Vector deltaX;

        if (rt == null) {
            deltaX = new Vector(Matrix.matrixMultiplication(A, x));
        } else {
            Vector vectorRt = getRt(rt, t);
            deltaX = Vector.addition(new Vector(Matrix.matrixMultiplication(A, x)), new Vector(Matrix.matrixMultiplication(B, vectorRt)));
        }

        return deltaX;
    }

    public static Vector getRt(String[] rt, double t) {
        if (rt == null) return new Vector(2, new double[2]);

        Vector vectorRt = new Vector(rt.length, new double[rt.length]);

        for (int i = 0; i < rt.length; i++) {
            if (rt[i].matches("-?\\d+(\\.\\d+)?")) {
                vectorRt.setElementAt(i, Double.parseDouble(rt[i]));
            } else {
                vectorRt.setElementAt(i, t);
            }
        }

        return vectorRt;
    }

    public void calculateError(Vector x, Double t) {
        error.setElementAt(0, error.getElementAt(0) + Math.abs(x.getElementAt(0) - function.apply(t).getElementAt(0)));
        error.setElementAt(1, error.getElementAt(1) + Math.abs(x.getElementAt(1) - function.apply(t).getElementAt(1)));
    //    System.out.println(Math.abs(x.getElementAt(0) - function.apply(t).getElementAt(0)));
    //    System.out.println(Math.abs(x.getElementAt(1) - function.apply(t).getElementAt(1)));
    }

    public Vector getError() {
        return error;
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

}
