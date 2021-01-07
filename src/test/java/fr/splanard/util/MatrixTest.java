package fr.splanard.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MatrixTest {

    @Test
    public void add_shouldReturnTheRightResult(){
        double[][] data1 = {{1, 2, 3, 4}};
        Matrix matrix1 = new Matrix(data1);
        double[][] data2 = {{1, 2, 3, 4}};
        Matrix matrix2 = new Matrix(data2);

        double[][] expectedResult = {{2, 4, 6, 8}};
        assertThat( Matrix.add(matrix1, matrix2).data() ).isEqualTo(expectedResult);
    }

    @Test
    public void add_shouldThrowException_whenMatricesShapesDoNotMatch(){
        assertThrows(IllegalArgumentException.class, () -> {
            double[][] data1 = {{1, 2, 3, 4}};
            Matrix matrix1 = new Matrix(data1);
            double[][] data2 = {{1, 2, 3}};
            Matrix matrix2 = new Matrix(data2);

            Matrix.add(matrix1, matrix2);
        });
    }

    @Test
    public void apply_shouldReturnTheRightResult(){
        double[][] data = {{1, 2, 3}, {4, 5, 6}};
        Matrix matrix = new Matrix(data);

        double[][] expectedResult = {{1, 2, 3}, {-4, -5, -6}};
        assertThat( matrix.apply((double d) -> d <= 3 ? d : -d).data() )
                .isEqualTo(expectedResult);
    }

    @Test
    public void clone_shouldReturnAnEquivalentInstanceWithDifferentReferences(){
        double[][] data = {{1, 2, 3}, {4, 5, 6}};
        Matrix matrix = new Matrix(data);

        Matrix clone = matrix.clone();

        assertThat( matrix.cols() ).isEqualTo( clone.cols() );
        assertThat( matrix.rows() ).isEqualTo( clone.rows() );
        for(int i=0; i < data.length; i++){
            assertThat(matrix.data()[i] == clone.data()[i]).isFalse();
            assertThat( Arrays.equals(matrix.data()[i], clone.data()[i] ) ).isTrue();
        }
    }

    @Test
    public void dataConstructor_shouldSetTheRightRowsAndCols() {
        double[][] data = {{1, 2, 3}, {1, 2, 3}};
        Matrix matrix = new Matrix(data);

        assertThat(matrix.cols()).isEqualTo(3);
        assertThat(matrix.rows()).isEqualTo(2);
        assertThat(matrix.data()).isEqualTo(data);
    }

    @Test
    public void dotProduct_shouldReturnTheRightResult(){
        double[][] data1 = {{1, 2, 3}, {1, 2, 3}};
        Matrix matrix1 = new Matrix(data1);
        double[][] data2 = {{1, 1}, {1, 1}, {1, 1}};
        Matrix matrix2 = new Matrix(data2);

        double[][] expectedResult = {{6, 6}, {6, 6}};
        assertThat( Matrix.dotProduct(matrix1, matrix2).data() ).isEqualTo(expectedResult);
    }

    @Test
    public void dotProduct_shouldThrowException_whenMatricesShapesDoNotMatch(){
        assertThrows(IllegalArgumentException.class, () -> {
            double[][] data1 = {{1, 2, 3}, {1, 2, 3}};
            Matrix matrix1 = new Matrix(data1);
            double[][] data2 = {{1, 1, 1}, {1, 1, 1}};
            Matrix matrix2 = new Matrix(data2);

            Matrix.dotProduct( matrix1, matrix2 );
        });
    }

    @Test
    public void equals_shouldReturnTrue_whenMatricesAreEquivalent(){
        double[][] data1 = {{1, 2, 3}, {1, 2, 3}};
        Matrix matrix1 = new Matrix(data1);
        double[][] data2 = {{1, 2, 3}, {1, 2, 3}};
        Matrix matrix2 = new Matrix(data2);

        assertThat( matrix1.equals(matrix2) ).isTrue();
        assertThat( matrix2.equals(matrix1) ).isTrue();
    }
}
