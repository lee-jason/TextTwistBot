import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Snapshot extends MrRoboto{

	//image of game window only
	BufferedImage gameWindowImage;

	public Snapshot()
	{
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			System.out.println("AWT exception on creation of Snapshot robot...");
		}
	}

	private Coordinates findStartingPoint()
	{
		//scan a square of 1000 x 1000 to see if the upper left hand corner pixels are the right shade of blue
		BufferedImage largeScreenSample = robot.createScreenCapture
		(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())); 

		//scan all pixels in this grid to find the magic matching pixels the blue ones on the upper left hand corner
		for(int i = 0; i < Toolkit.getDefaultToolkit().getScreenSize().width; i++)
		{
			for(int j = 0; j < Toolkit.getDefaultToolkit().getScreenSize().height; j++)
			{
				//if the first 2 pixels match, then its the correct upper let
				if(largeScreenSample.getRGB(i, j) == Colors.STARTING_INDEX_BLUE.colorNum &&
						largeScreenSample.getRGB(i + 1, j + 1) == Colors.SECOND_INDEX_BLUE.colorNum)
				{
					return new Coordinates(i, j);
				}
			}
		}
		return null;
	}

	private BufferedImage snapshotGameScreen()
	{
		Coordinates startCoord = findStartingPoint();
		if(startCoord != null)
		{
			gameWindowSize.x = startCoord.getX();
			gameWindowSize.y = startCoord.getY();
			gameWindowImage = robot.createScreenCapture(gameWindowSize);
			System.out.println(startCoord);
			return gameWindowImage;
		}

		System.out.println("Can't find starting game screen");
		System.exit(-1);
		return null;
	}

	public char[] findLetters()
	{
		snapshotGameScreen();
		activateWindow();
		//hold up the robot because he's clicking then typing too fast
		robot.delay(50);

		List<Character> lettersInGram = new ArrayList<Character>();

		//sort the available letters three times
		//we do this because the same letter might appear more than three times.
		for(int i = 0; i < 3; i++){
			for(int charCode = 65; charCode < 91; charCode++)
			{
				robot.keyPress(charCode);
				robot.delay(10);
			}
		}
		

		//enter to send them back to the bottom
		robot.keyPress(KeyEvent.VK_ENTER);
		//wait to allow all the letters to go back to their spaces
		robot.delay(160);
		


		//each sphere square is w:54, h:45
		//centers for first letter exist in x:212 y:194
		//add 54 for each subsequent letter

		//limit 6 for the total amt of letters in anagram
		while(lettersInGram.size() < 6){
			//alphaCounter stores the current char
			for(int alphaCounter = 65; alphaCounter < 91 && lettersInGram.size() < 6; alphaCounter++){
				robot.keyPress(alphaCounter);
				robot.delay(160);
				gameWindowImage = robot.createScreenCapture(gameWindowSize);
				
				if(gameWindowImage.getRGB(212 + 50*lettersInGram.size(), 194) == Colors.ORANGE.colorNum ||
						gameWindowImage.getRGB(212 + 50*lettersInGram.size(), 194) == Colors.YELLOW.colorNum)
				{
					System.out.println("letter found " + (char)alphaCounter);
					lettersInGram.add(lettersInGram.size(), (char)alphaCounter);
				}
			}
		}
		
		return ListToCharPrimitiveArr(lettersInGram);

		
//		
//		for(int i = 0; i < 6; i++)
//		{
//			while(alphaCounter < 91)
//			{
//				//account for double letters
//				//when a double letter is hit, also increment i to let it keep track
//				//of where we are
//				for(int j = 0; j < 2; j++)
//				{
//					robot.keyPress(alphaCounter);
//					robot.delay(160);
//					gameWindowImage = robot.createScreenCapture(gameWindowSize);
//
//					//another i check to prevent checking for incorrect image coordinates
//					//when there are double letters
//					if(i < 6)
//					{
//						System.out.println(gameWindowImage.getRGB(212 + 52*i, 194));
//						if(gameWindowImage.getRGB(212 + 52*i, 194) == Colors.ORANGE.colorNum ||
//								gameWindowImage.getRGB(212 + 52*i, 194) == Colors.YELLOW.colorNum)
//						{
//							System.out.println("letter found" + (char)alphaCounter);
//							//if match move up index
//							lettersInGram.add(i, (char)alphaCounter);
//							i++;
//							if(i >= 6)
//							{
//								robot.keyPress(KeyEvent.VK_ENTER);
//								return lettersInGram;
//							}
//						}
//					}
//				}
//				//no need to continue scanning if the last letter has been found
//
//				alphaCounter++;
//			}
//
//		}
//
//		//now go through the alphabet again
//		//check if a yellow or orange pixel pops up, 
//		//if it does, the letter is in the anagram
//
//		return lettersInGram;
	}
	
	private char[] ListToCharPrimitiveArr(List<Character> charList){
		char[] charArr = new char[charList.size()];
		for(int i = 0 ; i < charList.size(); i++){
			charArr[i] = charList.get(i);
		}
		return charArr;
	}


}
