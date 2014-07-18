package kalacool.kalaeditor.uibehavior.behavior;


import kalacool.kalaeditor.ui.BasicItem;
import kalacool.kalaeditor.uibehavior.MouseDownRunner;
import kalacool.kalaeditor.uibehavior.MouseMoveRunner;
import kalacool.kalaeditor.uibehavior.MouseUpRunner;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Event;

public class DragBehavior {
	private Point downPoint;
	protected boolean mouseDown = false;
	static private boolean isAlignNet = false;
	public DragBehavior(final BasicItem basicItem){

		MouseDownRunner mouseDownRunner = new MouseDownRunner(){
			@Override
			public void run(Event event) {
				mouseDown = true;
				if(downPoint==null)
					downPoint = new Point(event.x,event.y);
				else{
					downPoint.x = event.x;
					downPoint.y = event.y;
				}
			}};
		basicItem.registerMouseDownRunner(mouseDownRunner);
			
		MouseMoveRunner mouseMoveRunner = new MouseMoveRunner(){
			@Override
			public void run(Event event) {
				if(mouseDown&&downPoint!=null)
					if(!isAlignNet)
					{
						basicItem.setLocation(basicItem.getLocation().x+(event.x-downPoint.x),basicItem.getLocation().y+(event.y-downPoint.y));
					}
					else{
						int locX = basicItem.getLocation().x+(event.x-downPoint.x);
						int locY = basicItem.getLocation().y+(event.y-downPoint.y);
						int transLocX;
						int transLocY;
						if(locX%10>5)
							transLocX = locX - locX%10 + 10;
						else
							transLocX = locX - locX%10;
						if(basicItem.getLocation().y+(event.y-downPoint.y)%10>5)
							transLocY = locY - locY%10 + 10;
						else
							transLocY = locY - locY%10;
						basicItem.setLocation(transLocX,transLocY);
					}
			}};
		basicItem.registerMouseMoveRunner(mouseMoveRunner);
		
		MouseUpRunner mouseUpRunner = new MouseUpRunner(){
			@Override
			public void run(Event event) {
				mouseDown = false;
			}};
		basicItem.registerMouseUpRunner(mouseUpRunner);
		}
	public static void setAlignNet(){
		if(isAlignNet)
			isAlignNet = false;
		else
			isAlignNet = true;
	}

	public static boolean isAlignNet(){
		return isAlignNet;
	}
}
