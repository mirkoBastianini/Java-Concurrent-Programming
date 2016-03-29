package hwj3;

import basic.BinaryTreeImplementation;
import interfaces.Node;
import java.util.concurrent.Callable;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author mirko
 */
public class OnerousProcessorTask extends RecursiveTask<Integer> {

    private int edge;
    private int currentDepth;
    private Node n;
    private BinaryTreeImplementation b;
    private FakeProcessor processor;

    public OnerousProcessorTask(BinaryTreeImplementation b, int currentDepth, int dege, Node n, FakeProcessor processor) {
        super();
        this.b = b;
        this.currentDepth = currentDepth;
        this.n = n;
        this.edge = dege;
        this.processor = processor;
    }

    @Override
    protected Integer compute() {

        /*
            
            if ( N < SEQUENTIAL_THRESHOLD ) {
                    // più conveniente una soluzione seriale
                    << soluzione diretta (e seriale) del problema >>
            } else {
                    // più conveniente una decomposizione parallela
                    << decomposizione in sotto-task >>
                    << fork di tutti i sotto-task >>
                    << join di tutti i sotto-task >>
                    << ricomposizione dei risultati parziali >>
         */
        
        //la somma seriale parte quando supero una profondità dell'albero tale per cui non è piu conventiente
        //fare il calcolo parallelo....
        if (currentDepth >= edge) {

            return b.serialSum(n);

        } else {

            int nextDepth = currentDepth + 1;

            Integer dxSol = 0;
            Integer sxSol = 0;
            OnerousProcessorTask dx = null;
            OnerousProcessorTask sx = null;

            //figlio destro lo mando in coda per un altro thread
            if (n.getDx() != null) {
                dx = new OnerousProcessorTask(b, nextDepth, edge, n.getDx(), new FakeProcessor(10000));
                dx.fork();/*Arranges to asynchronously execute this task in 
                                            the pool the current task is running in*/
            }

            //figlio sinistro lo calcolo nel current thread.
            if (n.getSx() != null) {
                sx = new OnerousProcessorTask(b, nextDepth, edge, n.getSx(), new FakeProcessor(10000));
                sxSol = sx.compute();
            }

            if (dx != null) {
                dxSol = dx.join();/*Returns the result of the computation when it is done.*/
            }

            int ret = processor.onerousFunction(n.getValue());

            return ret + dxSol + sxSol;

        }

    }

}
