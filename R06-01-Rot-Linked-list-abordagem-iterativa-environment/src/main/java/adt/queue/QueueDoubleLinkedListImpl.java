package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

    protected DoubleLinkedList<T> list;
    protected int size;

    public QueueDoubleLinkedListImpl(int size) {
        this.size = size;
        this.list = new DoubleLinkedListImpl<>();
    }

    @Override
    public void enqueue(T element) throws QueueOverflowException {
        list.insert(element);
    }

    @Override
    public T dequeue() throws QueueUnderflowException {
        if (list.size() == 0) {
            throw new QueueUnderflowException();
        }
        T result = list.getFirst();
        list.removeFirst();
        return result;
    }

    @Override
    public T head() {
        return list.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean isFull() {
        return list.size() == this.size;
    }

}
