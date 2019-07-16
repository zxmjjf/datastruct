package com.arraybag;

public class EmptyBagException extends Exception {
    EmptyBagException(){
        System.out.println("The Bag is Empty");
    }
}
