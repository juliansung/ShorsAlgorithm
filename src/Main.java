import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//          long startTime = System.currentTimeMillis();
//        BigInteger N = new BigInteger("10097063");
//        BigInteger factor = ShorsAlgorithm.findFactors(N).get(0);
//        BigInteger factor2 = N.divide(factor);
//        System.out.println("Factors of " + N + " are " + factor + " and " + factor2);
//        long endTime = System.currentTimeMillis();
//        System.out.println("Processing time = " + (endTime-startTime));
//        2d06ff8gcad9c22g1a50dadg4d32fa5g80de2g
//Your primes were 7079 and 39397
//The private key is 167306933, 278891363
//The public key is 5, 278891363
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome. Input E for encryption and D for decryption");
        String input = scan.nextLine();
        if(input.equalsIgnoreCase("E")) encrypt();
        else if(input.equalsIgnoreCase("D")) decrypt();

    }

    public static boolean isPrime(BigInteger num) {
        if (num.compareTo(BigInteger.ONE) <= 0) return false;
        return num.isProbablePrime(100);
    }

    private static BigInteger generatePrime(int digits) {
//        BigInteger min = BigInteger.TEN.pow(numDigits - 1);
//        BigInteger max = BigInteger.TEN.pow(numDigits+1);
//        BigInteger prime = new BigInteger(numDigits * 5, new Random());
//        while (prime.compareTo(max)<0) {
//            if (prime.isProbablePrime(100000)){
//                return prime;
//            }
//            prime = prime.add(BigInteger.ONE);
//        }
//        return prime;
        BigInteger max = BigInteger.valueOf(10^(digits+1)); // maximum value
        Random random = new Random();
        BigInteger result;
        do {
            result = new BigInteger((int) (digits*3.3), random);
        } while (!result.isProbablePrime(1000));
        return result;
    }




    public static String decrypt(){
        Scanner scan = new Scanner(System.in);
        rsa rsa = new rsa();
        System.out.println("You have chosen to decrypt.  Enter N to leave, P to decrypt with primes, S to decrypt with your key.  Enter G to let the computer guess, given the public key.  Guessing may take time.");
        String ok = "ok";
        String input = scan.nextLine();
        if(input.equals("N")) {
            System.out.println(ok);
            return ok;
        }else if(input.equalsIgnoreCase("P")){
            System.out.println("Enter your primes.");
            BigInteger prime1 = scan.nextBigInteger();
            BigInteger prime2 = scan.nextBigInteger();
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
            return text2;
        }else if(input.equalsIgnoreCase("G")) {
            System.out.println("Enter your key.  The computer will take some time to process your input.  Numbers over 10 digits will take more than a few minutes. 4/5 digits works well.");
            BigInteger e = scan.nextBigInteger();
            BigInteger n = scan.nextBigInteger();
            long startTime1 = System.currentTimeMillis();
            List<BigInteger> factors = ShorsAlgorithm.findFactors(n);
            BigInteger p = factors.get(0);
            BigInteger q = factors.get(1);
            BigInteger lambda = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
            BigInteger e1 = BigInteger.valueOf(3);
            while (lambda.gcd(e1).intValue() > 1) e1 = e1.add(BigInteger.valueOf(2));
            BigInteger d = e1.modInverse(lambda);
            long endTime1 = System.currentTimeMillis();
            System.out.println("Enter your message.");
            scan.nextLine();
            String encrypted = scan.nextLine();
            long startTime2 = System.currentTimeMillis();
            String[] splitHex = encrypted.split("g");
            BigInteger[] decrypted = new BigInteger[splitHex.length];
            BigInteger[] decryptedHex = new BigInteger[splitHex.length];
//            System.out.println(Arrays.toString(splitHex));
            for (int i = 0; i < splitHex.length; i++) {
                BigInteger hex = new BigInteger(splitHex[i], 16);
                decryptedHex[i] = BigInteger.valueOf(hex.intValue());
            }
//            System.out.println(Arrays.toString(decryptedHex));
            for (int i = 0; i < splitHex.length; i++) decrypted[i] = rsa.decryptSpecific(decryptedHex[i], d, n);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < splitHex.length; i++) {
                char letter = (char) (decrypted[i].intValue());
                sb.append(letter);
            }
            String text2 = sb.toString();
            BigInteger N = n;
//            List<BigInteger> factors1 = new ArrayList<>();
            long endTime2 = System.currentTimeMillis();
            System.out.println("Code cracked.  The primes were " + p + " and " + q);
            System.out.println("Your message was " + text2);
            System.out.println("Processing time: " + (((endTime1-startTime1)/1000) + ((endTime2-startTime2)/1000)) + " s");
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
        else if(input.equalsIgnoreCase("R")){
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
        else if(input.equalsIgnoreCase("P")){
            System.out.println("Enter your primes. Alternatively, enter I if you want some inspiration. ");
            if(scan.next().equalsIgnoreCase("I")){
                System.out.println("Enter the approximate amount of digits you want.");
                System.out.println("If the product of your two primes exceeds 10 digits or so it will take a while.");
                int digits = scan.nextInt();
                BigInteger prime1 = BigInteger.valueOf(1);
                while(!isPrime(prime1)){
                    prime1 = generatePrime(digits);
                }
                System.out.println(prime1);
                System.out.println("You can press I again, or enter your two primes.");
            }
            if(scan.next().equalsIgnoreCase("I")){
                System.out.println("Enter the amount of digits you want.");
                int digits = scan.nextInt();
                BigInteger prime2 = BigInteger.valueOf(1);
                while(!isPrime(prime2)){
                    prime2 = generatePrime(digits);
                }
                System.out.println(prime2);
                System.out.println("Enter the two generated primes.");
            }
            BigInteger p = scan.nextBigInteger();
            BigInteger q = scan.nextBigInteger();
            while(!isPrime(p)&&isPrime(q)){
                System.out.println("Not prime.  Try again.");
                System.out.println("Enter your primes.");
                p = scan.nextBigInteger();
                q = scan.nextBigInteger();
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
            StringBuilder encryptedHex = new StringBuilder(new String());
            for(int i = 0; i<chars.length; i++){
                encryptedInt[i] = rsa.encrypt(values[i]);
                encryptedHex.append(Integer.toHexString(encryptedInt[i].intValue())).append("g");
            }
            String encryptedText = encryptedHex.toString();
            System.out.println(encryptedText);
            System.out.println("Your primes were " + rsa.p + " and " + rsa.q);
            System.out.println("The private key is " + rsa.d + ", " + rsa.n);
            System.out.println("The public key is " + rsa.e + ", " + rsa.n);
            return encryptedText;
        }else if(input.equals("S")){
            System.out.println("Enter your key in order of size.");
            BigInteger e = scan.nextBigInteger();
            BigInteger n = scan.nextBigInteger();
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
                encryptedInt[i] = rsa.encryptSpecific(values[i],e, n);
                encryptedHex += Integer.toHexString(encryptedInt[i].intValue()) + "g";
            }
            String encryptedText = encryptedHex;
            System.out.println(encryptedText);
//            BigInteger N = (n);
//            BigInteger factor1 = shorsAlgorithm.shorsAlgorithm(n);
//            System.out.println("Your primes were " + factor1 + " and " +  N.divide(factor1));
            return encryptedText;
        }
        return ok;
    }


}