package com.recursive.hanoi;

/**
 * @version 1.0.1
 * @author jjf 2019-07-16 19:11
 * 自己设计汉诺塔类及其的决解方案，构造函数的参数表示汉诺塔的盘子数，重载两个构造函数
 */
public class MyHanoi {
    /*数据域*/
    private int dish; //盘子数量
    /**三座塔，常量*/
    private final String stringA = "塔A";
    private final String stringB = "塔B";
    private final String stringC = "塔C";

    /**
     * 无参数构造器，域dish值设为20
     */
    public MyHanoi(){
        dish = 20;
    }
    /**
     * 有参构造器，域dish的值赋为实参的值
     *
     * @param dish
     */
    public MyHanoi(int dish) {
        this.dish = dish;
    }

    /**
     * 汉诺塔求解方法，并打印每一步的求解过程
     */
    public void resolve(){
        move(stringA, stringB, stringC, dish);
    }

    /**
     * 此辅助函数主要是因为，在设计类的时候塔是约定好的，而在构造汉诺塔的时候盘子也随之确定
     * 所以，对于汉诺塔问题来说，这些基本数据本身就十分清楚，所以在提供决解方案接口的时候，
     * 不因该再一次提供这些数据。使接口冗余，而对于递归来说，这些数据有必不可少，所以，应该
     * 有汉诺塔实例内部自己提供
     * @param A
     * @param B
     * @param C
     * @param dish
     *
     * 注意：在理解上，参数的意义是：
     *     第一个参数始终表示开始装盘子的塔
     *     第二个参数始终表示辅助塔
     *     第三个参数表示最终盘子要放置的塔
     */
    private void move(String A, String B, String C, int dish) {
        if (dish == 1) { //基本条件是dish == 1
            System.out.println(A + " --> " + C);
        } else {
            move(A, C, B, dish - 1); //表示将A塔上的n-1个盘子借助C塔移到B塔
            System.out.println(A + " --> " + C);
            move(B, A, C, dish - 1); //表示将B塔上的n-1个盘子借助A塔移到C塔
        }
    }

    public static void main(String[] args) {
        MyHanoi hanoi = new MyHanoi(4);
        hanoi.resolve();
    }
}
