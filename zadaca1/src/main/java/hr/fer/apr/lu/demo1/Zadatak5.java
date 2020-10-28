package hr.fer.apr.lu.demo1;

import hr.fer.apr.lu.TaskSolver;
import hr.fer.apr.lu.matrix.SquareMatrix;
import hr.fer.apr.lu.matrix.Vector;

public class Zadatak5 {
    public static void main(String[] args) {
        SquareMatrix A = new SquareMatrix("A5.txt");
        Vector b = new Vector("b5.txt");
        //System.out.println(TaskSolver.solveWithLU(A, b));
        System.out.println(TaskSolver.solveWithLUP(A, b));
    }
}
