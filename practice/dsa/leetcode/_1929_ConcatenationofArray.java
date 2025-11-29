
public class _1929_ConcatenationofArray {
    public int[] getConcatenation(int[] nums) {
        int temp = 0;
        int[] ans = new int[2*nums.length];
        for(int i=0; i<(2*nums.length); i++) {
            if (i<nums.length) {
                ans[i] = nums[i];
            } else {
                ans[i] = nums[temp];
                temp++;
            }
            
        }
        return ans;
    }

    public int[] getConcatenationAI(int[] nums) {
        int[] ans = new int[2*nums.length];
        for(int i=0; i<nums.length; i++) {
            ans[i] = nums[i];
            ans[i+nums.length] = nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        _1929_ConcatenationofArray solution = new _1929_ConcatenationofArray();
        int[] result = solution.getConcatenation(new int[] { 1, 2, 3, 1});
        int[] result1 = solution.getConcatenationAI(new int[] { 1, 2, 3, 1});
        for (int i : result1) {
            System.out.print(i);
        }       
    }
}
