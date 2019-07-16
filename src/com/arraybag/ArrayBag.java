package com.arraybag;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * 此类作为一种集合：袋
 * 此数据结构的设计使用数组来存储元素，袋中的元素之间没有任何直接关系，对元素的操作都可能具有随机性
 * @author : jjf
 * @time : 2019-7-12
 * @version 1.0.1(无修改)
 */
public class ArrayBag implements BagADT {
    /**
     * 私有数据
     */
    private static Random rand = new Random();
    private final int DEFAULT_CAPACITY = 100; //袋的默认初始容量
    private final int NOT_FOUND = -1;

    private int count; //袋中实际元素的个数
    private Object[] contents; //存放袋元素的数组（容器）

    /**
     * 构造函数
     */

    public ArrayBag() {
        count = 0;
        contents = new Object[DEFAULT_CAPACITY];
    }

    public ArrayBag(int initialCapacity) {
        count = 0;
        contents = new Object[initialCapacity];
    }

    /**
     * 内部支持函数
     */
    private void expandCapacity(){
        Object[] larger = new Object[contents.length * 2];

        for (int index = 0; index < contents.length; ++index) {
            larger[index] = contents[index];
        }

        contents = larger;
    }

    /**
     * 外部接口
     */
    @Override
    public void add(Object element) {
        if (size() == contents.length) {
            expandCapacity();
        }


        contents[count++] = element;

    }

    @Override
    public void addAll(BagADT bag) {
        Iterator iterator = bag.iterator();

        while (iterator.hasNext()) {
            add(iterator.next());
        }
    }

    @Override
    public Object remove(Object element) throws EmptyBagException {
        int search = NOT_FOUND;

        if (isEmpty()) {
            throw new EmptyBagException();
        }
        for (int index = 0; index < count && search == NOT_FOUND; ++index) {
            if (contents[index].equals(element)) {
                search = index;
            }
        }

        if (search == NOT_FOUND) {
            throw new NoSuchElementException();
        }
        Object result = contents[search];
        contents[search] = contents[count - 1];
        contents[count - 1] = null;
        count--;

        return result;
    }

    @Override
    public Object removeRandom() throws EmptyBagException {
        if (isEmpty()) {
            throw new EmptyBagException();
        }
        int choice = rand.nextInt(count);
        Object result = contents[choice];

        contents[choice] = contents[count - 1];
        contents[count -1] = null;
        count--;

        return result;
    }

    @Override
    public BagADT union(BagADT set) {
        ArrayBag both = new ArrayBag();

        for (int index = 0; index < count; index++) {
            both.add(contents[index]);
        }

        Iterator scan = set.iterator();
        while (scan.hasNext()) {
            both.add(scan.next());
        }

        return both;
    }

    @Override
    public boolean contains(Object target) {
        int search = NOT_FOUND;

        for (int index = 0; index < count && search == NOT_FOUND; ++index) {
            if (contents[index].equals(target)) {
                search = index;
            }
        }

        return (search != NOT_FOUND);
    }

    @Override
    public boolean equals(BagADT bag) throws EmptyBagException{
        boolean result = false;
        ArrayBag temp1 = new ArrayBag();
        ArrayBag temp2 = new ArrayBag();
        Object obj;

        if (size() == bag.size()) {
            temp1.addAll(this);
            temp2.addAll(bag);

            Iterator scan = bag.iterator();

            while (scan.hasNext()) {
                obj  = scan.next();
                if (temp1.contains(obj)) {
                    temp1.remove(obj);
                    temp2.remove(obj);
                }
            }
            result = (temp1.isEmpty() && temp2.isEmpty());
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return (count == 0);
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterator iterator() {
        return new ArrayIterator(contents, size());
    }

    @Override
    public String toString() {
        String result = "";

        for (int index = 0; index < count; index++) {
            result = result + contents[index].toString() + "\n";
        }
        return result;
    }

}
