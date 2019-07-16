package com.arraybag;

public class BingoMain {
    public static void main(String[] args) throws  EmptyBagException{
        final int NUM_BALLS = 75, NUM_PULLS = 10;

        ArrayBag bingBag = new ArrayBag();
        BingoBall bingoBall;

        try {
            bingBag.removeRandom();
        } catch (EmptyBagException e) {
            System.out.println("catch the Exception!");
        }


        for (int num = 1; num <= NUM_BALLS; num++) {
            bingoBall = new BingoBall(num);
            bingBag.add(bingoBall);
        }

        System.out.println("Size: " + bingBag.size());
        System.out.println();

        for (int num = 1; num <= NUM_PULLS; num++) {
            bingoBall = (BingoBall)bingBag.removeRandom();
            System.out.println(bingoBall);
        }

    }
}
