package hr.fer.apr.lu;

import hr.fer.apr.lu.matrix.SquareMatrix;
import hr.fer.apr.lu.matrix.Vector;

public class TaskSolver {

    private TaskSolver() {
    }

    public static Vector solveWithLU(SquareMatrix A, Vector b) {
        LUDecomposition(A);
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

    public static SquareMatrix inverseOfMatrix(SquareMatrix matrix) {
        double[] elements = new double[matrix.getNumberOfRows()];
        for (int i = 0; i < matrix.getNumberOfRows(); i++) {
            elements[i] = i;
        }
        Vector P = new Vector(matrix.getNumberOfRows(), elements);
        LUPDecomposition(matrix, P);
        SquareMatrix result = new SquareMatrix(matrix.getNumberOfRows(), new double[matrix.getNumberOfRows() * matrix.getNumberOfRows()]);
        for (int i = 0; i < matrix.getNumberOfRows(); i++) {
            double[] e = new double[matrix.getNumberOfRows()];
            for (int j = 0; j < e.length; j++) {
                if (P.getElementAt(j) == i) {
                    e[j] = 1;
                    break;
                }
            }
            Vector y = forwardSubstitution(matrix.getL(), new Vector(e.length, e));
            Vector x = backSubstitution(matrix.getU(), y);
            for (int j = 0; j < x.getNumberOfRows(); j++) {
                result.setElementAt(j, i, x.getElementAt(j));
            }
        }
        return result;
    }

    public static double determinantOfMatrix(SquareMatrix matrix) {
        double[] elements = new double[matrix.getNumberOfRows()];
        for (int i = 0; i < matrix.getNumberOfRows(); i++) {
            elements[i] = i;
        }
        Vector P = new Vector(matrix.getNumberOfRows(), elements);
        LUPDecomposition(matrix, P);
        int numberOfPermutations = 0;
        for (int i = 0; i < P.getNumberOfRows(); i++) {
            if (P.getElementAt(i) != i) numberOfPermutations++;
        }
        double l = 1;
        double u = 1;
        for (int i = 0; i < matrix.getNumberOfRows(); i++) {
            l *= matrix.getL().getElementAt(i, i);
            u *= matrix.getU().getElementAt(i, i);
        }
        double determinant = numberOfPermutations % 2 == 1 ? 1 : -1;
        determinant *= l * u;
        return determinant;
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
            if (Math.abs(A.getElementAt(i, i)) < 1e-6)
                throw new ArithmeticException(String.format("Division by %.20f", A.getElementAt(i, i)));
            x.setElementAt(i, x.getElementAt(i) / A.getElementAt(i, i));
        }
        return x;
    }

    private static void LUDecomposition(SquareMatrix A) {
        for (int i = 0; i < A.getNumberOfRows() - 1; i++) {
            for (int j = i + 1; j < A.getNumberOfRows(); j++) {
                if (Math.abs(A.getElementAt(i, i)) < 1e-6) throw new ArithmeticException("Division by zero.");
                A.setElementAt(j, i, A.getElementAt(j, i) / A.getElementAt(i, i));
                for (int k = i + 1; k < A.getNumberOfRows(); k++) {
                    A.setElementAt(j, k, A.getElementAt(j, k) - A.getElementAt(j, i) * A.getElementAt(i, k));
                }
            }
        }
    }

    private static void LUPDecomposition(SquareMatrix A, Vector b) {
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
    }
}
