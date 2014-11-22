import java.awt.MouseInfo;

import javax.swing.SwingUtilities;


public class MouseTracker implements Runnable{

	public MouseTracker()
	{

	}

	public void trackMouse()
	{
		System.out.println(MouseInfo.getPointerInfo().getLocation().toString());
	}

	public static void main(String[] args)
	{
		Runnable mouseTrackerRunnable = new MouseTracker();
		Thread mouseTrackerThread = new Thread(mouseTrackerRunnable);
		mouseTrackerThread.start();
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			trackMouse();
		}
	}}
