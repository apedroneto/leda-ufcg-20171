package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
        AbstractHashtableOpenAddress<T> {

    public HashtableOpenAddressLinearProbingImpl(int size,
                                                 HashFunctionClosedAddressMethod method) {
        super(size);
        hashFunction = new HashFunctionLinearProbing<T>(size, method);
        this.initiateInternalTable(size);
    }

    @Override
    public void insert(T element) {
        if(isFull()){
            throw new HashtableOverflowException();
        }
        if (element != null && indexOf(element) == -1) {
            boolean isInserted = false;
            for (int probe = 0; probe < this.table.length && !isInserted; probe++) {
                int hash = ((HashFunctionLinearProbing) getHashFunction()).hash(element, probe);
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
                hash = ((HashFunctionLinearProbing) getHashFunction()).hash(element, probe);
                if (this.table[hash] != null && this.table[hash].equals(element)) {
                    result = hash;
                }
                probe++;
            } while (this.table[hash] != null && !this.table[hash].equals(element) && probe != this.table.length);
        }

        return result;
    }

}
