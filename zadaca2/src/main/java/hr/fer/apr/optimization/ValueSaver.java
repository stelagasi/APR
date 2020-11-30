package hr.fer.apr.optimization;

import hr.fer.apr.lu.matrix.Vector;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueSaver that = (ValueSaver) o;
        return Objects.equals(point, that.point) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, value);
    }
}
