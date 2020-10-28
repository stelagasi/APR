package hr.fer.apr.lu.demo1;

import hr.fer.apr.lu.TaskSolver;
import hr.fer.apr.lu.matrix.SquareMatrix;
import hr.fer.apr.lu.matrix.Vector;

public class Zadatak4 {
    public static void main(String[] args) {
        SquareMatrix A = new SquareMatrix("A4.txt");
        SquareMatrix A2 = new SquareMatrix("A4.txt");
        Vector b = new Vector("b4.txt");
        System.out.println(TaskSolver.solveWithLU(A, b));
        System.out.println(TaskSolver.solveWithLUP(A2, b));
    }
}
