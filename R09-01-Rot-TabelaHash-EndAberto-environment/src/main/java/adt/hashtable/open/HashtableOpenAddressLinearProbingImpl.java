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
        if (element != null) {
            boolean inserted = false;
            boolean isDuplicate = false;
            for (int i = 0; i < this.table.length && !inserted && !isDuplicate; i++) {
                int hash = ((HashFunctionLinearProbing) getHashFunction()).hash(element, i);
                if (this.table[hash] == null || this.deletedElement.equals(this.table[hash])) {
                    this.table[hash] = element;
                    inserted = true;
                    COLLISIONS += i;
                } else if (this.table[hash].equals(element)) {
                    isDuplicate = true;
                }
            }

            if (inserted) {
                this.elements++;
            } else if (!isDuplicate) {
                throw new HashtableOverflowException();
            }
        }
    }

    @Override
    public void remove(T element) {
        int idx = indexOf(element);
        if(idx != -1){
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
            int i = 0;
            int hash;
            do {
                hash = ((HashFunctionLinearProbing) getHashFunction()).hash(element, i);
                if (this.table[hash] != null && this.table[hash].equals(element)) {
                    result = hash;
                }
                i++;
            } while (this.table[hash] != null && !this.table[hash].equals(element) && i != this.table.length);
        }

        return result;
    }

}
