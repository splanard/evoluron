package fr.splanard.neuralnetwork;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LayerTest {

    @Test
    public void clone_shouldReturnEquivalentButIndependentInstance(){
        Layer layer = new Layer(3, 3);

        Layer clone = layer.clone();

        assertThat( layer.equals(clone) ).isFalse();
        assertThat( layer.weights().equals(clone.weights()) ).isTrue();
        assertThat( layer.weights() == clone.weights() ).isFalse();
        assertThat( layer.bias().equals(clone.bias()) ).isTrue();
        assertThat( layer.bias() == clone.bias() ).isFalse();
    }

}
