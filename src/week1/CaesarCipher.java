package week1;
import edu.duke.*;

public class CaesarCipher {
	//fields
    private static String alphabet;
    private static String shiftedAlphabet;
    private int mainKey;
    
    // Constructor
    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }
    
    // Methods
    public  static String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
                
        for (int i = 0; i < input.length(); i++) {
            char letter = encrypted.charAt(i);
            int index = alphabet.indexOf(Character.toUpperCase(letter));
            if (index != -1) {
                
                if (Character.isUpperCase(letter)) {
                    encrypted.setCharAt(i, shiftedAlphabet.charAt(index));
                }
                else {
                    encrypted.setCharAt(i, Character.toLowerCase(shiftedAlphabet.charAt(index)));
                }
            }
        }
        return encrypted.toString();
    }

    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
    
    public static void main(String args[]) {
    	String s = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
    	CaesarCipher c = new CaesarCipher(15);
    	String t = encrypt(s);
    	System.out.print(t);
    }
}

