import java.math.BigInteger;
import java.util.Random;

public class shorsAlgorithm {
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger TWO = BigInteger.valueOf(2L);
    private static final Random RANDOM = new Random();


    public static BigInteger shorsAlgorithm(BigInteger N) {
        if (N.mod(TWO).equals(BigInteger.ZERO)) {
            return TWO;
        }

        BigInteger a;
        BigInteger gcd;
        do {
            a = getRandomNumber(N);
            gcd = a.gcd(N);
        } while (gcd.equals(ONE));

        if (!gcd.equals(N)) {
            return gcd;
        }

        while (true) {
            BigInteger r = findPeriod(a, N);
            if (r.mod(TWO).equals(BigInteger.ZERO)) {
                BigInteger x = a.modPow(r.divide(TWO), N);
                BigInteger y = x.subtract(ONE).gcd(N);
                if (!y.equals(ONE) && !y.equals(N)) {
                    return y;
                }
            }
        }
    }

    private static BigInteger getRandomNumber(BigInteger N) {
        return new BigInteger(N.bitLength(), RANDOM).mod(N.subtract(TWO)).add(TWO);
    }

    private static BigInteger findPeriod(BigInteger a, BigInteger N) {
        BigInteger x = BigInteger.ONE;
        BigInteger exponent = BigInteger.ZERO;
        BigInteger result = a.modPow(exponent, N);

        while (!result.equals(ONE)) {
            exponent = exponent.add(BigInteger.ONE);
            result = a.modPow(exponent, N);
            x = x.add(BigInteger.ONE);
        }
        return x;
    }
}