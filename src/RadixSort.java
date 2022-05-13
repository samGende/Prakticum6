import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int a =1661914940 ;
        int[] arr = new int[]{1, 0, 4, 5,6 };
        arr[0] = a;
        radixSort(arr);
        System.out.println(Arrays.toString(arr));


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


}
