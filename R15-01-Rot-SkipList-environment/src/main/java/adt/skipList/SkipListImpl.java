package adt.skipList;

import java.util.LinkedList;
import java.util.List;

public class SkipListImpl<T> implements SkipList<T> {

    protected SkipListNode<T> root;
    protected SkipListNode<T> NIL;

    protected int maxHeight;

    protected double PROBABILITY = 0.5;

    public SkipListImpl(int maxHeight) {
        this.maxHeight = maxHeight;
        root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
        NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
        connectRootToNil();
    }

    /**
     * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
     * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
     * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
     * metodo deve conectar apenas o forward[0].
     */
    private void connectRootToNil() {
        for (int i = 0; i < maxHeight; i++) {
            root.forward[i] = NIL;
        }
    }

    @Override
    public void insert(int key, T newValue, int height) {
        if (height < maxHeight) {
            SkipListNode<T>[] update = getUpdateArray();
            SkipListNode<T> node = root;
            for (int i = maxHeight - 1; i >= 0; i--) {
                while (node.forward[i].key < key) {
                    node = node.forward[i];
                }
                update[i] = node;
            }
            node = node.forward[0];
            if (node.key == key) {
                node.value = newValue;
            } else {
                int level = height;
                node = new SkipListNode<>(key, level, newValue);
                for (int i = 0; i < level; i++) {
                    node.forward[i] = update[i].forward[i];
                    update[i].forward[i] = node;
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private SkipListNode<T>[] getUpdateArray() {
        return new SkipListNode[maxHeight];
    }

    private int randomLevel() {
        int level = 0;
        while (Math.random() < PROBABILITY) {
            level++;
        }
        return Math.min(level, maxHeight);
    }

    @Override
    public void remove(int key) {
        SkipListNode<T> node = root;
        SkipListNode<T>[] update = getUpdateArray();
        for (int i = maxHeight - 1; i >= 0; i--) {
            while (node.forward[i].key < key) {
                node = node.forward[i];
            }
            update[i] = node;
        }
        node = node.forward[0];
        if (node.key == key) {
            for (int i = 0; i < maxHeight && update[i].forward[i] == node; i++) {
                update[i].forward[i] = node.forward[i];
            }
        }
    }

    @Override
    public int height() {
        int level;
        for (level = maxHeight - 1; root.forward[level] != null; level--) ;
        return level;
    }

    @Override
    public SkipListNode<T> search(int key) {
        SkipListNode<T> node = root;
        for (int i = maxHeight - 1; i >= 0; i--) {
            while (node.forward[i].key < key) {
                node = node.forward[i];
            }
        }
        node = node.forward[0];
        SkipListNode<T> result = null;
        if (node.key == key) {
            result = node;
        }

        return result;
    }

    @Override
    public int size() {
        SkipListNode<T> node = root.forward[0];
        int result = -1;
        while (node != null) {
            node = node.forward[0];
            result++;
        }

        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public SkipListNode<T>[] toArray() {
        SkipListNode<T> node = root;
        List<SkipListNode> result = new LinkedList<>();
        while (node != null) {
            result.add(node);
            node = node.forward[0];
        }

        return (SkipListNode<T>[]) result.toArray(new SkipListNode[size()]);
    }

}
