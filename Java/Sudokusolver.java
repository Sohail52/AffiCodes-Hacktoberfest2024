public class SudokuSolver {

    // Main method to solve the Sudoku puzzle using backtracking
    public boolean solveSudoku(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {  // Find an empty cell
                    for (int num = 1; num <= 9; num++) {
                        if (isValidPlacement(board, row, col, num)) {  // Check if the number can be placed
                            board[row][col] = num;  // Place the number

                            if (solveSudoku(board)) {
                                return true;  // Solved
                            }

                            board[row][col] = 0;  // Backtrack if not solvable
                        }
                    }
                    return false;  // If no number can be placed
                }
            }
        }
        return true;  // Puzzle solved
    }

    // Check if placing a number in the current row, column, or 3x3 grid is valid
    private boolean isValidPlacement(int[][] board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;  // Number is already in the row or column
            }
        }

        int gridRowStart = row - row % 3;
        int gridColStart = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[gridRowStart + i][gridColStart + j] == num) {
                    return false;  // Number is already in the 3x3 grid
                }
            }
        }

        return true;  // Number placement is valid
    }

    // Method to print the Sudoku board
    public void printBoard(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    // Main method to test the solver
    public static void main(String[] args) {
        SudokuSolver solver = new SudokuSolver();

        int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        System.out.println("Original Sudoku Board:");
        solver.printBoard(board);

        if (solver.solveSudoku(board)) {
            System.out.println("\nSolved Sudoku Board:");
            solver.printBoard(board);
        } else {
            System.out.println("\nNo solution exists for the given Sudoku board.");
        }
    }
}
