public class ZuvyString {
    
    /* 
     * Print the original sentence.
        Count and print the total number of characters in the sentence (including spaces).
        Find and print the index of the first occurrence of the word "Java" (case-sensitive). 
        If not found, print a suitable message.
        Replace all occurrences of the letter 'e' with '3' and print the modified sentence.
        Check if the sentence starts with "Hello" (case-insensitive) and print the result.

    */

    public static void PrintStrings(String s){
            System.out.println("Orignal Sentence = " + s);
            System.out.println("Length = " + s.length());

            int javaIndex = s.indexOf("Java");
            if (javaIndex != -1){
                System.out.println("The string java was found at index = " + javaIndex);
            }
            else{
                System.out.println("Java not found");
            }

            String modifiedString = s.replace('e', '3');
            System.out.println("The modified string is " + modifiedString);

            boolean startsWithHello = s.toLowerCase().startsWith("hello");
            System.out.println("Does the sentence start with Hello? " + startsWithHello);

        }
        /*
         * Problem: Dynamic Report Generator
            Imagine you're generating a simple report based on some data.
            Start with a StringBuilder and initialize it with "Report for ".
            Append a specific date (e.g., "2025-07-18").
            Append a new line and a heading: "--- Daily Summary ---".
            Append two bullet points for data, e.g., "• Sales: 500 units\n" and "• Revenue: ₹50,000\n".
            Before "Daily Summary", insert the user's name (e.g., "Priya's ").
            Finally, print the complete report as a String.

         */
        public static void PrintStringBuilder(){
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
            /* Expected Output:
            Report for 2025-07-18
            Priya's --- Daily Summary ---
            • Sales: 500 units
            • Revenue: ₹50,000
            */

        }
        
    
    
    
    public static void main(String[] args){
        String testString = "I really love attending Java class";
        PrintStrings(testString);
     }
}
