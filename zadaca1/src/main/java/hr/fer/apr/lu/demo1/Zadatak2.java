package hr.fer.apr.lu.demo1;

import hr.fer.apr.lu.TaskSolver;
import hr.fer.apr.lu.matrix.SquareMatrix;
import hr.fer.apr.lu.matrix.Vector;

public class Zadatak2 {
    public static void main(String[] args) {
        SquareMatrix A = new SquareMatrix("A2.txt");
        Vector b = new Vector("b2.txt");
        System.out.println(TaskSolver.solveWithLU(A, b));
        //System.out.println(TaskSolver.solveWithLUP(A, b));
    }
}
