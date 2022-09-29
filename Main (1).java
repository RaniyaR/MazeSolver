

import java.util.*;
import java.io.*;

public class Main {
    private static int rows, cols;
    private static int startRow, startCol, endRow, endCol;
    private static char[][] maze;

    public static char[][] getMaze(String filename) {
        char[] lineArray = null;
        char[][] maze = null;

        try {
            Scanner scan = new Scanner(System.in);
            BufferedReader r = new BufferedReader(new FileReader(filename));
            String RowsAndCols = r.readLine(); // gets rows and columns from first line of file
            String[] arrOfRowsAndCols = RowsAndCols.split(" ", 2); // stores number of rows and columns in an array
            rows = Integer.parseInt(arrOfRowsAndCols[0]); // gets number of rows from the array of rows and columns
            cols = Integer.parseInt(arrOfRowsAndCols[1]); // gets number of columns from the array of rows and columns
            maze = new char[rows][cols]; // size of maze array

            for (int i = 0; i < maze.length; i++) {
                RowsAndCols = r.readLine(); // gets the first full line of the maze
                lineArray = RowsAndCols.toCharArray(); // stores the line in a char array
                for (int j = 0; j < maze[0].length; j++) {
                    maze[i][j] = lineArray[j]; // assigns values from the lineArray to the maze array
                }
            }

        } catch (IOException e) {
            System.out.println("Cannot load file and/or get maze.");
        }
        return maze;
    }

    public static int getStartRow(char[][] givenMaze) { // gets row of starting point
        rows = givenMaze.length;
        cols = givenMaze[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (givenMaze[r][c] == '+') {
                    startRow = r;
                }
            }
        }
        return startRow;
    }

    public static int getEndRow(char[][] givenMaze) { // gets row of ending point
        rows = givenMaze.length;
        cols = givenMaze[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (givenMaze[r][c] == '-') {
                    endRow = r;
                }
            }
        }
        return endRow;
    }

    public static int getStartCol(char[][] givenMaze) { // gets column of starting point
        rows = givenMaze.length;
        cols = givenMaze[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (givenMaze[r][c] == '+') {
                    startCol = c;
                }
            }
        }
        return startCol;
    }

    public static int getEndCol(char[][] givenMaze) { // gets column of ending point
        rows = givenMaze.length;
        cols = givenMaze[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (givenMaze[r][c] == '-') {
                    endCol = c;
                }
            }
        }
        return endCol;
    }

    public static boolean isValid(char[][] givenMaze, int r, int c) {
        boolean valid = false;// checks if a position is valid and can be traversed through
        if (r > 0 && r < givenMaze.length && c >= 0 && c < givenMaze[0].length) {
            if (givenMaze[r][c] == ' ') {
                valid = true;
            }
        }
        return valid;
    }

    public static boolean traverse(char[][] givenMaze, int r, int c) {
        boolean solved = false;
        if (isValid(givenMaze, r, c)) {
            givenMaze[r][c] = '.';
            int endR = getEndRow(givenMaze);
            int endC = getEndCol(givenMaze);
            if (r == endR && c == endC - 1) {
                solved = true;
            } else {
                if (solved == false) {
                    solved = traverse(givenMaze, (r - 1), c);
                }
                if (solved == false) {
                    solved = traverse(givenMaze, (r + 1), c);
                }
                if (solved == false) {
                    solved = traverse(givenMaze, r, (c - 1));
                }
                if (solved == false) {
                    solved = traverse(givenMaze, r, (c + 1));
                }

            }
            if (solved) {
                givenMaze[r][c] = '+';
                System.out.println("----------------------------");
                for (char[] chars : givenMaze) {
                    System.out.println(Arrays.toString(chars));
                }
                System.out.println("----------------------------");


            }
        }
        return solved;

    }

    public static void main(String[] args) {

        int startR;
        int startC;

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name of the file: ");
        String file = scan.nextLine();
        char[][] newMaze = getMaze(file);
        startR = getStartRow(getMaze(file));
        startC = getStartCol(getMaze(file));
        boolean mazeTraversal = traverse(getMaze(file), startR, startC + 1);
        if (mazeTraversal == true) {
            System.out.println("This maze has a solution!");
        } else {
            System.out.println("No solution found.");
        }

    }
}





















