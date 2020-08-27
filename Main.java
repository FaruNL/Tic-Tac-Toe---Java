package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[][] board = new char[3][3];
        for (char[] vector : board) {
            Arrays.fill(vector, '_');
        }
        printFormattedBoard(board);

        String SposX;
        String SposY;
        int count = 0;
        while (true) {

            do {
                System.out.print("Enter the coordinates: ");
                SposX = sc.next();
                SposY = sc.next();
            } while (!isInsertable(board, SposX, SposY));

            int posX = Integer.parseInt(SposX);
            int posY = Integer.parseInt(SposY);
            count++;

            char mark = count % 2 == 0 ? 'O' : 'X';
            insertMark(board, posX, posY, mark);

            printFormattedBoard(board);
            if (count >= 5) {
                if (winXorO(board, 'X')) {
                    System.out.println("X wins");
                    break;
                } else if (winXorO(board, 'O')) {
                    System.out.println("O wins");
                    break;
                } else if (isDraw(board)) {
                    System.out.println("Draw");
                    break;
                }
            }
        }
    }

    public static void printFormattedBoard(char[][] chars) {
        System.out.println("---------");
        for (char[] vector : chars) {
            System.out.printf("| %c %c %c |%n", vector[0], vector[1], vector[2]);
        }
        System.out.println("---------");
    }

    public static boolean winXorO(char[][] matrix, char mark) {
        /*
         * Based on sum of neighbors
         */
        // Horizontal & Vertical
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (x != y) {
                    continue;
                }
                if (matrix[y][x] == mark && matrix[y][x] == matrix[y][(x + 1 + 3) % 3] && matrix[y][x] == matrix[y][(x - 1 + 3) % 3]) {
                    return true;
                } else if (matrix[y][x] == mark && matrix[y][x] == matrix[(y + 1 + 3) % 3][x] && matrix[y][x] == matrix[(y - 1 + 3) % 3][x]) {
                    return true;
                }
            }
        }
        // Diagonal
        if (matrix[0][0] == mark && matrix[1][1] == mark && matrix[2][2] == mark) {
            return true;
        } else return matrix[0][2] == mark && matrix[1][1] == mark && matrix[2][0] == mark;
    }

    public static boolean isDraw(char[][] matrix) {
            for (char[] vector : matrix) {
                for (char mark : vector) {
                    if (mark == '_') {
                        return false;
                    }
                }
            }
        return true;
    }

    public static boolean isInsertable(char[][] matrix, String SposX, String SposY) {
        if (!SposX.matches("\\d") || !SposY.matches("\\d")) {
            System.out.println("You should enter numbers!");
            return false;
        }
        int posX = Integer.parseInt(SposX);
        int posY = Integer.parseInt(SposY);
        if (posX < 0 || posX > 3 || posY < 0 || posY > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
        if (matrix[(2 - (posY - 1)) % 3][posX - 1] != '_') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        return true;
    }

    public static void insertMark(char[][] matrix, int posX, int posY, char mark) {
        matrix[(2 - (posY - 1)) % 3][posX - 1] = mark;
    }
}
/*
---------  ---------  ---------
| X X X |  | - - - |  | - - - |
| - - - |  | X X X |  | - - - |
| - - - |  | - - - |  | X X X |
---------  ---------  ---------
---------  ---------  ---------
| X - - |  | - X - |  | - - X |
| X - - |  | - X - |  | - - X |
| X - - |  | - X - |  | - - X |
---------  ---------  ---------
---------  ---------
| - - X |  | X - - |
| - X - |  | - X - |
| X - - |  | - - X |
---------  ---------
 */
