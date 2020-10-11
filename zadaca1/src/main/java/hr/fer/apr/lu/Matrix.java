package hr.fer.apr.lu;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

//todo metoda za mijenjanje dimenzije matrice?
public class Matrix {
    int numberOfRows, numberOfColumns;
    double[] elements;

    public Matrix(Matrix matrix) {
        this.numberOfRows = matrix.getNumberOfRows();
        this.numberOfColumns = matrix.getNumberOfColumns();
        this.elements = matrix.getElements();
    }

    public Matrix(String file) {
        int numberOfColumns = -1;
        int numberOfRows = 0;
        ArrayList<String> strings = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (numberOfColumns == -1) numberOfColumns = line.split("\\s+").length;
                else if (numberOfColumns != line.split("\\s+").length) throw new IllegalArgumentException();
                numberOfRows++;
                Collections.addAll(strings, line.split("\\s+"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        double[] elements = new double[strings.size()];
        for (int i = 0; i < strings.size(); i++) {
            elements[i] = Double.parseDouble(strings.get(i));
        }
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
        this.elements = elements;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public double[] getElements() {
        return elements;
    }

    public void setElementTo(int numberOfRow, int numberOfColumn, double value) {
        if (numberOfRow >= numberOfRows || numberOfColumn >= numberOfColumns) throw new IllegalArgumentException();
        elements[numberOfRow * numberOfColumns + numberOfColumn] = value;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < numberOfRows; i++) {
            stringBuilder.append("[");
            for (int j = 0; j < numberOfColumns - 1; j++) {
                stringBuilder.append(elements[i * numberOfColumns + j]).append(" ");
            }
            stringBuilder.append(elements[i * numberOfColumns + numberOfColumns - 1]).append("]\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("\n")).append("]");
        return stringBuilder.toString();
    }
}
