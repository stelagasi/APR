package hr.fer.apr.lu.matrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Vector extends Matrix {

    public Vector(int numberOfRows, double[] elements) {
        super(numberOfRows, 1, elements);
    }

    public Vector(Vector vector) {
        super(vector);
    }

    public Vector(String file) {
        int numberOfColumns = -1;
        int numberOfRows = 0;
        ArrayList<String> strings = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (numberOfColumns == -1) numberOfColumns = line.split("\\s+").length;
                else if (numberOfColumns != 1) throw new IllegalArgumentException();
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
        this.setNumberOfRows(numberOfRows);
        this.setElements(elements);
    }

    public void setElementAt(int numberOfRow, double value) {
        super.setElementAt(numberOfRow, 0, value);
    }

    public double getElementAt(int numberOfRow) {
        return super.getElementAt(numberOfRow, 0);
    }
}
