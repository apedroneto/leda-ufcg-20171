package adt.bst;

import java.util.LinkedList;
import java.util.List;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(root);
	}

	protected int height(BSTNode<T> node) {
		if (node.isEmpty()) {
			return -1;
		} else {
			return 1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight()));
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(element, this.root);
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		BSTNode<T> result = new BSTNode<>();
		if(element != null) {
			if (!node.isEmpty()) {
				if (node.getData().equals(element)) {
					result = node;
				} else if (element.compareTo(node.getData()) < 0) {
					result = search(element, (BSTNode<T>) node.getLeft());
				} else if (element.compareTo(node.getData()) > 0) {
					result = search(element, (BSTNode<T>) node.getRight());
				}
			}
		}

		return result;
	}

	@Override
	public void insert(T element) {
		this.insert(element, this.root, null);
	}

	@SuppressWarnings("unchecked")
	private void insert(T element, BSTNode<T> node, BSTNode<T> parent) {
		if(element != null) {
			insertNotNull(element, node, parent);
		}
	}

	private void insertNotNull(T element, BSTNode<T> node, BSTNode<T> parent) {
		if (node.isEmpty()) {
            insertBase(element, node, parent);
        } else {
            insertInductiveCase(element, node);
        }
	}

	protected void insertInductiveCase(T element, BSTNode<T> node) {
		if (node.getData().compareTo(element) > 0) {
            insert(element, (BSTNode<T>) node.getLeft(), node);
        } else if (node.getData().compareTo(element) < 0) {
            insert(element, (BSTNode<T>) node.getRight(), node);
        }
	}

	private void insertBase(T element, BSTNode<T> node, BSTNode<T> parent) {
		node.setParent(parent);
		node.setData(element);
		node.setLeft(getNilNode());
		node.setRight(getNilNode());
	}

	@Override
	public BSTNode<T> maximum() {
		BSTNode<T> result = null;
		if (!this.isEmpty()) {
			result = maximum(this.root);
		}
		return result;
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> result;
		if (node.getRight().isEmpty()) {
			result = node;
		} else {
			result = maximum((BSTNode<T>) node.getRight());
		}

		return result;
	}

	@Override
	public BSTNode<T> minimum() {
		BSTNode<T> result = null;
		if (!this.isEmpty()) {
			result = minimum(this.root);
		}
		return result;
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> result;
		if (node.getLeft().isEmpty()) {
			result = node;
		} else {
			result = minimum((BSTNode<T>) node.getLeft());
		}

		return result;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> elementNode = search(element);
		BSTNode<T> result = null;
		if (!elementNode.isEmpty()) {
			if(!elementNode.getRight().isEmpty()){
				result = minimum((BSTNode<T>) elementNode.getRight());
			}else{
				BSTNode<T> parent = (BSTNode<T>) elementNode.getParent();
				BSTNode<T> actual = elementNode;
				while(parent != null && actual.equals(parent.getRight())){
					actual = parent;
					parent = (BSTNode<T>) parent.getParent();
				}

				result = parent;
			}
		}

		return result;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> result = null;
		if(element != null) {
			BSTNode<T> elementNode = search(element);
			if (!elementNode.isEmpty()) {
				if (!elementNode.getLeft().isEmpty()) {
					result = maximum((BSTNode<T>) elementNode.getLeft());
				} else {
					BSTNode<T> parent = (BSTNode<T>) elementNode.getParent();
					BSTNode<T> actual = elementNode;
					while (parent != null && actual.equals(parent.getLeft())) {
						actual = parent;
						parent = (BSTNode<T>) parent.getParent();
					}

					result = parent;
				}
			}
		}

		return result;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		if(!node.isEmpty()){
			remove(node);
		}
	}

	private void remove(BSTNode<T> node){
		if(!node.isEmpty()){
			if(node.isLeaf()){
				removeLeaf(node);
			}else if(hasOneChild(node)){
				removeHasOneChild(node);
			}else{
				removeAndGetSuccessor(node);
			}
		}
	}

	private void removeAndGetSuccessor(BSTNode<T> node) {
		BSTNode<T> sucessor = sucessor(node.getData());
		node.setData(sucessor.getData());
		remove(sucessor);
	}

	protected boolean hasOneChild(BSTNode<T> node) {
		return (node.getLeft().isEmpty() && !node.getRight().isEmpty()) || (!node.getLeft().isEmpty() && node.getRight().isEmpty());
	}

	protected void removeLeaf(BSTNode<T> node) {
		node.setData(null);
	}

	protected void removeHasOneChild(BSTNode<T> node) {
		if(node != root){
            if(node.getParent().getLeft().equals(node)){
                if(!node.getLeft().isEmpty()) {
                    node.getParent().setLeft(node.getLeft());
                    node.getLeft().setParent(node.getParent());
                }else{
                    node.getParent().setLeft(node.getRight());
                    node.getRight().setParent(node.getParent());
                }
            }else{
                if(!node.getLeft().isEmpty()){
                    node.getParent().setRight(node.getLeft());
                    node.getLeft().setParent(node.getParent());
                }else{
                    node.getParent().setRight(node.getRight());
                    node.getRight().setParent(node.getParent());
                }
            }
        }else {
            if(node.getRight().isEmpty()){
                root = (BSTNode<T>) node.getLeft();
            }else {
                root = (BSTNode<T>) node.getRight();
            }
        }
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] preOrder() {
		return (T[]) makeArrayFromList(preOrder(root));
	}

	private List<T> preOrder(BSTNode<T> node) {
		List<T> result = new LinkedList<>();
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				result.add(node.getData());
			} else if (!node.isEmpty()) {
				result.add(node.getData());
				result.addAll(preOrder((BSTNode<T>) node.getLeft()));
				result.addAll(preOrder((BSTNode<T>) node.getRight()));
			}
		}

		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T[] order() {
		Comparable[] result1 = makeArrayFromList(order(root));
		return (T[]) result1;
	}

	private List<T> order(BSTNode<T> node) {
		List<T> result = new LinkedList<>();
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				result.add(node.getData());
			} else if (!node.isEmpty()) {
				result.addAll(order((BSTNode<T>) node.getLeft()));
				result.add(node.getData());
				result.addAll(order((BSTNode<T>) node.getRight()));
			}
		}

		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T[] postOrder() {
		return (T[]) makeArrayFromList(postOrder(root));
	}

	private List<T> postOrder(BSTNode<T> node) {
		List<T> result = new LinkedList<>();
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				result.add(node.getData());
			} else if (!node.isEmpty()) {
				result.addAll(order((BSTNode<T>) node.getLeft()));
				result.addAll(order((BSTNode<T>) node.getRight()));
				result.add(node.getData());
			}
		}

		return result;
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

	private Comparable[] makeArrayFromList(List<T> result) {
		Comparable[] result1 = new Comparable[result.size()];
		for (int i = 0; i < result.size(); i++) {
			result1[i] = result.get(i);
		}
		return result1;
	}

	@SuppressWarnings("unchecked")
	private BSTNode<T> getNilNode() {
		return new BSTNode.Builder<T>()
				.data(null)
				.parent(null)
				.left(null)
				.right(null)
				.build();
	}
}