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
        while (lambda.gcd(e).intValue() > 1) e = e.add(BigInteger.valueOf(2));


        d = e.modInverse(lambda);
    }
    public void rsaSpecific(BigInteger prime1, BigInteger prime2) {
        p = prime1;
        q = prime2;

        n = p.multiply(q);

        lambda = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        e = BigInteger.valueOf(3);
        while (lambda.gcd(e).intValue() > 1) e = e.add(BigInteger.valueOf(2));

        d = e.modInverse(lambda);
    }

    public static void setP(BigInteger p1){
        p=p1;
    }
    public static void setQ(BigInteger q1){
        q=q1;
    }

    public static void setD(BigInteger d1){
        d=d1;
    }
    public static void setE(BigInteger e1){
        e=e1;
    }
    public static void setN(BigInteger n1){
        n=n1;
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
