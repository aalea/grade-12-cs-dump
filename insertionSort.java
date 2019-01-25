import java.util.Arrays;

public class insertionSort {

	//public static void main (String[] args) {
		//int[] array = new int[] {8,5,20,43,1,9};

	public int[] insertionSort (int[] array) {

		for (int e = 0; e <= array.length - 1; e++) {
			
			if (e + 1 >= 6) { //check to avoid out of bounds array error
				break;
			}
			else if (array[e] > array[e + 1]) {

				swap(array, e, e + 1);
				System.out.println(e + Arrays.toString(array));
				
				for (int x = e; x >= 0; x--) { //switching back to organize sublist

					if (x - 1 < 0) { //check to avoid out of bounds array error
						break;
					}
					else if (array[x] < array[x - 1]) {

						swap(array, x, x - 1);
						System.out.println("x: " + x + Arrays.toString(array));
					}

				}

			}

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
