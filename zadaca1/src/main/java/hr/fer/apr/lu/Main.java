package hr.fer.apr.lu;

import hr.fer.apr.lu.matrix.Matrix;

public class Main {

    public static void main(String[] args) {
        Matrix A = new Matrix("A.txt");
        Matrix B = new Matrix(A);
        System.out.println("A:");
        System.out.println(A);
        System.out.println("Pridruživanje B = A:");
        System.out.println(B);
        A.writeInFile("C.txt");
        A.setElementAt(0, 0, 10);
        System.out.println("A:");
        System.out.println(A);
        System.out.printf("Na poziciji (0,0) u A je: %f%n%n", A.getElementAt(0, 0));
        Matrix C = new Matrix("B.txt");
        System.out.println("C:");
        System.out.println(C);
        System.out.println("C transponirano:");
        System.out.println(C.transpose());
        System.out.println("2*C:");
        System.out.println(C.multiplicationWithScalar(2));
        System.out.println("A*C:");
        System.out.println(Matrix.matrixMultiplication(A, C));
        System.out.println("A+B:");
        System.out.println(Matrix.addition(A, B));
        System.out.println("A-B:");
        System.out.println(Matrix.subtraction(A, B)); //todo ljepsi primjer napravi
        System.out.println("A += B");
        System.out.println(A.selfAddition(B));
        System.out.println("A -= B");
        System.out.println(A.selfSubtraction(B));
        System.out.println(A.swapRows(1, 2));
    }
}
