package com.recursive.maze;


import java.util.Random;

/**
 * @version 1.0.1
 * @author jjf 2017-7-16
 * 对Maze类的算法优化，降低算法消耗
 * 增加四个按方向查找出路的私有辅助函数：traverseMode1，traverseMode2，traverseMode3，traverseMode4
 * 和一个开始位置的私有辅助函数：traverseMode0.
 * 同时为了对比效益，在增加一个clone()方法，深拷贝对象本身，保证在对比的时候迷宫的结构一致
 * 解法由直接递归变为间接递归
 *
 */
public class OptimizeMaze implements Cloneable{
    private final int TRIED = 3;
    private final int PATH = 7;
    public static int cur = 0; //记录查找路径的次数
    public static int cur2 = 0; //记录查找路径的次数

    /**迷宫结构初始化*/
    /**private int[][] grid = {
            {1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0 ,0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0},
            {1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1},
            {1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };*/
    private int[][] grid = new int[20][70];
    private Random random = new Random();
    /**
     * 构造函数:
     * 初始化迷宫，随机数
     */
    public OptimizeMaze(){
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                int num = random.nextInt(3);
                if (num == 0) {
                    grid[row][col] = 0;
                }else {
                    grid[row][col] = 1;
                }
            }
        }
        grid[0][0] = 1;
        grid[grid.length -1][grid[0].length -1] = 1;
    }
    /**至此初始化完毕*/

    /**
     * 直接递归迷宫求解法
     * @param row
     * @param col
     * @return
     */
    public boolean oldtraverse(int row, int col) {
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
                done = oldtraverse(row + 1, col);
                //只要done值不为true，则说明还没到终点
                if (!done) {
                    done = oldtraverse(row, col + 1);
                }
                if (!done) {
                    done = oldtraverse(row - 1, col);
                }
                if (!done) {
                    done = oldtraverse(row, col - 1);
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
     *  递归方法，求解迷宫路径
     *  默认出口为入口坐标为
     */
    public boolean newtraverse(int row, int col) {
        return traverseMode0(row, col);
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

    /**拷贝函数*/
    @Override
    protected OptimizeMaze clone() throws CloneNotSupportedException{
        OptimizeMaze clone = null;
        try	{
            clone = (OptimizeMaze) super.clone();
        }catch(CloneNotSupportedException e){
            System.err.println(e.toString());
        }
        clone.grid = new int[20][70];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                clone.grid[row][col] = grid[row][col];
            }
        }

        return clone;
    }

/**私有辅助函数*/

    /**
     * Mode0 : 表示入口点
     **/
    private boolean traverseMode0(int row, int col) {
        ++cur2;
        boolean done = false;

        if (valid(row, col)) {
            /*如果当前的格子不为墙或不为已经走过的，则进行下一步操作*/
            grid[row][col] = TRIED; /*当前格子可行，设为已走过的*/

            /*判断当前格子是否已经是出口*/
            if (row == grid.length - 1 && col == grid[0].length - 1) {
                done = true; /*到达出口则done值为true*/
            }else {
                /*不是，则在走下一个格子，分别以当前格子为基准，下，右，上，坐的顺序继续走*/
                done = traverseMode1(row + 1, col);
                //只要done值不为true，则说明还没到终点
                if (!done) {
                    done = traverseMode2(row, col + 1);
                }
                if (!done) {
                    done = traverseMode3(row - 1, col);
                }
                if (!done) {
                    done = traverseMode4(row, col - 1);
                }

            }

            if (done) {
                grid[row][col] = PATH; //把走通的道路设为7
            }
        }

        return done;
    }

    /**
     * Mode1 : 表示向下
     **/
    private boolean traverseMode1(int row, int col) {
        ++cur2;
        boolean done = false;

        if (valid(row, col)) {
            /*如果当前的格子不为墙或不为已经走过的，则进行下一步操作*/
            grid[row][col] = TRIED; /*当前格子可行，设为已走过的*/

            /*判断当前格子是否已经是出口*/
            if (row == grid.length - 1 && col == grid[0].length - 1) {
                done = true; /*到达出口则done值为true*/
            }else {
                /*不是，则在走下一个格子，分别以当前格子为基准，下，右，上，坐的顺序继续走*/
                done = traverseMode1(row + 1, col);
                //只要done值不为true，则说明还没到终点
                if (!done) {
                    done = traverseMode2(row, col + 1);
                }
                if (!done) {
                    done = traverseMode4(row, col - 1);
                }

            }

            if (done) {
                grid[row][col] = PATH; //把走通的道路设为7
            }
        }

        return done;
    }

    /**
     * Mode2 ：表示向右
     */
    private boolean traverseMode2(int row, int col) {
        ++cur2;
        boolean done = false;

        if (valid(row, col)) {
            /*如果当前的格子不为墙或不为已经走过的，则进行下一步操作*/
            grid[row][col] = TRIED; /*当前格子可行，设为已走过的*/

            /*判断当前格子是否已经是出口*/
            if (row == grid.length - 1 && col == grid[0].length - 1) {
                done = true; /*到达出口则done值为true*/
            }else {
                /*不是，则在走下一个格子，分别以当前格子为基准，下，右，上，坐的顺序继续走*/
                done = traverseMode1(row + 1, col); //1
                //只要done值不为true，则说明还没到终点
                if (!done) {
                    done = traverseMode2(row, col + 1); //2
                }
                if (!done) {
                    done = traverseMode3(row - 1, col); //4
                }
            }

            if (done) {
                grid[row][col] = PATH; //把走通的道路设为7
            }
        }

        return done;
    }

    /**
     * Mode3：表示向上
     */
    private boolean traverseMode3(int row, int col) {
        ++cur2;
        boolean done = false;

        if (valid(row, col)) {
            /*如果当前的格子不为墙或不为已经走过的，则进行下一步操作*/
            grid[row][col] = TRIED; /*当前格子可行，设为已走过的*/

            /*判断当前格子是否已经是出口*/
            if (row == grid.length - 1 && col == grid[0].length - 1) {
                done = true; /*到达出口则done值为true*/
            }else {
                /*不是，则在走下一个格子，分别以当前格子为基准，下，右，上，坐的顺序继续走*/
                done = traverseMode2(row, col + 1);
                //只要done值不为true，则说明还没到终点

                if (!done) {
                    done = traverseMode3(row - 1, col);
                }
                if (!done) {
                    done = traverseMode4(row, col - 1);
                }
            }

            if (done) {
                grid[row][col] = PATH; //把走通的道路设为7
            }
        }

        return done;
    }

    /**
     * Mode4：表示向左
     */
    private boolean traverseMode4(int row, int col) {
        ++cur2;
        boolean done = false;

        if (valid(row, col)) {
            /*如果当前的格子不为墙或不为已经走过的，则进行下一步操作*/
            grid[row][col] = TRIED; /*当前格子可行，设为已走过的*/

            /*判断当前格子是否已经是出口*/
            if (row == grid.length - 1 && col == grid[0].length - 1) {
                done = true; /*到达出口则done值为true*/
            }else {
                /*不是，则在走下一个格子，分别以当前格子为基准，下，右，上，坐的顺序继续走*/
                done = traverseMode1(row + 1, col);
                //只要done值不为true，则说明还没到终点

                if (!done) {
                    done = traverseMode3(row - 1, col);
                }
                if (!done) {
                    done = traverseMode4(row, col - 1);
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



    /**测试*/
    public static void main(String[] args) throws CloneNotSupportedException{
        OptimizeMaze maze = new OptimizeMaze();
        OptimizeMaze maze1 = maze.clone();

        if (maze.newtraverse(0, 0)) {
            System.out.println("Yes! the Maze can travered");
        }else{
            System.out.println("No! the Maze can't travered");
        }
        System.out.println("Using newtracerse !!");
        System.out.println("此迷宫计算机共走了 " + maze.cur2 + " 次");
        System.out.println(maze);


        //System.out.println(maze1);
        if (maze1.oldtraverse(0, 0)) {
            System.out.println("Yes! the Maze can travered");
        }else{
            System.out.println("No! the Maze can't travered");
        }
        System.out.println("Using oldtracerse !!");
        System.out.println("此迷宫计算机共走了 " + maze.cur + " 次");
        System.out.println(maze1);


    }

}
