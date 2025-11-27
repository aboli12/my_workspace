import java.util.*;

public class _0217_ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> temp = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            boolean result = temp.add(nums[i]);
            if (!result) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        _0217_ContainsDuplicate solution = new _0217_ContainsDuplicate();
        boolean result = solution.containsDuplicate(new int[] { 1, 2, 3, 1});
        System.out.println(result);
    }
}
