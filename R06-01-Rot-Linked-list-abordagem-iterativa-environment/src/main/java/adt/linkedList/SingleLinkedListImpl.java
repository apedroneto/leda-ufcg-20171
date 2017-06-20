package adt.linkedList;

import java.util.ArrayList;
import java.util.List;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<>();
	}

	@Override
	public boolean isEmpty() {
		return getHead().isNIL();
	}

	@Override
	public int size() {
		SingleLinkedListNode<T> aux = getHead();
		int size = 0;
		
		while(!aux.isNIL()){
			size++;
			aux = aux.getNext();
		}
		
		return size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> aux = getHead();
		T result = null;
		boolean found = false;
		while(!aux.isNIL() && !found){
			if(aux.getData().equals(element)){
				result = aux.getData();
				found = true;
			}
			aux = aux.getNext();
		}
		return result;
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> aux = getHead();
		while(!aux.isNIL()){
			aux = aux.getNext();
		}
		aux.setData(element);
		aux.setNext(new SingleLinkedListNode<T>());
	}

	@Override
	public void remove(T element) {
		if(head.getData().equals(element)){
			head = head.getNext();
		}else{
			SingleLinkedListNode<T> aux = getHead().getNext();
			SingleLinkedListNode<T> previous = getHead();
			while(!aux.isNIL() && !aux.getData().equals(element)){
				previous = aux;
				aux = aux.getNext();
			}
			if(!aux.isNIL()){
				previous.setNext(aux.getNext());
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		List<T> elements = new ArrayList<T>();
		SingleLinkedListNode<T> aux = getHead();
		while(!aux.isNIL()){
			elements.add(aux.getData());
			aux = aux.getNext();
		}
		
		return (T[])elements.toArray(new Object[elements.size()]);
	}

	@Override
	public T getFirst() {
		return getHead().getData();
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}
}
