import java.util.Arrays;

public class _0242_ValidAnagram2 {
    public boolean isAnagram(String s, String t) {
        if (s.length() == t.length()) {
            char[] charArrS = s.toCharArray();
            char[] charArrT = t.toCharArray();

            Arrays.sort(charArrS);
            Arrays.sort(charArrT);

            return Arrays.equals(charArrS, charArrT);
        }
        return false;
    }

    public static void main(String[] args) {
        _0242_ValidAnagram2 solution = new _0242_ValidAnagram2();
        boolean result = solution.isAnagram("tar","rat");
        System.out.println(result);
    }
}
