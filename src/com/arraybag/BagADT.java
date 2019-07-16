package com.arraybag;

import java.util.Iterator;

public interface BagADT {
    public void add(Object element); /**添加元素*/

    public void addAll(BagADT bag);

    public Object removeRandom() throws EmptyBagException;   /**随机删除一个元素*/

    public Object remove(Object element) throws EmptyBagException; /**删除指定元素*/

    public BagADT union(BagADT set); /**合并两个袋作为新的袋作为结果返回*/

    public boolean contains(Object target); /**查找袋中是否包含指定元素*/

    public boolean equals(BagADT bag) throws EmptyBagException; /**检查两个袋是否相等*/

    public boolean isEmpty(); /**检查袋是否为空，是返回true*/

    public int size(); /**获取袋中元素的个数*/

    public Iterator iterator(); /** 产生迭代器此自身对应的实例*/

    public String toString(); /**袋的字符串说明*/

}
