package Ted;

import java.util.Scanner;

/********************************************************************
 * Runs the TED program through use of the Editor class
 * @author Mitchell Couturier
 * @version 4/01/14
 ********************************************************************/

public class EditorMain {
	public static void main(String [] args){
		Editor ed = new Editor();
		String input;
		
		//repeatedly prompts the user for commands
		Scanner scan = new Scanner(System.in);
		while(true){
			input = scan.nextLine();
			
			//processes the user's command and applies it
			ed.processCommand(input);
		}
	}
}
