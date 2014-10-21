package Ted;

/********************************************************************
 * Creates a Node in which the data is stored in a DoubleLinkedList
 * @author Mitchell Couturier
 * @version 3/29/14
 ********************************************************************/

public class Node {
	
	/** holds the data entered into the Node **/
	private String data;
	
	/** the previous Node in the LinkedList **/
	private Node prev;
	
	/** the next Node in the LinkedList **/
	private Node next;
	
	/********************************************************************
	 * A constructor that sets the parameters to the instance variables
	 * 
	 * @param the data of type String
	 * @param the previous Node in the list
	 * @param the next Node in the list
	 ********************************************************************/
	public Node(String data, Node prev, Node next) {
		this.data = data;
		this.prev = prev;
		this.next = next;
	}

	/********************************************************************
	 * A constructor that sets all instance variables to null
	 ********************************************************************/
	public Node() {
	}

	/********************************************************************
	 * Returns the data of the current Node
	 * 
	 * @return the data of the current Node of type String
	 ********************************************************************/
	public String getData() {
		return data;
	}
	
	/********************************************************************
	 * Sets the data of the current Node
	 * 
	 * @param the data for the current Node of type String
	 ********************************************************************/
	public void setData(String data) {
		this.data = data;
	}
	
	/********************************************************************
	 * Returns the data of the next Node
	 * 
	 * @return the data of the next Node of type String
	 ********************************************************************/
	public Node getNext() {
		return next;
	}
	
	/********************************************************************
	 * Sets the data of the next Node
	 * 
	 * @param the data for the next Node of type String
	 ********************************************************************/
	public void setNext(Node next) {
		this.next = next;
	}
	
	/********************************************************************
	 * Returns the data of the previous Node
	 * 
	 * @return the data of the previous Node of type String
	 ********************************************************************/
	public Node getPrev() {
		return prev;
	}
	
	/********************************************************************
	 * Sets the data of the previous Node
	 * 
	 * @param the data for the previous Node of type String
	 ********************************************************************/
	public void setPrev(Node prev) {
		this.prev = prev;
	}
}
