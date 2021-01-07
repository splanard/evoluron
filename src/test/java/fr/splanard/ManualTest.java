package fr.splanard;

import fr.splanard.neuralnetwork.NeuralNetwork;
import fr.splanard.util.Matrix;

public class ManualTest {

    public static void main(String[] args) {
        double[][] input = {{1, 2, 3}};

        NeuralNetwork nn = new NeuralNetwork(3, 3, 2);
        System.out.println( nn );
        System.out.println( nn.forward(new Matrix(input)) );
    }

}
