package sorting.linearSorting;

import sorting.AbstractSorting;
import util.Util;

import java.util.Arrays;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

    @Override
    public void sort(Integer[] array, int leftIndex, int rightIndex) {
        if (array != null && leftIndex < rightIndex) {
            Integer minVal = Util.getMinValue(array, leftIndex, rightIndex);
            //Making this subtraction for every element in the array we are making it contains only positive integers.
            for (int i = leftIndex; i <= rightIndex; i++) {
                array[i] -= minVal;
            }

            Integer maxVal = Util.getMaxValue(array, leftIndex, rightIndex);
            Integer[] counter = new Integer[maxVal + 1];
            Integer[] aux = new Integer[rightIndex - leftIndex + 1];

            Arrays.fill(counter, 0);

            for (int i = leftIndex; i <= rightIndex; i++) {
                counter[array[i]]++;
            }

            for (int i = 1; i <= maxVal; i++) {
                counter[i] += counter[i - 1];
            }

            for (int i = leftIndex; i <= rightIndex; i++) {
                aux[counter[array[i]] - 1] = array[i];
                counter[array[i]]--;
            }

            System.arraycopy(aux, 0, array, leftIndex, rightIndex + 1 - leftIndex);

            for (int i = leftIndex; i <= rightIndex; i++) {
                array[i] += minVal;
            }
        }
    }

}
