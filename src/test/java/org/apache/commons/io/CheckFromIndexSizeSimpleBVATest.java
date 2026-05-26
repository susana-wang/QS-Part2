package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CheckFromIndexSizeSimpleBVATest {



    // T1
    @Test
    public void testOffMin() {
        assertDoesNotThrow(() ->
                IOUtils.checkFromIndexSize("hello", 0, 2)
        );
    }

    // T2
    @Test
    public void testOffMinPlus() {
        assertDoesNotThrow(() ->
                IOUtils.checkFromIndexSize("hello", 1, 2)
        );
    }

    // T3
    @Test
    public void testNominal() {
        assertDoesNotThrow(() ->
                IOUtils.checkFromIndexSize("hello", 2, 2)
        );
    }

    // T4
    @Test
    public void testOffMaxMinus() {
        assertThrows(IndexOutOfBoundsException.class, () ->
                IOUtils.checkFromIndexSize("hello", 4, 2)
        );
    }

    // T5
    @Test
    public void testOffMax() {
        assertThrows(IndexOutOfBoundsException.class, () ->
                IOUtils.checkFromIndexSize("hello", 5, 2)
        );
    }

    // T6
    @Test
    public void testLenMin() {
        assertDoesNotThrow(() ->
                IOUtils.checkFromIndexSize("hello", 2, 0)
        );
    }

    // T7
    @Test
    public void testLenMinPlus() {
        assertDoesNotThrow(() ->
                IOUtils.checkFromIndexSize("hello", 2, 1)
        );
    }

    // T8
    @Test
    public void testLenMaxMinus() {
        assertThrows(IndexOutOfBoundsException.class, () ->
                IOUtils.checkFromIndexSize("hello", 2, 4)
        );
    }

    // T9
    @Test
    public void testLenMax() {
        assertThrows(IndexOutOfBoundsException.class, () ->
                IOUtils.checkFromIndexSize("hello", 2, 5)
        );
    }
}