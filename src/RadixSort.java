import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RadixSort {
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
        radixSort(arr);
        Instant end = Instant.now();

        double time = Duration.between(start, end).toMillis();
        assert isSorted(arr);

        System.out.println("sortiert " + Arrays.toString(arr));

        System.out.println("Time: " + time);



    }

    public static void radixSort(int[] data){
        for(int i = 0; i < 4; i++){
            sortByByte(data, 0, data.length-1, i);
        }

    }

    public static void sortByByte(int[] array, int l, int r, int b){

        int [] frequenceArray = new int[256];

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
