package hr.fer.apr.numericintegration.demo;

import hr.fer.apr.lu.matrix.SquareMatrix;
import hr.fer.apr.lu.matrix.Vector;
import hr.fer.apr.numericintegration.*;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

public class Task2 {
    public static void main(String[] args) {
        SquareMatrix A = new SquareMatrix("C:\\FER\\APR\\zadaca5\\data\\A2.txt");
        Vector x0 = new Vector("C:\\FER\\APR\\zadaca5\\data\\x02.txt");
        SquareMatrix B = new SquareMatrix(A.getNumberOfRows(), new double[A.getNumberOfRows()*A.getNumberOfColumns()]);

        double T = 0.1, tMax = 1;

        RungeKuttaMethod rungeKuttaMethod = new RungeKuttaMethod(new MethodHelper(null));
        rungeKuttaMethod.apply(A, x0, B, null, T, tMax,  false);
        XYChart chart1 = new XYChartBuilder().width(800).height(600).title("Runge-Kutta").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart1.addSeries("x1", rungeKuttaMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(0)).toArray());
        chart1.addSeries("x2", rungeKuttaMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(1)).toArray());
        new SwingWrapper(chart1).displayChart();

        TrapezoidalMethod trapezoidalMethod = new TrapezoidalMethod(new MethodHelper(null));
        trapezoidalMethod.transformToExplicit(A, B, T);
        trapezoidalMethod.apply(x0, null, T, tMax, false);
        XYChart chart2 = new XYChartBuilder().width(800).height(600).title("Trapezni").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart2.addSeries("x1", trapezoidalMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(0)).toArray());
        chart2.addSeries("x2", trapezoidalMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(1)).toArray());
        new SwingWrapper(chart2).displayChart();

        EulerMethod eulerMethod = new EulerMethod(new MethodHelper(null));
        eulerMethod.apply(A, x0, B, null, T, tMax, false);
        XYChart chart3 = new XYChartBuilder().width(800).height(600).title("Euler").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart3.addSeries("x1", eulerMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(0)).toArray());
        chart3.addSeries("x2", eulerMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(1)).toArray());
        new SwingWrapper(chart3).displayChart();

        BackwardEulerMethod backwardEulerMethod = new BackwardEulerMethod(new MethodHelper(null));
        backwardEulerMethod.transformToExplicit(A, B, T);
        backwardEulerMethod.apply(x0, null, T, tMax, false);
        XYChart chart4 = new XYChartBuilder().width(800).height(600).title("Obrnuti Euler").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart4.addSeries("x1", backwardEulerMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(0)).toArray());
        chart4.addSeries("x2", backwardEulerMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(1)).toArray());
        new SwingWrapper(chart4).displayChart();

        PredictorCorrectorMethod predictorCorrectorMethod = new PredictorCorrectorMethod(new EulerMethod(new MethodHelper(null)), new BackwardEulerMethod(new MethodHelper(null)), new MethodHelper(null));
        predictorCorrectorMethod.apply(A, x0, B, null, T, tMax, 2, false);
        XYChart chart5 = new XYChartBuilder().width(800).height(600).title("PE(CE)^2 - Euler, obrnuti Euler").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart5.addSeries("x1", predictorCorrectorMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(0)).toArray());
        chart5.addSeries("x2", predictorCorrectorMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(1)).toArray());
        new SwingWrapper(chart5).displayChart();

        PredictorCorrectorMethod predictorCorrectorMethod2 = new PredictorCorrectorMethod(new EulerMethod(new MethodHelper(null)), new TrapezoidalMethod(new MethodHelper(null)), new MethodHelper(null));
        predictorCorrectorMethod2.apply(A, x0, B, null, T, tMax, 1, false);
        XYChart chart6 = new XYChartBuilder().width(800).height(600).title("PECE - Euler, trapezni").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart6.addSeries("x1", predictorCorrectorMethod2.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(0)).toArray());
        chart6.addSeries("x2", predictorCorrectorMethod2.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(1)).toArray());
        new SwingWrapper(chart6).displayChart();
    }
}
