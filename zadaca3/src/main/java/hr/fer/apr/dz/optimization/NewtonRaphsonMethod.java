package hr.fer.apr.dz.optimization;

import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.SquareMatrix;
import hr.fer.apr.lu.matrix.Vector;

import java.util.function.Function;

import static hr.fer.apr.lu.TaskSolver.inverseOfMatrix;

public class NewtonRaphsonMethod {

    private NewtonRaphsonMethod() {}

    public static Vector of(Function<Vector, Double> function, Function<Vector, Vector> gradient, Function<Vector, SquareMatrix> hesseMatrix, Vector startingPoint, double epsilon) {
        Vector nextPoint = startingPoint;
        while (true) {
            Vector point = nextPoint;
            SquareMatrix H = hesseMatrix.apply(point);
            Vector result = new Vector(Matrix.matrixMultiplication(inverseOfMatrix(H), gradient.apply(point)));
            nextPoint.setElementAt(0, point.getElementAt(0) - result.getElementAt(0));
            nextPoint.setElementAt(1, point.getElementAt(1) - result.getElementAt(1));
            if (result.getEuclideanNorm() < epsilon) return point;
        }
    }
}
