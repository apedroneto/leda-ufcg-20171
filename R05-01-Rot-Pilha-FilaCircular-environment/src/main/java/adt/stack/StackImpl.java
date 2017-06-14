package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		return array[top];
	}

	@Override
	public boolean isEmpty() {
		return top == -1;
	}

	@Override
	public boolean isFull() {
		return top - 1 == array.length;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (top + 1 == array.length) {
			throw new StackOverflowException();
		}
		top++;
		array[top] = element;
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (top - 1 < -1) {
			throw new StackUnderflowException();
		}
		T element = array[top];
		top--;

		return element;
	}

}
