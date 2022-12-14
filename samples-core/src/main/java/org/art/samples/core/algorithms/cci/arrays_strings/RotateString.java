package main.java.org.art.samples.core.algorithms.cci.arrays_strings;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * "Rotate String" solution from "Cracking the Coding Interview".
 */
public class RotateString {

    public boolean isRotateString(String s1, String s2) {
        return StringUtils.contains(s2 + s2, s1);
    }

    @Test
    void test0() {
        String s1 = "waterbottle";
        String s2 = "erbottlewat";
        boolean result = isRotateString(s1, s2);
        assertTrue(result);
    }
}
