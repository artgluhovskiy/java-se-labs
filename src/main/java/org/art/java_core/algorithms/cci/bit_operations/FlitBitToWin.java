package org.art.java_core.algorithms.cci.bit_operations;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "Flip Bit to Win" solution from "Cracking the Coding Interview".
 *
 * You have an integer and you can flip exactly one bit from a O to a 1.
 * Write code to find the length of the longest sequence of 1s you could create.
 * EXAMPLE
 * Input: 1775 (or: 11011101111)
 * Output: 8
 */
public class FlitBitToWin {

    public int flipBitV1(int num) {
        List<Integer> bitSequences = buildBitSequences(num);
        return findLongestSequence(bitSequences);
    }

    private List<Integer> buildBitSequences(int num) {
        int counter = 0;
        int bitToSearch = 0;
        List<Integer> sequences = new ArrayList<>();
        for (int i = 0; i < Integer.BYTES * 8; i++) {
            if ((num & 1) != bitToSearch) {
                sequences.add(counter);
                counter = 0;
                //Flipping bit for searching
                bitToSearch = bitToSearch == 0 ? 1 : 0;
            }
            counter++;
            num = num >>> 1;
        }
        sequences.add(counter);
        return sequences;
    }

    private int findLongestSequence(List<Integer> bitSequences) {
        int longestSeq = 1;
        for (int i = 0; i < bitSequences.size(); i = i + 2) {
            int zerosSeq = bitSequences.get(i);
            int onesLeftSeq = i - 1 > 0 ? bitSequences.get(i - 1) : 0;
            int onesRightSeq = i + 1 < bitSequences.size() ? bitSequences.get(i + 1) : 0;
            if (zerosSeq == 1) {
                longestSeq = Math.max(onesLeftSeq + 1 + onesRightSeq, longestSeq);
            } else if (zerosSeq > 1) {
                longestSeq = Math.max(Math.max(onesLeftSeq + 1, onesRightSeq + 1), longestSeq);
            } else if (zerosSeq == 0) {
                longestSeq = Math.max(Math.max(onesLeftSeq, onesRightSeq), longestSeq);
            }
        }
        return longestSeq;
    }

    /**
     * Optimized solution with O(1) memory usage
     */
    public int flipBitV2(int num) {
        int longestSeq = 1;
        int currentSeq = 0;
        int previousSeq = 0;
        for (int i = 0; i < Integer.BYTES * 8; i++) {
            if ((num & 1) == 1) {
                currentSeq++;
            } else {
                // Update to 0 (if next bit is 0) or currentLength (if next bit is 1)
                previousSeq = (num & 2) == 0 ? 0 : currentSeq;
                currentSeq = 0;
            }
            longestSeq = Math.max(previousSeq + 1 + currentSeq, longestSeq);
            num = num >>> 1;
        }
        return Math.max(previousSeq + 1 + currentSeq, longestSeq);
    }

    @Test
    void test0() {
        int input = 1775;
        int result = 8;
        assertEquals(result, flipBitV1(input));
    }

    @Test
    void test1() {
        int input = 1775;
        int result = 8;
        assertEquals(result, flipBitV2(input));
    }
}
