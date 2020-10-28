package hr.fer.apr.lu.demo1;

import hr.fer.apr.lu.matrix.SquareMatrix;
import hr.fer.apr.lu.matrix.Vector;

public class Zadatak6 {
    public static void main(String[] args) {
        SquareMatrix A = new SquareMatrix("A6.txt");
        Vector b = new Vector("b6.txt");
       // System.out.println(TaskSolver.solveWithLUP(A, b));
    }
}
