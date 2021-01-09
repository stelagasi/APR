package hr.fer.apr.numericintegration.demo;

import hr.fer.apr.lu.matrix.SquareMatrix;
import hr.fer.apr.lu.matrix.Vector;
import hr.fer.apr.numericintegration.*;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

public class Task4 {
    public static void main(String[] args) {
        SquareMatrix A = new SquareMatrix("C:\\FER\\APR\\zadaca5\\data\\A4.txt");
        Vector x0 = new Vector("C:\\FER\\APR\\zadaca5\\data\\x04.txt");
        SquareMatrix B = new SquareMatrix("C:\\FER\\APR\\zadaca5\\data\\B4.txt");
        String[] rt = new String[]{"t", "t"};

        double T = 0.01, tMax = 1;

        RungeKuttaMethod rungeKuttaMethod = new RungeKuttaMethod(new MethodHelper(null));
        rungeKuttaMethod.apply(A, x0, B, rt, T, T, tMax, 1, false);
        XYChart chart1 = new XYChartBuilder().width(800).height(600).title("Runge-Kutta").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart1.addSeries("x1", rungeKuttaMethod.getX1());
        chart1.addSeries("x2", rungeKuttaMethod.getX2());
        new SwingWrapper(chart1).displayChart();

        TrapezoidalMethod trapezoidalMethod = new TrapezoidalMethod(new MethodHelper(null));
        trapezoidalMethod.transformToExplicit(A, B, T);
        trapezoidalMethod.apply(x0, rt, T, T, tMax, 1, false);
        XYChart chart2 = new XYChartBuilder().width(800).height(600).title("Trapezni").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart2.addSeries("x1", trapezoidalMethod.getX1());
        chart2.addSeries("x2", trapezoidalMethod.getX2());
        new SwingWrapper(chart2).displayChart();

        EulerMethod eulerMethod = new EulerMethod(new MethodHelper(null));
        eulerMethod.apply(A, x0, B, rt, T, T, tMax, 1, false);
        XYChart chart3 = new XYChartBuilder().width(800).height(600).title("Euler").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart3.addSeries("x1", eulerMethod.getX1());
        chart3.addSeries("x2", eulerMethod.getX2());
        new SwingWrapper(chart3).displayChart();

        BackwardEulerMethod backwardEulerMethod = new BackwardEulerMethod(new MethodHelper(null));
        backwardEulerMethod.transformToExplicit(A, B, T);
        backwardEulerMethod.apply(x0, rt, T, T, tMax, 1, false);
        XYChart chart4 = new XYChartBuilder().width(800).height(600).title("Obrnuti Euler").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart4.addSeries("x1", backwardEulerMethod.getX1());
        chart4.addSeries("x2", backwardEulerMethod.getX2());
        new SwingWrapper(chart4).displayChart();

        PredictorCorrectorMethod predictorCorrectorMethod = new PredictorCorrectorMethod(new EulerMethod(new MethodHelper(null)), new BackwardEulerMethod(new MethodHelper(null)), new MethodHelper(null));
        predictorCorrectorMethod.apply(A, x0, B, rt, T, tMax, 2, 1, false);
        XYChart chart5 = new XYChartBuilder().width(800).height(600).title("PE(CE)^2 - Euler, obrnuti Euler").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart5.addSeries("x1", predictorCorrectorMethod.getX1());
        chart5.addSeries("x2", predictorCorrectorMethod.getX2());
        new SwingWrapper(chart5).displayChart();

        PredictorCorrectorMethod predictorCorrectorMethod2 = new PredictorCorrectorMethod(new EulerMethod(new MethodHelper(null)), new TrapezoidalMethod(new MethodHelper(null)), new MethodHelper(null));
        predictorCorrectorMethod2.apply(A, x0, B, rt, T, tMax, 1, 1, false);
        XYChart chart6 = new XYChartBuilder().width(800).height(600).title("PECE - Euler, trapezni").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart6.addSeries("x1", predictorCorrectorMethod2.getX1());
        chart6.addSeries("x2", predictorCorrectorMethod2.getX2());
        new SwingWrapper(chart6).displayChart();
    }
}
