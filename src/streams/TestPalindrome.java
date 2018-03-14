package streams;

import java.nio.file.*;
import java.util.stream.Stream;

public class TestPalindrome {
    
    // system dependant path of dictionary - this is where it is on *nux ans w
    //private static final String dictpath = "/usr/share/dict/words";
    private static final String dictpath = "D:\\workspaces\\eclipseOxygen\\FunctionalProgJava9\\words.txt";

    public static void main(String[] args) throws Exception {
        long starttime = System.currentTimeMillis();
        Path dict = Paths.get(dictpath);
        @SuppressWarnings("resource")
		Stream<String> wordStream = Files.lines(dict);

        long palindrome = wordStream
                .filter(TestPalindrome::isPalindrome ) // find all palindromes
                .peek(System.out::println)  // write each match 
                .count(); // terminal - return a count
  
        System.out.println("Count of palindromes: "+palindrome);
        System.out.println("Total time elapsed = "+
                (System.currentTimeMillis() - starttime)+" milliseconds");
    }
    
    private static boolean isPalindrome(String word) {
        boolean isEven = word.length() % 2 == 0;
        return isPalindrome(word, isEven, isEven ? 0 : 1);
    }
    
    private static boolean isPalindrome(String word, boolean isEven, int offset) {
        int midpoint = word.length() / 2;
        if (offset > midpoint + (isEven ? -1 : 0) ) {
            return true;
        } 
        char xchar = word.charAt(midpoint - offset + (isEven ? -1 : 0));
        char ychar = word.charAt(midpoint + offset);
        if (xchar != ychar) {
            return false;
        }
        return isPalindrome(word, isEven, offset + 1);
    }
}
