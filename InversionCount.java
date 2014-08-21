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

public class InversionCount {

	public long sortAndCount(int[] arrayA){
		
		int arrayLength = arrayA.length;
		
		int[] arrayB = new int[Math.round(arrayLength/2)];
		int[] arrayC = new int[arrayLength - Math.round(arrayLength/2)];
		
		if(arrayLength == 1)
			return 0;
		else{
			for(int i = 0;i < Math.round(arrayLength/2); i++){
				arrayB[i] = arrayA[i];
			}
			for(int i = 0; i < arrayLength - Math.round(arrayLength)/2; i++){
				arrayC[i] = arrayA[i + Math.round(arrayLength)/2];
			}
			long x = sortAndCount(arrayB);
			long y = sortAndCount(arrayC);
			
			int[] arrayD = new int[arrayLength];
			long z = mergeAndCountSplitInv(arrayB, arrayC, arrayD);
			
			for(int i = 0; i < arrayLength; i++){
				arrayA[i] = arrayD[i];
			}
			
			return x + y + z;
		}
	}

	private int mergeAndCountSplitInv(int[] arrayB2, int[] arrayC2, int[] arrayD) {
		// TODO Auto-generated method stub
		int b = arrayB2.length;
		int c = arrayC2.length;
		int splitCount = 0;
		
		int i = 0, j = 0, k = 0;
		
			while(i < b && j < c){
				if(arrayB2[i] < arrayC2[j]){
					arrayD[k] = arrayB2[i];
					i++;
				}else{
					arrayD[k] = arrayC2[j];
					j++;
					splitCount += b - i;
				}	
				k++;
			}
			
		if(i == b){
			for(int m = j; m < c; m++){
				arrayD[k++] = arrayC2[m];	
			}
		}else{
			for(int m = i; m < b; m++){
				arrayD[k++] = arrayB2[m];
			}
		}
		
		return splitCount;
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
		
		InversionCount ic = new InversionCount();
		
		try {
			InputStream is = new FileInputStream("C:\\IntegerArray.txt");
			
			int[] sample = ic.getArrayFromFile(new BufferedInputStream(is));
			
			long inversions = ic.sortAndCount(sample);
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
