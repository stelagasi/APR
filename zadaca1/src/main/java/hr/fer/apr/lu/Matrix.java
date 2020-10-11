package hr.fer.apr.lu;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

//todo metoda za mijenjanje dimenzije matrice?
public class Matrix {
    int numberOfRows, numberOfColumns;
    double[] elements;

    public Matrix(Matrix matrix) {
        this(matrix.getNumberOfRows(), matrix.getNumberOfColumns(), matrix.getElements());
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

    public Matrix(int numberOfRows, int numberOfColumns, double[] elements) {
        if (numberOfColumns <= 0 || numberOfRows <= 0) throw new IllegalArgumentException();
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.elements = Arrays.copyOf(elements, numberOfRows*numberOfColumns);
    }

    public static Matrix addition(Matrix m1, Matrix m2){
        if(m1.getNumberOfRows() != m2.getNumberOfRows() || m1.getNumberOfColumns() != m2.getNumberOfColumns())
            throw new MatrixException("Matrices are not the same size.");
        double[] resultElements;
        return null;
    }

    public static Matrix subtraction(Matrix m1, Matrix m2){
        if(m1.getNumberOfRows() != m2.getNumberOfRows() || m1.getNumberOfColumns() != m2.getNumberOfColumns())
            throw new MatrixException("Matrices are not the same size.");
        double[] resultElements;
        return null;//new Matrix(m1.numberOfColumns, m1.numberOfRows, resultElements);
    }

    public static Matrix matrixMultiplication(Matrix m1, Matrix m2){
        if(m1.getNumberOfColumns() != m2.getNumberOfRows()) throw new MatrixException("Matrices can't be multiplied.");
        double[] resultElements = new double[m1.getNumberOfRows()*m2.getNumberOfColumns()];
        for(int i = 0; i < m1.getNumberOfRows(); i++){
            for(int j = 0; j < m2.getNumberOfColumns(); j++){
                for(int k = 0; k < m2.getNumberOfRows(); k++){
                    resultElements[i*m2.getNumberOfColumns()+j] += m1.getElementAt(i, k) * m2.getElementAt(k, j);
                }
            }
        }
        return new Matrix(m1.getNumberOfRows(), m2.getNumberOfColumns(), resultElements);
    }

    public Matrix multiplicationWithScalar(double scalar){
        return new Matrix(this.numberOfRows, this.numberOfColumns, Arrays.stream(this.elements).map(i -> scalar * i).toArray());
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

    //todo što ako želim veću matricu?
    public void setElementAt(int numberOfRow, int numberOfColumn, double value) {
        if (numberOfRow >= numberOfRows || numberOfColumn >= numberOfColumns) throw new IllegalArgumentException();
        elements[numberOfRow * numberOfColumns + numberOfColumn] = value;
    }

    public double getElementAt(int numberOfRow, int numberOfColumn) {
        if (numberOfRow >= numberOfRows || numberOfColumn >= numberOfColumns) throw new IllegalArgumentException();
        return elements[numberOfRow * numberOfColumns + numberOfColumn];
    }

    //todo što s točkama kad je cijeli br?
    public void writeInFile(String file) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            for (int i = 0; i < numberOfRows; i++) {
                for (int j = 0; j < numberOfColumns; j++) {
                    bufferedWriter.write(String.valueOf(elements[i * numberOfColumns + j]));
                    if (j != numberOfColumns - 1) bufferedWriter.write(" ");
                }
                if (i != numberOfRows - 1) bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Matrix transpose() {
        double[] transposeElements = new double[this.elements.length];
        for(int i = 0; i < numberOfRows; i++){
            for(int j = 0; j < numberOfColumns; j++){
                transposeElements[i*numberOfColumns+j] = this.elements[j*numberOfRows + i];
            }
        }
        return new Matrix(this.numberOfColumns, this.numberOfRows, transposeElements);
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
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("\n")).append("]\n");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return numberOfRows == matrix.numberOfRows &&
                numberOfColumns == matrix.numberOfColumns &&
                Arrays.equals(elements, matrix.elements);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(numberOfRows, numberOfColumns);
        result = 31 * result + Arrays.hashCode(elements);
        return result;
    }

}
