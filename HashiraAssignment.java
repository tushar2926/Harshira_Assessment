import java.io.*;
import java.util.*;

public class HashiraAssignment {

    // Convert a number string from given base to decimal
    public static long toDecimal(String num, int base) {
        long value = 0;
        for (char c : num.toCharArray()) {
            int digit;
            if (Character.isDigit(c)) {
                digit = c - '0';
            } else {
                digit = 10 + (Character.toLowerCase(c) - 'a');
            }
            value = value * base + digit;
        }
        return value;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java HashiraAssignment <input.json>");
            return;
        }

        String fileName = args[0];
        StringBuilder sb = new StringBuilder();

        // Read file content
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        String jsonInput = sb.toString();

        int n = 0, k = 0;
        List<Integer> bases = new ArrayList<>();
        List<String> values = new ArrayList<>();

        // Simple parsing
        String[] lines = jsonInput.split("\n");
        for (String line : lines) {
            line = line.trim();

            if (line.contains("\"n\"")) {
                n = Integer.parseInt(line.replaceAll("[^0-9]", ""));
            } else if (line.contains("\"k\"")) {
                k = Integer.parseInt(line.replaceAll("[^0-9]", ""));
            } else if (line.contains("\"base\"")) {
                int base = Integer.parseInt(line.replaceAll("[^0-9]", ""));
                bases.add(base);
            } else if (line.contains("\"value\"")) {
                String value = line.split(":")[1].replaceAll("[^0-9a-zA-Z]", "");
                values.add(value);
            }
        }

        // Convert to decimal roots
        List<Long> roots = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            roots.add(toDecimal(values.get(i), bases.get(i)));
        }

        // Take first k roots
        roots = roots.subList(0, k);

        // Build polynomial coefficients
        List<Long> coeffs = new ArrayList<>();
        coeffs.add(1L);

        for (long r : roots) {
            List<Long> newPoly = new ArrayList<>(Collections.nCopies(coeffs.size() + 1, 0L));
            for (int i = 0; i < coeffs.size(); i++) {
                newPoly.set(i, newPoly.get(i) - r * coeffs.get(i));
                newPoly.set(i + 1, newPoly.get(i + 1) + coeffs.get(i));
            }
            coeffs = newPoly;
        }

        // Print coefficients
        System.out.println("Polynomial Coefficients:");
        for (long c : coeffs) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}
