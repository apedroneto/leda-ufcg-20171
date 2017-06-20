package adt.linkedList;

public class DoubleLinkedListNode<T> extends SingleLinkedListNode<T> {
    protected DoubleLinkedListNode<T> previous;

    public DoubleLinkedListNode() {
    }

    public DoubleLinkedListNode(T data, DoubleLinkedListNode<T> next,
                                DoubleLinkedListNode<T> previous) {
        super(data, next);
        this.previous = previous;
    }

    @Override
    public void setNext(SingleLinkedListNode<T> next) {
        throw new UnsupportedOperationException("Only DoubleLinkedListNode allowed for the next of this class.");
    }

    public void setNext(DoubleLinkedListNode<T> next) {
        this.next = next;
    }

    @Override
    public DoubleLinkedListNode<T> getNext() {
        return (DoubleLinkedListNode<T>) next;
    }

    public DoubleLinkedListNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleLinkedListNode<T> previous) {
        this.previous = previous;
    }

}
