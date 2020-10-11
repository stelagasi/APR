package hr.fer.apr.lu;

import hr.fer.apr.lu.matrix.SquareMatrix;
import hr.fer.apr.lu.matrix.Vector;

public abstract class EquationSolver {
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

    public static Vector LUDecomposition() {
        return null;
    }

    public static Vector LUPDecomposition() {
        return null;
    }

}
