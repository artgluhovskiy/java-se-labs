package org.art.java_core.algorithms.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsefulIdioms {

    public static void main(String[] args) {

        //Idiom 1. Converting a string to a list of characters
        String str1 = "abcd";
        List<Character> chars1 = str1.chars()
            .mapToObj(ch -> (char) ch)
            .collect(Collectors.toList());

        //Idiom 2. Sort characters in a string
        String str2 = "sbsddf";
        char[] chars2 = str2.toCharArray();
        Arrays.sort(chars2);
        String strWithSortedChars = new String(chars2);

    }
}
