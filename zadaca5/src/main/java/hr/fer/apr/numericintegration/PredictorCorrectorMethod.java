package hr.fer.apr.numericintegration;

import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.Vector;

public class PredictorCorrectorMethod {
    private final ExplicitMethod predictor;
    private final ImplicitMethod corrector;
    private final MethodHelper methodHelper;
    private double[] x1;
    private double[] x2;

    public PredictorCorrectorMethod(ExplicitMethod predictor, ImplicitMethod corrector, MethodHelper methodHelper) {
        this.predictor = predictor;
        this.corrector = corrector;
        this.methodHelper = methodHelper;
    }

    public Vector apply(Matrix A, Vector x0, Matrix B, String[] rt, double T, double tMax, int numberOfCorrectorApplies, int numberOfPrintingIteration, boolean calculateError){
        Vector xCurrent = new Vector(x0);
        Vector xNext = new Vector(x0);
        int numberOfIteration = 0;
        x1 = new double[(int) (tMax/T)];
        x2 = new double[(int) (tMax/T)];

        for(double i = T; i <= tMax; i = i + T){
            Vector xApprox = predictor.apply(A, xCurrent, B, rt, T, i, i, 0, false);

            for (int j = 0; j < numberOfCorrectorApplies; j++) {
                xNext = corrector.correct(A, B, xCurrent, xApprox, rt, T, i);
                xApprox = xNext;
            }
            xCurrent = xNext;
            if(calculateError) methodHelper.calculateError(xCurrent, i);
            if (numberOfIteration++ % numberOfPrintingIteration == 0) {
                methodHelper.getStringBuilder().append(xCurrent).append(System.lineSeparator());
                x1[numberOfIteration - 1] = xCurrent.getElementAt(0);
                x2[numberOfIteration - 1] = xCurrent.getElementAt(1);
            }
        }

        return xCurrent;
    }

    public MethodHelper getMethodHelper() {
        return methodHelper;
    }

    public ImplicitMethod getCorrector() {
        return corrector;
    }

    public double[] getX1() {
        return x1;
    }

    public double[] getX2() {
        return x2;
    }
}
