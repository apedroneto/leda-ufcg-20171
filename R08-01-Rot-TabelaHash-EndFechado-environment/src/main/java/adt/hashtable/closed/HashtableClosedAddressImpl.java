package adt.hashtable.closed;


import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;
import util.Util;

import java.util.LinkedList;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

    /**
     * A hash table with closed address works with a hash function with closed
     * address. Such a function can follow one of these methods: DIVISION or
     * MULTIPLICATION. In the DIVISION method, it is useful to change the size
     * of the table to an integer that is prime. This can be achieved by
     * producing such a prime number that is bigger and close to the desired
     * size.
     * <p>
     * For doing that, you have auxiliary methods: Util.isPrime and
     * getPrimeAbove as documented bellow.
     * <p>
     * The length of the internal table must be the immediate prime number
     * greater than the given size. For example, if size=10 then the length must
     * be 11. If size=20, the length must be 23. You must implement this idea in
     * the auxiliary method getPrimeAbove(int size) and use it.
     *
     * @param desiredSize
     * @param method
     */

    @SuppressWarnings({"rawtypes", "unchecked"})
    public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
        int realSize = desiredSize;

        if (method == HashFunctionClosedAddressMethod.DIVISION) {
            realSize = this.getPrimeAbove(desiredSize); // real size must the
            // the immediate prime
            // above
        }
        initiateInternalTable(realSize);
        initiateCollectionInternalTable(realSize);
        HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
        this.hashFunction = function;
    }

    private void initiateCollectionInternalTable(int realSize) {
        for (int i = 0; i < realSize; i++) {
            table[i] = new LinkedList<T>();
        }
    }

    // AUXILIARY

    /**
     * It returns the prime number that is closest (and greater) to the given
     * number. You can use the method Util.isPrime to check if a number is
     * prime.
     */
    int getPrimeAbove(int number) {
        int answer = number + 1;
        while (!Util.isPrime(answer)) {
            answer++;
        }

        return answer;
    }

    @Override
    public void insert(T element) {
        if (!isFull() && element != null) {
            LinkedList<T> listTable = getListForElement(element);
            if (!listTable.isEmpty()) {
                this.COLLISIONS++;
            }
            listTable.add(element);
            this.elements++;
        }
    }

    @SuppressWarnings("unchecked")
    private LinkedList<T> getListForElement(T element) {
        int index = getHashFunction().hash(element);
        return (LinkedList<T>) table[index];
    }

    @Override
    public void remove(T element) {
        LinkedList<T> listTable = getListForElement(element);
        if(listTable.contains(element)){
            listTable.remove(element);
            this.elements--;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T search(T element) {
        T result = null;
        if (!isEmpty() && element != null) {
            LinkedList<T> listTable = getListForElement(element);
            for (T currentElement : listTable) {
                if (element.equals(currentElement)) {
                    result = currentElement;
                }
            }
        }

        return result;
    }

    @Override
    public int indexOf(T element) {
        int result = -1;
        if(getListForElement(element).contains(element)){
            result = getHashFunction().hash(element);
        }

        return result;
    }

    @Override
    public HashFunctionClosedAddress<T> getHashFunction() {
        return (HashFunctionClosedAddress<T>) super.getHashFunction();
    }
}