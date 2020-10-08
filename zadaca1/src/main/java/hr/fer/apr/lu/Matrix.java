package hr.fer.apr.lu;

import java.io.*;
import java.util.ArrayList;

//todo metoda za mijenjanje dimenzije matrice?
public class Matrix {
    int numberOfRows, numberOfColumns;
    double[] elements;

    //todo A = B ?
    public Matrix(Matrix matrix){
        this(matrix.getNumberOfRows(), matrix.getNumberOfColumns(), matrix.getElements());
    }

    public Matrix(File file){
        ArrayList<String> strings = new ArrayList<>();
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while((line = bufferedReader.readLine()) !=  null){
                strings.addAll(line.split());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Matrix(int numberOfRows, int numberOfColumns, double[] elements) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.elements = new double[numberOfRows * numberOfColumns];
        for (int i = 0; i < numberOfColumns; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                if (i * numberOfColumns + j >= elements.length) break;
                this.elements[i * numberOfColumns + j] = elements[i * numberOfColumns + j];
            }
        }
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
}
