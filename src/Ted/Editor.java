package Ted;

import java.util.Scanner;

/********************************************************************
 * Processes the commands entered in the text editor
 * @author Mitchell Couturier
 * @version 3/29/14
 ********************************************************************/

public class Editor implements IEditor{

	/** The list of Nodes used in the Editor program **/
	DoubleLinkedList list;
	
	/** is true if the buffer has been saved to a file since the last changes 
	 * have been made **/
	private boolean isSaved;
	
	/************************************************************
	 * The constructor that instantiates the list
	 ************************************************************/
	public Editor(){
		list = new DoubleLinkedList();
		isSaved = false;
	}
	
	/*************************************************************
	 * Processes the given editor command
	 * 
	 * @param the command entered in the editor
	 *************************************************************/
	public void processCommand (String command){
		String com = "";
		
		//first checks if it is a cut or paste command
		try{
			//looks at the first 3 characters of the String
			//for the command
			com = command.substring(0,3);
		}
		catch(IndexOutOfBoundsException error){
			com = "";
		}
		if(com.equals("cut")){
			int from;
			int to;
			int num;
			try{
				Scanner scan = new Scanner(command.substring(4)).useDelimiter(" ");
				try{ 		
					//scans for the parameters needed for the 'cut' command
					from = Integer.parseInt(scan.next());
					to = Integer.parseInt(scan.next());
					num = Integer.parseInt(scan.next());
				}catch(NumberFormatException error){
					System.out.println("This command is not accepted. Please enter with proper format");
					return;
				}
			}catch(IndexOutOfBoundsException error){
				System.out.println("This command is not accepted. Please enter with proper format");
				return;
			}
			list.cutToClipboard(from, to, num);
			isSaved = false;
		}
		else if(com.equals("pas")){
			int num;
			try{
				Scanner scan = new Scanner(command.substring(4)).useDelimiter(" ");
				try{ 
					//scans for the parameter needed for the 'pas' command
					num = Integer.parseInt(scan.next());
				}catch(NumberFormatException error){
					System.out.println("This command is not accepted. Please enter with proper format");
					return;
				}
			}catch(IndexOutOfBoundsException error){
				System.out.println("This command is not accepted. Please enter with proper format");
				return;
			}
			list.pasteFromClipboard(num);
			isSaved = false;
		}
		
		//Checks for all other commands
		else{
			//looks at the first character only of the String 
			//for the command
			char c = command.charAt(0);
			String data = "";
			
			if(c == 'b'){
				try{
					data = command.substring(2);
					list.insertBeforeCurrent(data);
					isSaved = false;
				}
				catch(IndexOutOfBoundsException error){
					System.out.println("\nYou must insert data after the command\n");
				}
			}
			else if(c == 'i'){
				try{
					data = command.substring(2);
					list.insertAfterCurrent(data);
					isSaved  = false;
				}
				catch(IndexOutOfBoundsException error){
					System.out.println("\nYou must insert data after the command\n");
				}
			}
			else if(c == 'e'){
				try{
					data = command.substring(2);
					list.insertAtEnd(data);
					isSaved = false;
				}
				catch(IndexOutOfBoundsException error){
					System.out.println("\nYou must insert data after the command\n");
				}
			}
			else if(c == 'm'){
				int num;
				try{
					Scanner scan = new Scanner(command.substring(2)).useDelimiter(" ");
					try{ 			
						//scans for parameters needed for the 'm' command, else just
						//uses 1
						num = Integer.parseInt(scan.next());
					}catch(NumberFormatException error){
						num = 1;
					}
				}catch(IndexOutOfBoundsException error){
					num = 1;
				}
				
				list.moveDown(num);
			}
			else if(c == 'u'){
				int num;
				try{
					Scanner scan = new Scanner(command.substring(2)).useDelimiter(" ");
					try{ 
						//scans for parameters needed for the 'u' command, else just
						//uses 1
						num = Integer.parseInt(scan.next());
					}catch(NumberFormatException error){
						num = 1;
					}
				}catch(IndexOutOfBoundsException error){
					num = 1;
				}
				
				list.moveUp(num);
			}
			else if(c == 'r'){
				int num;
				try{
					Scanner scan = new Scanner(command.substring(2)).useDelimiter(" ");
					try{ 
						//scans for parameters needed for the 'r' command, else just
						//uses 1
						num = Integer.parseInt(scan.next());
					}catch(NumberFormatException error){
						num = 1;
					}
				}catch(IndexOutOfBoundsException error){
					num = 1;
				}
				
				list.removeCurrentLine(num);
				isSaved = false;
			}
			else if(c == 'd'){
				int from;
				int to;
				try{
					Scanner scan = new Scanner(command.substring(2)).useDelimiter(" ");
					try{ 
						//scans for the 'from' parameter for the 'd' command, else just
						//uses 0
						from = Integer.parseInt(scan.next());
					}catch(NumberFormatException error){
						from = 0;
					}
					try{
						//scans for the 'to' parameter for the 'd' command, else just
						//uses the size of the list
						to = Integer.parseInt(scan.next());
					}catch(NumberFormatException error){
						to = list.listSize();
					}
				}catch(IndexOutOfBoundsException error){
					from = 0;
					to = list.listSize();
				}
				
				System.out.println(list.display(from, to));
			}
			else if(c == 'c'){
				
				//only executes if it has been saved first
				if (isSaved){
					list.clearAll();
				}else
					System.out.println("\nThe file must be saved before this"
							+ " action can be performed\n");
			}
			else if(c == 's'){
				try{
					data = command.substring(2);
					list.saveFile(data);
					isSaved = true;
				}
				catch(IndexOutOfBoundsException error){
					System.out.println("\nYou must save the file to a specified filename\n");
				}
			}
			else if(c == 'l'){
				
				//only executes if it has been saved first
				if(isSaved){
					try{
						data = command.substring(2);
						list.loadFile(data);
					}
					catch(IndexOutOfBoundsException error){
						System.out.println("\nYou must enter a filename to load\n");
					}
				}else
					System.out.println("\nThe file must be saved before this"
							+ " action can be performed\n");
			}
			else if(c == 'h'){
				System.out.println(list.helpPage());
			}
			else if(c == 'x'){
				
				//only executes if it has been saved first
				if (isSaved){
					System.exit(0);
				}else
					System.out.println("\nThe file must be saved before this"
							+ " action can be performed\n");
			}
		}
	}
	
	/***********************************************************
	 * Returns the line at the given line number
	 * 
	 * @param the line number of the desired line
	 ***********************************************************/
	public String getLine (int lineNbr){
		Node temp = list.getLine(lineNbr);
		if(temp != null){
			return temp.getData();
		}
		return "";
	}
	
	/************************************************************
	 * Returns the data from the current line
	 ************************************************************/
	public String getCurrentLine(){
		
		return list.getCurrent().getData();
	}
	
	/***********************************************************
	 * Returns the size of the list in the buffer
	 * 
	 * @return the number of lines in the list
	 ***********************************************************/
	public int getNumLines(){
		if(list.getTop() == null){
			return 0;
		}else{
			return list.listSize() + 1;
		}
	}
}
