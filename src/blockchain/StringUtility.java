package blockchain;

import java.security.MessageDigest;


public class StringUtility {
    
    public static String applySha256(String input){
       //Runs input through algorithm, converts the output to
       // hex and then returns the hexadecimal code as a string
       try{
           MessageDigest digest = MessageDigest.getInstance("SHA-256");
           //applies algorithm to our input
           byte[] hash = digest.digest(input.getBytes("UTF-8"));
           // turn the hash into a hexadecimal
           StringBuffer hexString = new StringBuffer();
           for (int i = 0; i < hash.length; i++){
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
		hexString.append(hex);
           }
           return hexString.toString();
        }
        catch(Exception e) {
	    throw new RuntimeException(e);
        }
    }
}
