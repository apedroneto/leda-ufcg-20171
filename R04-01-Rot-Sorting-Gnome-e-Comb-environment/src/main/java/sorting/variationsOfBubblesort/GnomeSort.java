package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex < rightIndex && array != null){
			int i = leftIndex + 1;
			while(i <= rightIndex){
				if(i == leftIndex){
					i++;
				}
				if(array[i].compareTo(array[i - 1]) < 0){
					Util.swap(array, i, i - 1);
					i--;
				}else {
					i++;
				}
			}
		}
	}
}
