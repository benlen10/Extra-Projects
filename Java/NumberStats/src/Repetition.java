
 //This program will calcualte the average, lowest and highest grades bases on an input string

import java.util.Scanner;
public class Repetition {
    
    static int x = 0;
    public static void even() {
        while (x < 100) {
            x = (x +2);
            System.out.println(x);
        }
    }
    static double exp = 1;
    public static void powers() {
        while (exp < 1000) {
            exp = exp * 2;
            System.out.println(exp);
        }
    }
    public static void alphabet() {
        for (char a = 'a'; a < 'z'; a++ ) {
            System.out.println(a);
        }
    }
    public static String s = "";
    public static void vertical() {
        int l = s.length();       
        for(int t = 0; t < l; t++) {
            System.out.println(s.charAt(t));
        }
    }
    
    public static void testResults() {
        Scanner in = new Scanner(System.in);
        int current = 0;
        int count = 0;
        int sum = 0;
        int lowest = 100;
        int highest =0;
        while (in.hasNextInt()) {
            current = in.nextInt();
            count ++;
            sum = sum + current;
            if (current < lowest) {
                lowest = current;
            }
            if (current > highest) {
                highest = current;
            }
        }
        System.out.println("=====-----=====-----=====-----=====");
        System.out.println("=            Test Results         =");
        System.out.printf("= Average:%23d =\n", (sum / count) );
        System.out.printf("= Lowest: %23d =\n", lowest);
        System.out.printf("= Highest:%23d =\n", highest);
        System.out.println("=====-----=====-----=====-----=====");
    }
    
    
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Repetition r = new Repetition();
        s = in.nextLine();
        r.testResults();
    }
}
