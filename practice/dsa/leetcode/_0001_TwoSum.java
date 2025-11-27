import java.util.*;

class _0001_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        // int[] result = new int[2];
        for(int i=0; i < nums.length; i++) {
            for(int j=i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i,j};
                    // break;
                }
            }
        }
        return new int[]{};
        // return result;
    }

    public static void main(String[] args) {
        _0001_TwoSum solution = new _0001_TwoSum();
        int[] result = solution.twoSum(new int[]{2,7,3,6}, 9);
        System.out.println(Arrays.toString(result));
        int[] result1 = solution.twoSumAI(new int[]{2,7,3,6}, 9);
        System.out.println(Arrays.toString(result1));
    }


    public int[] twoSumAI(int[] nums, int target) {
        // Map to store number -> index
        // Key: The number value
        // Value: The index where that number was found
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // Check if the complement exists in the map
            if (map.containsKey(complement)) {
                // If found, return the stored index and the current index
                return new int[] { map.get(complement), i };
            }

            // If not found, add the current number and index to the map
            map.put(nums[i], i);
        }

        // In case there is no solution, though problem guarantees one
        return new int[] {};
    }

}