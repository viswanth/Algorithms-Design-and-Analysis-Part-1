package DivideConquer;

import java.util.Random;

public class RandomSelection {

	private int[] arrayOfNumbers;
	
	private int arrayLength;
	
	private int result;
	
	private int orderEle;
	
	public void init(int[] array, int order){
		
		if(array == null || array.length == 0)
			return;
		
		this.arrayOfNumbers = array;
		arrayLength = array.length;
		this.orderEle = order;
		this.result = rSelect(0,arrayLength-1, order);
		
	}

	private int rSelect(int left, int right, int order) {
		// TODO Auto-generated method stub
		int length = right - left + 1;
		if(length == 1){
			return arrayOfNumbers[left];
		}
		
		int pivotIndex = left + choosePivot(length);
		int pivot = arrayOfNumbers[pivotIndex];
		if(pivotIndex != left)
			swap(left,pivotIndex);
		int i = left + 1;
		for(int j = left + 1; j <= right; j++){
			
			if(arrayOfNumbers[j] < pivot){
				swap(i,j);
				i++;
			}
		}
		swap(left,i-1);
		if((i - left) == order){
			return pivot;
		}else if((i - left) > order){
			return rSelect(left, i - 2, order);
		}else
			return rSelect(i, right, order - i + left);
		
	}

	private void swap(int i, int j) {
		// TODO Auto-generated method stub
		int temp = arrayOfNumbers[i];
		arrayOfNumbers[i] = arrayOfNumbers[j];
		arrayOfNumbers[j] = temp;
	}

	private int choosePivot(int length) {
		// TODO Auto-generated method stub
		Random r = new Random();
		return r.nextInt(length);		
	}
	
	public static void main(String[] args){
		int[] sample = new int[]{-1,1,20,50,33,3,6,9,12,4,2,8,7,5,0,14};
		RandomSelection rs= new RandomSelection();
		int order = 16;
		rs.init(sample, order);
		System.out.println(rs.result);
	}
	
}
