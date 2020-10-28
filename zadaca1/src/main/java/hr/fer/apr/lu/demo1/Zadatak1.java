package hr.fer.apr.lu.demo1;

import hr.fer.apr.lu.matrix.SquareMatrix;

public class Zadatak1 {
    public static void main(String[] args) {
        SquareMatrix A = new SquareMatrix("A2.txt");
        System.out.println(A.getElementAt(0, 0) * 16.4);
        System.out.println(A.getElementAt(0, 0) / 17);
    }
}
