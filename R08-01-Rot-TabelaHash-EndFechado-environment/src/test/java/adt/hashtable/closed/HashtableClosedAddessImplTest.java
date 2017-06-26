package adt.hashtable.closed;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import org.junit.Assert;
import org.junit.Test;

public class HashtableClosedAddessImplTest {

    @Test
    public void testInsert(){
        HashtableClosedAddressImpl<Integer> impl = getImpl(3);
        impl.insert(3);
        Assert.assertEquals(1, impl.size());
        Assert.assertFalse(impl.isEmpty());
        impl.insert(4);
        Assert.assertEquals(2, impl.size());
        impl.insert(5);
        Assert.assertEquals(3, impl.size());
        Assert.assertEquals(0, impl.getCOLLISIONS());
    }

    @Test
    public void testInsertEmpty(){
        HashtableClosedAddressImpl<Integer> impl = getImpl(3);
        impl.insert(null);
        Assert.assertEquals(impl.size(), 0);
    }

    @Test
    public void testCollision(){
        HashtableClosedAddressImpl<Integer> impl = getImpl(3);
        impl.insert(3);
        impl.insert(2);
        Assert.assertEquals(0, impl.getCOLLISIONS());
        impl.insert(3);
        Assert.assertEquals(1, impl.getCOLLISIONS());
    }

    @Test
    public void remove() {
        HashtableClosedAddressImpl<Integer> impl = getImpl(4);
        impl.remove(2);
        impl.insert(1);
        impl.insert(2);
        impl.insert(3);
        impl.insert(3);
        impl.remove(3);
        Assert.assertEquals(3, impl.size());
        impl.remove(3);
        Assert.assertEquals(null, impl.search(3));
        impl.remove(2);
        impl.remove(1);
        impl.remove(2);
    }

    @Test
    public void search() {
        HashtableClosedAddressImpl<Integer> impl = getImpl(4);
        Assert.assertNull(impl.search(2));
        impl.insert(1);
        impl.insert(2);
        impl.insert(3);
        Assert.assertEquals(integer(3), impl.search(3));
        Assert.assertEquals(integer(1), impl.search(1));
        Assert.assertEquals(integer(2), impl.search(2));
        Assert.assertNull(impl.search(5));
    }

    private Integer integer(int num){
        return num;
    }

    private HashtableClosedAddressImpl<Integer> getImpl(int size){
        return new HashtableClosedAddressImpl<>(size, getHashFunctionClosedAddressMethod());
    }

    private HashFunctionClosedAddressMethod getHashFunctionClosedAddressMethod(){
        return HashFunctionClosedAddressMethod.DIVISION;
    }
}