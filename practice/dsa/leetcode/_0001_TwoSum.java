import java.util.Arrays;

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
    }
}