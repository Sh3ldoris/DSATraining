package excercise.arrays_hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SolutionGroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
         return Collections.emptyList();
        }

        Map<String, List<String>> anagrams = new HashMap<>();
        for (String str : strs) {
            String sorted = sortString(str);

            List<String> anagramsList = anagrams.getOrDefault(sorted, new ArrayList<>());
            anagramsList.add(str);
            anagrams.put(sorted, anagramsList);
        }

        return anagrams.values().stream().toList();
    }

    public static String sortString(String unsorted) {
        char[] chars = unsorted.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
