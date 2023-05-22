import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShorsAlgorithm {

    public static List<BigInteger> findFactors(BigInteger n) {
        List<BigInteger> factors = new ArrayList<>();
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            factors.add(BigInteger.TWO);
            factors.add(n.divide(BigInteger.TWO));
            return factors;
        }

        while (true) {
            BigInteger a = randomBigInteger(n.subtract(BigInteger.ONE)).add(BigInteger.ONE);
            BigInteger gcd = a.gcd(n);
            if (gcd.compareTo(BigInteger.ONE) > 0) {
                factors.add(gcd);
                factors.add(n.divide(gcd));
                return factors;
            }

            BigInteger r = findPeriod(a, n);

            if (r != null && r.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
                BigInteger x = a.modPow(r.divide(BigInteger.TWO), n);
                BigInteger factor1 = x.subtract(BigInteger.ONE).gcd(n);
                BigInteger factor2 = x.add(BigInteger.ONE).gcd(n);

                if (factor1.compareTo(BigInteger.ONE) > 0 && factor2.compareTo(BigInteger.ONE) > 0) {
                    factors.add(factor1);
                    factors.add(factor2);
                    return factors;
                }
            }
        }
    }

    private static BigInteger findPeriod(BigInteger a, BigInteger n) {
        BigInteger x = BigInteger.ONE;
        BigInteger y = a;

        for (BigInteger i = BigInteger.ONE; i.compareTo(n) < 0; i = i.add(BigInteger.ONE)) {
            if (y.equals(BigInteger.ONE) && !i.equals(BigInteger.ONE)) {
                return i;
            }

            x = x.multiply(y).mod(n);
            y = y.multiply(a).mod(n);
        }

        return null;
    }

    private static BigInteger randomBigInteger(BigInteger max) {
        BigInteger result;
        Random random = new Random();
        do {
            result = new BigInteger(max.bitLength(), random);
        } while (result.compareTo(max) >= 0);
        return result;
    }
}