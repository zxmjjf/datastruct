package com.recursive.maze;

import java.util.Random;

/**
 * @version 1.0.1
 * @author jjf 2017-7-14
 * 迷宫类：创建迷宫，求解路径
 */
public class Maze {
    private final int TRIED = 3;
    private final int PATH = 7;
    public static int cur = 0; //记录查找路径的次数
    public static int root = 0;

    /*private int[][] grid = {
            {1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0 ,0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0},
            {1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1},
            {1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };*/
    private int[][] grid = new int[15][15];
    private Random random = new Random();

    /**
     * 构造函数:
     * 初始化迷宫，随机数
     */
    public Maze(){
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                int num = random.nextInt(4);
                if (num == 0) {
                    grid[row][col] = 0;
                }else {
                    grid[row][col] = 1;
                    root++;
                }
            }
        }
        grid[0][0] = 1;
        grid[grid.length -1][grid[0].length -1] = 1;
    }

    /**
     * @param row
     * @param col
     *  递归方法，求解迷宫路径
     */
    public boolean traverse(int row, int col) {
        ++cur;
        boolean done = false;

        if (valid(row, col)) {
            /*如果当前的格子不为墙或不为已经走过的，则进行下一步操作*/
            grid[row][col] = TRIED; /*当前格子可行，设为已走过的*/

            /*判断当前格子是否已经是出口*/
            if (row == grid.length - 1 && col == grid[0].length - 1) {
                done = true; /*到达出口则done值为true*/
            }else {
                /*不是，则在走下一个格子，分别以当前格子为基准，下，右，上，坐的顺序继续走*/
                done = traverse(row + 1, col);
                //只要done值不为true，则说明还没到终点
                if (!done) {
                    done = traverse(row, col + 1);
                }
                if (!done) {
                    done = traverse(row - 1, col);
                }
                if (!done) {
                    done = traverse(row, col - 1);
                }
            }

            if (done) {
                grid[row][col] = PATH; //把走通的道路设为7
            }
        }

        return done;
    }

    /**
     * @param row
     * @param col
     * @return result
     * 功能：判断某一步中是否为1
     */
    private boolean valid(int row, int col) {
        boolean result = false;

        if (row >= 0 && row < grid.length && col >= 0 && col < grid[row].length) {
            if (grid[row][col] == 1) {
                result = true;
            }
        }

        return result;
    }

    public String toString() {
        String result = "\n";

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                result += grid[row][col] + " ";
            }
            result += "\n";
        }

        return result;
    }

}
