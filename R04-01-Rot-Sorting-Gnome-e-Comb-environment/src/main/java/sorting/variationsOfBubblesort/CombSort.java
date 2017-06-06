package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	private static final double FACTOR = 1.25;

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex < rightIndex && array != null) {
			int gap = (int) ((rightIndex - leftIndex) / FACTOR);
			boolean swapped = false;
			while(gap > 1 || swapped){
				swapped = false;
				for(int i = leftIndex; i + gap <= rightIndex; i++){
					if(array[i] .compareTo(array[i + gap]) > 0){
						Util.swap(array, i, i + gap);
						swapped = true;
					}
				}
				if(gap > 1) {
					gap = (int) (gap / FACTOR);
				}
			}
		}
	}
}
