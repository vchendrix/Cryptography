package cs6520;

/**
 * These are various algorithms that I have learned through my Cryptography and
 * Data Security class. The purpose of implementing the algorithms was to
 * understand there Number Theory concepts behind them. Also, I have never
 * really done much bitwise arithmetic in java to this was a good introduction
 * to that.
 * 
 * @author Valerie Hendrix
 * 
 */
public final class Algorithms
{
    /**
     * This is Euclids Extended algorithm {@linkplain http
     * ://en.wikipedia.org/wiki/Extended_Euclidean_algorithm}
     * 
     * @param m
     *            - modulus
     * @param b
     * @return {d,x,y} where d=mx+by or d=gcd(m,b)
     */
    static int[] euclid(int m, int b)
    {
        int[] A =
        { 1, 0, m };
        int[] B =
        { 0, 1, b };
        while (true)
        {
            if (B[2] == 0)
            {
                A[2] = gcd(m, b);
                return new int[]
                { A[2], A[0], A[1] };
            }
            if (B[2] == 1)
            {
                B[2] = gcd(m, b);
                return new int[]
                { B[2], B[0], B[1] };
            }
            int Q = (A[2] / B[2]);
            int[] T =
            { A[0] - (Q * B[0]), A[1] - (Q * B[1]), A[2] - (Q * B[2]) };
            A = B;
            B = T;
        }

    }

    /**
     * This is Euclid's algorithm for finding the greatest common divisor
     * 
     * @param a
     * @param b
     * @return
     */
    static int gcd(int a, int b)
    {
        int A = a;
        int B = b;
        while (B > 0)
        {
            int R = A % B;
            A = B;
            B = R;
        }
        return A;
    }

    /**
     * Uses the {@link #miller_rabin_32(int)} test to determine if the specified
     * number is prime.
     * 
     * @param n
     * @return "INCONCUSIVE" the test is passed and "COMPOSITE" if it is not
     */
    static String prime(int n)
    {
        return miller_rabin_32(n) ? "INCONCLUSIVE" : "COMPOSITE";

    }

    /**
     * This is a deterministic, 100% accurate implemetation that tries the values 
     * of 2,4,7, and 61(Jaeschke's result) for a returning true if they all produce true and false 
     * otherwise.
     * 
     * {@linkplain http://en.literateprograms.org/Miller-Rabin_primality_test_(Java)#chunkdef:Miller-Rabin pass}
     * 
     * @param n
     * @return
     */
    public static boolean miller_rabin_32(int n)
    {
        if (n <= 1)
            return false;
        else if (n == 2)
            return true;
        else if (miller_rabin_pass_32(2, n)
                && (n <= 7 || miller_rabin_pass_32(7, n))
                && (n <= 61 || miller_rabin_pass_32(61, n)))
            return true;
        else
            return false;
    }

    /**
     * This function tests the desired formulas for a given a and n. It works by
     * staring with a^d and squaring s-1 times, comparing each to -1(n-1
     * actually; we have to add n because we're workign with nonnegative
     * numbers)
     * 
     * {@linkplain http://en.literateprograms.org/Miller-Rabin_primality_test_(Java)#chunkdef:Miller-Rabin pass}
     * 
     * @param n
     * @return TRUE if prime FALSE if composite
     */
    private static boolean miller_rabin_pass_32(int a, int n)
    {
        int d = n - 1;
        int s = Integer.numberOfTrailingZeros(d);
        d >>= s;
        int a_to_power = modular_exponent_32(a, d, n);
        if (a_to_power == 1)
            return true;
        for (int i = 0; i < s - 1; i++)
        {
            if (a_to_power == n - 1)
                return true;
            a_to_power = modular_exponent_32(a_to_power, 2, n);
        }
        if (a_to_power == n - 1)
            return true;
        return false;
    }

    /**
     * This is a way to perform efficient modular exponentiation on an arbitrary
     * 32-bit integer. We accomplish this using exponentiation by squaring. int
     * is a 32-bit integer type and long is a 64-bit integer type. Since the
     * product of two 32-bit numbers has at most 64 bits, we know for certain
     * that this will never overflow or truncate bits.
     * 
     * {@linkplain http://en.literateprograms.org/Miller-Rabin_primality_test_(Java)#chunkdef:Miller-Rabin pass}
     * 
     * @param base
     * @param power
     * @param modulus
     * @return
     */
    private static int modular_exponent_32(int base, int power, int modulus)
    {
        long result = 1;
        for (int i = 31; i >= 0; i--)
        {
            result = (result * result) % modulus;
            if ((power & (1 << i)) != 0)
            {
                result = (result * base) % modulus;
            }
        }
        return (int) result; // Will not truncate since modulus is an int
    }

}
