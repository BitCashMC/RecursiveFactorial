package org.bitcash.testing.javasandbox.recursivefactorial;

import java.math.BigInteger;

public class ConstructBigIntArray {

    private final BigInteger[] list;

    public ConstructBigIntArray(int bounds) {
        list = new BigInteger[bounds];
        for (int i = 0; i < list.length; i++) {
            list[i] = new BigInteger(""+(i+1)); //BigInteger allows you to display large integers since int overflows beyond a certain amount
        }
    }

    public BigInteger[] getList() {
        return list;
    }


    
}
