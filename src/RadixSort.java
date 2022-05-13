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
        int[] hilfsArray = new int[r - l + 1];
        for (int i = l; i <= r; i++){
           hilfsArray[i - l] = (array[i] >> (8 * b)) & 0xFF;
        }
        countingSort(hilfsArray);
        for (int i = l; i <= r; i++){
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
            }
        }
        System.out.println(Arrays.toString(hilfsArray));


    }

    public static int getMin(int[] data){
        int min = data[0];
        for(int i = 1; i < data.length; i++){
            if(data[i] < min){
                min = data[i];
            }
        }
        return min;
    }
    public static int getMax(int[] data){
        int max = data[0];
        for(int i = 1; i < data.length; i++){
            if(data[i] > max){
                max = data[i];
            }
        }
        return max;
    }

    public static int[] count (int[] input, int min, int max){
        int [] output = new int[max - min + 1];
        for (int i = 0; i < output.length; i++) {
            output[i] = 0;
        }
        for(int i = 0; i< input.length; i++){
            if(input[i] >= min && input[i] <= max){
                output[input[i] - min] += 1;
            }

        }
        return output;
    }

    public static int[] countingSort(int[] input){
        int min = getMin(input);
        int[] helper = count(input, min, getMax((input)));
        for (int i = helper.length-2; i >= 0; i--){
            helper[i] += helper[i+1];
        }
        int[] output = new int[input.length];
        for(int i = 0; i < input.length; i++){
            output[helper[input[i] - min] -1] = input[i];
            helper[input[i] - min] -= 1;

        }

        for(int i = 0; i < input.length; i++){
            input[i] = output[i];
        }


        return helper;
    }
}
