package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insert(T element) {
		if (this.isEmpty()) {
			this.data = element;
			this.next = new RecursiveDoubleLinkedListImpl<>();
			if (this.previous == null) {
				this.previous = new RecursiveDoubleLinkedListImpl<>();
			}
		} else {
			((RecursiveDoubleLinkedListImpl<T>) this.next).insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if (!this.isEmpty()) {
			if (this.data.equals(element)) {
				if (this.next.isEmpty() && this.previous.isEmpty()) {
					this.data = null;
					this.next = this.previous = null;
				} else {
					data = this.next.data;
					this.next = this.next.next;
					if (this.next != null) {
						((RecursiveDoubleLinkedListImpl<T>) next).previous = this;
					}
				}
			} else {
				((RecursiveDoubleLinkedListImpl<T>) next).insert(element);
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void removeFirst() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void removeLast() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
