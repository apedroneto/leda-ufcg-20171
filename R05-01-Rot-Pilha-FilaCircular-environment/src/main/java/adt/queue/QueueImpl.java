package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		if(isEmpty()){
			return null;
		}
		return array[0];
	}

	@Override
	public boolean isEmpty() {
		return tail == -1;
	}

	@Override
	public boolean isFull() {
		return tail + 1 == array.length;
	}

	private void shiftLeft() {
		for(int i = 0; i < tail; i++){
			array[i] = array[i + 1];
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(!isFull()){
			tail++;
			array[tail] = element;
		}else{
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T result = null;
		if(!isEmpty()){
			result = array[0];
			tail--;
			shiftLeft();
		}else{
			throw new QueueUnderflowException();
		}
		
		return result;
	}

}
