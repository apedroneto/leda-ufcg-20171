package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
        DoubleLinkedList<T> {

    protected DoubleLinkedListNode<T> last;

    public DoubleLinkedListImpl() {
        this.setLast(new DoubleLinkedListNode<>());
        getLast().setNext(new DoubleLinkedListNode<>());
        setHead(getLast());
    }

    @Override
    public void insert(T element) {
        DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(element, getLast().getNext(), getLast());
        getLast().setNext(newNode);
        setLast(newNode);
        if (getHead().isNIL()) {
            setHead(newNode);
        }
    }

    @Override
    public T getFirst() {
        return getHead().getData();
    }

    @Override
    public T getLastElement() {
        return getLast().getData();
    }

    @Override
    public void insertFirst(T element) {
        DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(element, getHead(), new DoubleLinkedListNode<>());
        setHead(newNode);
        if (getLast().isNIL()) {
            setLast(newNode);
            newNode.setNext(new DoubleLinkedListNode<>());
        }
    }

    @Override
    public void removeFirst() {
        if (!getHead().isNIL()) {
            setHead(getHead().getNext());
            getHead().setPrevious(new DoubleLinkedListNode<>());
        }
        //Caso não haja mais nenhum elemento na lista, devemos atualizar a cauda também.
        if (getHead().getNext().isNIL()) {
            setLast(getHead());
        }
    }

    @Override
    public void removeLast() {
        if (!getLast().isNIL()) {
            setLast(getLast().getPrevious());
            getLast().setNext(new DoubleLinkedListNode<>());
        }
    }

    public DoubleLinkedListNode<T> getLast() {
        return last;
    }

    public void setLast(DoubleLinkedListNode<T> last) {
        this.last = last;
    }

    @Override
    public DoubleLinkedListNode<T> getHead() {
        return (DoubleLinkedListNode<T>) super.getHead();
    }
}
