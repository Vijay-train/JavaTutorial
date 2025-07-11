import java.util.Scanner;
import java.lang.System;



public class Basic {
    
    public static int GetAverageMarks (int[] marksArray){
        
        int totalMarks = 0;
        for (int i = 0 ; i < marksArray.length; i++ ){
            totalMarks = totalMarks + marksArray[i];
        }
      
        int averageMarks = totalMarks/marksArray.length;

        return averageMarks;
    }

     public static int GetLowestMarks (int[] marksArray){
        int lowest = marksArray[0];
       
        for( int i=1;i<marksArray.length;i++){
            if(lowest > marksArray[i]){
                lowest=marksArray[i];
            }
        }
        return lowest;
     }

     public static void ByRefOrVal(int x, int[] marks){
        System.out.println("In Function ByRefOrVal");
        int[] marks1 = new int[100];
        marks1[5] = 50;
        x = 500;
        marks[0] = 5000;

        System.out.println("In Function ByRefOrVal" + " X = " + x + " marks[0] = " + marks[0]);


     }

    public static void main(String[] args) {
       
        int val = 10;
        int[] testArray = new int[10];
        testArray[0] = 1000;

        System.out.println("In Function main " + " val = " + val + " testArray[0] = " + testArray[0]);

        ByRefOrVal(val,testArray);

        
        //val = 20;
        //testArray[0] = 2000;
        
        System.out.println("In Function main" + " val = " + val + " testArray[0] = " + testArray[0]);

        ByRefOrVal(val,testArray);


       /*
        * 1. Create an array that can store marks of 10 students, you can intialize the marks to some value
        * 2. Get the input from the user and store the marks in this array.
        * 3. Print the average marks
        * 4. Print the lowest mark
        * 5. Print the 3rd lowest mark
        

     
        int[] studentMarks = new int[10]; // Heap
        int i = 0; // stack


        Scanner sc = new Scanner(System.in);
        System.out.println("--Enter the marks of the students --");
        for(int i=0;i<studentMarks.length;i++){
            System.out.print("Enter Student-" + i + " marks: ");
            studentMarks[i] = sc.nextInt();
        }

        // 3. Get  the average marks
        int avg = GetAverageMarks(studentMarks);
        System.out.println("The average marks is = " + avg);

        // 4. Print the lowerst mark
        int low = GetLowestMarks(studentMarks);
        System.out.println("The Lowest marks is = " + low);
 
        



        sc.close();
        */
    }
  


}
    

