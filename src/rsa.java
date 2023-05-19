import java.math.BigInteger;
import java.util.Random;
public class rsa {



    static BigInteger p;
    static BigInteger q;
    static BigInteger n;
    private static BigInteger lambda;
    static BigInteger e;

    static BigInteger d;

    public rsa() {
        p = BigInteger.probablePrime(16, new Random());
        q = BigInteger.probablePrime(16, new Random());

        n = p.multiply(q);

        lambda = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        e = BigInteger.valueOf(3);
        while (lambda.gcd(e).intValue() > 1){
            e = e.add(BigInteger.valueOf(2));
        }

        d = e.modInverse(lambda);
    }
    public void rsaSpecific(long prime1, long prime2) {
        p = BigInteger.valueOf(prime1);
        q = BigInteger.valueOf(prime2);

        n = p.multiply(q);

        lambda = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        e = BigInteger.valueOf(3);
        while (lambda.gcd(e).intValue() > 1){
            e = e.add(BigInteger.valueOf(2));
        }

        d = e.modInverse(lambda);
    }

    public static void setP(long p1){
        BigInteger p1B = BigInteger.valueOf(p1);
        p = p1B;
    }
    public static void setQ(long q1){
        BigInteger q1B = BigInteger.valueOf(q1);
        q = q1B;
    }

    public static void setD(int d1){
        BigInteger d1B = BigInteger.valueOf(d1);
        d = d1B;
    }
    public static void setE(int e1){
        BigInteger e1B = BigInteger.valueOf(e1);
        e = e1B;
    }
    public static void setN(int n1){
        BigInteger n1B = BigInteger.valueOf(n1);
        n = n1B;
    }

    public static BigInteger encrypt(BigInteger message){
        return message.modPow(e, n);
    }

    public static BigInteger encryptSpecific(BigInteger message, BigInteger e, BigInteger n){ return message.modPow(e,n);}

    public static BigInteger decrypt(BigInteger encryptedMessage){
        return encryptedMessage.modPow(d,n);
    }

    public static BigInteger decryptSpecific(BigInteger message, BigInteger d, BigInteger n){ return message.modPow(d,n);}

//    public static BigInteger forceDecrypt(BigInteger encrypted){
//        return encrypted.modPow();


}
