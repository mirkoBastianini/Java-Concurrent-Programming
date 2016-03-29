package hwj1;
/**
 *
 * @author mirko
 */
public class Sum {
	
	private int partialSum;

	public Sum(){
		this.partialSum = 0;
	}
	
	public synchronized void sum(int value){
		this.partialSum = this.partialSum + value;
	}
	
	public int getCurrentSum(){
		return this.partialSum;
	}

}