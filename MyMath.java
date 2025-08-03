import java.util.Scanner;
import java.lang.System;



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
    

