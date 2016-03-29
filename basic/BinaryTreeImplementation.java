package basic;

import hwj1.FakeProcessor;

import interfaces.Node;
import interfaces.OnerousProcessor;

import java.util.*;
/**
 *
 * @author mirko
 */


public class BinaryTreeImplementation{

	private Node root; //radice dell'albero
	private final Set<String> SetFreeNodes = new HashSet<String>(); //set dei nodi liberi
	private final Set<Node> SetTreeNodes = new HashSet<Node>(); //i nodi 
	private final Map<String,Node> MapGreenTree = new HashMap<String,Node>();
	

	
	public BinaryTreeImplementation(int rootValue, int randomNodes){
		this.root = null;
		addNode(rootValue); //aggiunge la radice
		if(randomNodes>0){ //se ci sono altri nodi, li aggiungo
			for(int i=0; i<randomNodes; i++){
				addNode(1);
				}
		}
	}
	

	
	public void addNode(int value){
		
		Node newNode = new NodeImplementation(value);
		
		if(this.root == null){ // se la radice è vuota
			this.root = newNode; //creo il nodo
			String s1 = new String("0"); //creo i figli
			String s2 = new String("1");
			this.SetTreeNodes.add(newNode); //aggiungo il nodo alla lista dei nodi
			this.MapGreenTree.put("root", newNode); //aggiungo il nodo alla struttura
			this.SetFreeNodes.add(s1); //aggiungo i figli al set dei nodi liberi
			this.SetFreeNodes.add(s2);
		}
		else{
			this.SetTreeNodes.add(newNode);
			String LeafFree = getRandomFreeLeaf(); 
			previousNode(LeafFree, newNode);
			String enteringLeaf = new String(LeafFree);
			this.MapGreenTree.put(enteringLeaf, newNode);
			String newLeftLeaf = new String(LeafFree.concat("0"));
			String newRightLeaf = new String(LeafFree.concat("1"));
			this.SetFreeNodes.add(newRightLeaf);
			this.SetFreeNodes.add(newLeftLeaf);
			this.SetFreeNodes.remove(LeafFree);

		}
	}
	
	private String getRandomFreeLeaf(){
		int size = this.SetFreeNodes.size();
		int item = new Random().nextInt(size); //torna un int random tra 0 e size
		int i = 0;
		for(String leaf : SetFreeNodes)
		{
		    if (i == item)
		        return leaf;
		    i = i + 1;
		}
		return null;
	}
	
	private void previousNode(String nodePosition, Node actualNode){
		if(nodePosition.length()==1)
			if(nodePosition.charAt(0)=='0')
				MapGreenTree.get("root").setSx(actualNode);
			else
				MapGreenTree.get("root").setDx(actualNode);
		else{
			String father = nodePosition.substring(0, nodePosition.length()-1);
			if(nodePosition.charAt(nodePosition.length()-1)=='0')
				MapGreenTree.get(father).setSx(actualNode);
			else
				MapGreenTree.get(father).setDx(actualNode);
		}
	}
	
	public Node getRoot() {
		return root;
	}

	public Set<String> getFreeLeafs() {
		return SetFreeNodes;
	}

	public Set<Node> getTreeNodes() {
		return SetTreeNodes;
	}

	public Map<String, Node> getTreeStruct() {
		return MapGreenTree;
	}
	
	
	public int serialSum(){
		OnerousProcessor fp = new FakeProcessor(10000);
		return serialSumRecursive(this.root, fp);
	}
	
	
	public int serialSumRecursive(Node add, OnerousProcessor fp){
		
		
		int partialSum = fp.onerousFunction(add.getValue());
		
		if(add.getDx() != null)
			partialSum += serialSumRecursive(add.getDx(), fp);
		
		if(add.getSx() != null)
			partialSum += serialSumRecursive(add.getSx(), fp);
		
		return partialSum;
		
	}
	
	public int serialSum(Node n){
		OnerousProcessor fp = new FakeProcessor(10000);
		return serialSumRecursive(n, fp);
	}
	

	
	
	public int size(){
		return this.SetTreeNodes.size();
	}

}
