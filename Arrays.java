import java.util.Scanner;
import java.lang.System;

/*
1) Given an array of integers, print all its elements.
2) Calculate the sum of all elements in an integer array.
3) Find the largest element in an integer array.
4) Find the smallest element in an integer array.
5) Check if a specific number exists in an array. If it does, tell its index.
6) Count how many times a specific number appears in an array.
7) Write a program that takes an array of temperatures (e.g., double[] temperatures = {25.5, 28.1, 30.0, 27.8, 29.5};) and counts how many days the temperature was above 29.0.

8) Reverse the order of elements in an array.
9) Determine if an array of numbers is sorted in ascending order.
10)Given an array with duplicate elements, create a new array with only unique elements.
11)Write a program to rotate an array to the left by one position.
12)Find the second largest element in an array.
13)Given an array of integers and a target sum, find if there exists a pair of elements in the array that add up to the target sum.
14)Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing.
15)Count the frequency of each element in an array.
16)Given an array of integers, count the number of even and odd numbers in it.

*/
public class Arrays {


   //1) Given an array of integers, print all its elements.
   public static void PrintArray(int[] arr){
      System.out.println("[PrintArray] Printing all elements in the Array ");

      for(int i = 0; i < arr.length; i++){
         System.out.println((arr[i]));
      }
   }

   // 2) Calculate the sum of all elements in an integer array.
   public static void PrintSum(int[] arr){
      int currSum = 0;

      for(int i = 0; i < arr.length; i++){
         currSum = currSum + arr[i];
      }

      System.out.println("[PrintSum]Sum of the array = " + currSum);

   }


   //  3) Find the largest element in an integer array.
   public static void PrintLargest(int[] arr){
     int currMax = 0;
  
       for(int i = 0; i < arr.length; i++){
            if (currMax < arr[i]){
               currMax = arr[i];
            }
         }
      
      System.out.println("[PrintLargest], the largest number in the arr is " + currMax);

   }

   //5) Check if a specific number exists in an array. If it does, tell its index.
   public static void FindNumber(int numberToCheck, int[] arr){
      int index = -1;

      for(int i = 0; i < arr.length; i++){
         if (numberToCheck == arr[i]){ // found it
            index = i;
            break;
         }
      }

      if (index != -1)
         System.out.println(("[FindNumber] Found the number at index " + index));
      else
         System.out.println(("[FindNumber] Number not found "));

   }

   // 6) Count how many times a specific number appears in an array.
   public static void CountOccurence(int numberToCheck, int[] arr){
      int occurenceCount = 0;

      for(int i = 0; i < arr.length; i++){
         if (numberToCheck == arr[i]){ // found it
            occurenceCount++;
         }
      }

      System.out.println(("[CountOccurence] # of times the number occured is  " + occurenceCount));     

   }

   //7) Write a program that takes an array of temperatures and 
   // counts how many days the temperature was above 29
   public static void CheckTemp(int temp, int[] arr){
      int occurenceCount = 0;

      for(int i = 0; i < arr.length; i++){
         if (arr[i] >= temp){ // found it
            occurenceCount++;
         }
      }

      System.out.println(("[CountOccurence] # of times the number occured is  " + occurenceCount));     

   }

   //8) Reverse the order of elements in an array.
   public static void ReverseArray(int[] arr){
      // create a new array, and store the value in reverse order of arr
      // print the reversed array
   }

     public static void main(String[] args) {
       int[] testArr = {10,20,30,40,50};
   

       PrintArray(testArr);
       PrintSum(testArr);
       PrintLargest(testArr);

       FindNumber(15,testArr);

       CountOccurence(130,testArr);

       

     }

}