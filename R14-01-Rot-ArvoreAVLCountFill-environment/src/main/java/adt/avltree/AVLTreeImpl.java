package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * Performs consistency validations within a AVL Tree instance
 *
 * @param <T>
 * @author Claudio Campelo
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	//Apenas para garantir que nao vai dar erro de compilacao no servidor, favor ignorar na correcao.
	@Override
	public void insert(T element) {
		super.insert(element);
	}

	//Apenas para garantir que nao vai dar erro de compilacao no servidor, favor ignorar na correcao.
	@Override
	public void remove(T element) {
		super.remove(element);
	}

	/**
	 * Inductive case of recursive insert implementation.
	 *
	 * @param element element to be inserted.
	 * @param node    tree where you will insert the node.
	 */
	@Override
	protected void insertInductiveCase(T element, BSTNode<T> node) {
		super.insertInductiveCase(element, node);
		rebalance(node);
	}

	@Override
	protected void removeLeaf(BSTNode<T> node) {
		super.removeLeaf(node);
		rebalanceUp(node);
	}

	@Override
	protected void removeHasOneChild(BSTNode<T> node) {
		super.removeHasOneChild(node);
		rebalanceUp(node);
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int result = 0;
		if (!node.isEmpty()) {
			result = height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());
		}

		return result;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if (Math.abs(balance) > 1) {
			BSTNode<T> aux = null;
			if (leftLeftCase(node)) {
				aux = solveLeftLeft(node);
			} else if (rightRightCase(node)) {
				aux = solveRightRight(node);
			} else if (leftRightCase(node)) {
				aux = solveLeftRight(node);
			} else if (rightLeftCase(node)) {
				aux = solveRightLeft(node);
			}
			if(root.equals(node)){
				root = aux;
			}
		}
	}

	protected BSTNode<T> solveRightLeft(BSTNode<T> node) {
		Util.rightRotation((BSTNode<T>) node.getRight());
		return Util.leftRotation(node);
	}

	protected BSTNode<T> solveLeftRight(BSTNode<T> node) {
		Util.leftRotation((BSTNode<T>) node.getLeft());
		return Util.rightRotation(node);
	}

	protected BSTNode<T> solveRightRight(BSTNode<T> node) {
		return Util.leftRotation(node);
	}

	protected BSTNode<T> solveLeftLeft(BSTNode<T> node) {
		return Util.rightRotation(node);
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while (parent != null) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
	}

	private boolean leftRightCase(BSTNode<T> node) {
		return calculateBalance(node) > 0 && calculateBalance((BSTNode<T>) node.getLeft()) < 0;
	}

	private boolean rightLeftCase(BSTNode<T> node) {
		return calculateBalance(node) < 0 && calculateBalance((BSTNode<T>) node.getRight()) > 0;
	}

	private boolean rightRightCase(BSTNode<T> node) {
		return calculateBalance(node) < 0 && calculateBalance((BSTNode<T>) node.getLeft()) <= 0;
	}

	private boolean leftLeftCase(BSTNode<T> node) {
		return calculateBalance(node) > 0 && calculateBalance((BSTNode<T>) node.getLeft()) >= 0;
	}
}