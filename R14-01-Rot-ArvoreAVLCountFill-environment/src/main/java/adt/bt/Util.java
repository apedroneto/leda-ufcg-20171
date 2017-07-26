package adt.bt;

import adt.bst.BSTNode;

public class Util {

	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();
		node.setRight(pivot.getLeft());
		pivot.setLeft(node);
		pivot.setParent(node.getParent());
		updateParentChild(node, pivot);
		return pivot;
	}

	private static <T extends Comparable<T>> void updateParentChild(BSTNode<T> node, BSTNode<T> pivot) {
		if(node.getParent().getLeft().equals(node)){
			node.getParent().setLeft(pivot);
		}else{
			node.getParent().setRight(pivot);
		}
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();
		node.setLeft(pivot.getRight());
		pivot.setRight(node);
		pivot.setParent(node.getParent());
		updateParentChild(node, pivot);
		return pivot;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}