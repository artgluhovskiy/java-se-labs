package org.art.java_core.algorithms.utils.bits;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BitOperations {

    public static int clearBit(int num, int i) {
        int mask = ~(1 << i);
        return num & mask;
    }
}
