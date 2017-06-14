package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	@SuppressWarnings("unchecked")
	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = 0;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(elements < array.length){
			tail = (tail + 1) % array.length;
			array[tail] = element;
			elements++;
		}else{
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T element = null;
		if(elements > 0){
			element = array[head];
			head = (head + 1) % array.length;
			elements--;
		}else{
			throw new QueueUnderflowException();
		}
		
		return element;
	}

	@Override
	public T head() {
		if(isEmpty()){
			return null;
		}
		return array[head];
	}

	@Override
	public boolean isEmpty() {
		return elements == 0;
	}

	@Override
	public boolean isFull() {
		return elements == array.length;
	}

}
