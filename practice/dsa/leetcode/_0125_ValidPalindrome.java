/*
125. Valid Palindrome
A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.

 

Example 1:

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
Example 2:

Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
Example 3:

Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.
 

Constraints:

1 <= s.length <= 2 * 105
s consists only of printable ASCII characters.
*/

public class _0125_ValidPalindrome {
    public boolean isPalindrome(String s) {
        if (s.isEmpty())
            return true;

        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            char startChar = s.charAt(left);
            char endChar = s.charAt(right);
            if (!Character.isLetterOrDigit(startChar)) {
                left++;
            } else if (!Character.isLetterOrDigit(endChar)) {
                right--;
            } else {
                if (Character.toLowerCase(startChar) != Character.toLowerCase(endChar)) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        _0125_ValidPalindrome solution = new _0125_ValidPalindrome();
        boolean result = solution.isPalindrome("A man, a plan, a canal: Panama");
        System.out.println(result);
    }
}
