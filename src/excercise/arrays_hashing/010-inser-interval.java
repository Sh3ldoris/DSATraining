package excercise.arrays_hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SolutionInsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // Case that there is no intervals, just new interval
        if (intervals.length == 0) {
            return new int[][]{newInterval};
        }

        // New intervals is before intervals
        if (intervals[0][0] > newInterval[1]) {
            int[][] result = Arrays.copyOf(new int[][]{newInterval}, 1 + intervals.length);
            System.arraycopy(intervals, 0, result, 1, intervals.length);
            return result;
        }

        // New intervals is after all intervals
        if (intervals[intervals.length - 1][1] < newInterval[0]) {
            int[][] result = Arrays.copyOf(intervals, 1 + intervals.length);
            System.arraycopy(new int[][]{newInterval}, 0, result, intervals.length, 1);
            return result;
        }


        List<int[]> result = new ArrayList<>();
        for (int[] arr : intervals) {
            if (result.isEmpty()) {
                // New interval is all smaller than current iterated
                if (newInterval[1] < arr[0]) {
                    result.add(newInterval);
                    result.add(arr);
                } else if (newInterval[0] <= arr[1]) {
                    // New interval's first value is smaller or equal to current's second value
                    arr[0] = Math.min(arr[0], newInterval[0]);
                    arr[1] = Math.max(arr[1], newInterval[1]);
                    result.add(arr);
                } else {
                    // New interval is all greater than current iterated
                    result.add(arr);
                }
                continue;
            }


            int[] last = result.getLast();
            // New interval overlaps with last interval
            if (overlaps(last, newInterval[0])) {
                last[1] = Math.max(last[1], newInterval[1]);
            }
            // New interval doesn't overlap with lastInterval and
            // as whole is smaller than current iterated
            if (!overlaps(last, newInterval[0]) && newInterval[0] > last[1] && newInterval[1] < arr[0]) {
                result.add(newInterval);
                last = newInterval;
            }
            // New interval doesn't overlap whit last and
            // its first value is smaller or equal than second value of current iterated (new interval overlaps with current)
            if (!overlaps(last, newInterval[0]) && newInterval[0] > last[1] && newInterval[0] <= arr[1]) {
                arr[0] = Math.min(arr[0], newInterval[0]);
                arr[1] = Math.max(arr[1], newInterval[1]);
            }
            boolean currentOverlapsWithLast = overlaps(last, arr[0]);

            if (currentOverlapsWithLast) {
                last[1] = Math.max(last[1], arr[1]);
            } else {
                result.add(arr);
            }
        }

        return result.toArray(new int[result.size()][2]);
    }

    public int[][] insertBetter(int[][] intervals, int[] newInterval) {
        // Case that there is no intervals, just new interval
        if (intervals.length == 0) {
            return new int[][]{newInterval};
        }

        // New intervals is before intervals
        if (intervals[0][0] > newInterval[1]) {
            int[][] result = Arrays.copyOf(new int[][]{newInterval}, 1 + intervals.length);
            System.arraycopy(intervals, 0, result, 1, intervals.length);
            return result;
        }

        // New intervals is after all intervals
        if (intervals[intervals.length - 1][1] < newInterval[0]) {
            int[][] result = Arrays.copyOf(intervals, 1 + intervals.length);
            System.arraycopy(new int[][]{newInterval}, 0, result, intervals.length, 1);
            return result;
        }

        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            if (newInterval[1] < intervals[i][0]) {
                result.add(newInterval);
                result.addAll(Arrays.asList(intervals).subList(i, intervals.length));
                return result.toArray(new int[result.size()][2]);
            } else if (newInterval[0] > intervals[i][1]) {
                result.add(intervals[i]);
            } else {
                // Merge intervals
                newInterval = new int[]{Math.min(intervals[i][0], newInterval[0]), Math.max(intervals[i][1], newInterval[1])};
            }
        }

        result.add(newInterval);

        return result.toArray(new int[result.size()][2]);
    }

    public static boolean overlaps(int[] interval, int v) {
        return interval[0] <= v && interval[1] >= v;
    }

    public static void main(String[] args) {
        SolutionInsertInterval solution = new SolutionInsertInterval();
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};
        int[][] result = solution.insertBetter(intervals, newInterval);
        for (int[] interval : result) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }
    }
}
