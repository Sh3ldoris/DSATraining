package excercise.arrays_hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class SolutionValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        // Check valid rows
        for (char[] row : board) {
            Set<Character> rowNums = new HashSet<>();
            for (char rc : row) {
                if ('.' == rc) {
                    continue;
                }
                if (rowNums.contains(rc)) {
                    return false;
                }
                rowNums.add(rc);
            }
        }

        // Check valid columns
        for (int i = 0; i < board.length; i++) {
            Set<Character> columnNums = new HashSet<>();
            for (int j = 0; j < board.length; j++) {
                char cc = board[j][i];
                if ('.' == cc) {
                    continue;
                }
                if (columnNums.contains(cc)) {
                    return false;
                }
                columnNums.add(cc);
            }
        }

        // Check valid 3x3 sub-grids
        // Init sets for each sub-grid
        Map<String, Set<Character>> subGridSets = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                String sKey = String.format("%d%d", i / 3, j / 3);
                subGridSets.put(sKey, new HashSet<>());
            }
        }

        // Walk the sub-grids and check
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char c = board[i][j];
                if ('.' == c) {
                    continue;
                }
                String sKey = String.format("%d%d", i / 3, j / 3);
                Set<Character> s = subGridSets.get(sKey);
                if (s.contains(c)) {
                    return false;
                }
                s.add(c);
            }
        }

        return true;
    }

    public boolean isValidSudokuOptimized(char[][] board) {
        // Check valid 3x3 sub-grids
        // Init sets for each sub-grid, row, and column
        Map<String, Set<Character>> subGridSets = new HashMap<>();
        Map<Integer, Set<Character>> rowSets = new HashMap<>();
        Map<Integer, Set<Character>> columnSets = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                String sKey = String.format("%d%d", i / 3, j / 3);
                subGridSets.computeIfAbsent(sKey, k -> new HashSet<>());
                rowSets.computeIfAbsent(i, k -> new HashSet<>());
                columnSets.computeIfAbsent(j, k -> new HashSet<>());
            }
        }

        // Walk the sub-grids and check
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char c = board[i][j];
                if ('.' == c) {
                    continue;
                }
                String sKey = String.format("%d%d", i / 3, j / 3);
                Set<Character> s = subGridSets.get(sKey);
                if (s.contains(c))
                    return false;
                Set<Character> r = rowSets.get(i);
                if (r.contains(c))
                    return false;
                Set<Character> cs = columnSets.get(j);
                if (cs.contains(c))
                    return false;

                s.add(c);
                r.add(c);
                cs.add(c);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        SolutionValidSudoku solution = new SolutionValidSudoku();
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(solution.isValidSudokuOptimized(board)); // Output:
    }
}
