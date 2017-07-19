package adt.heap;


import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.assertArrayEquals;

public class HeapTest {

    @Test
    public void testBuildHeap(){
        Integer[] nums = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        HeapImpl<Integer> heap = getImpl(Comparator.comparingInt(i -> i));
        heap.buildHeap(nums);
        System.out.println(Arrays.toString(heap.getHeap()));
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8}, heap.getHeap());

        heap = getImpl((i1, i2) -> i2 - i1);
        heap.buildHeap(nums);
        assertArrayEquals(new Integer[]{8, 7, 6, 5, 4, 3, 2, 1}, heap.getHeap());
    }

    private HeapImpl<Integer> getImpl(Comparator<Integer> comparator){
        return new HeapImpl<>(comparator);
    }
}
