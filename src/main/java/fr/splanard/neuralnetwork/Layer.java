package fr.splanard.neuralnetwork;

import fr.splanard.util.Matrix;

public class Layer {

    private final Matrix weights;
    private final Matrix bias;

    public Layer(int inputLength, int outputLength){
        weights = new Matrix(inputLength, outputLength);
        bias = new Matrix(1, outputLength);
    }

    private Layer(Matrix weights, Matrix bias){
        this.weights = weights;
        this.bias = bias;
    }

    public Matrix forward( Matrix input ){
        Matrix dotProduct = Matrix.dotProduct(input, weights);
        Matrix withBias = Matrix.add(dotProduct, bias);
        return withBias.apply(this::sigmoid);
    }

    /**
     * Sigmoid activation function.
     * @param d the input
     * @return A value between 0 and 1
     */
    private double sigmoid(double d){
        return 1/(1+Math.exp(-d));
    }

    /**
     * ReLU activation function, as Rectified Linear Unit.
     * @param d the input
     * @return A value between 0 and infinity
     */
    private double relu(double d){
        return Math.max(0, d);
    }

    public Layer clone(){
        return new Layer(this.weights.clone(), this.bias.clone());
    }

    public Matrix weights() {
        return weights;
    }

    public Matrix bias() {
        return bias;
    }

    @Override
    public String toString() {
        return "Layer["+System.lineSeparator()
                + "  w: "+weights.toString()+System.lineSeparator()
                + "  b: "+bias.toString()+System.lineSeparator()
                + "]";
    }
}
