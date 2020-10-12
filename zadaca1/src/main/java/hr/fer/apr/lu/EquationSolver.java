package hr.fer.apr.lu;

import hr.fer.apr.lu.matrix.SquareMatrix;
import hr.fer.apr.lu.matrix.Vector;

public class EquationSolver {

    private EquationSolver() {
    }

    public static Vector solveWithLU(SquareMatrix A, Vector b) {
        A = LUDecomposition(A);
        Vector y = forwardSubstitution(A.getL(), b);
        Vector x = backSubstitution(A.getU(), y);
        return x;
    }

    public static Vector solveWithLUP(SquareMatrix A, Vector b) {
        LUPDecomposition(A, b);
        Vector y = forwardSubstitution(A.getL(), b);
        Vector x = backSubstitution(A.getU(), y);
        return x;
    }

    private static Vector forwardSubstitution(SquareMatrix A, Vector b) {
        if (A.getNumberOfRows() != b.getNumberOfRows()) throw new IllegalArgumentException();
        Vector y = new Vector(b);
        for (int i = 0; i < b.getNumberOfRows() - 1; i++) {
            for (int j = i + 1; j < b.getNumberOfRows(); j++) {
                y.setElementAt(j, y.getElementAt(j) - A.getElementAt(j, i) * y.getElementAt(i));
            }
        }
        return y;
    }

    private static Vector backSubstitution(SquareMatrix A, Vector y) {
        if (A.getNumberOfRows() != y.getNumberOfRows()) throw new IllegalArgumentException();
        Vector x = new Vector(y);
        for (int i = x.getNumberOfRows() - 1; i >= 0; i--) {
            x.setElementAt(i, y.getElementAt(i));
            for (int j = x.getNumberOfRows() - 1; j > i; j--) {
                x.setElementAt(i, x.getElementAt(i) - A.getElementAt(i, j) * x.getElementAt(j));
            }
            if (Math.abs(A.getElementAt(i, i)) < 1e-6) throw new ArithmeticException("Division by zero.");
            x.setElementAt(i, x.getElementAt(i) / A.getElementAt(i, i));
        }
        return x;
    }

    //todo jel hocu resultMatrix ili samo A
    private static SquareMatrix LUDecomposition(SquareMatrix A) {
        SquareMatrix resultMatrix = new SquareMatrix(A);
        for (int i = 0; i < A.getNumberOfRows() - 1; i++) {
            for (int j = i + 1; j < A.getNumberOfRows(); j++) {
                if (Math.abs(A.getElementAt(i, i)) < 1e-6) throw new ArithmeticException("Division by zero.");
                A.setElementAt(j, i, A.getElementAt(j, i) / A.getElementAt(i, i));
                for (int k = i + 1; k < A.getNumberOfRows(); k++) {
                    A.setElementAt(j, k, A.getElementAt(j, k) - A.getElementAt(j, i) * A.getElementAt(i, k));
                }
            }
        }
        return resultMatrix;
    }

    private static SquareMatrix LUPDecomposition(SquareMatrix A, Vector b) {
        for (int i = 0; i < A.getNumberOfRows() - 1; i++) {
            int pivot = i;
            for (int j = i + 1; j < A.getNumberOfRows(); j++) {
                if (Math.abs(A.getElementAt(j, i)) > Math.abs(A.getElementAt(pivot, i))) pivot = j;
            }
            A.swapRows(i, pivot);
            b.swapRows(i, pivot);
            for (int j = i + 1; j < A.getNumberOfRows(); j++) {
                if (Math.abs(A.getElementAt(i, i)) < 1e-6) throw new ArithmeticException("Division by zero.");
                A.setElementAt(j, i, A.getElementAt(j, i) / A.getElementAt(i, i));
                for (int k = i + 1; k < A.getNumberOfRows(); k++) {
                    A.setElementAt(j, k, A.getElementAt(j, k) - A.getElementAt(j, i) * A.getElementAt(i, k));
                }
            }
        }
        return A;
    }
}
