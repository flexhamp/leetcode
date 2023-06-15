package com.flexhamp.leetcode;

import java.util.Arrays;

//https://leetcode.com/problems/h-index/submissions/972049204/
public class HIndex {
    public static void main(String[] args) {
        int[] m = new int[]{3, 0, 6, 1, 5};
        System.out.println(hIndex(m));
    }
    //https://www.youtube.com/watch?v=z2Y0S9SfzEs
    public static int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }

        int n = citations.length;
        int[] tmp = new int[n + 1];

        for (int citation : citations) {
            if (citation >= n) {
                tmp[n]++;
            } else {
                tmp[citation]++;
            }
        }

        System.out.println(Arrays.toString(tmp));

        int pos = 0;
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < tmp[i]; j++) {
                citations[pos] = i;
                pos++;
            }
        }

        System.out.println(Arrays.toString(citations));

        int h = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (citations[i] < h) {
                return h - 1;
            }
            h++;
        }

        return n;
    }
}
