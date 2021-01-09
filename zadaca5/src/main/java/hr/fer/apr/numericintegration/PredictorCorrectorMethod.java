package hr.fer.apr.numericintegration;

import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.Vector;

public class PredictorCorrectorMethod {
    private final Predictor predictor;
    private final ImplicitMethod corrector;
    private final MethodHelper methodHelper;

    public PredictorCorrectorMethod(Predictor predictor, ImplicitMethod corrector, MethodHelper methodHelper) {
        this.predictor = predictor;
        this.corrector = corrector;
        this.methodHelper = methodHelper;
    }

    public Vector apply(Matrix A, Vector x0, Matrix B, String[] rt, double T, double tMax, int numberOfCorrectorApplies, boolean calculateError){
        Vector xCurrent = new Vector(x0);
        Vector xNext = new Vector(x0);

        for(double i = T; i <= tMax; i += T){
            Vector xApprox = predictor.predict(A, xCurrent, B, rt, T, i);

            for (int j = 0; j < numberOfCorrectorApplies; j++) {
                xNext = corrector.correct(A, B, xCurrent, xApprox, rt, T, i);
                xApprox = xNext;
            }
            xCurrent = xNext;

            methodHelper.addToHistory(xCurrent);

            if(calculateError) methodHelper.calculateError(xCurrent, i);

        }

        return xCurrent;
    }

    public MethodHelper getMethodHelper() {
        return methodHelper;
    }
}
