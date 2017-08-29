import java.util.Scanner;
public class StringGenerator {
    public static String name = "";
    int space = 0;
    String a = "";
    String b = "";
    public static String newName = "";
    public String createName(String name) {
        this.name = name;
        name = name.toLowerCase();
        space = name.indexOf(" ");
        space ++;
        a = name.substring(0,1);
        b = name.substring(space);
        newName = a.concat(b);
        return newName;
    }
    public static void main(String[] args) {
         Scanner in = new Scanner(System.in);
         StringGenerator e = new StringGenerator();
         System.out.println("Enter Full Name");
         name = in.nextLine();
         e.createName(name);
         System.out.println("Enter Domain");
         String domain = in.nextLine();
         System.out.println(newName.concat(domain));
    }
}

             
       
        
            
            
            
        
        
    
    