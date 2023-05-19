import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        BigInteger N = new BigInteger("185585902431430716049");
//        BigInteger factor = shorsAlgorithm.shorsAlgorithm(N);
//        BigInteger factor2 = N.divide(factor);
//        System.out.println("Factors of " + N + " are " + factor + " and " + factor2);
//        rsa rsa = new rsa();
//        String text = "Albert Hou";
//        char[] chars = text.toCharArray();
//        BigInteger[] values = new BigInteger[chars.length];
//        for (int i = 0; i < chars.length; i++) {
//            int intVal = (int) chars[i];
//            values[i] = BigInteger.valueOf(intVal);
//        }
//        BigInteger[] encryptedInt = new BigInteger[chars.length];
//        String encryptedHex = new String();
//        for(int i = 0; i<chars.length; i++){
//            encryptedInt[i] = rsa.encrypt(values[i]);
//            encryptedHex += Integer.toHexString(encryptedInt[i].intValue()) + "g";
//        }
//        System.out.println(encryptedHex);
//        System.out.println(rsa.p);
//        System.out.println(rsa.q);
//        BigInteger[] decrypted = new BigInteger[chars.length];
//        BigInteger[] decryptedHex = new BigInteger[chars.length];
//        String[] splitHex = encryptedHex.split("g");
////        System.out.println(Arrays.toString(splitHex));
//        for(int i = 0; i<chars.length; i++){
//            BigInteger hex = new BigInteger(splitHex[i],16);
//            decryptedHex[i] = BigInteger.valueOf(hex.intValue());
//
//        }
//        System.out.println(Arrays.toString(decryptedHex));
//        for(int i = 0; i< chars.length; i++){
//            decrypted[i] = rsa.decrypt(decryptedHex[i]);
//        }
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < chars.length; i++) {
//            char letter = (char) (decrypted[i].intValue());
//            sb.append(letter);
//        }
//        String text2 = sb.toString();
//        System.out.println(text2);

        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome. Input E for encryption and D for decryption");
        String input = scan.nextLine();
        if(input.equalsIgnoreCase("E")){
            encrypt();
        }else if(input.equalsIgnoreCase("D")){
            decrypt();
        }
    }

    public static boolean isPrime(long n)
    {
        if (n <= 1)
            return false;
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;
        return true;
    }

    public static String decrypt(){
        Scanner scan = new Scanner(System.in);
        rsa rsa = new rsa();
        System.out.println("You have chosen to decrypt.  Enter N to leave, P to decrypt with primes, S to decrypt with your key.  Enter G to let the computer guess, given the public key.  May not always work.");
        String ok = "ok";
        String input = scan.nextLine();
        if(input.equals("N")) {
            System.out.println(ok);
            return ok;
        }else if(input.equals("P")){
            System.out.println("Enter your primes.");
            int prime1 = scan.nextInt();
            int prime2 = scan.nextInt();
            System.out.println("Enter your message.");
            scan.nextLine();
            String encrypted = scan.nextLine();
            rsa.rsaSpecific(prime1, prime2);
            String[] splitHex = encrypted.split("g");
            BigInteger[] decrypted = new BigInteger[splitHex.length];
            BigInteger[] decryptedHex = new BigInteger[splitHex.length];
//            System.out.println(Arrays.toString(splitHex));
            for(int i = 0; i<splitHex.length; i++){
                BigInteger hex = new BigInteger(splitHex[i],16);
                decryptedHex[i] = BigInteger.valueOf(hex.intValue());
            }
//            System.out.println(Arrays.toString(decryptedHex));
            for(int i = 0; i< splitHex.length; i++){
                decrypted[i] = rsa.decrypt(decryptedHex[i]);
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < splitHex.length; i++) {
                char letter = (char) (decrypted[i].intValue());
                sb.append(letter);
            }
            String text2 = sb.toString();
            System.out.println(text2);
            return text2;
        }else if(input.equalsIgnoreCase("S")){
            System.out.println("Enter your key.");
            BigInteger d = BigInteger.valueOf(scan.nextLong());
            BigInteger n = BigInteger.valueOf(scan.nextLong());
            System.out.println("Enter your message.");
            scan.nextLine();
            String encrypted = scan.nextLine();
            String[] splitHex = encrypted.split("g");
            BigInteger[] decrypted = new BigInteger[splitHex.length];
            BigInteger[] decryptedHex = new BigInteger[splitHex.length];
//            System.out.println(Arrays.toString(splitHex));
            for(int i = 0; i<splitHex.length; i++){
                BigInteger hex = new BigInteger(splitHex[i],16);
                decryptedHex[i] = BigInteger.valueOf(hex.intValue());
            }
//            System.out.println(Arrays.toString(decryptedHex));
            for(int i = 0; i< splitHex.length; i++){
                decrypted[i] = rsa.decryptSpecific(decryptedHex[i], d, n);
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < splitHex.length; i++) {
                char letter = (char) (decrypted[i].intValue());
                sb.append(letter);
            }
            String text2 = sb.toString();
            System.out.println("Your message was " + text2);
//            BigInteger N = n;
//            BigInteger factor1 = shorsAlgorithm.shorsAlgorithm(N);
//            System.out.println("Your primes were " + factor1 + " and " +  N.divide(factor1));
//            e554000g98ae6ccdg6a01d01bge554000g
//            Your primes were 663526531001 and 354656231779
//            The private key is 201706130716447116101143, 235323819170206484880779
//            The public key is 7, 235323819170206484880779
            return text2;
        }else if(input.equalsIgnoreCase("G")) {
            System.out.println("Enter your key.");
            BigInteger e = scan.nextBigInteger();
            BigInteger n = scan.nextBigInteger();
            BigInteger p = shorsAlgorithm.shorsAlgorithm(n);
            BigInteger q = n.divide(p);
            BigInteger lambda = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
            BigInteger e1 = BigInteger.valueOf(3);
            while (lambda.gcd(e1).intValue() > 1){
                e1 = e1.add(BigInteger.valueOf(2));
            }
            BigInteger d = e1.modInverse(lambda);
            System.out.println("Enter your message.");
            scan.nextLine();
            String encrypted = scan.nextLine();
            String[] splitHex = encrypted.split("g");
            BigInteger[] decrypted = new BigInteger[splitHex.length];
            BigInteger[] decryptedHex = new BigInteger[splitHex.length];
//            System.out.println(Arrays.toString(splitHex));
            for (int i = 0; i < splitHex.length; i++) {
                BigInteger hex = new BigInteger(splitHex[i], 16);
                decryptedHex[i] = BigInteger.valueOf(hex.intValue());
            }
//            System.out.println(Arrays.toString(decryptedHex));
            for (int i = 0; i < splitHex.length; i++) {
                decrypted[i] = rsa.decryptSpecific(decryptedHex[i], d, n);
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < splitHex.length; i++) {
                char letter = (char) (decrypted[i].intValue());
                sb.append(letter);
            }
            String text2 = sb.toString();
            BigInteger N = n;
            BigInteger factor1 = shorsAlgorithm.shorsAlgorithm(N);
            System.out.println("Code cracked.  The primes were " + factor1 + " and " + N.divide(factor1));
            System.out.println("Your message was " + text2);
            return text2;
        }
    return ok;
    }
    public static String encrypt(){
        Scanner scan = new Scanner(System.in);
        rsa rsa = new rsa();
        System.out.println("You have chosen to encrypt.  Enter N to leave, R to encrypt with a random key, P to encrypt with primes and S to encrypt with a specified key. ");
        String ok = "ok";
        String input = scan.nextLine();
        if(input.equals("N")) {
            System.out.println(ok);
            return ok;
        }
        else if(input.equals("R")){
            System.out.println("Enter your message.");
            String plainText = null;
            plainText = scan.nextLine();
            char[] chars = plainText.toCharArray();
            BigInteger[] values = new BigInteger[chars.length];
            for (int i = 0; i < chars.length; i++) {
                int intVal = (int) chars[i];
                values[i] = BigInteger.valueOf(intVal);
            }
            BigInteger[] encryptedInt = new BigInteger[chars.length];
            String encryptedHex = new String();
            for(int i = 0; i<chars.length; i++){
                encryptedInt[i] = rsa.encrypt(values[i]);
                encryptedHex += Integer.toHexString(encryptedInt[i].intValue()) + "g";
            }
            String encryptedText = encryptedHex;
            System.out.println(encryptedText);
            System.out.println("Your primes were " + rsa.p + " and " + rsa.q);
            System.out.println("The private key is " + rsa.d + ", " + rsa.n);
            System.out.println("The public key is " + rsa.e + ", " + rsa.n);
            return encryptedText;

        }
        else if(input.equals("P")){
            System.out.println("Enter your primes.");
            long p = scan.nextLong();
            long q = scan.nextLong();

            while(!isPrime(p)&&isPrime(q)){
                System.out.println("Not prime.  Try again.");
                System.out.println("Enter your primes.");
                p = scan.nextInt();
                q = scan.nextInt();
            }
//            System.out.println("These numbers are prime.");
            rsa.setP(p);
            rsa.setQ(q);
            rsa.rsaSpecific(p,q);
            String plainText = null;
            System.out.println("Enter your message.");
            scan.nextLine();
            plainText = scan.nextLine();
            char[] chars = plainText.toCharArray();
            BigInteger[] values = new BigInteger[chars.length];
            for (int i = 0; i < chars.length; i++) {
                int intVal = (int) chars[i];
                values[i] = BigInteger.valueOf(intVal);
            }
            BigInteger[] encryptedInt = new BigInteger[chars.length];
            String encryptedHex = new String();
            for(int i = 0; i<chars.length; i++){
                encryptedInt[i] = rsa.encrypt(values[i]);
                encryptedHex += Integer.toHexString(encryptedInt[i].intValue()) + "g";
            }
            String encryptedText = encryptedHex;
            System.out.println(encryptedText);
            System.out.println("Your primes were " + rsa.p + " and " + rsa.q);
            System.out.println("The private key is " + rsa.d + ", " + rsa.n);
            System.out.println("The public key is " + rsa.e + ", " + rsa.n);
            return encryptedText;
        }else if(input.equals("S")){
            System.out.println("Enter your key.");
            int e = scan.nextInt();
            int n = scan.nextInt();
//            System.out.println("These numbers are prime.");
            rsa.setE(e);
            rsa.setN(n);
            String plainText = null;
            System.out.println("Enter your message.");
            scan.nextLine();
            plainText = scan.nextLine();
            char[] chars = plainText.toCharArray();
            BigInteger[] values = new BigInteger[chars.length];
            for (int i = 0; i < chars.length; i++) {
                int intVal = (int) chars[i];
                values[i] = BigInteger.valueOf(intVal);
            }
            BigInteger[] encryptedInt = new BigInteger[chars.length];
            String encryptedHex = new String();
            for(int i = 0; i<chars.length; i++){
                encryptedInt[i] = rsa.encryptSpecific(values[i],BigInteger.valueOf(e), BigInteger.valueOf(n));
                encryptedHex += Integer.toHexString(encryptedInt[i].intValue()) + "g";
            }
            String encryptedText = encryptedHex;
            System.out.println(encryptedText);
            BigInteger N = BigInteger.valueOf(n);
            BigInteger factor1 = shorsAlgorithm.shorsAlgorithm(BigInteger.valueOf(n));
            System.out.println("Your primes were " + factor1 + " and " +  N.divide(factor1));
            return encryptedText;
        }
        return ok;
    }


}