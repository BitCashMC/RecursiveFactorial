package org.bitcash.testing.javasandbox.recursivefactorial;

import java.math.BigInteger;
import java.time.Clock;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        /*
         * Program Initialization
         */
        ConstructBigIntArray test = new ConstructBigIntArray(5000);
        Clock time = Clock.systemDefaultZone();
        long start,stop;
        /*
         * Parallel computing implementation using ForkJoin Framework
        */
        RecursiveFactorial rSum = new RecursiveFactorial(test.getList());
        /*
         * 
         * Getting the amount of processors the JVM currently has access to. Reminder, the calculation of thread distribution among cores is intelligently done by the framework in collaboration with
         * the O.S and hardware.
        */
        final int nThreads = Runtime.getRuntime().availableProcessors();
        /*
         * Instantiate the ForkJoinPool Thread Pool with the specified amount of max worker threads.
         */
        ForkJoinPool pool = new ForkJoinPool(nThreads);
        /*
         * Enqueues the provided ForkJoinTask to the shared queue of the thread pool to be immediately polled and computed by a worker thread. Reminder that invoke() will halt the execution flow
         * of the program until it has finished the parallel computation of the given Recursive task.
         */
        start = time.millis();
        pool.invoke(rSum);
        stop = time.millis();

        /*
         * Pass the value of the completed computation
         */
        BigInteger product = rSum.result;
        /*
         * Print the results of the computation
         */
        System.out.println("Parallelized Product: " + product);
        System.out.println("Computation time: " + (stop-start)  + "ms");
        /*
         * Sequential implementation
         */
        start = time.millis();
        BigInteger serialProduct = new BigInteger("1");
        for (int i = 0; i < test.getList().length; i++) {
            serialProduct = serialProduct.multiply(test.getList()[i]);
        }
        stop = time.millis();
        System.out.println("Serial Product: " + serialProduct);
        System.out.println("Computation time: " + (stop-start) + "ms");
    }
    
}
