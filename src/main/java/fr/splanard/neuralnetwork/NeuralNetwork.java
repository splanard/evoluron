package fr.splanard.neuralnetwork;

import fr.splanard.util.Matrix;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {

    private final List<Layer> layers = new ArrayList<>();

    public NeuralNetwork(int inputLength, int hiddenLayerNodes, int outputLayerNodes) {
        layers.add(new Layer(inputLength, hiddenLayerNodes));
        layers.add(new Layer(hiddenLayerNodes, outputLayerNodes));
    }

    private NeuralNetwork(List<Layer> layers){
        this.layers.addAll( layers );
    }

    public Matrix forward(Matrix input) {
        Matrix result = input;
        for (Layer layer : layers){
            result = layer.forward(result);
        }
        return result;
    }

    protected NeuralNetwork clone(){
        List<Layer> newLayers = new ArrayList<>();
        for(Layer layer : layers){
            newLayers.add( layer.clone() );
        }
        return new NeuralNetwork(newLayers);
    }

    public List<Layer> layers() {
        return layers;
    }

    @Override
    public String toString() {
        return "hidden: "+ layers.get(0).toString()
                + System.lineSeparator()
                + "output: "+ layers.get(1).toString();
    }
}
