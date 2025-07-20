/* Problmems

 * 
 * 
 * 3) Word Count in a Sentence (that are seperated by single space)
 * "     I am going to develop an eagle eye.      "
 * 
 * 4) Write a Java program that checks if two given strings are anagrams of each other. 
 *    Anagrams are words or phrases formed by rearranging the letters of another, 
 *    such as "listen" and "silent".
 * 
 *  * 1) Write a Java program that takes a given String (e.g., "banana") 
 *    and counts the frequency of each character in it.
 * 
 * 
 * 
 * 
 */



public class ZuvyString {

    /*
     * Print the original sentence.
     * Count and print the total number of characters in the sentence (including
     * spaces).
     * Find and print the index of the first occurrence of the word "Java"
     * (case-sensitive).
     * If not found, print a suitable message.
     * Replace all occurrences of the letter 'e' with '3' and print the modified
     * sentence.
     * Check if the sentence starts with "Hello" (case-insensitive) and print the
     * result.
     * 
     */

    public static void PrintStrings(String s) {
        System.out.println("Orignal Sentence = " + s);
        System.out.println("Length = " + s.length());

        int javaIndex = s.indexOf("Java");
        if (javaIndex != -1) {
            System.out.println("The string java was found at index = " + javaIndex);
        } else {
            System.out.println("Java not found");
        }

        String modifiedString = s.replace('e', '3');
        System.out.println("The modified string is " + modifiedString);

        boolean startsWithHello = s.toLowerCase().startsWith("hello");
        System.out.println("Does the sentence start with Hello? " + startsWithHello);

    }

    /*
     * Problem: Dynamic Report Generator
     * Imagine you're generating a simple report based on some data.
     * Start with a StringBuilder and initialize it with "Report for ".
     * Append a specific date (e.g., "2025-07-18").
     * Append a new line and a heading: "--- Daily Summary ---".
     * Append two bullet points for data, e.g., "• Sales: 500 units\n" and
     * "• Revenue: ₹50,000\n".
     * Before "Daily Summary", insert the user's name (e.g., "Priya's ").
     * Finally, print the complete report as a String.
     * 
     */
    public static void PrintStringBuilder() {
        // 1. Initialize StringBuilder
        StringBuilder report = new StringBuilder("Report for ");

        // 2. Append a specific date
        report.append("2025-07-18");

        // 3. Append new line and heading
        report.append("\n--- Daily Summary ---\n");

        // 4. Append two bullet points
        report.append("• Sales: 500 units\n");
        report.append("• Revenue: ₹50,000\n");

        // 5. Insert user's name before "Daily Summary"
        // Find the index where "Daily Summary" starts.
        int dailySummaryIndex = report.indexOf("--- Daily Summary ---");
        if (dailySummaryIndex != -1) { // Check if found
            report.insert(dailySummaryIndex, "Priya's ");
        }

        // 6. Print the final report
        System.out.println(report.toString());
        /*
         * Expected Output:
         * Report for 2025-07-18
         * Priya's --- Daily Summary ---
         * • Sales: 500 units
         * • Revenue: ₹50,000
         */

    }

    public static void checkPalindrome(String str) {
        // 1. Clean the string of any non alpha numeric, convert to lower case
        StringBuilder cleanedSB = new StringBuilder();
        for (int i = 0; i < str.length(); i++){
            if (Character.isLetter(str.charAt(i))){
                cleanedSB.append(Character.toLowerCase(str.charAt(i)));
            }
        } // clean string , converted to LowerCase
        
        // 2. Reverse the cleaned string
        StringBuilder reversedSB = new StringBuilder(cleanedSB);
        reversedSB.reverse();
        
        boolean isPalindrome = false;
        // 3. Compare Original(that is cleaned) with reversed string
        

        String conCleanString = cleanedSB.toString();
        String conReverseString = reversedSB.toString();
        isPalindrome = conCleanString.equals(conReverseString);

        System.out.println("Original String = " + str);
        System.out.println("Cleaned String = " + cleanedSB);
        System.out.println("Reversed String = " + reversedSB);
        System.out.println("Is it a palindrome? " + isPalindrome);




    }

    public static void main(String[] args) {
        String testString = "I really love attending Java class";
        StringBuilder sb = new StringBuilder("MAd1tera2m");
        checkPalindrome("MADam");

    }
}