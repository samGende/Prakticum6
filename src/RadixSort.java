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
                return;
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
        radixSort(arr);
        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }

    public static void MSDRadixSort(int[] arr, int l , int r, int b){

        sortByByte(arr, l , r, 4);

    }

    public static void radixSort(int[] data){
        for(int i = 0; i < 4; i++){
            sortByByte(data, 0, data.length-1, i);
        }

    }

    public static void sortByByte(int[] array, int l, int r, int b){
        int[] hilfsArray = new int[r - l + 1];
        for (int i = l; i <= r; i++){
           hilfsArray[i - l] = (array[i] >> (8 * b)) & 0xFF ;
        }
        countingSort(hilfsArray, array);
      /*  for (int i = l; i <= r; i++){
            int temp = array[i];
            array[i] = 0;
            for(int j = 0; j < 4; j++){
                if (j == b) {
                    array[i] += (int) (hilfsArray[i - l] * Math.pow(256, b));
                }
                else{
                    int temp2 = (temp >> (8 * j)) & 0xFF;
                    array[i] += (int) (temp2 * Math.pow(256, j));
                }
           } }*/
      System.out.println(Arrays.toString(hilfsArray));


    }


    public static int[] count (int[] input){
        int [] output = new int[256];

        for(int i = 0; i< input.length; i++){
                output[input[i]] += 1;


        }
        return output;
    }

    public static int[] countingSort(int[] input, int[] zahlArray){
        int[] helper = count(input);
        for (int i = helper.length-2; i >= 0; i--){
            helper[i] += helper[i+1];
        }
        int[] output = new int[input.length];
        for(int i = 0; i < input.length; i++){
            //frequence array richtig stellen
            output[helper[input[i]] -1] = zahlArray[i];
            helper[input[i]] -= 1;

        }

        for(int i = 0; i < input.length; i++){
            input [i] = output[i];
        }


        return input;
    }
}
