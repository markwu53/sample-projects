package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sudoku {

    public static void main(String[] args) {
        new Sudoku().go();
    }

    public static final int[] game = {
            6,8,7,5,0,0,4,0,0,
            1,2,0,7,0,4,8,0,3,
            0,0,0,8,0,0,0,5,0,
            4,0,0,2,8,0,1,7,0,
            2,0,9,4,0,0,6,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,1,6,0,0,5,2,8,
            7,0,8,0,0,2,0,0,0,
            0,0,0,3,0,0,0,0,0
    };

    public static final int[] game2 = {
            6,4,0,8,0,0,0,0,0,
            7,2,0,0,9,0,6,4,0,
            8,0,0,6,0,5,7,0,2,
            1,8,0,0,2,0,3,0,0,
            3,0,0,0,6,0,0,2,1,
            0,5,0,3,1,0,8,0,0,
            4,3,0,2,0,9,1,0,7,
            0,0,1,0,8,0,0,0,0,
            0,0,2,0,3,0,9,8,0
    };
    
    public static final int[] game1 = {
            6,8,2,1,0,0,0,0,9,
            0,0,0,6,9,7,2,5,0,
            9,7,0,0,4,0,0,1,3,
            7,0,1,2,8,0,4,3,0,
            3,0,0,0,7,0,8,0,1,
            0,6,0,0,1,0,0,0,0,
            0,0,7,5,2,9,0,6,0,
            0,2,0,0,0,0,0,9,0,
            0,3,9,0,0,1,5,0,2
    };
    public static final List<Integer> zeros = new ArrayList<Integer>();

    public void go() {
        printGame();
        indexZeros();
        //printZeros();
        //System.out.println(zeros.size());
        //printAllowedValues();
        fit(0);
    }
    
    public void printAllowedValues() {
        for (int level = 0; level < zeros.size(); level ++) {
            System.out.print(level);
            System.out.print(":");
            System.out.println(allowedValues(level));
        }
    }

    public void fit(int level) {
        for (Integer value: allowedValues(level)) {
            game[zeros.get(level)] = value;
            if (level == zeros.size() - 1) {
                //solution
                System.out.println("------------------------------");
                printGame();
            } else {
                fit(level + 1);
            }
        }
        game[zeros.get(level)] = 0;
    }

    public Set<Integer> allowedValues(int level) {
        Set<Integer> allowed = new HashSet<Integer>();
        allowed.addAll(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9}));
        Set<Integer> used = new HashSet<Integer>();
        int index = zeros.get(level);
        int row = index / 9;
        int col = index % 9;
        for (int i = 0; i < 9; i ++) {
            used.add(game[row * 9 + i]);
        }
        for (int i = 0; i < 9; i ++) {
            used.add(game[i * 9 + col]);
        }
        for (int i = 0; i < 9; i ++) {
            int r = row / 3 * 3 + i / 3;
            int c = col / 3 * 3 + i % 3;
            used.add(game[r * 9 + c]);
        }
        allowed.removeAll(used);
        return allowed;
    }

    public void printZeros() {
        for (Integer i: zeros) {
            System.out.print(i);
            System.out.print(",");
        }
    }

    public void indexZeros() {
        for (int i = 0; i < 81; i ++) {
            if (game[i] == 0) {
                zeros.add(i);
            }
        }
    }

    public void printGame() {
        for (int row = 0; row < 9; row ++) {
            for (int col = 0; col < 9; col ++) {
                int index = row * 9 + col;
                int number = game[index];
                if (number != 0) {
                    System.out.print(number);
                } else {
                    System.out.print("  ");
                }
                System.out.print("   ");
            }
            System.out.println();
        }
    }

}
