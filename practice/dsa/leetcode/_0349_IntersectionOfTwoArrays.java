import java.util.*;

public class _0349_IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        int k = 0;
        HashSet<Integer> set1 = new HashSet<>();
        for (int i=0; i<nums1.length; i++) {
            set.add(nums1[i]);
        }
        for (int j=0; j<nums2.length; j++) {
            System.out.println(nums2[j]);  
            if (set.contains(nums2[j])) {
                set1.add(nums2[j]);
            }
        } 
        return set1.stream().mapToInt(Integer::intValue).toArray();  
    }

    public int[] intersectionSpaceOptimized(int[] nums1, int[] nums2) {       
        Arrays.sort(nums1);
        Arrays.sort(nums2);     
        Set<Integer> result = new HashSet<>();
        
        int i = 0, j = 0;      
        while (i < nums1.length && j < nums2.length) {           
            if (nums1[i] == nums2[j]) {
                result.add(nums1[i]);
                i++;
                j++;
            } 
            else if (nums1[i] < nums2[j]) {
                i++;
            } 
            else {
                j++;
            }
        }
        
        return result.stream().mapToInt(Integer::intValue).toArray(); 
    }

    public static void main(String[] args) {
        _0349_IntersectionOfTwoArrays solution = new _0349_IntersectionOfTwoArrays();
        int[] result = solution.intersection(new int[]{1,2,2,4}, new int[]{2,4});
        System.out.println(Arrays.toString(result));
    }
}
