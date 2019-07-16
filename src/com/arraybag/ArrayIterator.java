package com.arraybag;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArrayIterator类是迭代器，其实例用于实现数组元素的遍历
 */
public class ArrayIterator implements Iterator {
    /** 私有数据部分 */
    private int count; /* 数组元素的个数，但不是数组的长度*/
    private int current; /* 表示计数或者标记作用*/
    private Object[] items; /* 数组的引用变量*/

    /**
     * 构造函数，迭代器初始化
     */
    ArrayIterator(Object[] collection, int size) {
        items = collection;
        count = size;
        current = 0;
    }


    /**实现Iterator接口*/

    /**实现hasNext()方法*/
    public boolean hasNext(){
        return (current < count);
    }
    /**实现next()方法*/
    public Object next(){
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        current++;
        return items[current - 1];
    }

}
