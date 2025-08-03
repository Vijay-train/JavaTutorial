import java.util.Scanner;
import java.lang.System;
/*
 
 * 1) Calculate the volume of a cylinder. The formula is V=pi*r^2*h.
 * 1.1) Fix formatting for decimals
 
 * 2) Generating a Fibonacci sequence
 
 * 3) The Shopping Bill with Tax and Discount
      "A customer buys two items: a shirt for ₹750 
       and a pair of trousers for ₹1200.
       The store is running a promotion offering a 10% discount 
       on the total subtotal before tax.
       Calculate the final bill after applying 
       the discount and adding a Goods and Services Tax (GST) of 18%
       to the discounted price."
      
 * 4) (** HOMEWORK **) The Factorial Finder
 
 * 5) Random OTP Generation
      You are building an app and need to generate
      a 6-digit One-Time Password (OTP) for user verification.
      The OTP should be a random number between 100000 and 999999.
   
 * 6) Unit Conversion: from Centimeters to Feet and Inches
      (e.g., 180.0) into feet and inches.
      1 inch = 2.54 cm
      1 foot = 12 inches


 * 7) The CGPA Calculator
      A student has completed three courses
      with the following grades and credits:
      Course 1: Grade 9.0 (4 credits)
      Course 2: Grade 8.5 (3 credits)
      Course 3: Grade 9.5 (3 credits)
      The CGPA is calculated as the 
      sum of (Grade * Credits) for all courses,
      divided by the total number of credits. 
      
 * 8) The Loan EMI Calculator
      You've taken a loan of ₹2,50,000 at a 10% annual interest rate
      for a tenure of 2 years. 
      Formula: EMI = P * r * (1+r)^n/ (1+r)^n -1
         P = Principal loan amount
         r = Monthly interest rate (annual rate divided by 12)
         n = Total number of months

 * 9) The Time Difference between Cities
      Bangalore is located at longitude 77.59° E,
      and London is at 0.12° W.
      The time difference between two locations
      is approximately 4 minutes per degree of longitude.
      Write a program to calculate the
      total time difference in hours and minutes.
      Hint:
         The difference in longitude is 77.59 - (-0.12).
         Calculate the total time difference in minutes.
         Use integer casting and modulus to convert
          the total minutes into hours and minutes.
         Print the result in the format:
          "The time difference is X hours and Y minutes."
 */


public class MyMath {
     public static void Divide(double a, double b){

        double c = a / b;
        int x = 10;

        // upcast int->double
        double d = x;

        // downcast double->int
        double d1 = 20.5;
        int n = (int)d1;

        



        System.out.println(n);
  
     }

     public static void AreaOfCircle(double r){
        // are of circle pi r^2
        System.out.println("Area is :"+ Math.PI*Math.pow(r, 2));
        int Num = -30;
        System.out.println("Absoulte Num is: "+Math.abs(Num));
      }
  

    public static void main(String[] args) {
       // Divide(10,3);
       AreaOfCircle(15);
    }
  


}
    

