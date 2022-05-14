import java.util.Arrays;

public class MSDRadixSort {
    public static void main(String[] args) {
        int a =1661914940 ;
        int[] arr = new int[]{1, 0, 4, 5,6 };
        arr[0] = a;
        msdRadix(arr,0,arr.length-1,3);
        System.out.println(Arrays.toString(arr));


    }

    public static void msdRadix(int[] data, int l, int r, int b){
    		if(r<=l){
    			return;
        	}else if(r-l+1<=32){
        		for(int i=l; i<=r; i++){
        			int tosort=data[i];
        			int j=i;
        			while(j>l && tosort<data[j-1]){
        				data[j]=data[j-1];
        				j--;
        			}
        			data[j]=tosort;
        		}
        		return;
        	}else{
        		sortByByte(data, l, r, b);
        		int[] subintervall=new int[257];
        		subintervall[0]=l;
        		int i=1;
        		for(int j=subintervall[i-1];j<=r;j++){
        			int temp1=(data[j] >> (8 * b)) & 0xFF;
        			int temp2=(data[j+1] >> (8 * b)) & 0xFF;
        			if(temp1==temp2){
        				subintervall[i]++;
        			}else{
        				subintervall[i]++;
        				i++;
        			}
        		}
        		for(int t=0; t<256;t++){
        			msdRadix(data,subintervall[t],subintervall[t+1],b-1);
        		}
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


    public static int[] count (int[] input){
        int [] output = new int[256];
        for (int i = 0; i < output.length; i++) {
            output[i] = 0;
        }
        for(int i = 0; i< input.length; i++){
            
                output[input[i]] += 1;
            

        }
        return output;
    }

    public static int[] countingSort(int[] input){
        int[] helper = count(input);
        for (int i = helper.length-2; i >= 0; i--){
            helper[i] += helper[i+1];
        }
        int[] output = new int[input.length];
        for(int i = 0; i < input.length; i++){
            output[helper[input[i]] -1] = input[i];
            helper[input[i]] -= 1;

        }

        for(int i = 0; i < input.length; i++){
            input[i] = output[i];
        }


        return helper;
    }
}
