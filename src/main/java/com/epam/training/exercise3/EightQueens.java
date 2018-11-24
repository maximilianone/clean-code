package com.epam.training.exercise3;

import java.util.ArrayList;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

/**
 * Class to place eight quines without interceptions.
 */
@Slf4j
public final class EightQueens {
    private static final int BOARD_LENGTH = 8;

    private EightQueens() {
    }

    /**
     * Main method.
     *
     * @param args - starting values
     */
    public static void main(String[] args) {
        ArrayList<char[][]> solutions = new ArrayList<>();

        char[][] result = new char[BOARD_LENGTH][BOARD_LENGTH];
        for (int r1 = 0; r1 < BOARD_LENGTH; r1++) {
            Arrays.fill(result[r1], '.');
        }

        solveAllNQueens(result, 0, solutions);

        log.info("" + solutions.size());
        showSolutions(solutions);
    }

    /**
     * Recursive method.
     *
     * @param board     - checks board representation
     * @param col       - column
     * @param solutions - solutions
     */
    private static void solveAllNQueens(char[][] board, int col, ArrayList<char[][]> solutions) {
        if (col == board.length) {
            char[][] copy = copyArray(board);
            solutions.add(copy);
        } else {
            for (int row = 0; row < board.length; row++) {
                board[row][col] = 'q';

                if (canBeSafe(board)) {
                    solveAllNQueens(board, col + 1, solutions);
                }
                board[row][col] = '.';
            }
        }
    }

    /**
     * Method to find if coordinates are in board bounds.
     *
     * @param row   - board row
     * @param col   - board column
     * @param board - board
     * @return true if coordinates are in board bounds
     */
    private static boolean inbounds(int row, int col, char[][] board) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }

    private static boolean checkVertical(char[][] board) {
        boolean canBeSafe = true;
        for (char[] aBoard : board) {
            boolean found = false;
            for (int j = 0; j < board.length; j++) {
                if (aBoard[j] == 'q') {
                    if (found) {
                        canBeSafe = false;
                    }
                    found = true;
                }
            }
        }
        return canBeSafe;
    }

    private static boolean checkDiagonal(char[][] board) {
        boolean canBeSafe = true;
        for (int offset = -board.length; offset < board.length; offset++) {
            boolean found = false;
            for (int i = 0; i < board.length; i++) {
                if (checkIfCanBeSafe(i, board.length - offset - i - 1, board)) {
                    if (found) {
                        canBeSafe = false;
                    }
                    found = true;
                }
            }
        }
        return canBeSafe;
    }

    private static boolean checkReverseDiagonal(char[][] board) {
        boolean canBeSafe = true;
        for (int offset = -board.length; offset < board.length; offset++) {
            boolean found = false;
            for (int i = 0; i < board.length; i++) {
                if (checkIfCanBeSafe(i, i + offset, board)) {
                    if (found) {
                        canBeSafe = false;
                    }
                    found = true;
                }
            }
        }
        return canBeSafe;
    }

    private static boolean canBeSafe(char[][] board) {
        return checkVertical(board) && checkDiagonal(board) && checkReverseDiagonal(board);
    }

    private static void showSolutions(ArrayList<char[][]> solutions) {
        for (int i = 0; i < solutions.size(); i++) {
            log.info("\nSolution " + (i + 1));

            char[][] board = solutions.get(i);

            final StringBuilder builder = new StringBuilder();
            for (char[] aBoard : board) {
                builder.setLength(0);
                for (char anABoard : aBoard) {
                    builder.append(anABoard);
                }
                log.info(builder.toString());
            }
        }
    }

    private static char[][] copyArray(char[][] array) {
        char[][] copy = new char[array.length][array[0].length];
        for (int r = 0; r < array.length; r++) {
            System.arraycopy(array[r], 0, copy[r], 0, array[0].length);
        }
        return copy;
    }

    private static boolean checkIfCanBeSafe(int index, int column, char[][] board) {
        return inbounds(index, column, board) && board[index][column] == 'q';
    }
}
