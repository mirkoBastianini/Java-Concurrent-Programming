package basic;

import interfaces.Node;
/**
 *
 * @author mirko
 */
public class NodeImplementation implements Node {
	
	private Node dx;
	private Node sx;
	private int value;
	
	public NodeImplementation(int value){
		this.sx = null;
		this.dx = null;
		this.value = value;
	}

	@Override
	public Node getSx() {
		return this.sx;
	}

	@Override
	public Node getDx() {
		return this.dx;
	}

	@Override
	public int getValue() {
		return this.value;
	}
	
	public void setSx(Node n){
		this.sx = n;
	}
	
	public void setDx(Node n){
		this.dx = n;
	}

}
