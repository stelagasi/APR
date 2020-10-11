package hr.fer.apr.lu.matrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SquareMatrix extends Matrix {
    public SquareMatrix(Matrix matrix) {
        if(matrix.getNumberOfRows() != matrix.getNumberOfColumns()) throw new IllegalArgumentException();
        this.setNumberOfRows(matrix.getNumberOfRows());
        this.setNumberOfColumns(matrix.getNumberOfColumns());
        this.setElements(Arrays.copyOf(matrix.getElements(), matrix.getNumberOfRows()*matrix.getNumberOfRows()));
    }

    public SquareMatrix(String file) {
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
        if(numberOfColumns != numberOfRows) throw new IllegalArgumentException();
        double[] elements = new double[strings.size()];
        for (int i = 0; i < strings.size(); i++) {
            elements[i] = Double.parseDouble(strings.get(i));
        }
        this.setNumberOfColumns(numberOfColumns);
        this.setNumberOfRows(numberOfRows);
        this.setElements(elements);
    }

    public SquareMatrix(int dimension, double[] elements){
        super(dimension, dimension, elements);
    }
}
