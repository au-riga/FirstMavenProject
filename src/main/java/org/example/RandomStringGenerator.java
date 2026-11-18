package org.example;

import java.util.Random;

public class RandomStringGenerator {


    public static String getRandomString() {
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        char[] symbols = {
            'a', 'b', 'c', 'A', 'B', 'C',
                    '1', '2', '3'} ;

        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(symbols.length);

            result.append(symbols[index]);
        }

        return result.toString();
    }
}

class Main{
    public static void main(String[] args) {

        System.out.println(RandomStringGenerator.getRandomString());
    }
}