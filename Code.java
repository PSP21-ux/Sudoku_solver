import java.util.Random;

class Sudoku_solve {
    private int[][] grid = new int[9][9];
    private Random random = new Random();

    public Sudoku_solve() {
        generateGrid();
    }

    private void generateGrid() {
        // Randomly fill some cells to create a starting board
        for (int i = 0; i < 10; i++) { // Fill 10 random cells
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            int num = random.nextInt(9) + 1;
            if (isSafe(row, col, num)) {
                grid[row][col] = num;
            }
        }
    }

    private boolean isSafe(int row, int col, int num) {
        // Check row
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == num) {
                return false;
            }
        }

        // Check column
        for (int i = 0; i < 9; i++) {
            if (grid[i][col] == num) {
                return false;
            }
        }

        // Check 3x3 subgrid
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean solve() {
        int[] empty = findEmpty();
        if (empty == null) {
            return true; // Solved
        }

        int row = empty[0], col = empty[1];
        for (int num = 1; num <= 9; num++) {
            if (isSafe(row, col, num)) {
                grid[row][col] = num;
                if (solve()) {
                    return true;
                }
                grid[row][col] = 0; // Backtrack
            }
        }
        return false;
    }

    private int[] findEmpty() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public void printGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j] == 0 ? ". " : grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Sudoku_solve solver = new Sudoku_solve();
        System.out.println("Generated Sudoku:");
        solver.printGrid();
        if (solver.solve()) {
            System.out.println("\nSolved Sudoku:");
            solver.printGrid();
        } else {
            System.out.println("\nNo solution exists!");
        }
    }
}
