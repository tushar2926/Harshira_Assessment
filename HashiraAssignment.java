import java.util.*;

public class HashiraAssignment {

    // Convert number string from given base to decimal
    public static long toDecimal(String num, int base) {
        long value = 0;
        for (char c : num.toCharArray()) {
            int digit;
            if (Character.isDigit(c)) {
                digit = c - '0';
            } else {
                digit = 10 + (Character.toLowerCase(c) - 'a'); // supports hex-like digits
            }
            value = value * base + digit;
        }
        return value;
    }

    public static void main(String[] args) {
        // Example Input (replace with JSON parsing if needed)
        int n = 4, k = 3;

        int[] bases = {10, 2, 10, 4};
        String[] values = {"4", "111", "12", "213"};

        // Convert values into decimal roots
        List<Long> roots = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            roots.add(toDecimal(values[i], bases[i]));
        }

        // Only keep first k roots
        roots = roots.subList(0, k);

        // Polynomial degree = k (not k-1)
        int degree = k;
        double[][] mat = new double[k][degree + 1];

        // Build system of equations
        for (int i = 0; i < k; i++) {
            double x = roots.get(i);
            double power = 1.0;
            for (int j = 0; j <= degree; j++) {
                mat[i][j] = power;
                power *= x;
            }
            mat[i][degree] = 0; // f(root) = 0
        }

        // Gaussian elimination
        for (int i = 0; i < k; i++) {
            double pivot = mat[i][i];
            if (pivot == 0) continue;
            for (int j = i; j <= degree; j++) mat[i][j] /= pivot;

            for (int r = 0; r < k; r++) {
                if (r != i) {
                    double factor = mat[r][i];
                    for (int c = i; c <= degree; c++) {
                        mat[r][c] -= factor * mat[i][c];
                    }
                }
            }
        }

        // Extract coefficients
        double[] coeffs = new double[k];
        for (int i = 0; i < k; i++) coeffs[i] = mat[i][degree];

        // Print output
        System.out.println("Polynomial Coefficients:");
        for (double c : coeffs) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}

