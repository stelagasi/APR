package hr.fer.apr.optimization;

import hr.fer.apr.lu.matrix.Vector;

public class ValueSaver {
    private Vector point;
    private Double value;

    public ValueSaver(Vector point, Double value) {
        this.point = point;
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public Vector getPoint() {
        return point;
    }

    public void setPoint(Vector point) {
        this.point = point;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
