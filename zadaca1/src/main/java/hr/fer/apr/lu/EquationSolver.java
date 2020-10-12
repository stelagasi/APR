package hr.fer.apr.lu;

import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.SquareMatrix;
import hr.fer.apr.lu.matrix.Vector;

public class EquationSolver {

    private EquationSolver() {
    }

    public static Vector forwardSubstitution(SquareMatrix A, Vector b) {
        if (A.getNumberOfRows() != b.getNumberOfRows()) throw new IllegalArgumentException();
        Vector y = new Vector(b);
        for (int i = 0; i < b.getNumberOfRows() - 1; i++) {
            for (int j = 0; j < b.getNumberOfRows(); j++) {
                y.setElementAt(j, y.getElementAt(j) - A.getElementAt(j, i) * y.getElementAt(i));
            }
        }
        return y;
    }

    public static Vector backSubstitution(SquareMatrix A, Vector b) {
        if (A.getNumberOfRows() != b.getNumberOfRows()) throw new IllegalArgumentException();
        Vector y = new Vector(b);
        for (int i = b.getNumberOfRows(); i > 0; i--) {
            y.setElementAt(i, y.getElementAt(i) / A.getElementAt(i, i));
            for (int j = 0; j < i - 1; j++) {
                y.setElementAt(j, y.getElementAt(j) - A.getElementAt(j, i) * y.getElementAt(i));
            }
        }
        return y;
    }

    //todo dijeljenje s nulom
    public static SquareMatrix LUDecomposition(SquareMatrix A) {
        SquareMatrix resultMatrix = new SquareMatrix(A);
        for (int i = 0; i < A.getNumberOfRows() - 1; i++) {
            for (int j = i + 1; j < A.getNumberOfRows(); j++) {
                A.setElementAt(j, i, A.getElementAt(j, i) / A.getElementAt(i, i));
                for (int k = i + 1; k < A.getNumberOfRows(); k++) {
                    A.setElementAt(j, k, A.getElementAt(j, k) - A.getElementAt(j, i) * A.getElementAt(i, k));
                }
            }
        }
        return resultMatrix;
    }

    //todo dijeljenje s nulom PAZIII
    public static SquareMatrix LUPDecomposition(SquareMatrix A) {
        for (int i = 0; i < A.getNumberOfRows() - 1; i++) {
            int pivot = i;
            for(int j= i+1; j < A.getNumberOfRows(); j++){
                if(Math.abs(A.getElementAt(j, i)) > Math.abs(A.getElementAt(pivot, i))) pivot = j;
            }
            A.swapRows(i, pivot);
            for(int j = i+1; j < A.getNumberOfRows(); j++){
                A.setElementAt(j, i, A.getElementAt(j, i) / A.getElementAt(i, i));
                for(int k = i+1; k < A.getNumberOfRows(); k++){
                    A.setElementAt(j, k, A.getElementAt(j, k) - A.getElementAt(j, i) * A.getElementAt(i, k));
                }
            }
        }
        return A;
    }
}
