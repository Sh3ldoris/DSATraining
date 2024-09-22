package excercise.arrays_hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class SolutionMergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> result = new ArrayList<>();
        for (int[] arr: intervals) {
            if (result.isEmpty()) {
                result.add(arr);
                continue;
            }

            int[] last = result.getLast();
            boolean overlap = arr[0] <= last[1];

            if (overlap) {
                last[1] = Math.max(last[1], arr[1]);
            } else {
                result.add(arr);
            }
        }

        return result.toArray(new int[result.size()][2]);
    }

    public static void main(String[] args) {
        SolutionMergeIntervals solution = new SolutionMergeIntervals();
        int[][] intervals = {{1, 4}, {0, 2}, {3, 5}};
        int[][] merged = solution.merge(intervals);
        for (int[] mergedInterval : merged) {
            System.out.println("[" + mergedInterval[0] + ", " + mergedInterval[1] + "]");
        }
    }
}
