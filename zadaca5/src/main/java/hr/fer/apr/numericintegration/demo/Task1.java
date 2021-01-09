package hr.fer.apr.numericintegration.demo;

import hr.fer.apr.lu.matrix.SquareMatrix;
import hr.fer.apr.lu.matrix.Vector;
import hr.fer.apr.numericintegration.*;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.function.Function;

import static java.lang.Math.*;

public class Task1 {
    public static void main(String[] args) {
        Function<Double, Vector> function = t -> {
            double x1 = cos(t) + sin(t);
            double x2 = cos(t) - sin(t);
            return new Vector(2, new double[]{x1, x2});
        };

        SquareMatrix A = new SquareMatrix("C:\\FER\\APR\\zadaca5\\data\\A1.txt");
        Vector x0 = new Vector("C:\\FER\\APR\\zadaca5\\data\\x01.txt");
        SquareMatrix B = new SquareMatrix(A.getNumberOfRows(), new double[A.getNumberOfRows()*A.getNumberOfColumns()]);

        double T = 0.01, tMax = 10;

        RungeKuttaMethod rungeKuttaMethod = new RungeKuttaMethod(new MethodHelper(function));
        rungeKuttaMethod.apply(A, x0, B, null, T, tMax, 1, true);
        System.out.println("Runge-Kutta: ");
        System.out.println(rungeKuttaMethod.getMethodHelper().getError());
        XYChart chart1 = new XYChartBuilder().width(800).height(600).title("Runge-Kutta").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart1.addSeries("x1", rungeKuttaMethod.getX1());
        chart1.addSeries("x2", rungeKuttaMethod.getX2());
        new SwingWrapper(chart1).displayChart();

        TrapezoidalMethod trapezoidalMethod = new TrapezoidalMethod(new MethodHelper(function));
        trapezoidalMethod.transformToExplicit(A, B, T);
        trapezoidalMethod.apply(x0, null, T, tMax, 1, true);
        System.out.println("Trapezni: ");
        System.out.println(trapezoidalMethod.getMethodHelper().getError());
        XYChart chart2 = new XYChartBuilder().width(800).height(600).title("Trapezni").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart2.addSeries("x1", trapezoidalMethod.getX1());
        chart2.addSeries("x2", trapezoidalMethod.getX2());
        new SwingWrapper(chart2).displayChart();

        EulerMethod eulerMethod = new EulerMethod(new MethodHelper(function));
        eulerMethod.apply(A, x0, B, null, T, tMax, 1, true);
        System.out.println("Euler: ");
        System.out.println(eulerMethod.getMethodHelper().getError());
        XYChart chart3 = new XYChartBuilder().width(800).height(600).title("Euler").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart3.addSeries("x1", eulerMethod.getX1());
        chart3.addSeries("x2", eulerMethod.getX2());
        new SwingWrapper(chart3).displayChart();

        BackwardEulerMethod backwardEulerMethod = new BackwardEulerMethod(new MethodHelper(function));
        backwardEulerMethod.transformToExplicit(A, B, T);
        backwardEulerMethod.apply(x0, null, T, tMax, 1, true);
        System.out.println("Obrnuti Euler: ");
        System.out.println(backwardEulerMethod.getMethodHelper().getError());
        XYChart chart4 = new XYChartBuilder().width(800).height(600).title("Obrnuti Euler").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart4.addSeries("x1", backwardEulerMethod.getX1());
        chart4.addSeries("x2", backwardEulerMethod.getX2());
        new SwingWrapper(chart4).displayChart();

        PredictorCorrectorMethod predictorCorrectorMethod = new PredictorCorrectorMethod(new EulerMethod(new MethodHelper(function)), new BackwardEulerMethod(new MethodHelper(function)), new MethodHelper(function));
        predictorCorrectorMethod.apply(A, x0, B, null, T, tMax, 2, 1, true);
        System.out.println("PE(CE)^2 - Euler, obrnuti Euler:");
        System.out.println(predictorCorrectorMethod.getMethodHelper().getError());
        XYChart chart5 = new XYChartBuilder().width(800).height(600).title("PE(CE)^2 - Euler, obrnuti Euler").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart5.addSeries("x1", predictorCorrectorMethod.getX1());
        chart5.addSeries("x2", predictorCorrectorMethod.getX2());
        new SwingWrapper(chart5).displayChart();

        PredictorCorrectorMethod predictorCorrectorMethod2 = new PredictorCorrectorMethod(new EulerMethod(new MethodHelper(function)), new TrapezoidalMethod(new MethodHelper(function)), new MethodHelper(function));
        predictorCorrectorMethod2.apply(A, x0, B, null, T, tMax, 2, 1, true);
        System.out.println("PE(CE)^2 - Euler, obrnuti Euler:");
        System.out.println(predictorCorrectorMethod2.getMethodHelper().getError());
        XYChart chart6 = new XYChartBuilder().width(800).height(600).title("PECE - Euler, trapezni").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart6.addSeries("x1", predictorCorrectorMethod2.getX1());
        chart6.addSeries("x2", predictorCorrectorMethod2.getX2());
        new SwingWrapper(chart6).displayChart();
    }
}
