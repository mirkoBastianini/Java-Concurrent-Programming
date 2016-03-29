package main;

import basic.BinaryTreeImplementation;

/**
 *
 * @author mirko
 */
public class HWJ3 {

    public static void main(String[] args) throws InterruptedException {

        warmUp();

        System.out.print("Albero da 1000 nodi ...");
        BinaryTreeImplementation tree100 = new BinaryTreeImplementation(1, 999);
        System.out.println(" Done!");
        System.out.print("Albero da 100000 nodi ...");
        BinaryTreeImplementation tree100k = new BinaryTreeImplementation(1, 99999);
        System.out.println(" Done!");

        System.out.println("Processori disponibili: " + Runtime.getRuntime().availableProcessors());

        System.out.println("Benchmark start ...");

        measureHWJ3(tree100, tree100k);

        System.out.println();

        System.out.println("Benchmark stop.");
    }

    public static void measureHWJ3(BinaryTreeImplementation tree1k, BinaryTreeImplementation tree100k) {

        long recursiveTimer, concurrentTimer;
        long chrono1 = 0;
        long chrono2 = 0;
        long recursiveValue;
        long concurrentValue;
        System.out.println("--------------------------------------------------------------");
        BinaryTreeImplementation bt = tree1k;
        hwj3.BinaryTreeAdderImplementation bta = new hwj3.BinaryTreeAdderImplementation(990);//cosi cambio l'implementazione per ogni Homework

        chrono2 = System.currentTimeMillis();
        recursiveValue = bt.serialSum();
        recursiveTimer = System.currentTimeMillis() - chrono2;

        chrono1 = System.currentTimeMillis();
        concurrentValue = bta.computeOnerousSum(bt.getRoot());
        concurrentTimer = System.currentTimeMillis() - chrono1;

        System.out.println("Albero da: " + bt.size() + " nodi. ");
        System.out.println("Speed up:" + ((float) recursiveTimer / (float) concurrentTimer));
        System.out.println("Durata algoritmo concorrente: " + concurrentTimer);
        System.out.println("Durata algoritmo sequenziale: " + recursiveTimer);
        System.out.println("Nodi visitati: concorrente = " + concurrentValue + " sequenziale = " + recursiveValue);
        System.out.println("End.");
        System.out.println("--------------------------------------------------------------");

        bt = tree100k;
        bta = new hwj3.BinaryTreeAdderImplementation(10000);

        chrono1 = System.currentTimeMillis();
        concurrentValue = bta.computeOnerousSum(bt.getRoot());
        concurrentTimer = System.currentTimeMillis() - chrono1;

        chrono2 = System.currentTimeMillis();
        recursiveValue = bt.serialSum();
        recursiveTimer = System.currentTimeMillis() - chrono2;

        System.out.println("Albero da: " + bt.size() + " nodi. ");
        System.out.println("Speed up:" + ((float) recursiveTimer / (float) concurrentTimer));
        System.out.println("Durata algoritmo concorrente: " + concurrentTimer);
        System.out.println("Durata algoritmo sequenziale: " + recursiveTimer);
        System.out.println("Nodi visitati: concorrente = " + concurrentValue + " sequenziale = " + recursiveValue);
        System.out.println("End.");
        System.out.println("--------------------------------------------------------------");
        System.out.println("FINE TEST HWJ3");
    }

    public static void warmUp() throws InterruptedException {
        System.out.println("--------------------------------------------------------------");
        System.out.println("INIZIO TEST HWJ3");

        for (int i = 0; i <= 10; i++) {
            System.out.println("Loading: " + i * 10 + "% ");

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Loading: Done!");
        System.out.println("--------------------------------------------------------------");

    }

}
