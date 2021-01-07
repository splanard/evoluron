package fr.splanard.neuralnetwork;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NeuralNetworkTest {

    @Test
    public void clone_shouldReturnEquivalentButIndependentInstance(){
        NeuralNetwork neuralNetwork = new NeuralNetwork(3, 3, 3);

        NeuralNetwork clone = neuralNetwork.clone();

        assertThat( clone.layers() ).hasSameSizeAs( neuralNetwork.layers() );
        for( int i=0; i < clone.layers().size(); i++ ){
            Layer initialLayer = neuralNetwork.layers().get(i);
            Layer cloneLayer = clone.layers().get(i);

            assertThat( cloneLayer == initialLayer ).isFalse();
            assertThat( cloneLayer.weights().equals( initialLayer.weights() ) ).isTrue();
            assertThat( cloneLayer.weights() == initialLayer.weights() ).isFalse();
            assertThat( cloneLayer.bias().equals( initialLayer.bias() ) ).isTrue();
            assertThat( cloneLayer.bias() == initialLayer.bias() ).isFalse();
        }
    }

}
