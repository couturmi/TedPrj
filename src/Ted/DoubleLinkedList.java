package Ted;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/********************************************************************
 * Creates two Linked Lists that are interconnected with each other
 * @author Mitchell Couturier
 * @version 3/29/14
 ********************************************************************/

public class DoubleLinkedList {
	
	/** The first Node in the LinkedList that contains all the other Nodes **/
	private Node top;
	
	/** Represents the current line that the user is editing **/
	private Node current;
	
	/** The first CNode in the Clipboard list **/
	private CNode first;
	
	/*******************************************************************
	 * A constructor that starts the list at null
	 *******************************************************************/
	public DoubleLinkedList(){
	}

	/*******************************************************************
	 * Returns the line of the current line indicator
	 * 
	 * @return the line of the current line indicator
	 *******************************************************************/
	public Node getCurrent(){
		return current;
	}
	
	/*******************************************************************
	 * Returns the first line
	 * 
	 * @return the first line
	 *******************************************************************/
	public Node getTop(){
		return top;
	}
	
	/*******************************************************************
	 * Inserts a sentence before the current line
	 * 
	 * @param a String that contains the data for the line
	 *******************************************************************/
	public void insertBeforeCurrent (String data){
		Node temp = new Node();
		temp.setNext(null);
		temp.setPrev(null);
		temp.setData(data);
		
		//if the list is currently empty
		if (top == null){
			top = temp;
			current = temp;
		}
		else{
			temp.setNext(current);
			temp.setPrev(current.getPrev());
			if(temp.getPrev() != null)
				current.getPrev().setNext(temp);
			else
				top = temp;
			current.setPrev(temp);
			current = temp;
		}
	}
	
	/******************************************************************
	 * Inserts a sentence after the current line
	 * 
	 * @param a String that contains the data for the line
	 ******************************************************************/
	public void insertAfterCurrent (String data){
		Node temp = new Node();
		temp.setNext(null);
		temp.setPrev(null);
		temp.setData(data);
		
		//if the list is currently empty
		if (top == null){
			top = temp;
			current = temp;
		}
		else{
			temp.setNext(current.getNext());
			temp.setPrev(current);
			if(temp.getNext() != null){
				current.getNext().setPrev(temp);
			}
			current.setNext(temp);
			current = temp;
		}
	}
	
	/****************************************************************
	 * Inserts a sentence after the last line; make the inserted 
	 * line the current line
	 * 
	 * @param a String that contains the data for the line
	 ****************************************************************/
	public void insertAtEnd(String data){
		//moves the current indicator to the end of the list
		if(current != null){
			while(current.getNext() != null){
				current = current.getNext();
			}
		
		}	
			//then inserts the data
			insertAfterCurrent(data);	
	}
	
	/***********************************************************
	 * Moves the current line indicator up
	 * 
	 * @param the number of positions to move the current indicator
	 *  up
	 ***********************************************************/
	public void moveUp(int num){
		if(current != null){
			//moves current up until num = 0
			while(num != 0){
				if(current.getPrev() != null){
					current = current.getPrev();
				}
				num--;
			}
		}
	}
	
	/**********************************************************
	 * Moves the current line indicator down
	 * 
	 * @param the number of positions to move current indicator 
	 * down
	 **********************************************************/
	public void moveDown(int num){
		if(current != null){
			
			//moves current down until num = 0
			while(num != 0){
				if(current.getNext() != null){
					current = current.getNext();
				}
				num--;
			}
		}
	}
	
	/**********************************************************
	 * Removes the current line
	 * 
	 * @param the number of lines to remove
	 **********************************************************/
	public void removeCurrentLine(int num){
		while (num != 0){
			Node temp = new Node();
			temp.setNext(current.getNext());
			temp.setPrev(current.getPrev());
			
			//sets the LinkedList's pointers to point around the current line,
			//so that it removes it from the list
			if(current.getPrev() != null){
				current.getPrev().setNext(temp.getNext());
			}else{
				top = current.getNext();
			}
			if(current.getNext() != null){
				current.getNext().setPrev(temp.getPrev());
			}
			
			if(current.getNext() != null){
				current = current.getNext();
			}else if(current.getPrev() != null){
				current = current.getPrev();
			}else{
				current = top;
			}
			num--;
		}
	}
	
	/**********************************************************
	 * Clears all lines in the buffer
	 **********************************************************/
	public void clearAll(){
		top = new Node();
		current = top;
	}
	
