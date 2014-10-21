package Ted;

import static org.junit.Assert.*;

import org.junit.Test;

/****************************************************************************
 * Tests the Editor Class for proper functionality of the Text Editor Program
 * @author Mitchell Couturier
 * @version 4/01/14
 ****************************************************************************/

public class EditorTest {

	/************************************************************************
	 * Tests the 'i' command for basic functionality
	 ************************************************************************/
	@Test
	public void testInsertAfterCurrent() {
		Editor ed = new Editor();
		ed.processCommand("i This is line 0");
		ed.processCommand("i This is line 1");
		ed.processCommand("i This is line 2");
		assertEquals("This is line 0", ed.getLine(0));
		assertEquals("This is line 1", ed.getLine(1));
		assertEquals("This is line 2", ed.getCurrentLine());
	}
	
	/************************************************************************
	 * Tests the 'b' command for basic functionality
	 ************************************************************************/
	@Test
	public void testInsertBeforeCurrent() {
		Editor ed = new Editor();
		ed.processCommand("i This is line 3");
		ed.processCommand("b This is line 0");
		ed.processCommand("i This is line 2");
		ed.processCommand("b This is line 1");
		assertEquals("This is line 0", ed.getLine(0));
		assertEquals("This is line 1", ed.getLine(1));
	}
	
	/************************************************************************
	 * Tests the 'm' command for moving the current indicator 
	 * down one position
	 ************************************************************************/
	@Test
	public void testMoveDownOne() {
		Editor ed = new Editor();
		ed.processCommand("i This is line 2");
		ed.processCommand("b This is line 1");
		ed.processCommand("b This is line 0");
		ed.processCommand("m");
		assertEquals("This is line 1", ed.getCurrentLine());
	}
	
	/************************************************************************
	 * Tests the 'm' command for moving the current indicator
	 * down multiple positions
	 ************************************************************************/
	@Test
	public void testMoveDownTwo() {
		Editor ed = new Editor();
		ed.processCommand("i This is line 2");
		ed.processCommand("b This is line 1");
		ed.processCommand("b This is line 0");
		ed.processCommand("m 2");
		assertEquals("This is line 2", ed.getCurrentLine());
	}
	
	/************************************************************************
	 * Tests the 'u' command for moving the current indicator
	 * up one position
	 ************************************************************************/
	@Test
	public void testMoveUpOne() {
		Editor ed = new Editor();
		ed.processCommand("i This is line 0");
		ed.processCommand("i This is line 1");
		ed.processCommand("i This is line 2");
		ed.processCommand("u");
		assertEquals("This is line 1", ed.getCurrentLine());
	}
	
	/************************************************************************
	 * Tests the 'u' command for moving the current indicator
	 * up multiple positions
	 ************************************************************************/
	@Test
	public void testMoveUpTwo() {
		Editor ed = new Editor();
		ed.processCommand("i This is line 0");
		ed.processCommand("i This is line 1");
		ed.processCommand("i This is line 2");
		ed.processCommand("u 2");
		assertEquals("This is line 0", ed.getCurrentLine());
	}
	
	/************************************************************************
	 * Tests the 'e' command for basic functionality
	 ************************************************************************/
	@Test
	public void testInsertAtEnd() {
		Editor ed = new Editor();
		ed.processCommand("i This is line 0");
		ed.processCommand("i This is line 1");
		ed.processCommand("i This is line 2");
		
		//moves the current indicator to the top
		ed.processCommand("u 2");
		
		//should still insert line at the bottom
		ed.processCommand("e This is line 3");
		assertEquals("This is line 3", ed.getLine(3));
	}

	/************************************************************************
	 * Tests the 'r' command for removing the current line only
	 ************************************************************************/
	@Test
	public void testRemoveCurrentLine() {
		Editor ed = new Editor();
		ed.processCommand("i This is line 0");
		ed.processCommand("i This line is removed");
		
		//removes the last line only
		ed.processCommand("r");
		assertEquals("This is line 0", ed.getCurrentLine());
		assertEquals(1, ed.getNumLines());
	}
	
	/************************************************************************
	 * Tests the 'r' command for removing multiple lines including the current
	 ************************************************************************/
	@Test
	public void testRemoveMultipleLines() {
		Editor ed = new Editor();
		ed.processCommand("i This is line 0");
		ed.processCommand("b This line is removed");
		ed.processCommand("b This line is removed");
		ed.processCommand("b This line is removed");
		
		//removes the last 3 lines
		ed.processCommand("r 3");
		assertEquals("This is line 0", ed.getCurrentLine());
		assertEquals(1, ed.getNumLines());
	}
	
	/************************************************************************
	 * Tests the 'd' command for displaying the all lines
	 ************************************************************************/
	@Test
	public void testDisplayFull(){
		Editor ed = new Editor();
		ed.processCommand("i This is line 0");
		ed.processCommand("i This is line 1");
		ed.processCommand("i This is line 2");
		
		//displays all lines into the console window
		System.out.println("\tAll 3 lines");
		ed.processCommand("d");
	}
	
