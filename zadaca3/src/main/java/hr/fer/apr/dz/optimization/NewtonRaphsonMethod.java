package hr.fer.apr.dz.optimization;

import hr.fer.apr.dz.optimization.functions.FunctionGenerator;
import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.SquareMatrix;
import hr.fer.apr.lu.matrix.Vector;
import hr.fer.apr.optimization.GoldenSectionSearch;

import java.util.function.Function;

import static hr.fer.apr.lu.TaskSolver.inverseOfMatrix;

public class NewtonRaphsonMethod {

    private NewtonRaphsonMethod() {}

    public static Vector of(Function<Vector, Vector> gradient, Function<Vector, SquareMatrix> hesseMatrix, Vector startingPoint, double epsilon) {
        Vector nextPoint = startingPoint;
        for (int i = 0; i < 10000; i++) {
            Vector point = nextPoint;
            SquareMatrix H = hesseMatrix.apply(point);
            Vector result = new Vector(Matrix.matrixMultiplication(inverseOfMatrix(H), gradient.apply(point)));
            if (result.getEuclideanNorm() < epsilon) return point;
            nextPoint = Vector.subtraction(point, result);
        }
        return nextPoint;
    }

    public static Vector withGoldenSection(Function<Vector, Double> function, Function<Vector, Vector> gradient, Function<Vector, SquareMatrix> hesseMatrix, Vector startingPoint, double epsilon){
        Vector nextPoint = startingPoint;

        for (int i = 0; i < 10000; i++) {
            Vector point = nextPoint;
            SquareMatrix H = hesseMatrix.apply(point);
            Vector result = new Vector(Matrix.matrixMultiplication(inverseOfMatrix(H), gradient.apply(point)));

            if(result.getEuclideanNorm() < epsilon) return nextPoint;

            nextPoint = GoldenSectionSearch.ofDirection(function, result, point, epsilon, 1);
        }
        return nextPoint;
    }
}
