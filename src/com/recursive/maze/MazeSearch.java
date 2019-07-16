package com.recursive.maze;

import java.util.Random;

/**
 * @version 1.0.1
 * @author jjf 2019-7-14 21:04
 * 用于测试Maze类的设计结果
 */
public class MazeSearch {
    public static void main(String[] args) {
        Maze maze = new Maze();

        System.out.println(maze);

        if (maze.traverse(0, 0)) {
            System.out.println("Yes! the Maze can travered");
        }else{
            System.out.println("No! the Maze can't travered");
        }

        System.out.println(maze);
        System.out.println("此迷宫中的 1 共有 " + Maze.root + " 块");
        System.out.println("共调用travers " + Maze.cur + " 次！");

        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            System.out.print(random.nextInt(3));
        }
    }
}
