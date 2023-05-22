import org.apache.commons.numbers.complex.Complex;
import org.apache.commons.math4.transform.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//import org.apache.commons.math3.transform;
public class ShorsAlgorithm {

    public static List<BigInteger> findFactors(BigInteger n) {
        List<BigInteger> factors = new ArrayList<>();
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            factors.add(BigInteger.TWO);
            factors.add(n.divide(BigInteger.TWO));
            return factors;
        }

        BigInteger one = BigInteger.ONE;
        BigInteger two = BigInteger.valueOf(2);
        Random random = new Random();

        while (true) {
            BigInteger a = BigInteger.valueOf(random.nextInt(n.intValue() - 2) + 2);
            BigInteger gcd = n.gcd(a);

            if (gcd.compareTo(one) > 0) {
                factors.add(gcd);
                factors.add(n.divide(gcd));
                return factors;
            }

            int r = findPeriod(a,n);


            if (r % 2 == 0) {
                BigInteger x = a.modPow(BigInteger.valueOf(r / 2), n);
                BigInteger factor = x.add(one).gcd(n);

                if (factor.compareTo(one) > 0 && factor.compareTo(n) < 0) {
                    factors.add(factor);
                    factors.add(n.divide(factor));
                    return factors;

                }
            }else{
                a = BigInteger.valueOf(random.nextInt(n.intValue() - 2) + 2);
                r = findPeriod(a,n);
                System.out.println(a);
            }
        }
    }

    public static int findPeriod(BigInteger a, BigInteger n) {
        Random rand = new Random();
        int m = (int) Math.ceil(2 * Math.sqrt(n.doubleValue()));
        Complex[] x = new Complex[m];
        for (int i = 0; i < m; i++) {
            x[i] = new Complex(rand.nextDouble(), 0);
        }
        Complex[] y = qft(x, false);
        Complex[] z = new Complex[m];
        for (int i = 0; i < m; i++) {
            z[i] = y[i].pow(2).subtract(new Complex(1, 0));
        }
        Complex[] w = qft(z, true);
        int r = -1;
        for (int i = 1; i < m && r == -1; i++) {
            double val = w[i].abs() / m;
            if (val > 0.1) {
                r = i;
            }
        }
        if (r == -1) {
            return -1;
        }
        BigInteger q = BigInteger.ZERO;
        for (int i = 0; i < r; i++) {
            double val = w[i + 1].getReal() / m;
            if(val > 0.5) {
                q = q.setBit(i);
            }
        }
        BigInteger period = n.divide(a.gcd(n)).multiply(q).mod(n);
        if (a.modPow(period, n).equals(BigInteger.ONE)) {
            return period.intValue();
        } else {
            return -1;
        }
    }

    public static Complex[] qft(Complex[] x, boolean inverse) {
        int n = x.length;
        Complex[] y = new Complex[n];
        for (int k = 0; k < n; k++) {
            y[k] = new Complex(0, 0);
            for (int j = 0; j < n; j++) {
                double theta = (inverse ? 2 * Math.PI : -2 * Math.PI) * k * j / n;
                y[k] = y[k].add(x[j].multiply(new Complex(Math.cos(theta), Math.sin(theta))));
            }
            if (inverse) {
                y[k] = y[k].divide(new Complex(n, 0));
            }
        }
        if (!inverse) {
            for (int k = 0; k < n; k++) {
                y[k] = y[k].divide(new Complex(Math.sqrt(n), 0));
            }
        }
        return y;
    }

    public static class Complex {
        private final double real;
        private final double imag;

        public Complex(double real, double imag) {
            this.real = real;
            this.imag = imag;
        }

        public double getReal() {
            return real;
        }

        public double getImag() {
            return imag;
        }

        public Complex add(Complex other) {
            return new Complex(real + other.real, imag + other.imag);
        }

        public Complex subtract(Complex other) {
            return new Complex(real - other.real, imag - other.imag);
        }

        public Complex multiply(Complex other) {
            double re = real * other.real - imag * other.imag;
            double im = real * other.imag + imag * other.real;
            return new Complex(re, im);
        }

        public Complex divide(Complex other) {
            double denominator = other.real * other.real + other.imag * other.imag;
            double re = (real * other.real + imag * other.imag) / denominator;
            double im = (imag * other.real - real * other.imag) / denominator;
            return new Complex(re, im);
        }

        public Complex pow(int n) {
            Complex result = new Complex(1, 0);
            for (int i = 0; i < n; i++) {
                result = result.multiply(this);
            }
            return result;
        }

        public double abs() {
            return Math.sqrt(real * real + imag * imag);
        }
    }
}

