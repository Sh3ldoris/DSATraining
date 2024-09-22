package excercise.arrays_hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class SolutionLongestConsecutiveSeq {
    // By Sorting nums
    public int longestConsecutive(int[] nums) {
        if (Objects.isNull(nums) || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int count = 1;
        int maxCount = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                // Skip duplicates
                continue;
            }
            if (nums[i - 1] + 1 == nums[i]) {
                count++;
                continue;
            }

            maxCount = Math.max(maxCount, count);
            count = 1;
        }

        return Math.max(maxCount, count);
    }

    public int longestConsecutiveNotSorting(int[] nums) {
        if (Objects.isNull(nums) || nums.length == 0) {
            return 0;
        }

        Set<Integer> numsSet = new HashSet<>();
        for (int num : nums) {
            numsSet.add(num);
        }

        int maxCount = 0;
        for (int num : nums) {
            // Check if num is not already in sub-seq
            if (numsSet.contains(num - 1)) {
                // nums is already in some sub-seq
                continue;
            }

            // Check if num has successor
            int curr = num;
            int seqSize = 0;
            while (numsSet.contains(curr)) {
                seqSize++;
                curr++;
            }
            maxCount = Math.max(maxCount, seqSize);
        }

        return maxCount;
    }

    public static void main(String[] args) {
        SolutionLongestConsecutiveSeq solution = new SolutionLongestConsecutiveSeq();
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println(solution.longestConsecutiveNotSorting(nums)); // Output: 4
    }
}
