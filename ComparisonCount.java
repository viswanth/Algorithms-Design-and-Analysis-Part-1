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
		/*
		int pivot = getPivot(left);
		
		
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
		*/
		int pivot = getPivot(right);
		int i = left;
		for(int j = left; j < right; j++){	
			if(unsortedArray[j] < pivot){
				swap(i,j);
				i++;
			}
		}
		swap(right,i);
		if(i > left)
			sort(left,i-1);
		if(right > i)
			sort(i+1, right);
			
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
		
		try {
			InputStream is = new FileInputStream("C:\\quicksortip.txt");
			
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
