package adt.linkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return this.isNil();
	}

	@Override
	public int size() {
		int result;
		if(this.isNil()){
			result = 0;
		}else{
			result = 1 + next.size();
		}
		
		return result;
	}

	@Override
	public T search(T element) {
		T result = null;
		if(!this.isNil()){
			if(this.data.equals(element)){
				result = data;
			}else{
				result = next.search(element);
			}
		}
		
		return result;
	}

	@Override
	public void insert(T element) {
		if(this.isNil()){
			this.data = element;
			this.next = new RecursiveSingleLinkedListImpl<>();
		}else{
			this.next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if(!this.isEmpty()){
			if(this.data.equals(element)){
				this.data = this.next.data;
				this.next = this.next.next;
			}else{
				this.next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] result = (T[])new Object[0];
		if(!this.isEmpty()){
			List<T> list = new ArrayList<T>();
			list.add(this.data);
			list.addAll(Arrays.asList(this.next.toArray()));
			
			T[] newArray = (T[])new Object[list.size()];
			result = list.toArray(newArray);
		}
		
		return result;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}
	
	private boolean isNil(){
		return this.data == null;
	}

}
