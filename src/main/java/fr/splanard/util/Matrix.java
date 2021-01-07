package fr.splanard.util;

import fr.splanard.neuralnetwork.Activation;

import java.util.ArrayList;
import java.util.Arrays;

public class Matrix {
    private final double[][] data;
    private final int cols;
    private final int rows;

    public Matrix(int rows, int cols) {
        data = new double[rows][cols];
        this.rows = rows;
        this.cols = cols;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = Math.random() * 2 - 1;
            }
        }
    }

    public Matrix(double[][] data) {
        if (data.length == 0 || data[0].length == 0) {
            throw new IllegalArgumentException("Cannot instanciate Matrix: given data is missing a dimension");
        }
        this.rows = data.length;
        this.cols = data[0].length;
        this.data = data;
    }

    public static Matrix add(Matrix a, Matrix b) {
        if (a.cols != b.cols || a.rows != b.rows) {
            throw new IllegalArgumentException("Cannot add matrices: shapes don't match");
        }

        double[][] resultData = new double[a.rows][a.cols];
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.cols; j++) {
                resultData[i][j] = a.data[i][j] + b.data[i][j];
            }
        }
        return new Matrix(resultData);
    }

    public static Matrix dotProduct(Matrix a, Matrix b) {
        if (a.cols != b.rows) {
            throw new IllegalArgumentException("Cannot dot product matrices: shapes don't match");
        }
        Matrix temp = new Matrix(a.rows, b.cols);
        for (int i = 0; i < temp.rows; i++) {
            for (int j = 0; j < temp.cols; j++) {
                double sum = 0;
                for (int k = 0; k < a.cols; k++) {
                    sum += a.data[i][k] * b.data[k][j];
                }
                temp.data[i][j] = sum;
            }
        }
        return temp;
    }

    public Matrix apply(Activation activation) {
        double[][] resultData = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                resultData[i][j] = activation.apply(data[i][j]);
        }
        return new Matrix(resultData);
    }

    public double[][] data() {
        return data;
    }

    public int cols() {
        return cols;
    }

    public int rows() {
        return rows;
    }

    public Matrix clone() {
        double[][] cloneData = new double[rows][cols];
        for (int i = 0; i < data.length; i++) {
            cloneData[i] = data[i].clone();
        }
        return new Matrix(cloneData);
    }

    public boolean equals(Matrix matrix) {
        if (cols != matrix.cols || rows != matrix.rows) {
            return false;
        }
        for (int i = 0; i < data.length; i++) {
            if (!Arrays.equals(data[i], matrix.data[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        ArrayList<String> strRows = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            strRows.add(Arrays.toString(data[i]));
        }
        return "M(" + rows + "," + cols + ")[" + String.join(", ", strRows) + "]";
    }
}
