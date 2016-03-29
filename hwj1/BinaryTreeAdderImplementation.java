package hwj1;

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
	private final ExecutorService pool;
	private BlockingQueue<Node> coda;
	
	public BinaryTreeAdderImplementation(){
		this.pool = Executors.newFixedThreadPool(processors); //pool di thread di dimensione numero processori <8>
		this.coda = new LinkedBlockingQueue<Node>();
	}
	
	@Override
	public int computeOnerousSum(Node root) {
		
		OnerousProcessor processor = new FakeProcessor(10000);
		Sum sum = new Sum(); 
		List<Future<Integer>> partial = new ArrayList<Future<Integer>>();
		Tasks c = new Tasks(this.processors);
	
		
		this.coda.add(root);
		/* Avvio n thread = numero processori
                    -prelevo dalla coda, se la trovo vuota mi metto in attesa
                    -se tutti i thread si trovano nello stato di attesa allora ho finito di visitare l'albero
                */
		
		for(int i=0; i<processors; i++)
			partial.add(this.pool.submit(new OnerousProcessorTask(this.coda, sum, processor, c)));
		
		try {
			c.waitComplete();
		} catch (InterruptedException e1) {
			return 0;
		}
		
		pool.shutdownNow(); //shutdown non accetta piu richieste, ma comunque esaurisce quelle in coda
	
		return sum.getCurrentSum();
	}

}