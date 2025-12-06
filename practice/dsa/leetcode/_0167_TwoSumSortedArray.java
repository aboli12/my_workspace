/*
167. Two Sum II - Input Array Is Sorted
Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.

Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.

The tests are generated such that there is exactly one solution. You may not use the same element twice.

Your solution must use only constant extra space.

 

Example 1:

Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
Example 2:

Input: numbers = [2,3,4], target = 6
Output: [1,3]
Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
Example 3:

Input: numbers = [-1,0], target = -1
Output: [1,2]
Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].
 

Constraints:

2 <= numbers.length <= 3 * 104
-1000 <= numbers[i] <= 1000
numbers is sorted in non-decreasing order.
-1000 <= target <= 1000
The tests are generated such that there is exactly one solution.
*/

import java.util.Arrays;
import java.util.HashMap;

public class _0167_TwoSumSortedArray {
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (!map.containsKey(target - numbers[i])) {
                map.put(numbers[i], i);
            } else {
                return new int[] { map.get(target - numbers[i]) + 1, i + 1 };
            }
        }
        return new int[] {};
    }

    public static void main(String[] args) {
        _0167_TwoSumSortedArray solution = new _0167_TwoSumSortedArray();
        int[] result = solution.twoSum(new int[]{2,7,11,15}, 9);
        System.out.println(Arrays.toString(result));
    }

    public int[] twoSumAI(int[] numbers, int target) {
        // Initialize pointers
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int currentSum = numbers[left] + numbers[right];

            if (currentSum == target) {
                // Found the pair. LeetCode requires 1-based indexing.
                return new int[]{left + 1, right + 1};
            } else if (currentSum < target) {
                // Sum is too small, need a larger number. Move left pointer right.
                left++;
            } else {
                // Sum is too large, need a smaller number. Move right pointer left.
                right--;
            }
        }

        // According to the problem constraints, exactly one solution exists, 
        // so this line should technically never be reached.
        return new int[]{};
    }
}