package hr.fer.apr.numericintegration.demo;

import hr.fer.apr.lu.matrix.SquareMatrix;
import hr.fer.apr.lu.matrix.Vector;
import hr.fer.apr.numericintegration.MethodHelper;
import hr.fer.apr.numericintegration.RungeKuttaMethod;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

public class Task2a {
    public static void main(String[] args) {
        SquareMatrix A = new SquareMatrix("C:\\FER\\APR\\zadaca5\\data\\A2.txt");
        Vector x0 = new Vector("C:\\FER\\APR\\zadaca5\\data\\x02.txt");
        SquareMatrix B = new SquareMatrix(A.getNumberOfRows(), new double[A.getNumberOfRows()*A.getNumberOfColumns()]);

        double tMax = 1;

        for(double T = 0.01; T <= 0.1; T += 0.01){
            RungeKuttaMethod rungeKuttaMethod = new RungeKuttaMethod(new MethodHelper(null));
            rungeKuttaMethod.apply(A, x0, B, null, T, tMax,  false);
            XYChart chart1 = new XYChartBuilder().width(800).height(600).title("" + T).xAxisTitle("t").yAxisTitle("Varijabla stanja").build();
            chart1.addSeries("x1", rungeKuttaMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(0)).toArray());
            chart1.addSeries("x2", rungeKuttaMethod.getMethodHelper().getHistory().stream().mapToDouble(e -> e.getElementAt(1)).toArray());
            new SwingWrapper(chart1).displayChart();
        }
    }
}
