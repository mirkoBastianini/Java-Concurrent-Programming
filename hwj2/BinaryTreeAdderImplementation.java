package hwj2;

import hwj1.*;
import interfaces.BinaryTreeAdder;
import interfaces.Node;
import interfaces.OnerousProcessor;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.*;

/**
 *
 * @author mirko
 */
public class BinaryTreeAdderImplementation implements BinaryTreeAdder {

    private final int processors = Runtime.getRuntime().availableProcessors(); //processori disponibili <8>
    private ExecutorService pool;

    public BinaryTreeAdderImplementation() {
        this.pool = Executors.newFixedThreadPool(processors); //pool di thread di dimensione numero processori <8>
    }

    @Override
    public int computeOnerousSum(Node root) {

        int sum = 0;
        List<Future<Integer>> partialSums = new ArrayList<Future<Integer>>();
        List<Syncro<Node>> code = new ArrayList<Syncro<Node>>();

        //creo una coda per ogni thread e aggiungo la root alla prima di queste code
        for (int i = 0; i < processors; i++) {
            code.add(new Syncro<Node>());
        }
        code.get(0).getQueue().add(root);

        //avvio n task (n processori), e gli passo anche i riferimenti degli altri code
        //la i rappresenta l'id della coda che chiama il metodo
        for (int i = 0; i < processors; i++) {
            partialSums.add(pool.submit(new OnerousProcessorTask(code, i, new FakeProcessor(10000))));
        }

        for (Future<Integer> i : partialSums) {
            try {
                sum += i.get().intValue();
            } catch (InterruptedException | ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        pool.shutdown();

        return sum;
    }
}
