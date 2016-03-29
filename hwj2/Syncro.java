package hwj2;

/**
 *
 * @author mirko
 */

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;


public class Syncro<T> {

	private BlockingDeque<T> queue;
	private Object mutex;
	
	public Syncro(){
		this.queue = new LinkedBlockingDeque<T>();
		this.mutex = new Object();
	}

	public BlockingDeque<T> getQueue() {
		return queue;
	}


	public Object getMutex() {
		return mutex;
	}

}