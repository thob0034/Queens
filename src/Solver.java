import java.util.*;

/**
 * Write a description of class Solver here.
 *
 * @Thobias Christensen og Christian Weis
 * @24-10-17
 */
public class Solver {

    private int noOfQueens;     //Number of queens you want to solve for
    private int[] queens;   //Positions of queens
    private int noOfSolutions;  //Number of Solutions for the problem
    private long timeStarted;   // Keep time of when we started
    private boolean showSolutions;  //Whether or not to print solutions we find
    private long duration; // Time taken

    /**
     * Find all the solutions for a given number of queens
     *
     * @noOfQueens The number of queens to place, also the number of rows and columns on the board.
     */
    public void findAllSolutions(int noOfQueens, boolean showSolutions) {
        this.noOfQueens = noOfQueens;
        this.showSolutions = showSolutions;
        queens = new int[noOfQueens];
        noOfSolutions = 0;
        timeStarted = System.currentTimeMillis();
        if (showSolutions) {
            System.out.println("*********************************************");
            System.out.println("Solutions for " + noOfQueens + " queens:");
            System.out.println("");
        }
        positionQueens(0);
        duration = (System.currentTimeMillis() - timeStarted);
        if (showSolutions) {
            System.out.println("");
            System.out.println("A total of " + noOfSolutions + " solutions were found in " + duration + " ms");
            System.out.println("*********************************************");
        }
    }

    /**
     * Place a queen on a legal position in the row
     *
     * @row The current row to try and place a queen
     */
    private void positionQueens(int row) {
        if (row == noOfQueens) { //Check if all queens have been placed
            if (showSolutions) {
                printSolution();
                System.out.println("");
            }
            noOfSolutions++;
            return;
        }
        for (int col = 0; col < noOfQueens; col++) {
            if (legal(row, col)) {
                queens[row] = col;
                positionQueens(row + 1);
            }
        }
    }

    /**
     * Check if the position is legal to place a queen on
     *
     * @row the row to check (y-coordinate)
     * @col the column to check (x-coordinate)
     */
    private boolean legal(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (queens[row - i - 1] == col) {
                return false;
            }
            if (queens[row - i - 1] == col - i - 1) {
                return false;
            }
            if (queens[row - i - 1] == col + i + 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prints the current found solution
     */
    private void printSolution() {
        for (int i = 0; i < noOfQueens; i++) {
            System.out.print(convert(i, queens[i]) + " ");
        }
    }

    /**
     * Converts coordinates from an (x,y) format to the format used in chess.
     *
     * @row the row to convert to numbers from 1 to n.
     * @col the column to convert to letters from a to the n'th letter.
     */
    private String convert(int row, int col) {
        char[] chars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        return "" + chars[col] + (row + 1);
    }

    /**
     * Finds the number of solutions to queen problems between min and max, both included, and outputs relevant information to the terminal.
     *
     * @min the minimum queen problem to check
     * @max the maximum queen problem to check
     */
    public void findNoOfSolutions(int min, int max) {
        showSolutions = false;
        System.out.println("*********************************************");
        System.out.format("%3s%12s%12s%14s", "Queens", "Solutions", "Time(ms)", "Solutions/ms");
        System.out.println("");
        for (int i = min; i <= max; i++) {
            findAllSolutions(i, false);
            System.out.format("  %3d %,12d   %,8d     %,8d %n", i, noOfSolutions, duration, duration == 0 ? noOfSolutions : noOfSolutions / duration);
        }
        System.out.println("*********************************************");
    }

    public static void testSolver() {
        Solver s1 = new Solver();
        s1.findAllSolutions(1, true);
        s1.findAllSolutions(2, true);
        s1.findAllSolutions(6, true);
        s1.findNoOfSolutions(1, 12);
    }
}