	/************************************************************
	 * Save the contents to the specified text file
	 * 
	 * @param the name of the file the user is saving
	 ************************************************************/
	public void saveFile(String fileName){
		PrintWriter out = null;
		try{
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
		}
		catch(IOException e){
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
		
		Node temp = top;
		while(temp != null){
				out.print(temp.getData() + "::");
				temp = temp.getNext();
		}
		out.close();
	}
	
	/**********************************************************************
	 * Load the contents of the specified text file into the editor buffer
	 * 
	 * @param the name of the file the user is loading
	 **********************************************************************/
	public void loadFile(String fileName){
		try{
			Scanner fileReader = new Scanner(new File(fileName)).useDelimiter("::");
			
			//goes through the file to recreate a LinkedList
			top = new Node(fileReader.next(), null, null);
			current = top;
			while(fileReader.hasNext()){
				current.setNext(new Node(fileReader.next(), current, null));
				current = current.getNext();
			}
			//sets the current line to the first line
			current = top;
		}	
		
		catch(FileNotFoundException error){
			System.out.println("File not found.");
			throw new IllegalArgumentException();
		}
		
		catch(IOException error){
			System.out.println("Oops! Something went wrong.");
			throw new IllegalArgumentException();
		}
	}
	
	/*************************************************************
	 * Displays a help page of editor commands
	 *************************************************************/
	public String helpPage(){
		String d ="";
		
		d += "\t COMMANDS";
		d += "\n\"b <sentence>\": Insert sentence before the current line";
		d += "\n\"i <sentence>\": Insert sentence after the current line";
		d += "\n\"e <sentence>\": Insert sentence after the last line";
		d += "\n\"m\": Move the current line indicator down 1 position";
		d += "\n\"m #\": Move the current line indicator down # positions";
		d += "\n\"u\": Move the current line indicator up 1 position";
		d += "\n\"u #\": Move the current line indicator up # positions";
		d += "\n\"r\": Remove the current line";
		d += "\n\"r #\": Remove # lines starting at the current line";
		d += "\n\"d\": Display all lines in the buffer";
		d += "\n\"d # *\": Display from line # to *(inclusive)";
		d += "\n\"c\": Clear all lines in the buffer";
		d += "\n\"s <filename>\": Save the contents to a specified text file";
		d += "\n\"l <filename>\": Load the contents of the specified text file into"
				+ "\n\teditor buffer, replacing the current contents";
		d += "\n\"cut # $ *\": Cut lines from the file from # to $ into clipboard *";
		d += "\n\"pas *\": Paste clipboard * before the current line position";
		d += "\n\"h\": Display this help page";
		d += "\n\"x\": Exit the editor\n\n";
		
		return d;
	}
	
	/*************************************************************************
	 * Displays the entire LinkedList and shows all of the lines of text
	 * with line numbers. Also shows the current line indicator as a ">".
	 * 
	 * @param starting line that will display
	 * @param ending line that will display
	 *************************************************************************/
	
	public String display(int from, int to){
		String d ="";
		Node temp = getLine(from);
		
		//if 'to' is larger than the list, then it sets it to the list size
		if(to > listSize()){
			to = listSize();
		}
		
		while(from <= to){
			//goes through each Node in the list and prints it out
			if(temp != null){
				if(temp == current){
					//displays which line is the current line
					d += ">" + from + ": "+ temp.getData() + "\n";
				}else{
					d += " " + from+ ": "+ temp.getData() + "\n";
				}
					temp = temp.getNext();
			}
			from++;
		}
		return d;
	}
	
	/***********************************************************************
	 * Cut lines from the file from 'from' to 'to' into clipboard 'cNum'
	 * 
	 * @param cuts from this line
	 * @param cuts to this line
	 * @param clipboard identity number
	 ***********************************************************************/
	public void cutToClipboard(int from, int to, int cNum){
		if(top == null){
			System.out.println("There is nothing in the buffer to cut");
			return;
		}
		Node temp = new Node();
		current = temp;
		int num = from;
		//if 'to' is larger than the list, then it sets it to the list size
		if(to > listSize()){
			to = listSize();
		}

		//creates a new list in which the exact data is copied
		current.setData(getLine(num).getData());
		num++;
		while(num <= to){
			insertAfterCurrent(getLine(num).getData());
			num++;
		}
		
		//adds the data to the Clipboard
		if(first == null){
			first = new CNode();
			first.setClipboardNum(cNum);
			first.setTop(temp);
		}else{
			CNode currentTemp =first;
			
			//goes to the end of the Clipboard list
			while (currentTemp.getNext() != null){
				currentTemp = currentTemp.getNext();
			}
			
			//sets the data into a CNode
			CNode Ctemp = new CNode();
			Ctemp.setClipboardNum(cNum);
			Ctemp.setTop(temp);
			
			//puts the CNode at the end of the Clipboard list
			currentTemp.setNext(Ctemp);
		}
		
		//removes the cut portion from the buffer
		current = getLine(from);
		removeCurrentLine(to-from+1);
	}
	
	/*******************************************************************
	 * Paste Clipboard * before the current line position
	 * 
	 * @param the clipboard identity number
	 *******************************************************************/
	public void pasteFromClipboard(int cNum){
		CNode temp = first;
		if(first == null){
			System.out.println("There is nothing on the clipboard to paste");
			return;
		}
		while(temp != null && temp.getClipboardNum() != cNum){
			temp = temp.getNext();
		}
		
		//takes the data from the Clipboard
		Node temp2 = temp.getTop();
		
		//inserts the Clipboard before the current indicator
		insertBeforeCurrent(temp2.getData());
		temp2 = temp2.getNext();
		while(temp2 != null){
			insertAfterCurrent(temp2.getData());
			temp2 = temp2.getNext();
		}
		
		//clears the used Clipboard
		temp = null;
	}
	
	/*******************************************************************
	 * returns the Node at location lineNbr
	 * 
	 * @param the line number that the Node is located at
	 *******************************************************************/
	public Node getLine(int lineNbr){
		Node temp = top;
		int num = 0;
		
		//goes through each Node in the list to find the desired Node
		while(temp!= null){
			if(num == lineNbr){
				return temp;
			}else{
				temp = temp.getNext();
				num++;
			}
		}
		return null;
	}
	
	/****************************************************************
	 * returns the number of the final position in the list
	 ****************************************************************/
	public int listSize(){
		int size = 0;
		Node temp = top;
		if(temp != null){
			while(temp.getNext() != null){
				temp = temp.getNext();
				size++;
			}
		}
		return size;
	}
}
