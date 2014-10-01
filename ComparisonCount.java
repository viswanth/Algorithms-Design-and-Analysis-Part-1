/**
 *  The ComparisonCount class is used to count the number of comparisons that 
 *  occur during a quick sort.
 *  It helps us analyze how the number of comparisons change with the choixe of 
 *  the pivot. We have here tried the positions of first, last and the median as the
 *  pivot element. Runs show that median gets the least number of comparisons on average.
 *     
 *  @author Viswanth Chadalawada
 */


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

	#the array used to contain the elements throughout the process of sorting.
	private int[] unsortedArray;
	
	private int arrayLength, comparisonCount = 0;

	private String selection;
	
	
	/**
     	* Initializes an empty unsorted array with the array provided 
     	* and send it over to the sort method.
     	* @param the integer array to be sorted
     	*/
	public void quickSort(int[] array){
		if(array == null || array.length == 0){
			return;
		}
		this.unsortedArray = array;
		arrayLength = array.length;
		
		sort(0,arrayLength - 1);
	}
	
	/**
     	* Sorts the sub array it receives in increasing order.
     	* @param left the left index of the subarray to be sorted
     	* @param right the right index of the subarray to be sorted
     	*/
	public void sort(int left, int right){
		
		//increment the count of comparison in every call to the 
		//sort method.
		comparisonCount += right - left;

		//the index of the pivot in the array
		int pivotIndex = 0;
		int pivot = 0;
		
		//the switch statement decides the position of the pivot
		switch(selection){
		case "FIRST"://the pivot is the first element of the array
			pivotIndex = left;
			pivot = getPivot(pivotIndex);
			break;
		case "LAST"://the pivot is the last element of the array
			pivotIndex = right;
			pivot = getPivot(pivotIndex);
			break;
		case "MEDIAN"://the pivot is the median of the elements of the array
			int first = left, last = right, middle = 0;
			if((right - left) % 2 == 0){ 
				middle = first + (last - first)/2;
			}else{
				middle = first + (last - first - 1)/2;
			}
			
			//the method to calculate the index of the pivot
			pivotIndex = getMedian(first,middle,last);
			pivot = getPivot(pivotIndex);
			break;
		}
		
		//after calculating the index of the pivot, take it to the front and continue execution
		if(pivotIndex != left)
			swap(left,pivotIndex);
		
		int i = left + 1;
		
		//the logic for sorting
		for(int j = left + 1; j <= right; j++){
			if(unsortedArray[j] < pivot){
				swap(i,j);
				i++;
			}
		}
		//sort the pivot now to put it at its exact location
		//all elements lesser than the pivot are now before this position
		//and all elements greater lie after the pivot position
		swap(left,i-1);
		//sort the left half of the unsorted list
		if(left < i-1)
			sort(left,i-2);
		//sort the right half of the unsorted list
		if(right > i)
			sort(i,right);

	}


	/**
     	* return the median of the subarray
     	* @param first the index of the first element in the subarray
     	* @param middle the index of the middle element in the subarray
     	* @param last the index of the last element in the subarray
     	* @return index of the median element in the subarray
     	*/
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

	/**
     	* returns the pivot element given the index
     	* @param right the index of the pivot element
     	* @return pivot element
     	*/
	private int getPivot(int right) {
		// TODO Auto-generated method stub
		//return unsortedArray[arrayLength - 1];
		return unsortedArray[right];
	}

	/**
     	* swap two elements of the array
     	* @param i index of the first element
     	* @param j index of the second element
     	*/
	private void swap(int i, int j) {
		// TODO Auto-generated method stub
		int temp = unsortedArray[i];
		unsortedArray[i] = unsortedArray[j];
		unsortedArray[j] = temp;
	}
	
	/**
     	* get the text file input of integers in the form of an integer array
     	* @param filename path of the input text file
     	* @return integer array of the inout file
     	*/
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
