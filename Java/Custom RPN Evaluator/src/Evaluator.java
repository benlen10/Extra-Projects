import java.util.Scanner;
import java.util.Stack;

public class Evaluator {
    public static int evaluate(String input) {
        Stack st = new Stack();
        Scanner scan = new Scanner(input);
        while (scan.hasNext()) {
            boolean status = true;
            String temp = scan.next();
            System.out.println("Input:" + temp);
            System.out.println("");
            if (temp.equals("+")) {
                int a = (Integer) st.pop(); 
                int b = (Integer) st.pop();
                st.push(new Integer (a+b));
                status = false;
            }
            
            if (temp.equals("-")) {
                int a = (Integer) st.pop(); 
                int b = (Integer) st.pop();
                st.push(new Integer (b-a));
                status = false;
            }
            
            if (temp.equals("*")) {
                int a = (Integer) st.pop(); 
                int b = (Integer) st.pop();
                st.push(new Integer (a*b));
                status = false;
            }
            if (status){
                st.push(new Integer(Integer.parseInt(temp)));
            }
            System.out.println("Current:" + (Integer) st.peek());
            
        }
        return (Integer) st.peek();
    }
    public static void main(String[] args){
        String input = "5 1 2 + 4 * + 3 - ";
        System.out.println(evaluate(input));
    }
}

