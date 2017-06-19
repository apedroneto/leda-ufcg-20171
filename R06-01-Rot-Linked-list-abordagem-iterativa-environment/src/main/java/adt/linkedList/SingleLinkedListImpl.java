package adt.linkedList;

import java.util.ArrayList;
import java.util.List;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
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
		}
		
		return size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> aux = getHead();
		T result = null;
		boolean found = false;
		while(!aux.isNIL() && !found){
			if(aux.data.equals(element)){
				result = aux.data;
				found = true;
			}
			aux = aux.next;
		}
		return result;
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> aux = getHead();
		while(!aux.isNIL()){
			aux = aux.next;
		}
		aux.data = element;
		aux.next = new SingleLinkedListNode<T>();
	}

	@Override
	public void remove(T element) {
		if(head.data.equals(element)){
			head = head.next;
		}else{
			SingleLinkedListNode<T> aux = getHead().next;
			SingleLinkedListNode<T> previous = getHead();
			while(!aux.isNIL() && !aux.data.equals(element)){
				previous = aux;
				aux = aux.next;
			}
			if(!aux.isNIL()){
				previous.next = aux.next;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		List<T> elements = new ArrayList<T>();
		SingleLinkedListNode<T> aux = getHead();
		while(!aux.isNIL()){
			elements.add(aux.data);
			aux = aux.next;
		}
		
		return (T[])elements.toArray(new Object[elements.size()]);
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}
}
