//Alex
//2020 J5/S2
//https://cemc.uwaterloo.ca/contests/computing/2020/ccc/juniorEF.pdf
//?/15 (1/15 on CCC Grader, 6/15 on DMOJ Grader)
//Random Error's and Wrong Output's even though I manually tested it????
//BFSearch Method

package com.company;

import java.util.*;
import java.io.*;

public class Main {

    static class Point {
        int x, y;
        public Point(int x, int y) {this.x = x; this.y = y;}
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num_col = Integer.parseInt(br.readLine());
        int num_row = Integer.parseInt(br.readLine());
        int[][] values = new int[num_col][num_row];

        for (int i = 0; i < num_col; i++) {
            values[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        }

        boolean[][] passed = new boolean[num_col][num_row];

        HashMap<Integer, ArrayList<Point>> keys = getKeys(num_col, num_row);
        //note that point coords aren't 0 based

        boolean good = search(values, keys, passed);

        if (good) System.out.println("yes");
        else System.out.println("no");
    }

    public static boolean search(int[][] values, HashMap<Integer, ArrayList<Point>> keys, boolean[][] passed) {
        ArrayList<Point> list = keys.get(values[0][0]);

        while (!list.isEmpty()) {
            Point p = list.remove(0);
            int x  = p.x;
            int y = p.y;

            if (passed[x - 1][y - 1]) continue;

            passed[x - 1][y - 1] = true;

            if (x == passed.length && y == passed[0].length) return true;

            int value = values[x - 1][y - 1];

            ArrayList<Point> temp = (keys.getOrDefault(value, null));
            if (temp != null) list.addAll(temp);
        }

        return false;
    }

    public static HashMap<Integer, ArrayList<Point>> getKeys(int num_col, int num_row) {
        HashMap<Integer, ArrayList<Point>> map = new HashMap<>();
        for (int x = 1; x <= num_col; x++) {
            for (int y = 1; y <= num_row; y++) {
                ArrayList<Point> temp = map.getOrDefault(x*y, new ArrayList<>());
                temp.add(new Point(x, y));
                map.put(x*y, temp);

            }
        }

        return map;
    }
}
