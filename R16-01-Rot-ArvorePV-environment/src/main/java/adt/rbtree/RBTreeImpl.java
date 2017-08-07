package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

import java.util.LinkedList;
import java.util.List;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
        implements RBTree<T> {

    public RBTreeImpl() {
        this.root = new RBNode<T>();
    }

    protected int blackHeight() {
        return blackHeight((RBNode<T>) root);
    }

    private int blackHeight(RBNode<T> node) {
        int result;
        if (node.isEmpty()) {
            result = 1;
        } else {
            int leftBHeight = blackHeight((RBNode<T>) node.getLeft());
            int rightBHeight = blackHeight((RBNode<T>) node.getRight());
            if (leftBHeight != rightBHeight) {
                result = -1;
            } else if (node.getColour() == Colour.BLACK) {
                result = leftBHeight + 1;
            } else {
                result = leftBHeight;
            }
        }

        return result;
    }

    protected boolean verifyProperties() {
        boolean resp = verifyNodesColour() && verifyNILNodeColour()
                && verifyRootColour() && verifyChildrenOfRedNodes()
                && verifyBlackHeight();

        return resp;
    }

    /**
     * The colour of each node of a RB tree is black or red. This is guaranteed
     * by the type Colour.
     */
    private boolean verifyNodesColour() {
        return true; // already implemented
    }

    /**
     * The colour of the root must be black.
     */
    private boolean verifyRootColour() {
        return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
        // implemented
    }

    /**
     * This is guaranteed by the constructor.
     */
    private boolean verifyNILNodeColour() {
        return true; // already implemented
    }

    /**
     * Verifies the property for all RED nodes: the children of a red node must
     * be BLACK.
     */
    private boolean verifyChildrenOfRedNodes() {
        return verifyChildrenOfRedNodes((RBNode<T>) root);
    }

    private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
        boolean result = false;
        if (!node.isEmpty()) {
            if (verifyChildrenOfRedNodes((RBNode<T>) node.getLeft()) &&
                    verifyChildrenOfRedNodes((RBNode<T>) node.getRight())) {
                result = true;
            }
            if (node.getColour() == Colour.RED &&
                    ((RBNode) node.getLeft()).getColour() != Colour.BLACK ||
                    ((RBNode) node.getRight()).getColour() != Colour.BLACK) {
            }
        } else {
            result = true;
        }
        return result;
    }

    /**
     * Verifies the black-height property from the root. The method blackHeight
     * returns an exception if the black heights are different.
     */
    private boolean verifyBlackHeight() {
        boolean result = true;
        if (blackHeight((RBNode<T>) root) == -1) {
            result = false;
        }
        return result;
    }

    @Override
    public void insert(T value) {
        super.insert(value);
        RBNode<T> insertedNode = (RBNode<T>) search(value);
        insertedNode.setColour(Colour.RED);
        fixUpCase1(insertedNode);
    }

    @Override
    protected void insertBase(T element, BSTNode<T> node, BSTNode<T> parent) {
        node.setParent(parent);
        node.setData(element);
        node.setLeft(new RBNode<>());
        node.setRight(new RBNode<>());
    }

    @Override
    @SuppressWarnings("unchecked")
    public RBNode<T>[] rbPreOrder() {
        List<RBNode<T>> result = new LinkedList<>();
        return makeArrayFromList(rbPreOrder((RBNode<T>) root, result));
    }

    @SuppressWarnings("unchecked")
    private RBNode<T>[] makeArrayFromList(List<RBNode<T>> result) {
        RBNode<T>[] result1 = new RBNode[result.size()];
        for (int i = 0; i < result.size(); i++) {
            result1[i] = result.get(i);
        }
        return result1;
    }

    public List<RBNode<T>> rbPreOrder(RBNode<T> node, List<RBNode<T>> result) {
        if (!node.isEmpty()) {
            if (node.isLeaf()) {
                result.add(node);
            } else if (!node.isEmpty()) {
                result.add(node);
                result.addAll(rbPreOrder((RBNode<T>) node.getLeft(), result));
                result.addAll(rbPreOrder((RBNode<T>) node.getRight(), result));
            }
        }

        return result;
    }

    // FIXUP methods
    protected void fixUpCase1(RBNode<T> node) {
        if (node.equals(root)) {
            node.setColour(Colour.BLACK);
        } else {
            fixUpCase2(node);
        }
    }

    protected void fixUpCase2(RBNode<T> node) {
        if (((RBNode<T>) node.getParent()).getColour() != Colour.BLACK) {
            fixUpCase3(node);
        }
    }

    protected void fixUpCase3(RBNode<T> node) {
        if (((RBNode<T>) node.getParent().getParent().getRight()).getColour() == Colour.RED) {
            ((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
            ((RBNode<T>) node.getParent().getParent().getRight()).setColour(Colour.BLACK);
            ((RBNode<T>) node.getParent().getParent()).setColour(Colour.RED);
            fixUpCase1((RBNode<T>) node.getParent().getParent());
        } else {
            fixUpCase4(node);
        }
    }

    protected void fixUpCase4(RBNode<T> node) {
        RBNode<T> aux = node;
        if (node.equals(node.getParent().getRight()) &&
                node.getParent().equals(node.getParent().getParent().getLeft())) {
            Util.leftRotation((BSTNode<T>) node.getParent());
            aux = (RBNode<T>) node.getLeft();
        } else if (node.equals(node.getParent().getLeft()) &&
                node.getParent().equals(node.getParent().getParent().getRight())) {
            Util.rightRotation((BSTNode<T>) node.getRight());
            aux = (RBNode<T>) node.getRight();
        }
        fixUpCase5(aux);
    }

    protected void fixUpCase5(RBNode<T> node) {
        ((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
        ((RBNode<T>) node.getParent().getParent()).setColour(Colour.RED);
        if (node.equals(node.getParent().getLeft())) {
            Util.rightRotation(node);
        } else {
            Util.leftRotation(node);
        }
    }
}
