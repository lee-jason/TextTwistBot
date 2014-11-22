import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;


public abstract class MrRoboto {


	//game window size is w:500 h:400
	Rectangle gameWindowSize = new Rectangle(0, 0, 500, 400);

	//spheres start at pos  x:159, y:181
	//each sphere square is w:54, h:45
	
	Robot robot;
	
	
	//click inside window frame
	public void activateWindow()
	{
		robot.mouseMove(gameWindowSize.x, gameWindowSize.y);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
}
