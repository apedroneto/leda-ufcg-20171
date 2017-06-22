package adt.stack;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Rerisson Matos
 */
public class StackTest {
    @Test
    public void testPush() throws StackOverflowException {
        Stack<Integer> impl = getImpl(5);
        impl.push(10);
        assertEquals(impl.top(), integer(10));
        impl.push(2);
        assertEquals(impl.top(), integer(2));
    }

    @Test(expected = StackOverflowException.class)
    public void testOverflow() throws StackOverflowException {
        Stack<Integer> impl = getImpl(5);
        impl.push(10);
        impl.push(10);
        impl.push(10);
        impl.push(10);
        impl.push(10);
        impl.push(10);
    }

    @Test
    public void testPop() throws StackUnderflowException, StackOverflowException {
        Stack<Integer> impl = getImpl(5);
        impl.push(10);
        impl.push(2);
        impl.push(1);
        assertEquals(impl.pop(), integer(1));
        assertEquals(impl.pop(), integer(2));
        assertEquals(impl.pop(), integer(10));

        impl.push(3);
        assertEquals(impl.pop(), integer(3));
    }

    @Test(expected = StackUnderflowException.class)
    public void testUnderflow() throws StackUnderflowException {
        Stack<Integer> impl = getImpl(3);
        impl.pop();
    }

    @Test
    public void testTop() throws StackOverflowException {
        Stack<Integer> impl = getImpl(5);
        impl.push(10);
        assertEquals(impl.top(), integer(10));
        impl.push(2);
        assertEquals(impl.top(), integer(2));
        impl.push(4);
        assertEquals(impl.top(), integer(4));
    }

    @Test
    public void testIsEmpty() throws StackOverflowException, StackUnderflowException {
        Stack<Integer> impl = getImpl(0);
        assertTrue(impl.isEmpty());
        impl = getImpl(10);
        assertTrue(impl.isEmpty());
        impl.push(10);
        assertFalse(impl.isEmpty());
        impl.pop();
        assertTrue(impl.isEmpty());
        impl.push(10);
        assertFalse(impl.isEmpty());
    }

    @Test
    public void testIsFull() throws StackOverflowException {
        Stack<Integer> impl = getImpl(0);
        assertTrue(impl.isFull());
        impl = getImpl(3);
        assertFalse(impl.isFull());
        impl.push(10);
        impl.push(10);
        impl.push(10);
        assertTrue(impl.isFull());
    }

    private StackRecursiveDoubleLinkedListImpl<Integer> getImpl(int size) {
        return new StackRecursiveDoubleLinkedListImpl<>(size);
    }

    private Integer integer(int number) {
        return number;
    }
}