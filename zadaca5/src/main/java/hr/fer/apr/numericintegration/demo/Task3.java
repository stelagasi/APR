package hr.fer.apr.numericintegration.demo;

import hr.fer.apr.lu.matrix.SquareMatrix;
import hr.fer.apr.lu.matrix.Vector;
import hr.fer.apr.numericintegration.*;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

public class Task3 {
    public static void main(String[] args) {
        SquareMatrix A = new SquareMatrix("C:\\FER\\APR\\zadaca5\\data\\A3.txt");
        Vector x0 = new Vector("C:\\FER\\APR\\zadaca5\\data\\x03.txt");
        SquareMatrix B = new SquareMatrix("C:\\FER\\APR\\zadaca5\\data\\B3.txt");
        String[] rt = new String[]{"1", "1"};

        double T = 0.01, tMax = 10;
        double[] xData = new double[(int) (tMax/T)];
        for (int i = 1; i < xData.length; i++) {
            xData[i] = xData[i-1] + T;
        }

        RungeKuttaMethod rungeKuttaMethod = new RungeKuttaMethod(new MethodHelper(null));
        rungeKuttaMethod.apply(A, x0, B, rt, T, tMax, false);
        XYChart chart1 = new XYChartBuilder().width(800).height(600).title("Runge-Kutta").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart1.addSeries("x1", xData, rungeKuttaMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(0)).toArray());
        chart1.addSeries("x2", xData, rungeKuttaMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(1)).toArray());
        new SwingWrapper(chart1).displayChart();

        TrapezoidalMethod trapezoidalMethod = new TrapezoidalMethod(new MethodHelper(null));
        trapezoidalMethod.transformToExplicit(A, B, T);
        trapezoidalMethod.apply(x0, rt, T, tMax,  false);
        XYChart chart2 = new XYChartBuilder().width(800).height(600).title("Trapezni").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart2.addSeries("x1", xData, trapezoidalMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(0)).toArray());
        chart2.addSeries("x2", xData, trapezoidalMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(1)).toArray());
        new SwingWrapper(chart2).displayChart();

        EulerMethod eulerMethod = new EulerMethod(new MethodHelper(null));
        eulerMethod.apply(A, x0, B, rt, T, tMax, false);
        XYChart chart3 = new XYChartBuilder().width(800).height(600).title("Euler").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart3.addSeries("x1", xData, eulerMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(0)).toArray());
        chart3.addSeries("x2", xData, eulerMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(1)).toArray());
        new SwingWrapper(chart3).displayChart();

        BackwardEulerMethod backwardEulerMethod = new BackwardEulerMethod(new MethodHelper(null));
        backwardEulerMethod.transformToExplicit(A, B, T);
        backwardEulerMethod.apply(x0, rt, T, tMax, false);
        XYChart chart4 = new XYChartBuilder().width(800).height(600).title("Obrnuti Euler").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart4.addSeries("x1", xData, backwardEulerMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(0)).toArray());
        chart4.addSeries("x2", xData, backwardEulerMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(1)).toArray());
        new SwingWrapper(chart4).displayChart();

        PredictorCorrectorMethod predictorCorrectorMethod = new PredictorCorrectorMethod(new EulerMethod(new MethodHelper(null)), new BackwardEulerMethod(new MethodHelper(null)), new MethodHelper(null));
        predictorCorrectorMethod.apply(A, x0, B, rt, T, tMax, 2, false);
        XYChart chart5 = new XYChartBuilder().width(800).height(600).title("PE(CE)^2 - Euler, obrnuti Euler").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart5.addSeries("x1", xData, predictorCorrectorMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(0)).toArray());
        chart5.addSeries("x2", xData, predictorCorrectorMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(1)).toArray());
        new SwingWrapper(chart5).displayChart();

        PredictorCorrectorMethod predictorCorrectorMethod2 = new PredictorCorrectorMethod(new EulerMethod(new MethodHelper(null)), new TrapezoidalMethod(new MethodHelper(null)), new MethodHelper(null));
        predictorCorrectorMethod2.apply(A, x0, B, rt, T, tMax, 1, false);
        XYChart chart6 = new XYChartBuilder().width(800).height(600).title("PECE - Euler, trapezni").xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
        chart6.addSeries("x1", xData, predictorCorrectorMethod2.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(0)).toArray());
        chart6.addSeries("x2", xData, predictorCorrectorMethod2.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(1)).toArray());
        new SwingWrapper(chart6).displayChart();
    }
}
