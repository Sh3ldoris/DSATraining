package excercise.arrays_hashing;

import java.util.HashMap;
import java.util.Map;

class SolutionTwoSum {
    public int[] twoSum(int[] nums, int target) {
        // Check the input
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        // Store the difference of i from nums and target
        Map<Integer, Integer> diffs = new HashMap<>();
        // Loop the nums
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            int diff = target - curr;
            // If there is already difference of target - 1 return the appropriate num with current num
            if (diffs.containsKey(diff)) {
                return new int[]{diffs.get(diff), i};
            }

            diffs.put(curr, i);
        }

        return new int[0];
    }
}
