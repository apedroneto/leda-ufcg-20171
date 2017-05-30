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
        int current = leftIndex;

        //This implementation considers the rightmost index the starting pivot of the array.
        while (current != rightIndex) {
            if (array[current].compareTo(array[rightIndex]) < 0) {
                Util.swap(array, wall, current);
                wall++;
            }
            current++;
        }

        Util.swap(array, rightIndex, wall);

        //Once the algorithm is finished, wall is our new pivot.
        return wall;
    }

    @Override
    public void sort(T[] array, int leftIndex, int rightIndex) {
        if (array == null) {
            throw new UnsupportedOperationException("The array must not be null.");
        }
        if (leftIndex < rightIndex) {
            int pivot = partition(array, leftIndex, rightIndex);
            sort(array, leftIndex, pivot - 1);
            sort(array, pivot + 1, rightIndex);
        }
    }
}
