package hr.fer.apr.lu.demo1;

import hr.fer.apr.lu.TaskSolver;
import hr.fer.apr.lu.matrix.SquareMatrix;

public class Zadatak8 {
    public static void main(String[] args) {
        SquareMatrix A = new SquareMatrix("A8.txt");
        System.out.println(TaskSolver.inverseOfMatrix(A));
    }
}
