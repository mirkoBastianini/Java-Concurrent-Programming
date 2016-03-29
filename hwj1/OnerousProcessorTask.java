package hwj1;

import interfaces.Node;
import interfaces.OnerousProcessor;
import java.util.concurrent.BlockingQueue;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
/**
 *
 * @author mirko
 */
public class OnerousProcessorTask implements Callable<Integer> {

	private OnerousProcessor processor;
	private BlockingQueue<Node> queue;
	private Node node;

	private Tasks counter;
	private Sum sum;

	public OnerousProcessorTask(BlockingQueue<Node> queue, Sum sum, OnerousProcessor processor, Tasks c){
		this.queue = queue;
		this.processor = new FakeProcessor(10000);
		this.counter = c;
		this.sum = sum;

	}


	@Override
	public Integer call() {

		while(true){
				
			counter.add();
				try {
					node = queue.take();
				} catch (InterruptedException e) {
					break;
				}
			counter.remove();

				if(node.getDx() != null){
					queue.add(node.getDx());

				}

				if(node.getSx() != null){
					queue.add(node.getSx());
				}

			//computa il valore da inserire nella somma e aggiungilo
			sum.sum(processor.onerousFunction(node.getValue()));

		}


		return 0;


	}

}