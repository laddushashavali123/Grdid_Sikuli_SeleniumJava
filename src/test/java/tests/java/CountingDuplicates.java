package tests.java;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CountingDuplicates {
    /*public static int duplicateCount(String text) {
        char chars[] = text.toCharArray();
        char result[];
        for (int i=0; i<chars.length; i++){
            for (int j=i+1; j<chars.length; j++){
                System.out.println("char i is " + chars[i]);
                System.out.println("char j is " + chars[j]);
                System.out.println("------");
                if (chars[i] == (chars[j])){
                    result. += 1;
                    break;
                }
            }
        }
        System.out.println("result");
        return result;
    }

    @Test
    public void abcdeReturnsZero() {
        assertEquals(duplicateCount("abcde"), 0);
    }

    @Test
    public void abcdeaReturnsOne() {
        assertEquals(1, CountingDuplicates.duplicateCount("abcdea"));
    }

    @Test
    public void indivisibilityReturnsOne() {
        assertEquals(1, CountingDuplicates.duplicateCount("indivisibility"));
    }*/
}
