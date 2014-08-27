package DivideConquer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ComparisonCount {

	private int[] unsortedArray;
	
	private int arrayLength, comparisonCount = 0;

	private String selection;
	
	public void quickSort(int[] array){
		if(array == null || array.length == 0){
			return;
		}
		this.unsortedArray = array;
		arrayLength = array.length;
		
		sort(0,arrayLength - 1);
	}
	
	public void sort(int left, int right){
	
		comparisonCount += right - left;
		
		int pivotIndex = 0;
		int pivot = 0;
		
		switch(selection){
		case "FIRST":
			pivotIndex = left;
			pivot = getPivot(pivotIndex);
			break;
		case "LAST":
			pivotIndex = right;
			pivot = getPivot(pivotIndex);
			break;
		case "MEDIAN":
			int first = left, last = right, middle = 0;
			if((right - left) % 2 == 0){ 
				middle = first + (last - first)/2;
			}else{
				middle = first + (last - first - 1)/2;
			}
			
			pivotIndex = getMedian(first,middle,last);
			pivot = getPivot(pivotIndex);
			break;
		}
		
		if(pivotIndex != left)
			swap(left,pivotIndex);
		
		int i = left + 1;
		
		for(int j = left + 1; j <= right; j++){
			if(unsortedArray[j] < pivot){
				swap(i,j);
				i++;
			}
		}
		swap(left,i-1);
		if(left < i-1)
			sort(left,i-2);
		if(right > i)
			sort(i,right);
		
		
	}

	private int getMedian(int first, int middle, int last) {
		// TODO Auto-generated method stub
		
		QuickSort qs = new QuickSort();
		int[] three = new int[]{unsortedArray[first], unsortedArray[middle],
								unsortedArray[last]};
		
		qs.quickSort(three);
		if(three[1] == unsortedArray[first])
			return first;
		else if(three[1] == unsortedArray[last])
			return last;
		else
			return middle;
	}

	private int getPivot(int right) {
		// TODO Auto-generated method stub
		//return unsortedArray[arrayLength - 1];
		return unsortedArray[right];
	}

	private void swap(int i, int j) {
		// TODO Auto-generated method stub
		int temp = unsortedArray[i];
		unsortedArray[i] = unsortedArray[j];
		unsortedArray[j] = temp;
	}
	
	private int[] getArrayFromFile(InputStream fileName)
	{
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(fileName));
			List<Integer> l=new ArrayList<Integer>();
			String line;
			while ((line=br.readLine())!=null)
			{
				l.add(Integer.parseInt(line));
			}
			int[] ar = new int[l.size()];
			for (int i=0;i<ar.length;i++)
				ar[i]=l.get(i);
			return ar;
		}
		catch (IOException e)
		{
			return null;
		}
	}
	
	public static void main(String[] args){
		
		ComparisonCount cc = new ComparisonCount();
		
		//Add FIRST, LAST or MEDIAN here to decide the kind of pivot you would 
		//need to partition the array of numbers.
		//FIRST always chooses the first element of the subarray as the pivot
		//LAST always chooses the last element of the subarray as the pivot
		//MEDIAN chooses the median of the elements of the subarray as the pivot
		
		cc.selection = "MEDIAN";
		
		try {
			//Open an input stream to the input text file of integers.
			InputStream is = new FileInputStream("C:\\quicksortip.txt");
			
			//Call the getArrayFromFile function to get the array
			int[] sample = cc.getArrayFromFile(new BufferedInputStream(is));
			//int[] sample = new int[]{1,3,6,9,12,4,2,8,7,5,0,14};
			cc.quickSort(sample);
			
			long inversions = cc.comparisonCount;
			
			is.close();
			System.out.println(inversions);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	
	
}
