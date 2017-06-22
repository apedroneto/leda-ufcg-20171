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
		if (element != null) {
			if (this.isEmpty()) {
				this.data = element;
				this.next = new RecursiveDoubleLinkedListImpl<>();
				if (this.previous == null) {
					this.previous = new RecursiveDoubleLinkedListImpl<>();
				}
			} else {
				this.next.insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			if (!this.isEmpty()) {
				if (this.data.equals(element)) {
					if (this.next.isEmpty() && this.previous.isEmpty()) {
						//Último elemento da lista.
						this.data = null;
						this.next = this.previous = null;
					} else {
						//Caso típico.
						data = this.next.data;
						this.next = this.next.next;
						if (this.next != null) {
							((RecursiveDoubleLinkedListImpl<T>) this.next).previous = this;
						}
					}
				} else {
					this.next.remove(element);
				}
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			if (this.isEmpty()) {
				this.data = element;
				this.next = new RecursiveDoubleLinkedListImpl<>();
				this.previous = new RecursiveDoubleLinkedListImpl<>();
			} else {
				RecursiveDoubleLinkedListImpl<T> newNode = new RecursiveDoubleLinkedListImpl<>();
				newNode.data = this.data;
				newNode.next = this.next;
				newNode.previous = this;
				((RecursiveDoubleLinkedListImpl<T>) newNode.next).previous = newNode;
				this.data = element;
				this.next = newNode;
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!this.isEmpty()) {
			this.data = next.data;
			if(!this.next.isEmpty()) {
				((RecursiveDoubleLinkedListImpl<T>) this.next.next).previous = this;
				this.next = this.next.next;
			}
		}
	}

	@Override
	public void removeLast() {
		if(this.next.isEmpty()){
			this.data = null;
		}else{
			((RecursiveDoubleLinkedListImpl<T>)this.next).removeLast();
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
