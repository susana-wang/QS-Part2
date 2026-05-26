package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CheckFromIndexSizeWeakECTest {

    // T1
    @Test
    public void testNullStr() {
        assertThrows(NullPointerException.class, () ->
                IOUtils.checkFromIndexSize((String)null, 2, 2)
        );
    }

    // T2
    @Test
    public void testOffPlusLenExceedsLength() {
        assertThrows(IndexOutOfBoundsException.class, () ->
                IOUtils.checkFromIndexSize("hello", 3, 4)
        );
    }

    // T3
    @Test
    public void testNegativeOff() {
        assertThrows(IndexOutOfBoundsException.class, () ->
                IOUtils.checkFromIndexSize("hello", -1, 2)
        );
    }

    // T4
    @Test
    public void testNegativeLen() {
        assertThrows(IndexOutOfBoundsException.class, () ->
                IOUtils.checkFromIndexSize("", 0, -1)
        );
    }
}