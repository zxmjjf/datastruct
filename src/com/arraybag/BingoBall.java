package com.arraybag;

public class BingoBall {
    private char letter; //ball 所对应的字符
    private int number; //ball的编号

    public BingoBall(int num) {
        number = num;
        if (num < 15) {
            letter = 'B';
        } else if (num <= 30) {
            letter = 'I';
        } else if (num <= 45) {
            letter = 'N';
        } else if (num <= 60) {
            letter = 'G';
        } else {
            letter = 'O';
        }
    }

    public String toString(){
        return (letter + "\t" + number);
    }
}
