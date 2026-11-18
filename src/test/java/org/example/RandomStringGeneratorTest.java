package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RandomStringGeneratorTest {

    @Test
    public void testStringLength() {
        String text = RandomStringGenerator.getRandomString();
        assertEquals(10, text.length());
    }

    @Test
    public void testStringContainsOnlyAllowedSymbols() {
        String text = RandomStringGenerator.getRandomString();
        char[] symbols = {
                'a', 'b', 'c', 'A', 'B', 'C',
                '1', '2', '3'
        };

        for (int i = 0; i < text.length(); i++) {
            boolean found = false;

            for (int j = 0; j < symbols.length; j++) {
                if (text.charAt(i) == symbols[j]) {
                    found = true;
                    break;
                }
            }

            assertTrue(found);
        }
    }

    @Test
    public void testStringIsNotNull() {
        String text = RandomStringGenerator.getRandomString();
        assertNotNull(text);
    }
}
