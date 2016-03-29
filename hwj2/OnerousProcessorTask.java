package hwj2;

import hwj1.*;
import interfaces.Node;
import interfaces.OnerousProcessor;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author mirko
 */
public class OnerousProcessorTask implements Callable<Integer> {

    private List<Syncro<Node>> code;
    private Syncro<Node> personalQueue;
    private OnerousProcessor processor;
    private int id;

    public OnerousProcessorTask(List<Syncro<Node>> queues, int id, OnerousProcessor processor) {
        this.code = queues;
        this.personalQueue = queues.get(id);
        this.processor = processor;
        this.id = id;

    }

    @Override
    public Integer call() throws Exception {

        Node n;
        int sum = 0;
        Syncro<Node> otherQueue;

        while (true) {

            //provo a prendere un nodo in mutex <synchronized, tutti mi aspettano>
            synchronized (personalQueue.getMutex()) {

                n = personalQueue.getQueue().pollFirst();
            }

            //se non ho nodi provo a rubare del lavoro agli altri
            if (n == null) {

                for (int i = 0; i < code.size(); i++) {
                    if (i != id) {

                        otherQueue = code.get(i);

                        synchronized (otherQueue.getMutex()) {

                            n = otherQueue.getQueue().pollFirst();

                        }
                    }
//se ho preso il nodo me ne vado
                    if (n != null) {
                        break;
                    }
                }
            }

            //se non ci sono nodi in assoluto, esco
            if (n == null) {
                break;
            } else {// oppure lo elaboro

                synchronized (personalQueue.getMutex()) {

                    if (n.getDx() != null) {
                        personalQueue.getQueue().add(n.getDx());
                    }
                    if (n.getSx() != null) {
                        personalQueue.getQueue().add(n.getSx());
                    }

                }

                sum += processor.onerousFunction(n.getValue());
            }

        }
        return sum;

    }

}
