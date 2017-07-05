package adt.hashtable.open;


import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import org.junit.Assert;
import org.junit.Test;

public class HashTableOpenAddressLinearProbing {

    @Test
    public void testSearch(){
        AbstractHashtableOpenAddress<HashtableElement> impl = getImpl(4);
        impl.insert(getElement(1));
        impl.insert(getElement(2));
        impl.insert(getElement(5));
        impl.insert(getElement(4));

        Assert.assertEquals(getElement(2), impl.search(getElement(2)));
        Assert.assertEquals(getElement(5), impl.search(getElement(5)));
        Assert.assertEquals(getElement(1), impl.search(getElement(1)));
        Assert.assertEquals(getElement(4), impl.search(getElement(4)));

        Assert.assertNull(impl.search(getElement(8)));
        Assert.assertNull(impl.search(getElement(7)));
    }

    @Test
    public void testRemove(){
        AbstractHashtableOpenAddress<HashtableElement> impl = getImpl(4);
        impl.insert(getElement(1));
        impl.insert(getElement(2));
        impl.insert(getElement(5));
        impl.insert(getElement(4));

        impl.remove(getElement(2));
        Assert.assertEquals(3, impl.size());

        impl.remove(getElement(2));
        Assert.assertEquals(3, impl.size());

        impl.remove(getElement(4));
        Assert.assertEquals(2, impl.size());
    }

    @Test
    public void testInsert() {
        AbstractHashtableOpenAddress<HashtableElement> impl = getImpl(4);
        impl.insert(getElement(3));
        Assert.assertEquals(1, impl.size());
        impl.insert(getElement(5));
        Assert.assertEquals(2, impl.size());
        impl.insert(getElement(6));
        Assert.assertEquals(3, impl.size());

        impl.insert(getElement(6));
        Assert.assertEquals(3, impl.size());
    }

    public HashtableElement getElement(int num){
        return new HashtableElement(num);
    }

    public AbstractHashtableOpenAddress<HashtableElement> getImpl(int size){
        return new HashtableOpenAddressQuadraticProbingImpl<>(size, HashFunctionClosedAddressMethod.DIVISION, 5, 6);
    }
}
