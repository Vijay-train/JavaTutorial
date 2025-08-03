import java.util.Scanner;


public class Numbers {

    public static void PrintFactorial(int n){
        long factorial = 1;

        if (n < 0){
            System.out.println("Factorial is not defined for negative numbers");
        }
        // 0! = 1
        if (n == 0){
            factorial = 1 ; 
        }
        else{
            // 5! = 5*4*3*2*1 (1*2*3*4*5)
            for (int i = 1; i <= n; i++){
                factorial = factorial*i;
            }
        }

        System.out.println("Facotorial = " + factorial);


    }
    
    public static void PrintGCD(int a, int b){
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        System.out.println("GCD is: " + a);
    }

    public static void IsPrime(int n){
        boolean isPrime = true;
        if (n <= 1){
            isPrime = false;
        }

        for (int i = 2; i < n ;i++){
            if (n % i == 0)// not prime
            {
                isPrime = false;
                break;
            }
        }

        if (isPrime == true){
            System.out.println("The number is a PRIME number");
        }
        else{
            System.out.println("The number is NOT a PRIME number");
        }

    }
    
    
    public static void PrintFactors(int n){
        System.out.println("Printing factors");

        for (int i = 1; i <= n; i++){
            if (n % i == 0){
                System.out.print(i + " ");
            }
        }

    }

    public static void power(long base, long exp){
        long result = 1;
        for( long i = 0; i < exp; i++){
            result = result * base;
        }
        System.out.println("The result from basic exp = " + result);
    }

    public static void powerwithBinaryExp(long base, long exp){
        long result = 1;

        while (exp > 0){
            if (exp % 2 == 1){ // odd
                result = result * base;
            }
            // square the base
            base = base * base;
            // Divide exp by 2
            exp = exp / 2;
        }

        System.out.println("The result through bin exp is = " + result);
    }
    
     public static void main(String[] args) {
        Scanner input = new Scanner((System.in));
       // System.out.println("Enter the number: ");
      //  int num = input.nextInt();

        power(95, 8);
        powerwithBinaryExp(95,8);
        input.close();
     }
}
