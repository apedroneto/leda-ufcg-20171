package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import util.Util;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	private void cleanTree(BSTNode<T> node){
		if(!node.isEmpty()){
			cleanTree((BSTNode<T>) node.getLeft());
			cleanTree((BSTNode<T>) node.getRight());
			node.setData(null);
		}
	}

	@Override
	public T[] sort(T[] array) {
		T [] result = null;
		if(array != null) {
			cleanTree(root);
			for (T element : array) {
				insert(element);
			}
			result = order();
		}

		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T[] reverseOrder() {
		return (T[])makeArrayFromList(reverseOrder(this.root));
	}

	private List<T> reverseOrder(BSTNode<T> node) {
		List<T> result = new LinkedList<>();
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				result.add(node.getData());
			} else if (!node.isEmpty()) {
				result.addAll(reverseOrder((BSTNode<T>) node.getRight()));
				result.add(node.getData());
				result.addAll(reverseOrder((BSTNode<T>) node.getLeft()));
			}
		}

		return result;
	}

	@Override
	protected int compareElements(T element1, T element2) {
		return comparator.compare(element1, element2);
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
