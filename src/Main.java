import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        BigInteger N = new BigInteger("185585902431430716049");
//        BigInteger factor = shorsAlgorithm.shorsAlgorithm(N);
//        BigInteger factor2 = N.divide(factor);
//        System.out.println("Factors of " + N + " are " + factor + " and " + factor2);


//        rsa rsa = new rsa();
//        String message = "12345";
//        BigInteger messageAsInt = new BigInteger(message.getBytes());
//        System.out.println("Message as integer: " + messageAsInt);
//
//        BigInteger encryptedMessage = rsa.encrypt(messageAsInt);
//        System.out.println("Encrypted message: " + encryptedMessage);
//
//        BigInteger decryptedMessage = rsa.decrypt(encryptedMessage);
//        System.out.println("Decrypted message as integer: " + decryptedMessage);
//
//        String decryptedText = new String(decryptedMessage.toByteArray());
//        System.out.println("Decrypted message: " + decryptedText);



        String text = "Hello";
        char[] chars = text.toCharArray();
        BigInteger[] values = new BigInteger[chars.length];
        for (int i = 0; i < chars.length; i++) {
            values[i] = BigInteger.valueOf(Character.getNumericValue(chars[i]));
        }
        System.out.println(Arrays.toString(values));


        StringBuilder sb = new StringBuilder();
        for (BigInteger num : values) {
            char letter = (char) num.intValue();
            sb.append(letter);
        }
        String text2 = sb.toString();
        System.out.println(text2);


    }

}