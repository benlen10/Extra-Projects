/**
 * This program is a basic CLI parser capible of interpreting basic
 * commands given as arguments.
 * 
 * @author Ben Lenington (bleningt@purdue.edu)
 * @version  (10/1/14)
 */
import java.util.Scanner;
public class MyCLIParser {
    
    public static void main(String[] args){
        
        // if no arguments, print help and return
        if(args.length == 0){
            help(args);
            return;
        }
        
        //-help
        else if (args[0].equals("-help"))    help(args);
        
        
        //-add
        else if (args[0].equals("-add"))     add(args);
        
        //-sub
        else if (args[0].equals("-sub"))     sub(args);
        
        //-mul
        else if (args[0].equals("-mul"))     mul(args);
        
        //-div
        else if (args[0].equals("-div"))     div(args);
        
        //-stats
        else if (args[0].equals("-stats"))   stats(args);
        
        //-table
        else if (args[0].equals("-table"))   table(args);
        
    }
    
    public static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        }
        return true;
    }
    
    private static void help(String[] args){
        System.out.println("Instructions for Ben's CLI Shell");
        System.out.println("-add : adds the following integer arguments.");
        System.out.println("-sub : subtract the following two integer arguemts.");
        System.out.println("-mul : multiply the following integer arguments.");
        System.out.println("-div : devide the follwing two integer arguments. ");
        System.out.println("-stats : takes the following arguments and computes the total,"); 
        System.out.println("         maximum, minimum and average values");
    }
    
    private static void add(String[] args){
        for (int i = 1; i < args.length; i++) {
            if (!isInteger(args[i])) {
                System.out.print("Argument type mismatch");
                return;
            }
        }
        if (args.length < 2) {
            System.out.print("Argument count mismatch");
            return;
        }
        
        
        int sum = 0;
        for (int i = 1; i < args.length; i++) {
            int num = (Integer.parseInt(args[i]));
            sum += num;
        }
        System.out.println(sum);
        
    }
    
    private static void sub(String[] args){
        for (int i = 1; i < args.length; i++) {
            if (!isInteger(args[i])) {
                System.out.print("Argument type mismatch");
                return;
            }
        }
        
        if (args.length != 3) {
            System.out.print("Argument count mismatch");
            return;
        }
        
        
        int sum = 0;
        sum = (Integer.parseInt(args[1])) - (Integer.parseInt(args[2]));
        System.out.println(sum);
        
    }
    
    private static void mul(String[] args){
        for (int i = 1; i < args.length; i++) {
            if (!isInteger(args[i])) {
                System.out.print("Argument type mismatch");
                return;
            }
        }
        if (args.length < 2) {
            System.out.print("Argument count mismatch");
            return;
            
        }
        
        int sum = 1;
        for (int i = 1; i < (args.length); i++) {
            sum = (sum * Integer.parseInt(args[i]));
        }
        System.out.println(sum);
        
    }
    
    private static void div(String[] args){
        for (int i = 1; i < args.length; i++) {
            if (!isInteger(args[i])) {
                System.out.print("Argument type mismatch");
                return;
            }
        }
        if (args.length != 3) {
            System.out.print("Argument count mismatch");
            return;
        }

        
        if (args.length > 1) {
            if (Integer.parseInt(args[2]) == 0) {
                System.out.print("undefined");
                return;
            }
        }
        
        
        double total = (Double.parseDouble(args[1])) / (Double.parseDouble(args[(2)]));
        System.out.printf("%.2f\n", total);
    }
    
    private static void stats(String[] args){
        for (int i = 1; i < args.length; i++) {
            if (!isInteger(args[i])) {
                System.out.print("Argument type mismatch");
                return;
            }
        }
        if (args.length < 2) {
            System.out.print("Argument count mismatch");
            return;
        }
        
        int sum = 0;
        
        for (int i = 1; i < args.length; i++) {
            int num = (Integer.parseInt(args[i]));
            sum += num;
        }
        System.out.println(sum);
        
        int max = 0;
        for (int i = 1; i < args.length; i++) {
            int num = (Integer.parseInt(args[i]));
            if (num > max) {
                max = num;
            }
        }
        System.out.println(max);
        
        int min = (Integer.parseInt(args[1]));
        for (int i =1; i < args.length; i++) {
            int num = (Integer.parseInt(args[i]));
            if (num < min) {
                min = num;
            }
            
        }
        System.out.println(min);
        
        
        double current = 0;
        for (int i = 1; i < args.length; i++) {
            double num = (Double.parseDouble(args[i]));
            current += num;
        }
        
        double average = (current / (args.length -1.0));
        
        System.out.printf("%.2f\n", average);
    }
    
    private static void table(String[] args){
        for (int i = 1; i < args.length; i++) {
            if (!isInteger(args[i])) {
                System.out.print("Argument type mismatch");
                return;
            }
        }
        if (args.length < 2) {
            System.out.print("Argument count mismatch");
            return;
        }
        
        
        int n = Integer.parseInt(args[1]);
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                System.out.printf("%6d",((i*j)+n));
            }
            System.out.println();
        }
    }
}


