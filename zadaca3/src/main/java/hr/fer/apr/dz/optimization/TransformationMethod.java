package hr.fer.apr.dz.optimization;

import hr.fer.apr.dz.optimization.restriction.ExplicitRestriction;
import hr.fer.apr.dz.optimization.restriction.ImplicitRestriction;
import hr.fer.apr.lu.matrix.Vector;
import hr.fer.apr.optimization.HookeJeevesSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.lang.Math.*;

public class TransformationMethod {

    private TransformationMethod() {
    }

    public static Vector of(Function<Vector, Double> function, List<ExplicitRestriction> explicitRestrictions, List<ImplicitRestriction> implicitRestrictions, Vector startingPoint, Vector dx, Vector epsilon, double t) {
        List<ImplicitRestriction> violatedRestrictions = new ArrayList<>();
        for (ImplicitRestriction implicitRestriction : implicitRestrictions) {
            if (implicitRestriction.getCondition().apply(startingPoint) < 0) {
                violatedRestrictions.add(implicitRestriction);
            }
        }
        if(violatedRestrictions.size() > 0) {
            Function<Vector, Double> G = vector -> {
                Double result = 0.0;
                for (ImplicitRestriction violatedRestriction : violatedRestrictions) {
                    result -= violatedRestriction.getCondition().apply(vector);
                }
                return result;
            };
            startingPoint = HookeJeevesSearch.of(G, startingPoint, dx, epsilon);
        }
        Vector point = startingPoint;
        for (int i = 0; i < 1000; i++) {
            Double finalT = t;
            Function<Vector, Double> mixedFunction = vector -> {
                Double implicitResult = 0.0;
                for (ImplicitRestriction implicitRestriction : implicitRestrictions) {
                    implicitResult += log(implicitRestriction.getCondition().apply(vector));
                }
                Double explicitResult = 0.0;
                for (ExplicitRestriction explicitRestriction : explicitRestrictions) {
                    explicitResult += pow(explicitRestriction.getCondition().apply(vector), 2);
                }
                return function.apply(vector) - 1 / finalT * implicitResult + finalT * explicitResult;
            };
            t = t * 10;
            Vector nextPoint = HookeJeevesSearch.of(mixedFunction, point, dx, epsilon);
            if (checkStoppingCondition(point, nextPoint, epsilon)) {
                return nextPoint;
            }
            point = nextPoint;
        }
        return point;
    }

    private static boolean checkStoppingCondition(Vector point, Vector nextPoint, Vector epsilon){
        for (int i = 0; i < point.getNumberOfRows(); i++) {
            if(abs(point.getElementAt(i) - nextPoint.getElementAt(i)) > epsilon.getElementAt(i)) return false;
        }
        return true;
    }
}
