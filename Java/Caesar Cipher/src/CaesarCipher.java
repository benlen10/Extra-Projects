
//This program will take a input string and key to use the specified cipher to encrypt the string. 
//The decrypt function will attempt to return the original string based on the key

public class CaesarCipher {
    private String text;
    private int key;
    private int count;
    private static int max;
    private static String current;
    private static int finalKey;
    
    
    
    public static String encrypt(String text, int key) {
        char[] array = text.toCharArray();
        for (int i = 0; i < array.length; i++) {
            array[i] += key;
            
            if (array[i] > 'Z') {
                array[i] -= 'Z';
                array[i] += ('A' - 1);
                
            }
        }
        return new String(array);
        
    }
    
    public static String decrypt(String text, int key) {
        char[] array = text.toCharArray();
        for (int i = 0; i < array.length; i++) {
            array[i] -= key;
            
            if (array[i] < 'A') {
                array[i] += 'Z';
                array[i] -= ('A' - 1);
                
            }
        }
        return new String(array);
    }
    
    
    public static String justTheLetters(String text) {
        text = text.replaceAll("[^\\p{L}\\p{Nd}]", "");
        text = text.replaceAll("[0-9]", "");
        text = text.toUpperCase();
        return text;
    }
    public static int crack(String text) {
        for (int i = 0; i < 26; i++) {
            current = CaesarCipher.decrypt(text, i);
            int count = current.length() - current.replace("E", "").length();
            if (count > max) {
                max = count;
                finalKey = i;
            }
        }
        return finalKey;
    }
}



