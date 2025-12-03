/*
344. Reverse String
Write a function that reverses a string. The input string is given as an array of characters s.

You must do this by modifying the input array in-place with O(1) extra memory.

 

Example 1:

Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
Example 2:

Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
 

Constraints:

1 <= s.length <= 105
s[i] is a printable ascii character.
*/

import java.util.Arrays;

public class _0344_ReverseString {
    public void reverseString(char[] s) {
        char[] rev = new char[s.length];
        for (int k = 0; k < s.length; k++) {
            rev[k] = s[k];
        }
        
        int j = 0;
        for (int i = s.length - 1; i >= 0; i--) {
            s[j] = rev[i];
            j++;   
        }
        System.out.println(Arrays.toString(s));
    }

    public static void main(String[] args) {
        _0344_ReverseString solution = new _0344_ReverseString();
        solution.reverseString(new char[]{'h','e','l','l','o'});
    }

    public void reverseStringAI(char[] s) {
        int left = 0;
        int right = s.length - 1;
        
        // Loop until they meet in the middle
        while (left < right) {
            // 1. Save the left character in a temporary variable
            char temp = s[left];
            
            // 2. Overwrite left with right
            s[left] = s[right];
            
            // 3. Overwrite right with the SAVED temp
            s[right] = temp;
            
            // 4. Move pointers inward
            left++;
            right--;
        }
    }
}
