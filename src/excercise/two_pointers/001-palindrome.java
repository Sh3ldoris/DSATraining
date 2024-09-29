package excercise.two_pointers;

class SolutionIsPalindrome {
    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int si = 0;
        int ei = s.length() - 1;
        while (si < ei) {
            while (si < ei && !Character.isLetterOrDigit(chars[si])) {
                si++;
            }
            while (si < ei && !Character.isLetterOrDigit(chars[ei])) {
                ei--;
            }

            if (Character.toLowerCase(chars[si]) != Character.toLowerCase(chars[ei])) {
                return false;
            }

            si++;
            ei--;
        }

        return true;
    }

    public static void main(String[] args) {
        SolutionIsPalindrome solution = new SolutionIsPalindrome();
        System.out.println(solution.isPalindrome("0P")); // Output: true
        System.out.println(solution.isPalindrome("race a car")); // Output: false
    }
}
