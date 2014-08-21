package DivideConquer;

public class MergeSort {

	private int[] unsortedArray;
	private int[] helperArray;
	
	private int arrayLength;
	
	public void sort(int[] array){
		this.unsortedArray = array;
		arrayLength = array.length;
		this.helperArray = new int[arrayLength];
		mergeSort(0, arrayLength-1);
		
		printArray(unsortedArray);
		//printArray(helperArray);
	}
	
	private void mergeSort(int low, int high) {
		// TODO Auto-generated method stub
		if(low < high){
			int middle = low + (high - low) / 2;
			mergeSort(low,middle);
			mergeSort(middle+1, high);
			merge(low,middle,high);
		}
	}

	private void merge(int low, int middle, int high) {
		// TODO Auto-generated method stub
		
		for(int i = low; i <= high; i++){
			helperArray[i] = unsortedArray[i];			
		}
		
		int i = low;
		int j = middle + 1;
		int k = low;
		
		while(i <= middle && j <= high){
			if(helperArray[i] <= helperArray[j]){
				unsortedArray[k] = helperArray[i];
				i++;
			} else{
				unsortedArray[k] = helperArray[j];
				j++;
			}
			k++;
		}
		
		while(i <= middle){
			unsortedArray[k] = helperArray[i];
			k++;
			i++;
		}
		
	}

	private void printArray(int[] array){
		for(int i = 0;i < array.length; i++){
			System.out.println(array[i]);
		}
	}
	
	public static void main(String[] args){
		int[] sample = new int[]{1,3,6,9,4,2,8,7,5,12};
		
		MergeSort ms = new MergeSort();
		
		ms.sort(sample);	
	
	}
	
}
