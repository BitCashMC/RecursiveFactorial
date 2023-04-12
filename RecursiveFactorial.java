package org.bitcash.testing.javasandbox.recursivefactorial;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class RecursiveFactorial extends RecursiveAction {
    private BigInteger[] list;
    public BigInteger result;

    public RecursiveFactorial(BigInteger[] list) {
        this.list = list;
    }

    @Override
    public void compute() {
        //breaking point where tasks are considered atomic and can no longer be subdivided further.
        if (this.list.length == 1) {
            result = list[0];
        } else {
            int midpoint = list.length/2;
            /*
             * Subdividing the data source to be computed in parallel
             */
            BigInteger[] l1 = Arrays.copyOfRange(this.list,0,midpoint);
            BigInteger[] l2 = Arrays.copyOfRange(this.list,midpoint,this.list.length);

            RecursiveFactorial rf1 = new RecursiveFactorial(l1);
            RecursiveFactorial rf2 = new RecursiveFactorial(l2);

            rf1.fork(); 
            rf2.fork();

            rf1.join();
            rf2.join();

            this.result = rf1.result.multiply(rf2.result);
        }

    }
    
}
