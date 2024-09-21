package excercise.arrays_hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class SolutionKMostFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        // Init the counts
        for (int num : nums) {
            int count = counts.getOrDefault(num, 0) + 1;
            counts.put(num, count);
        }

        List<Map.Entry<Integer, Integer>> countsList = new ArrayList<>(counts.entrySet());
        // Sort the counts by the most frequent
        countsList.sort(Map.Entry.comparingByValue());

        int[] mostFrequent = new int[k];
        for (int i = 0; i < k; i++) {
            mostFrequent[i] = countsList.get(countsList.size() - 1 - i).getKey();
        }

        return mostFrequent;
    }

    public int[] topKFrequentBetter(int[] nums, int k) {
        // Store the count of element occurrences
        Map<Integer, Integer> counts = new HashMap<>();
        // Store the elements by frequencies
        ArrayList<Integer>[] frequencies = (ArrayList<Integer>[]) new ArrayList[nums.length + 1];
        // Init the counts
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            ArrayList<Integer> frequency = frequencies[entry.getValue()];

            if (Objects.isNull(frequency)) {
                frequency = new ArrayList<>();
                frequency.add(entry.getKey());
                frequencies[entry.getValue()] = frequency;
            } else {
                frequency.add(entry.getKey());
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = frequencies.length - 1; i >= 0; i--) {
            List<Integer> frequency = frequencies[i];
            if (Objects.nonNull(frequency) && !frequency.isEmpty()) {
                for (int val: frequency) {
                    if (result.size() == k) {
                        return result.stream().mapToInt(Integer::intValue).toArray();
                    } else {
                        result.add(val);
                    }
                }
            }
        }


        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        var s = new SolutionKMostFrequent();

        System.out.println(Arrays.toString(s.topKFrequentBetter(new int[]{9, 9, 9, 2, 2, 3, 4}, 2)));
    }
}
