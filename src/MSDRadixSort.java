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

    public static int[] sortByByte(int[] array, int l, int r, int b){

        int [] frequenceArray = new int[257];

        // frequence von bytes Zahlen
        for(int i = l; i <= r; i++){
            frequenceArray[(array[i] >> (8 * b)) & 0xFF ]++;
        }

        // frequence anpassen
        for(int i = frequenceArray.length - 2; i >= 0; i--){
            frequenceArray[i] += frequenceArray[i+1];
        }

        int[] output = new int[r -l + 1];

        //zahlen sortieren
        for(int i = array.length-1; i >= 0; i--){
            output[frequenceArray[(array[i] >> (8 * b)) & 0xFF] -1 ] = array[i];
            frequenceArray[(array[i] >> (8 * b)) & 0xFF]--;
        }

        for(int i = 0; i < output.length; i ++){
            array[i] = output[i];
        }

        return frequenceArray;
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

