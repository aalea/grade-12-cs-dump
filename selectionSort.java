import java.util.Arrays;

public class selectionSort {
	
	//public static void main (String[] args) {
		//int[] array = new int[] {8,5,20,43,1,9};
		
	public int[] selectionSort (int[] array) {
		
		int temp = 0;
		boolean swapped;
		
		for (int y = 0; y < array.length - 1; y++) {
		
			//1. Scan list for lowest value
			for (int x = y; x <= array.length - 1; x++) {
				
				if (array[temp] > array[x]) {
					
					temp = x;
					
				}
				
			}
			//2. Swap
			System.out.println("y: " + y + " temp: " + temp);
			array = swap(array, y, temp);
			System.out.println(Arrays.toString(array));
			
			//3. Reset temp
			temp = y + 2;
			
		}
		
		return array;
		//System.out.println(Arrays.toString(array));
		
	}
	
	public static int[] swap (int[] array, int higher, int lower) {
		
		int temp = array[lower];
		
		array[lower] = array[higher];
		array[higher] = temp;
		
		System.out.println("swap");
		
		return array;
		
	}

}
