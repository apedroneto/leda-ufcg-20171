package adt.btree;

import java.util.LinkedList;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

    protected BNode<T> root;
    protected int order;

    public BTreeImpl(int order) {
        this.order = order;
        this.root = new BNode<T>(order);
    }

    @Override
    public BNode<T> getRoot() {
        return this.root;
    }

    @Override
    public boolean isEmpty() {
        return this.root.isEmpty();
    }

    @Override
    public int height() {
        return height(this.root);
    }

    private int height(BNode<T> node) {
        int result = -1;
        if (!node.isEmpty()) {
            result = 1 + height(node.getChildren().get(0));
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BNode<T>[] depthLeftOrder() {
        LinkedList<BNode<T>> list = new LinkedList<>();
        depthLeftOrder(root, list);
        return listToArray(list);
    }

    private BNode[] listToArray(LinkedList<BNode<T>> nodes) {
        BNode[] nodesArray = new BNode[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            nodesArray[i] = nodes.get(i);
        }
        return nodesArray;
    }

    private void depthLeftOrder(BNode<T> node, LinkedList<BNode<T>> result) {
        if (!node.isEmpty()) {
            result.add(node);
            for (int i = 0; i < node.getChildren().size(); i++) {
                depthLeftOrder(node.getChildren().get(i), result);
            }
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    public int size(BNode<T> node) {
        int result = 0;
        if (!node.isEmpty()) {
            result = node.size() + getChildrenSize(node);
        }
        return result;
    }

    private int getChildrenSize(BNode<T> node) {
        int result = 0;
        for (int i = 0; i < node.size(); i++) {
            result += size(node.getChildren().get(i));
        }
        return result;
    }

    @Override
    public BNodePosition<T> search(T element) {
        return search(root, element);
    }

    private BNodePosition<T> search(BNode<T> node, T element) {
        BNodePosition<T> result = new BNodePosition<>();
        if (element != null) {
            int elementPosition = getElementPosition(node, element);
            if (isValidElementPosition(node, element, elementPosition)) {
                result = new BNodePosition<>(node, elementPosition);
            }
            if (!node.isLeaf()) {
                result = search(node.getChildren().get(elementPosition), element);
            }
        }

        return result;
    }

    private boolean isValidElementPosition(BNode<T> node, T element, int position) {
        return position < node.size() && element.equals(node.getElementAt(position));
    }

    private int getElementPosition(BNode<T> node, T element) {
        int i = 0;
        while (i < node.size() && element.compareTo(node.getElementAt(i)) > 0) {
            i++;
        }
        return i;
    }

    @Override
    public void insert(T element) {
        insert(root, element);
    }

    public void insert(BNode<T> node, T element) {
        int idx = getInsertionIndex(node, element);
        if (node.isLeaf()) {
            insertNode(node, element, idx);
            if (node.isFull()) {
                split(node);
                updateRoot(node);
            }
        } else {
            insert(node.getChildren().get(idx), element);
        }
    }

    private int getInsertionIndex(BNode<T> node, T element) {
        int idx = 0;
        while (idx < node.size() && node.getElementAt(idx).compareTo(element) < 0) {
            idx++;
        }
        return Math.max(idx - 1, 0);
    }

    private void insertNode(BNode<T> node, T element, int idx) {
        node.addElement(element);
        node.addChild(idx, new BNode<>(order));
    }

    private void updateRoot(BNode<T> node) {
        while (node.getParent() != null) {
            node = node.getParent();
        }
        root = node;
    }

    private void split(BNode<T> node) {
        int mid = node.size() / 2;
        BNode<T> leftChild = generateLeftChild(node, mid);
        BNode<T> rightChild = generateRightChild(node, mid + 1);

        BNode<T> parent = node.getParent();
        if (parent == null) {
            parent = new BNode<>(order);
            node.setParent(parent);
            parent.addChild(0, node);
        }

        int idx = parent.getChildren().indexOf(node);
        parent.removeChild(node);
        parent.addChild(idx, leftChild);
        parent.addChild(idx + 1, rightChild);

        promote(node.getElementAt(mid), parent);
    }

    private void promote(T element, BNode<T> parent) {
        parent.addElement(element);
        if (parent.isFull()) {
            split(parent);
        }
    }

    private BNode<T> generateRightChild(BNode<T> node, int mid) {
        BNode<T> right = new BNode<>(order);
        for (int i = 0; i < mid; i++) {
            right.addElement(node.getElementAt(i));
        }
        for (int i = 0; i <= mid; i++) {
            right.addChild(i, node.getChildren().get(i));
        }
        return right;
    }

    private BNode<T> generateLeftChild(BNode<T> node, int mid) {
        BNode<T> left = new BNode<>(order);
        for (int i = mid; i < node.size(); i++) {
            left.addElement(node.getElementAt(i));
        }
        for (int i = mid; i < node.size(); i++) {
            left.addChild(i, node.getChildren().get(i));
        }
        return left;
    }

    private void promote(BNode<T> node) {

    }

    // NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
    @Override
    public BNode<T> maximum(BNode<T> node) {
        // NAO PRECISA IMPLEMENTAR
        throw new UnsupportedOperationException("Not Implemented yet!");
    }

    @Override
    public BNode<T> minimum(BNode<T> node) {
        // NAO PRECISA IMPLEMENTAR
        throw new UnsupportedOperationException("Not Implemented yet!");
    }

    @Override
    public void remove(T element) {
        // NAO PRECISA IMPLEMENTAR
        throw new UnsupportedOperationException("Not Implemented yet!");
    }

}
