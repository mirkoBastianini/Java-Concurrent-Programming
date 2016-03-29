package hwj1;

/**
 *
 * @author mirko
 */
public class Tasks {

	private int MaxTask;
	private int waitingTask;
	
	public Tasks(int threadNum){
		MaxTask = threadNum;
		this.waitingTask = 0;
	}
	
	public synchronized void add(){
		this.waitingTask++;
		if(this.waitingTask == MaxTask)
			notify();
		}
	
	
	public synchronized void remove(){
		this.waitingTask--;
	}
	
	public synchronized void waitComplete() throws InterruptedException{
		while(!(waitingTask == MaxTask))
			wait();
	}

}