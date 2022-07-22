package org.art.java_core.algorithms.common;

import java.util.HashMap;
import java.util.Map;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Utils {

    public static Map<Character, Integer> toCharCountMap(String s) {
        return s.chars()
            .mapToObj(ch -> (char) ch)
            .collect(HashMap::new, (map, ch) ->
                map.compute(ch, (existingChar, existingCount) -> {
                    if (existingCount != null) {
                        return ++existingCount;
                    } else {
                        return 1;
                    }
                }), HashMap::putAll);
    }

    public static int adjustIndex(int index, int arrLength) {
        return (arrLength + index % arrLength) % arrLength;
    }
}
