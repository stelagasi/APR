package hr.fer.apr.dz.optimization.restriction;

import hr.fer.apr.dz.optimization.restriction.ImplicitRestriction;

public class Restrictions {
    public static final ImplicitRestriction differenceBiggerThanZero = new ImplicitRestriction(vector -> vector.getElementAt(1) - vector.getElementAt(0));

    public static final ImplicitRestriction twoMinusFirst = new ImplicitRestriction(vector -> 2 - vector.getElementAt(0));

    public static final ImplicitRestriction threeMinusFirstMinusSecond = new ImplicitRestriction(vector -> 3 - vector.getElementAt(0) - vector.getElementAt(1));

    public static final ImplicitRestriction threePlusOnePointFiveFirstMinusSecond = new ImplicitRestriction(vector -> 3 + 1.5 * vector.getElementAt(0) - vector.getElementAt(1));

    public static final ImplicitRestriction secondMinusOne = new ImplicitRestriction(vector -> vector.getElementAt(1) - 1);

    private Restrictions(){}
}
