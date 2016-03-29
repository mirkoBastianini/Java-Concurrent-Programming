package hwj3;

import basic.BinaryTreeImplementation;
import interfaces.BinaryTreeAdder;
import interfaces.Node;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 *
 * @author mirko
 */
public class BinaryTreeAdderImplementation implements BinaryTreeAdder {

    private int edge; //soglia per il fork/join
    private ForkJoinPool pool;

    public BinaryTreeAdderImplementation(int estimatedEdge) {
        pool = new ForkJoinPool();
        this.edge = estimatedEdge;
    }

    @Override
    public int computeOnerousSum(Node root) {

        Integer returnValue;
        BinaryTreeImplementation bt;
        bt= new BinaryTreeImplementation(1, 0);
        //gli passo albero, profondità iniziale, soglia, radice
        OnerousProcessorTask o = new OnerousProcessorTask(bt, 0, edge, root, new FakeProcessor(10000));

        returnValue = pool.invoke(o);

        return returnValue;
    }
}
