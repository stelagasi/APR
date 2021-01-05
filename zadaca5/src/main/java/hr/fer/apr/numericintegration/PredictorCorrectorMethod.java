package hr.fer.apr.numericintegration;

import hr.fer.apr.lu.matrix.Matrix;
import hr.fer.apr.lu.matrix.Vector;

public class PredictorCorrectorMethod {
    private final ExplicitMethod predictor;
    private final ImplicitMethod corrector;

    public PredictorCorrectorMethod(ExplicitMethod predictor, ImplicitMethod corrector) {
        this.predictor = predictor;
        this.corrector = corrector;
    }

    public Vector apply(Matrix A, Vector x0, Matrix B, String[] rt, double T, double tMax, int numberOfCorrectorApplies){
        corrector.transformToExplicit(A, B, T);

        Vector x = new Vector(x0);

        for (double i = T; i <= tMax; i = i + T) {
            x = predictor.apply(A, x, B, rt, T, i);

            for (int j = 0; j < numberOfCorrectorApplies; j++) {
                x = corrector.apply(x, rt, T, i);
            }
        }
        return x;
    }
}
