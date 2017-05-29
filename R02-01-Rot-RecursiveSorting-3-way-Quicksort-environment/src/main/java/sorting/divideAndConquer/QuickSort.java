package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	private int partition(T[] array, int leftIndex, int rightIndex) {
		int wall = leftIndex;
		int pivot = rightIndex;
		int current = rightIndex;

		while (current != pivot) {
			if (array[pivot].compareTo(array[current]) < 0) {
				Util.swap(array, wall, current);
				wall++;
			}
			current++;
		}

		return wall;
	}

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			int pivot = partition(array, leftIndex, rightIndex);
			sort(array, leftIndex, pivot);
			sort(array, pivot + 1, rightIndex);
		}
	}
}
