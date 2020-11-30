package hr.fer.apr.dz.optimization.restriction;

import hr.fer.apr.lu.matrix.Vector;

import java.util.function.Function;

public class ImplicitRestriction extends Restriction {
    private Function<Vector, Double> condition;

    public ImplicitRestriction(Function<Vector, Double> condition){
        this.condition = condition;
    }

    public Function<Vector, Double> getCondition() {
        return condition;
    }
}
