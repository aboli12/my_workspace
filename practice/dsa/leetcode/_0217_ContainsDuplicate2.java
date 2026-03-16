import java.util.*;

public class _0217_ContainsDuplicate2 {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);           
        }
        return false;
    }

    public static void main(String[] args) {
        _0217_ContainsDuplicate2 solution = new _0217_ContainsDuplicate2();
        boolean result = solution.containsDuplicate(new int[] { 1, 2, 3, 1});
        System.out.println(result);
    }
}
