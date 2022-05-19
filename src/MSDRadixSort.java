import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MSDRadixSort {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> list= new ArrayList<Integer>();
        while (input.hasNext()) {
            try {
                list.add(input.nextInt());
            } catch (InputMismatchException e){
                System.err.println("Input contains a non number");
            }
            catch (NumberFormatException e) {
                System.err.println("Input list contains a non-number.");
                return;
            }
        }
        int[]arr= new int[list.size()];
        for (int i=0; i<list.size(); i++){
            arr[i]=list.get(i);
        }
        if(arr.length == 0){
            System.err.println("Empty list entered");
            return;
        }

        Instant start = Instant.now();
        msdRadix(arr);
        Instant end = Instant.now();

        double time = Duration.between(start, end).toMillis();
        assert isSorted(arr);

        System.out.println("sortiert " + Arrays.toString(arr));

        System.out.println("Time: " + time);



    }

    public static void msdRadix(int[] data){
        msdRadix(data, 0, data.length-1, 3);
    }

    public static void msdRadix(int[] data, int l, int r, int b) {
        // Abschnitte mit 1 oder 0 Elementen sind sortiert
        if(r > l) {
            // Kurze Abschnitte mit InsertionSort sortieren
            if (r - l + 1 <= 32) {
                insertionSort(data, l, r);
            } else if (b >= 0) {
                int[] c = sortByByte(data, l, r, b);

                // Spiegle c[0, 255]
                for (int i = 0; i < 255 / 2; i++) {
                    int h = c[i];
                    c[i] = c[255 - i];
                    c[255 - i] = h;
                }
                c[256] = data.length; // kleinste Zahl geht bis zum Ende

                // Sortiere alle Abschnitte
                // Intervall [c[i], c[i+1]-1] enthält alle Werte mit b-tem Byte = 255 - i
                for(int i=0; i < 256; i++) {
                    msdRadix(data, c[i], c[i+1] - 1, b - 1);
                }
            }
        }
    }

    public static void insertionSort(int[] data, int l, int r) {
        // Füge Elemente nacheinander an richtige Stelle ein
        for(int i=l+1; i<=r; i++) {
            int h = data[i];
            // Solange noch kleinere Elemente links vorhanden sind, rücke diese auf
            int j;
            for(j = i; j > l && data[j - 1] < h; j--) {
                data[j] = data[j - 1];
            }
            data[j] = h;
        }
    }

    public static int[] sortByByte(int[] input, int l, int r, int b) {
        int[] interval = new int[r-l+1];    // zu sortierende Werte
        int[] freq = new int[257];  // Länge 257, um es direkt für MSDRadixsort zu verwenden

        // Werte kopieren
        for(int i=0; i < interval.length; i++) {
            interval[i] = input[i + l];
        }

        // Schlüssel zählen
        for(int i=0; i < interval.length; i++) {
            freq[(interval[i] >> (8*b)) & 0xFF]++;
        }

        // Positionen aufaddieren
        for(int i= freq.length - 2; i >= 0; i--) {
            freq[i] += freq[i+1];
        }

        // Elemente passend einsortieren
        for(int i = interval.length-1; i >= 0; i--) {
            int key = (interval[i] >> (8*b)) & 0xFF;   // Schlüssel berechnen
            input[l + freq[key] - 1] = interval[i];
            freq[key]--;
        }

        return freq;
    }
    public static boolean isSorted(int[] arr){
        for(int i = 0; i < arr.length-2; i++){
            if(arr[i+1] > arr[i]){
                return false;
            }
        }
        return true;
    }
}

