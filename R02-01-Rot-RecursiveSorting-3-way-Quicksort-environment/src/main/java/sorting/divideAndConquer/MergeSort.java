package sorting.divideAndConquer;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	private void merge(T[] array, int leftIndex, int rightIndex) {
		int size = rightIndex - leftIndex;
		int mid = size / 2;
	
		int i = 0;
		int j = mid + 1;
		int k = leftIndex;

		T[] arrayAux = Arrays.copyOfRange(array, leftIndex, rightIndex + 1);
		while (i <= mid && j <= size) {
			if (arrayAux[i].compareTo(arrayAux[j]) < 1) {
				array[k] = arrayAux[i];
				i++;
			} else {
				array[k] = arrayAux[j];
				j++;
			}
			k++;
		}
		while (i <= mid) {
			array[k] = arrayAux[i];
			i++;
			k++;
		}

		while (j <= size) {
			array[k] = arrayAux[j];
			j++;
			k++;
		}
	}

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			int mid = (leftIndex + rightIndex) / 2;
			sort(array, leftIndex, mid);
			sort(array, mid + 1, rightIndex);
			merge(array, leftIndex, rightIndex);
		}
	}
}
