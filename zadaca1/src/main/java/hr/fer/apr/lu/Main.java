package hr.fer.apr.lu;

public class Main {

    public static void main(String[] args) {
        Matrix A("A.txt"), B("B.txt"), C;
        C = ~A;
        C += A * 0.5 * B * (A – 2 * B);
        double x = C[0][0];
        C[1][1] = x;
    }
}
