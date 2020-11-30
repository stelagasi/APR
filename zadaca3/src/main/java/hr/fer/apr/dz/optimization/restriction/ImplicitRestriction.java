package hr.fer.apr.dz.optimization.restriction;

import hr.fer.apr.lu.matrix.Vector;

import java.util.function.Function;

public class ImplicitRestriction extends Restriction {
    private Function<Vector, Boolean> condition;

    public ImplicitRestriction(Function<Vector, Boolean> condition){
        this.condition = condition;
    }

    public Function<Vector, Boolean> getCondition() {
        return condition;
    }
}