	/************************************************************************
	 * Tests the 'd' command for displaying only some lines
	 * (Displays the first 2 lines)
	 ************************************************************************/
	@Test
	public void testDisplayPartialOne(){
		Editor ed = new Editor();
		ed.processCommand("i This is line 0");
		ed.processCommand("i This is line 1");
		ed.processCommand("i This is line 2");
		
		//displays the first two lines only into the console window
		System.out.println("\tFirst 2 lines");
		ed.processCommand("d 0 1");
	}
	
	/************************************************************************
	 * Tests the 'd' command for displaying only some lines
	 * (Displays the first 2 lines)
	 ************************************************************************/
	@Test
	public void testDisplayPartialTwo(){
		Editor ed = new Editor();
		ed.processCommand("i This is line 0");
		ed.processCommand("i This is line 1");
		ed.processCommand("i This is line 2");
		
		//displays the last 2 lines only into the console window
		System.out.println("\tLast 2 lines");
		ed.processCommand("d 1 2");
	}
	
	/************************************************************************
	 * Tests the 'c' command for basic functionality
	 ************************************************************************/
	@Test
	public void testClear(){
		Editor ed = new Editor();
		ed.processCommand("i This is line 0");
		ed.processCommand("i This is line 1");
		ed.processCommand("i This is line 2");
		ed.processCommand("s test");
		ed.processCommand("c");
		assertEquals(null, ed.getLine(0));
	}
	
	/************************************************************************
	 * Tests the 'c' command for when the buffer has not been recently
	 * saved to a file
	 * (Should not process command)
	 ************************************************************************/
	@Test
	public void testClearIfNotSaved(){
		Editor ed = new Editor();
		ed.processCommand("i This is line 0");
		ed.processCommand("i This is line 1");
		ed.processCommand("i This is line 2");
		System.out.println("1: Error message should appear below");
		ed.processCommand("c");
		assertEquals(3, ed.getNumLines());
	}
	
	/************************************************************************
	 * Tests the 's' and 'l' command for basic functionality
	 ************************************************************************/
	@Test
	public void testSaveLoad(){
		Editor ed = new Editor();
		ed.processCommand("i This is line 0");
		ed.processCommand("i This is line 1");
		
		//saves the buffer and then reloads it back into the buffer
		ed.processCommand("s test");
		ed.processCommand("l test");
		
		//checks if the loaded file is the same
		assertEquals(2, ed.getNumLines());
		assertEquals("This is line 0", ed.getLine(0));
		assertEquals("This is line 1", ed.getLine(1));
	}
	
	/************************************************************************
	 * Tests the 'l' command for when the buffer has not recently been saved
	 * to a file
	 * (Should not process command)
	 ************************************************************************/
	@Test
	public void testLoadIfNotSaved(){
		Editor ed = new Editor();
		ed.processCommand("i This is just for testing");
		System.out.println("2: Error message should appear below");
		ed.processCommand("l test");
		assertEquals("This is just for testing", ed.getLine(0));
	}
	
	/************************************************************************
	 * Tests the 'cut' and 'pas' command for basic functionality
	 * (Makes one clipboard)
	 ************************************************************************/
	@Test
	public void testCutAndPasteOne(){
		Editor ed = new Editor();
		ed.processCommand("i This is line 0");
		ed.processCommand("i This is line 1");
		ed.processCommand("i This is line 2");
		
		//cuts all lines out of the buffer
		ed.processCommand("cut 0 2 1");
		assertEquals(0, ed.getNumLines());
		
		//pastes all line back into the buffer
		ed.processCommand("pas 1");
		assertEquals(3, ed.getNumLines());
		assertEquals("This is line 0", ed.getLine(0));
		assertEquals("This is line 1", ed.getLine(1));
		assertEquals("This is line 2", ed.getLine(2));
	}
	
	/************************************************************************
	 * Tests the 'cut' and 'pas' command for basic functionality
	 * (Makes 3 clipboards)
	 ************************************************************************/
	@Test
	public void testCutAndPasteTwo(){
		Editor ed = new Editor();
		ed.processCommand("i This line is cut");
		ed.processCommand("i This line is cut");
		ed.processCommand("i This is line 0");
		ed.processCommand("i This is line 1");
		ed.processCommand("i This is line 2");
		ed.processCommand("i This line is cut");
		
		//cuts the first 2 lines out
		ed.processCommand("cut 0 1 1");
		assertEquals(4, ed.getNumLines());
		
		//cuts the next 3 lines out
		ed.processCommand("cut 0 2 2");
		assertEquals(1, ed.getNumLines());
		
		//cuts the only line remaining out
		ed.processCommand("cut 0 0 3");
		assertEquals(0, ed.getNumLines());
		
		//pastes clipboard 2 (3 lines) back in
		ed.processCommand("pas 2");
		assertEquals(3, ed.getNumLines());
		assertEquals("This is line 0", ed.getLine(0));
		assertEquals("This is line 1", ed.getLine(1));
		assertEquals("This is line 2", ed.getLine(2));
	}
	
	/************************************************************************
	 * Tests the 'x' command for when the buffer has not been recently
	 * saved to a file
	 * (Should not process command)
	 ************************************************************************/
	@Test
	public void testExitIfNotSaved(){
		Editor ed = new Editor();
		System.out.println("3: Error message should appear below");
		ed.processCommand("x");
	}
}
