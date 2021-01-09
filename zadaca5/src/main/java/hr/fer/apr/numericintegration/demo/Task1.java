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

        double[] xData = new double[(int) (tMax/T)];
        for (int i = 1; i < xData.length; i++) {
            xData[i] = xData[i-1] + T;
        }

        RungeKuttaMethod rungeKuttaMethod = new RungeKuttaMethod(new MethodHelper(function));
        rungeKuttaMethod.apply(A, x0, B, null, T, tMax, true);
        System.out.println("Runge-Kutta: ");
        System.out.println(rungeKuttaMethod.getMethodHelper().getError());
        XYChart chart1 = new XYChartBuilder().width(800).height(600).title("Runge-Kutta").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart1.addSeries("x1", xData, rungeKuttaMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(0)).toArray());
        chart1.addSeries("x2", xData, rungeKuttaMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(1)).toArray());
        new SwingWrapper(chart1).displayChart();

        TrapezoidalMethod trapezoidalMethod = new TrapezoidalMethod(new MethodHelper(function));
        trapezoidalMethod.transformToExplicit(A, B, T);
        trapezoidalMethod.apply(x0, null, T, tMax, true);
        System.out.println("Trapezni: ");
        System.out.println(trapezoidalMethod.getMethodHelper().getError());
        XYChart chart2 = new XYChartBuilder().width(800).height(600).title("Trapezni").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart2.addSeries("x1", xData, trapezoidalMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(0)).toArray());
        chart2.addSeries("x2", xData, trapezoidalMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(1)).toArray());
        new SwingWrapper(chart2).displayChart();

        EulerMethod eulerMethod = new EulerMethod(new MethodHelper(function));
        eulerMethod.apply(A, x0, B, null, T, tMax, true);
        System.out.println("Euler: ");
        System.out.println(eulerMethod.getMethodHelper().getError());
        XYChart chart3 = new XYChartBuilder().width(800).height(600).title("Euler").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart3.addSeries("x1", xData, eulerMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(0)).toArray());
        chart3.addSeries("x2", xData, eulerMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(1)).toArray());
        new SwingWrapper(chart3).displayChart();

        BackwardEulerMethod backwardEulerMethod = new BackwardEulerMethod(new MethodHelper(function));
        backwardEulerMethod.transformToExplicit(A, B, T);
        backwardEulerMethod.apply(x0, null, T, tMax,  true);
        System.out.println("Obrnuti Euler: ");
        System.out.println(backwardEulerMethod.getMethodHelper().getError());
        XYChart chart4 = new XYChartBuilder().width(800).height(600).title("Obrnuti Euler").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart4.addSeries("x1", xData, backwardEulerMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(0)).toArray());
        chart4.addSeries("x2", xData, backwardEulerMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(1)).toArray());
        new SwingWrapper(chart4).displayChart();

        PredictorCorrectorMethod predictorCorrectorMethod = new PredictorCorrectorMethod(new EulerMethod(new MethodHelper(function)), new BackwardEulerMethod(new MethodHelper(function)), new MethodHelper(function));
        predictorCorrectorMethod.apply(A, x0, B, null, 0.01, tMax, 2, true);
        System.out.println("PE(CE)^2 - Euler, obrnuti Euler:");
        System.out.println(predictorCorrectorMethod.getMethodHelper().getError());
        XYChart chart5 = new XYChartBuilder().width(800).height(600).title("PE(CE)^2 - Euler, obrnuti Euler").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart5.addSeries("x1", xData, predictorCorrectorMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(0)).toArray());
        chart5.addSeries("x2", xData, predictorCorrectorMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(1)).toArray());
        new SwingWrapper(chart5).displayChart();

        PredictorCorrectorMethod predictorCorrectorMethod2 = new PredictorCorrectorMethod(new EulerMethod(new MethodHelper(function)), new TrapezoidalMethod(new MethodHelper(function)), new MethodHelper(function));
        predictorCorrectorMethod2.apply(A, x0, B, null, 0.01, tMax, 1, true);
        System.out.println("PECE - Euler, trapezni:");
        System.out.println(predictorCorrectorMethod2.getMethodHelper().getError());
        XYChart chart6 = new XYChartBuilder().width(800).height(600).title("PECE - Euler, trapezni").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart6.addSeries("x1", xData, predictorCorrectorMethod2.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(0)).toArray());
        chart6.addSeries("x2", xData, predictorCorrectorMethod2.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(1)).toArray());
        new SwingWrapper(chart6).displayChart();
    }
}
