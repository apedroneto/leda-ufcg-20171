package sorting.linearSorting;

import sorting.AbstractSorting;
import util.Util;

import java.util.Arrays;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 */
public class CountingSort extends AbstractSorting<Integer> {


    @Override
    public void sort(Integer[] array, int leftIndex, int rightIndex) {
        if (array != null && leftIndex < rightIndex) {
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
        }
    }

}
