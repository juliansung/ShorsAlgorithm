import java.math.BigInteger;
import java.util.Random;
public class rsa {



    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger phi;
    private BigInteger e;
    private BigInteger d;

    public rsa() {
        p = BigInteger.probablePrime(8, new Random());
        q = BigInteger.probablePrime(8, new Random());

        n = p.multiply(q);

        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        e = BigInteger.valueOf(3);
        while (phi.gcd(e).intValue() > 1) {
            e = e.add(BigInteger.valueOf(2));
        }

        d = e.modInverse(phi);
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    public BigInteger decrypt(BigInteger encryptedMessage) {
        return encryptedMessage.modPow(d, n);
    }

}
