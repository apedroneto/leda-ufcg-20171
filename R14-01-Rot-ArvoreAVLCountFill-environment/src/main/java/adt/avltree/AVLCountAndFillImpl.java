package adt.avltree;

import adt.bst.BSTNode;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
        AVLTreeImpl<T> implements AVLCountAndFill<T> {

    private int LLcounter;
    private int LRcounter;
    private int RRcounter;
    private int RLcounter;

    public AVLCountAndFillImpl() {

    }

    @Override
    protected BSTNode<T> solveLeftLeft(BSTNode<T> node) {
        LLcounter++;
        return super.solveLeftLeft(node);
    }

    @Override
    protected BSTNode<T> solveRightRight(BSTNode<T> node) {
        RRcounter++;
        return super.solveRightRight(node);
    }

    @Override
    protected BSTNode<T> solveRightLeft(BSTNode<T> node) {
        RLcounter++;
        return super.solveRightLeft(node);
    }

    @Override
    protected BSTNode<T> solveLeftRight(BSTNode<T> node) {
        LRcounter++;
        return super.solveLeftRight(node);
    }

    @Override
    public int LLcount() {
        return LLcounter;
    }

    @Override
    public int LRcount() {
        return LRcounter;
    }

    @Override
    public int RRcount() {
        return RRcounter;
    }

    @Override
    public int RLcount() {
        return RLcounter;
    }

    @Override
    public void fillWithoutRebalance(T[] array) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented yet!");
    }

}
