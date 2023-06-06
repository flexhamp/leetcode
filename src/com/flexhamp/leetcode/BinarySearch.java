package com.flexhamp.leetcode;

import java.util.stream.IntStream;

/*
Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.

You must write an algorithm with O(log n) runtime complexity.



Example 1:

Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4
Example 2:

Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1


Constraints:

1 <= nums.length <= 104
-104 < nums[i], target < 104
All the integers in nums are unique.
nums is sorted in ascending order.
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
        int target = 9;
        System.out.println(search(nums, target));
    }

    public static int search(int[] nums, int target) {
        return IntStream.range(0, nums.length)
                .filter(i -> nums[i] == target)
                .findFirst()
                .orElse(-1);
    }

    public int search2(int[] nums, int target) {
        int a = 0, b = nums.length-1;

        while (a <= b) {
            int k = (a + b) / 2;

            if (nums[k] == target) {
                return k;
            }

            if (nums[k] < target) {
                a = k + 1;
            } else {
                b = k - 1;
            }
        }

        return -1;
    }
}
