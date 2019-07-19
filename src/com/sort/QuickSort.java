package com.sort;

import java.util.Arrays;

public class QuickSort {
    int[] ints = {33, 16, 8, 9, 7, 153, 94, 65, 8, 13, 99, 647, -5, 0, 113, -17, 3, 19, 31, 6};
    public static int times = 0; /*记录递归调用的次数*/
    public static int fortotimes = 0; //for循环的次数


    public void quickSort(){
        ints = sort(ints, 0, ints.length - 1, ints.length);
    }

    public int[] getInts() {
        return ints;
    }

    /**此快速排序法通过复制数组并递归调用的方式实现，故所消耗的内存较大*/
    /**
     *
     * @param ints 完整的数组
     * @param indexmin 分段的最左下标
     * @param indexmax  分段的最右下标
     * @param stan 分段的参考值
     * @return
     */
    private int[] sort(int[] ints, int indexmin, int indexmax, int stan) {
        /**保存要快速排序的数组元素的范围*/
        if (indexmin >= indexmax) {
            /*如果需要处理的算数范围不大于一个，则就可以不用处理，直接返回。一定要 >= 符
            * 因为indexmin 有可能会大于 indexmax*/
            return ints;
        } else {
            ++times;
            int left = indexmin;
            int right = indexmax;

            /**设置在范围内第一个元素为锚点，并保存第一个元素的值*/
            int stander = ints[indexmin];
            int[] results = new int[ints.length];
            for (int i = 0; i < ints.length; ++i) {
                results[i] = ints[i];
            }

            /**排序的规则是：
             *      修改新建数组中指定的排序范围，即，对于新建的数组，在给定下标范围内的部分
             *      无视当前值，在给定下标之外的部分不改变其值。新建数组需要修改部分的值由实参
             *      提供
             *      从新建数组的给定范围起，检查在实参对应范围内的元素的值与锚点的大小关系
             *      小于或等于锚点的元素，依此赋值给新建数组指定范围的左端从左向右赋值，
             *      大于锚点的值从新建数组给点范围的右端从右向左赋值
             ********/
            for (int i = left + 1; i <= right; ++i) {
                ++fortotimes;
                if (ints[i] <= stander) {
                    results[indexmin] = ints[i];
                    ++indexmin;
                }else{
                    results[indexmax] = ints[i];
                    --indexmax;
                }
            }
            results[indexmax] = stander;
            System.out.println(Arrays.toString(results)); //输出每一次排序之后的结果


            /**递归的三种可能情况*/
            if (indexmax + 1 >= right){
                results = sort(results, left, indexmax - 1, indexmax);
            }else if (indexmin - 1 <= left) {
                results = sort(results, indexmax + 1, right, indexmax);
            }else{
                results = sort(results, left, indexmax - 1, indexmax);
                results = sort(results, indexmax + 1, right, indexmax);
            }

             return results;

        }
    }

    public static void main(String[] args) {
        QuickSort  quickSort = new QuickSort();
        System.out.println("排序之前");
        System.out.println(Arrays.toString(quickSort.getInts()) + "\n");

        System.out.println("排序过程：");

        quickSort.quickSort();
        System.out.println("排序之后");
        System.out.println(Arrays.toString(quickSort.getInts()));

        System.out.println("共递归函数：" + times + " 次; for循环的次数为: " + fortotimes);

    }
}
