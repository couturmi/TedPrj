package Ted;

/********************************************************************
 * Creates a Node in which the data is stored for a Clipboard
 * @author Mitchell Couturier
 * @version 4/10/14
 ********************************************************************/

public class CNode {

	/** Contains the next data entry to the Clipboard **/
	private CNode next;
	
	/** The data entry for this Clipboard **/ 
	private Node top;
	
	/** The Clipboard number used for identification **/
	private int clipboardNum;
	
	/***************************************************************
	 * The constructor for the Clipboard Node
	 ***************************************************************/
	public CNode(){
	}
	
	/***************************************************************
	 * Returns the Next Clipboard Node
	 * 
	 * @return the next Clipboard Node
	 ***************************************************************/
	public CNode getNext() {
		return next;
	}

	/***************************************************************
	 * Sets the Next Clipboard Node
	 * 
	 * @param the next Clipboard Node
	 ***************************************************************/
	public void setNext(CNode next) {
		this.next = next;
	}

	/***************************************************************
	 * Returns the data in the Clipboard Node
	 * 
	 * @return the data in the Clipboard Node
	 ***************************************************************/
	public Node getTop() {
		return top;
	}

	/***************************************************************
	 * Sets the data for the Clipboard Node
	 * 
	 * @param the data for the Clipboard Node
	 ***************************************************************/
	public void setTop(Node top) {
		this.top = top;
	}

	/***************************************************************
	 * Returns the identity number of the Clipboard Node
	 * 
	 * @return the identity number of the Clipboard Node
	 ***************************************************************/
	public int getClipboardNum() {
		return clipboardNum;
	}

	/***************************************************************
	 * Sets the identity number of the Clipboard Node
	 * 
	 * @param the identity number of the Clipboard Node
	 ***************************************************************/
	public void setClipboardNum(int clipboardNum) {
		this.clipboardNum = clipboardNum;
	}
	
	
}
