import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class InputAnswers extends MrRoboto{

	private final int KEY_DELAY = 25;
	
	public InputAnswers()
	{
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void typeInAnswer(String word)
	{
		System.out.println("typing: " + word);
		char[] charArray = word.toUpperCase().toCharArray();
		for(int i = 0; i < charArray.length; i++)
		{
			
			robot.keyPress(charArray[i]);
			robot.delay(KEY_DELAY);
		}
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(KEY_DELAY);
		
		//NOW! do it agian.. but add an S at the end..
		for(int i = 0; i < charArray.length; i++)
		{
			
			robot.keyPress(charArray[i]);
			robot.delay(KEY_DELAY);
		}
		robot.keyPress(KeyEvent.VK_S);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(KEY_DELAY);
	}
	
	public void typeInAnswers(List<String> wordList)
	{
		for(int i = 0; i < wordList.size(); i++)
		{
			typeInAnswer(wordList.get(i));
		}
	}
	
	public void typeInAnswers(Collection<String> wordSet)
	{
		Iterator<String> setIterator = wordSet.iterator();
		while(setIterator.hasNext())
		{
			typeInAnswer(setIterator.next());
		}
	}
}
