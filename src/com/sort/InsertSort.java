package com.sort;

import java.util.Arrays;

public class InsertSort implements Comparable{
    public static void main(String[] args) {
        InsertSort insertSort = new InsertSort();
        int[] dataArrays = {4, 1, 3, 2, 6, 9, 4, 8, 3};
        System.out.println(Arrays.toString(dataArrays));

        insertSort.sort(dataArrays);
        System.out.println(Arrays.toString(dataArrays));

        /***//*
        int[] ints1 = {1, 2, 3, 4, 5, 6};
        int[] ints2 = new int[3];
        ints2 = ints1;
        System.out.println(Arrays.toString(ints2));*/

    }

    public void sort(int[] dataArrays) {
        for(int index = 1; index < dataArrays.length; ++index){
            int key = dataArrays[index]; //保留当前值
            int position = index;

            while (position > 0 && dataArrays[position - 1] > key){//可以将position - 1理解为将要移动下标
                //position游标：如果不是第一个元素，且比前面一个元素小，则将其值改为前面元素的值，position位置前移（左移）
                dataArrays[position] = dataArrays[position - 1]; //后移，什么时候才移？只要比key大就后移
                position--; //移动标记，
            }
            dataArrays[position] = key;
            System.out.println(Arrays.toString(dataArrays));
        }
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
