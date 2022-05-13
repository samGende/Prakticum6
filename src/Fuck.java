import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Fuck {
    public static void main(String[] args) {
        // Eingabe lesen
        int[] values = new int[]{1661914940, 0, 4, 5,6 };
        lsdRadix(values);
        Instant finish = Instant.now();

        System.out.println("After sorting: " + Arrays.toString(values));


        // Sortierung überprüfen
        assert isSorted(values);
    }

    public static void lsdRadix(int[] data) {
        for(int i = 0; i <= 3; i++) {
            sortByByte(data, 0, data.length - 1, i);
        }
    }

    public static void sortByByte(int[] input, int l, int r, int b) {
        int[] count = new int[256];

        // Frequenzen zählen
        for(int value : input) {
            count[getKey(value, b)]++;
        }

        // Adressen berechnen
        for(int i = count.length - 2; i >= 0; i--) {
            count[i] += count[i + 1];
        }

        int[] output = new int[r - l + 1];

        // Ausgabearray füllen
        for(int i = input.length - 1; i >= 0; i--) {
            int key = getKey(input[i], b);
            output[count[key] - 1] = input[i];
            count[key]--;
        }

        // Daten ins Eingabearray schreiben
        for(int i = 0; i < output.length; i++) {
            input[l + i] = output[i];
        }
    }

    private static int getKey(int value, int b) {
        return (value >> (8 * b)) & 0xFF;
    }

    private static int[] readValues() {
        Scanner input = new Scanner(System.in);

        // Füge alle Eingaben in die ArrayList ein
        ArrayList<Integer> values = new ArrayList<>();
        while (input.hasNextLine()) {
            try {
                int value = Integer.parseInt(input.nextLine());
                values.add(value);
            } catch (NumberFormatException e) {
                input.close();
                System.err.println("Input list contains a non-number.");
                return null;
            }
        }
        input.close();

        // Übertrage Eingaben ins Array
        int[] allValues = new int[values.size()];
        for(int i = 0; i < allValues.length; i++) {
            allValues[i] = values.get(i);
        }

        // Array zurückgeben
        return allValues;
    }

    public static boolean isSorted(int[] data) {
        for(int i = 0; i < data.length - 1; i++) {
            // Sortierung verletzt
            if(data[i] < data[i + 1]) {
                return false;
            }
        }
        return true;
    }
}