package com.sort;

import java.util.Arrays;

public class AllSort{
    public static void main(String[] args) {
        AllSort allSort = new AllSort();

        /**五个相同的int类型数组*/
        int[] arrays1 = {33, 16, 8, 9, 7, 153, 94, 65, 8, 13, 99, 647, -5, 0, 113, -17, 3, 19, 31, 6};
        int[] arrays2 = {33, 16, 8, 9, 7, 153, 94, 65, 8, 13, 99, 647, -5, 0, 113, -17, 3, 19, 31, 6};
        int[] arrays3 = {33, 16, 8, 9, 7, 153, 94, 65, 8, 13, 99, 647, -5, 0, 113, -17, 3, 19, 31, 6};
        int[] arrays4 = {33, 16, 8, 9, 7, 153, 94, 65, 8, 13, 99, 647, -5, 0, 113, -17, 3, 19, 31, 6};
        int[] arrays5 = {33, 16, 8, 9, 7, 153, 94, 65, 8, 13, 99, 647, -5, 0, 113, -17, 3, 19, 31, 6};

        /**使用不同的排序的方法*/
        allSort.selectionSort(arrays1);
        allSort.insertionSort(arrays2);
        allSort.bubbleSort(arrays3);
        allSort.quickSort(arrays4);
        allSort.mergeSort(arrays5);

        /**输出结果*/
        System.out.println(Arrays.toString(arrays1));
        System.out.println(Arrays.toString(arrays2));
        System.out.println(Arrays.toString(arrays3));
        System.out.println(Arrays.toString(arrays4));
        System.out.println(Arrays.toString(arrays5));
    }

    /**
     * 选择排序法: 目前针对int类型数组
     * @param arrays
     */
    public void selectionSort(int[] arrays) {
        int min;
        int temp;
        for (int index = 0; index < arrays.length - 1; ++index) {
            min = index;
            for (int scan = index + 1; scan < arrays.length; ++scan) {
                if (arrays[min] > arrays[scan]) {
                    min = scan;
                }
            }
            temp = arrays[min];
            arrays[min] = arrays[index];
            arrays[index] = temp;
        }
    }

    /**
     * 插入排序法：目前针对int类型数组
     * @param arrays
     */
    public void insertionSort(int[] arrays) {
        for (int index = 1; index < arrays.length; index++) {
            int key = arrays[index];
            int position = index;

            while (position > 0 && arrays[position - 1] > key) {
                //key 之前的元素已经按顺序排好
                arrays[position] = arrays[position - 1];
                position--;
            }
            arrays[position] = key;
        }
    }

    /**int类型数组排序
     *
     * 冒泡排序法： @param arrays
     */
    public void bubbleSort(int[] arrays) {
        int position;
        int scan;
        int temp;
        for (position = arrays.length - 1; position >= 0; position--) {//将最大值放在数组最后面
            for (scan = 0; scan <= position - 1; scan++) {
                if (arrays[scan] > arrays[scan + 1]) {
                    temp = arrays[scan];
                    arrays[scan] = arrays[scan + 1];
                    arrays[scan + 1] = temp;
                }
            }
        }
    }

    /**
     * 快速排序法：按照某个值大小，左右分类
     * @param arrays
     */
    public void quickSort(int[] arrays) {
        findPartion(arrays, 0, arrays.length - 1);
    }
    /**
     * 私有方法，为quickSort的辅助方法
     * @param arrays 要排序的数组
     * @param min 要排序部分的最小下标
     * @param max 要排序部分的最大下标
     */
    private void findPartion(int[] arrays, int min, int max) {
        int left = min, right = max;
        int partitionelement = arrays[min];
        int temp;
        if (left > right) {
            return;
        }
        while (left < right) {
            while (arrays[left] <= partitionelement && left < right) {
                left++;
            }
            while (arrays[right] > partitionelement) {
                right--;
            }
            if (left < right) {
                temp = arrays[right];
                arrays[right] = arrays[left];
                arrays[left] = temp;
            }
        }
        /**只能用right，不能用left,因为在最后一次left++时，left的位置指向的值只要后面还有大于parttionelement 的元素，
         * left值就会指向它，导致以其交换的是大值，而不是最后一个小值，而right的最终结果一定一停留在最后一个最小值上
         * 所以这个位置应该成为parttionelement元素的位置*/
        temp = arrays[min];
        arrays[min] = arrays[right];
        arrays[right] = temp;

        /**递归*/
        if (right + 1 >= max) {//只有左边
            findPartion(arrays, min, right -1);
        }else if (right - 1 <= min) {//只有右边
            findPartion(arrays, right + 1, max);
        }else {
            findPartion(arrays, min, right - 1);
            findPartion(arrays, right + 1, max);
        }
    }


    /**
     * 归并排序法
     * @param arrays
     */
    public void mergeSort(int[] arrays) {
        merge(arrays, 0, arrays.length - 1);
    }
    /**
     * 归并辅助函数：归并算法，提供给mergeSort调用
     * @param arrays
     * @param min
     * @param max
     */
    private void merge(int[] arrays, int min, int max) {
        int[] temp;
        int index, left, right;

        /**递归基本结束条件*/
        if (min == max) {
            return;
        }

        int size = max - min + 1;
        int pivot = (min + max) / 2;
        temp = new int[size];

        merge(arrays, min, pivot);
        merge(arrays, pivot + 1, max);

        for (index = 0; index < size; index++) {
            temp[index] = arrays[min + index];
        }
        left = 0;
        right = pivot - min + 1;

        for (index = 0; index < size; index++) {
            if (right <= max - min) {
                if (left <= pivot - min) {
                    if (temp[left] > temp[right]) {
                        arrays[index + min] = temp[right++];
                    }else{
                        arrays[index + min] = temp[left++];
                    }
                }else {
                    arrays[index + min] = temp[right++];
                }
            }else {
                arrays[index + min] = temp[left++];
            }
        }
    }
}
