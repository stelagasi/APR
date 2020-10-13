package hr.fer.apr.lu;

import hr.fer.apr.lu.matrix.SquareMatrix;
import hr.fer.apr.lu.matrix.Vector;

public class Main {

    public static void main(String[] args) {
        //todo što se očekuje iz prvog zadatka? -> napravi ga

//        System.out.println("2. zadatak:");
//        SquareMatrix A2 = new SquareMatrix("A2.txt");
//        Vector b2 = new Vector("b2.txt");
//        //System.out.println(EquationSolver.solveWithLU(A2, b2));
//        System.out.println(EquationSolver.solveWithLUP(A2, b2));
//
//        System.out.println("3. zadatak:");
//        SquareMatrix A3 = new SquareMatrix("A3.txt");
        //System.out.println(EquationSolver.solveWithLU(A3, b2));
        //System.out.println(EquationSolver.solveWithLUP(A3, b2));

//        System.out.println("4. zadatak:");
//        SquareMatrix A4 = new SquareMatrix("A4.txt");
//        SquareMatrix A42 = new SquareMatrix("A4.txt");
//        Vector b4 = new Vector("b4.txt");
//        System.out.println(EquationSolver.solveWithLU(A4, b4));
//        System.out.println(EquationSolver.solveWithLUP(A42, b4));

        //todo
//        System.out.println("5. zadatak");
//        SquareMatrix A5 = new SquareMatrix("A5.txt");
//        Vector b5 = new Vector("b5.txt");
//      //  System.out.println(EquationSolver.solveWithLU(A5, b5));
//        System.out.println(EquationSolver.solveWithLUP(A5, b5));

        System.out.println("6. zadatak");
        SquareMatrix A6 = new SquareMatrix("A6.txt");
        Vector b6 = new Vector("b6.txt");
        System.out.println(TaskSolver.solveWithLUP(A6, b6));

//        System.out.println("7. zadatak");
//        SquareMatrix A7 = new SquareMatrix("A3.txt");
//        System.out.println(EquationSolver.inverseOfMatrix(A7));

        System.out.println("8. zadatak");
        SquareMatrix A8 = new SquareMatrix("A8.txt");
        System.out.println(TaskSolver.inverseOfMatrix(A8));

        System.out.println("9. zadatak");
        SquareMatrix A9 = new SquareMatrix("A8.txt");
        System.out.println(TaskSolver.determinantOfMatrix(A9));

        System.out.println("\n10. zadatak");
        SquareMatrix A10 = new SquareMatrix("A2.txt");
        System.out.println(TaskSolver.determinantOfMatrix(A10));

    }
}
