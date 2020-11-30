package hr.fer.apr.dz.optimization.restriction;

public class ExplicitRestriction extends Restriction {
    private Double upperBound;
    private Double lowerBound;

    public ExplicitRestriction(Double upperBound, Double lowerBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }
}
