package DivideConquer;

public class QuickSort {

	private int[] unsortedArray;
	
	private int arrayLength;
	
	public void quickSort(int[] array){
		if(array == null || array.length == 0){
			return;
		}
		this.unsortedArray = array;
		arrayLength = array.length;
		
		sort(0,arrayLength - 1);
	}
	
	public void sort(int left, int right){
	
		int pivot = unsortedArray[left];
		
		int i = left + 1;
		
		for(int j = left + 1; j <= right; j++){
			if(unsortedArray[j] < pivot){
				swap(i,j);
				i++;
			}
		}
		swap(left,i-1);
		if(left < i-1)
			sort(left,i-1);
		if(right > i)
			sort(i,right);
		
	}

	private void swap(int i, int j) {
		// TODO Auto-generated method stub
		int temp = unsortedArray[i];
		unsortedArray[i] = unsortedArray[j];
		unsortedArray[j] = temp;
	}
	
	public static void main(String[] args){
		
		int[] sample = new int[]{1,3,6,9,12,4,2,8,7,5,0,14};
		
		QuickSort qs = new QuickSort();
		
		qs.quickSort(sample);
		
		for(int i = 0; i < sample.length; i++){
			System.out.println(sample[i]);
		}
	}
	
}
