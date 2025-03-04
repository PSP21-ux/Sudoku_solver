import java.util.Random;

/**
 * Sudoku Solver using Backtracking Algorithm
 * Generates a Sudoku puzzle and solves it.
 */
class Sudoku_solve {
    private int[][] grid = new int[9][9];
    private Random random = new Random();

    /** Constructor: Generates a Sudoku grid with random values */
    public Sudoku_solve() {
        generateGrid();
    }

    /** Generates a random Sudoku grid with some pre-filled values */
    private void generateGrid() {
        for (int i = 0; i < 10; i++) { // Fill 10 random cells
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            int num = random.nextInt(9) + 1;
            if (isSafe(row, col, num)) {
                grid[row][col] = num;
            }
        }
    }

    /** Checks if placing a number is safe */
    private boolean isSafe(int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == num || grid[i][col] == num) {
                return false;
            }
        }

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

    /** Solves the Sudoku using backtracking */
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

    /** Finds an empty cell in the grid */
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

    /** Prints the Sudoku grid in a readable format */
    public void printGrid() {
        System.out.println("+-------+-------+-------+");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) System.out.print("| ");
                System.out.print(grid[i][j] == 0 ? ". " : grid[i][j] + " ");
            }
            System.out.println("|");
            if ((i + 1) % 3 == 0) {
                System.out.println("+-------+-------+-------+");
            }
        }
    }

    /** Main method to run the Sudoku solver */
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
