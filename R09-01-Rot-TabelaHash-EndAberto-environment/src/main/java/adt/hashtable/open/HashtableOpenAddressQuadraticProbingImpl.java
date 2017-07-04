package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
        extends AbstractHashtableOpenAddress<T> {

    public HashtableOpenAddressQuadraticProbingImpl(int size,
                                                    HashFunctionClosedAddressMethod method, int c1, int c2) {
        super(size);
        hashFunction = new HashFunctionQuadraticProbing<>(size, method, c1, c2);
        this.initiateInternalTable(size);
    }

    @Override
    public void insert(T element) {
        if (isFull()) {
            throw new HashtableOverflowException();
        }
        if (element != null && indexOf(element) == -1) {
            boolean isInserted = false;
            for (int probe = 0; probe < this.table.length && !isInserted; probe++) {
                int hash = ((HashFunctionQuadraticProbing) getHashFunction()).hash(element, probe);
                if (this.table[hash] == null || this.deletedElement.equals(this.table[hash])) {
                    this.table[hash] = element;
                    isInserted = true;
                    COLLISIONS += probe;
                }
            }

            if (isInserted) {
                this.elements++;
            }
        }
    }

    @Override
    public void remove(T element) {
        int idx = indexOf(element);
        if (idx != -1) {
            this.table[idx] = deletedElement;
            this.elements--;
        }
    }

    @Override
    public T search(T element) {
        T result = null;
        int idx = indexOf(element);
        if (idx != -1) {
            result = (T) table[idx];
        }

        return result;
    }

    @Override
    public int indexOf(T element) {
        int result = -1;

        if (element != null) {
            int probe = 0;
            int hash;
            do {
                hash = ((HashFunctionQuadraticProbing) getHashFunction()).hash(element, probe);
                if (this.table[hash] != null && this.table[hash].equals(element)) {
                    result = hash;
                }
                probe++;
            } while (this.table[hash] != null && !this.table[hash].equals(element) && probe != this.table.length);
        }

        return result;
    }

}
