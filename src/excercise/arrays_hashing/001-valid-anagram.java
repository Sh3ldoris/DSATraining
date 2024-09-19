package excercise.arrays_hashing;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        // Will hold the char counts
        Map<Character, Integer> cCounts = new HashMap<>();

        // Break s string to chars
        for (char c : s.toCharArray()) {
            int count = cCounts.getOrDefault(c, 0);
            cCounts.put(c, ++count);
        }

        // Break t string to chars and remove counts of s chars
        for (char c : t.toCharArray()) {
            int count = cCounts.getOrDefault(c, -1);
            if (count == -1) {
                // The char is not in the s string
                return false;
            }

            count--;
            if (count == 0) {
                // The char is already counted as 0 in s string
                cCounts.remove(c);
            } else {
                cCounts.put(c, count);
            }

        }

        return cCounts.isEmpty();
    }

    public boolean isAnagramBoosted(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] counts = new int[26]; // 26 letters in alphabet

        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            counts[c - 'a']--;
            if (counts[c - 'a'] < 0) {
                return false;
            }
        }

        for (int i : counts) {
            if (i!= 0) {
                return false;
            }
        }

        return true;
    }
}
