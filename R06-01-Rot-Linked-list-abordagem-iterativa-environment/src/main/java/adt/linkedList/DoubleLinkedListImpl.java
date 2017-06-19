package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	
	public DoubleLinkedListImpl() {
		this.last = new DoubleLinkedListNode<T>();
		setHead(last);
	}

	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, getHead(), new DoubleLinkedListNode<T>());
		setHead(newNode);
		if(last.isNIL()){
			setLast(newNode);
			newNode.next = new DoubleLinkedListNode<T>();
		}
	}

	@Override
	public void removeFirst() {
		if(!getHead().isNIL()){
			setHead(getHead().next);
			getHead().previous = new DoubleLinkedListNode<T>();
		}
		//Caso não haja mais nenhum elemento na lista, devemos atualizar a cauda também.
		if(getHead().next.isNIL()){
			setLast(getHead());
		}
	}

	@Override
	public void removeLast() {
		if(!getLast().isNIL()){
			setLast(getLast().previous);
			getLast().next = new DoubleLinkedListNode<T>();
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

	@Override
	public DoubleLinkedListNode<T> getHead(){
		return (DoubleLinkedListNode<T>)super.getHead();
	}
}
