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
        while (true) {
            Vector point = nextPoint;
            SquareMatrix H = hesseMatrix.apply(point);
            Vector result = new Vector(Matrix.matrixMultiplication(inverseOfMatrix(H), gradient.apply(point)));
            if (result.getEuclideanNorm() < epsilon) return point;
            nextPoint = Vector.subtraction(point, result);
        }
    }

    public static Vector withGoldenSection(Function<Vector, Double> function, Function<Vector, Vector> gradient, Function<Vector, SquareMatrix> hesseMatrix, Vector startingPoint, double epsilon){
        Vector nextPoint = startingPoint;

        while(true){
            Vector point = nextPoint;
            SquareMatrix H = hesseMatrix.apply(point);
            Vector result = new Vector(Matrix.matrixMultiplication(inverseOfMatrix(H), gradient.apply(point)));
            result = result.multiplicationWithScalar(-1);

            if(result.getEuclideanNorm() < epsilon) return nextPoint;

            Function<Vector, Double> lambdaFunction = FunctionGenerator.getLambdaFunction(function, result, point);
            Vector shift = GoldenSectionSearch.ofDirection(lambdaFunction, result, point, epsilon, 1);
            nextPoint = Vector.addition(point, shift);
        }
    }
}
