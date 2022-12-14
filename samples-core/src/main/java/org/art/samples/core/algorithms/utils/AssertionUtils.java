package org.art.samples.core.algorithms.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AssertionUtils {

    public static boolean assertArrayBeginsPrecisely(int[] srcArray, int[] beginsWith) {
        if (srcArray == null || beginsWith == null) {
            return false;
        }
        if (beginsWith.length > srcArray.length) {
            return false;
        }
        for (int i = 0; i < beginsWith.length; i++) {
            if (beginsWith[i] != srcArray[i]) {
                return false;
            }
        }
        return true;
    }
}
