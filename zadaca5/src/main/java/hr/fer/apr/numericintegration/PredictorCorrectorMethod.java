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
        Vector x = new Vector(x0);
        int numberOfIteration = 1;
        x1 = new double[(int) (tMax/T)+1];
        x2 = new double[(int) (tMax/T)+1];

        for(double i = T; i <= tMax; i = i + T){
            Vector xApprox = predictor.apply(A, x, B, rt, T, i, i, 0, false);

            for (int j = 0; j < numberOfCorrectorApplies; j++) {
                corrector.getMethodHelper().resetError();
                x = corrector.apply2(A, B, x, xApprox, rt, T, i, i, 1, true);
            }
            if (numberOfIteration++ % numberOfPrintingIteration == 0) {
                methodHelper.getStringBuilder().append(x).append(System.lineSeparator());
                x1[numberOfIteration - 1] = x.getElementAt(0);
                x2[numberOfIteration - 1] = x.getElementAt(1);
            }
        }

        return x;
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
