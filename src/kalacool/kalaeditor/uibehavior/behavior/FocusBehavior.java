package kalacool.kalaeditor.uibehavior.behavior;

import java.util.Observable;

import kalacool.kalaeditor.ui.BasicItem;
import kalacool.kalaeditor.uibehavior.MouseDownRunner;
import kalacool.kalaeditor.uibehavior.PaintRunner;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Event;

public class FocusBehavior  extends Observable{
	protected boolean focused = false;
	private BasicItem basicItem;
	private static FocusBehavior focusedBehavior;

	public FocusBehavior(final BasicItem basicItem){
		this.basicItem = basicItem;
		MouseDownRunner mouseDownRunner = new MouseDownRunner(){
			@Override
			public void run(Event event) {
				setFocused();
			}
		};
		basicItem.registerMouseDownRunner(mouseDownRunner);
		
		PaintRunner paintRunner = new PaintRunner(){
			@Override
			public void run(PaintEvent paintEvent) {
				if(focused){
					paintEvent.gc.setLineWidth(5);
					paintEvent.gc.setLineStyle(SWT.LINE_DASH);
					paintEvent.gc.drawRectangle(0,0,basicItem.getRecSize().x-1,basicItem.getRecSize().y-1);
					paintEvent.gc.setLineWidth(1);
					paintEvent.gc.setLineStyle(SWT.LINE_SOLID);
				}
			}
			
		};
		basicItem.registerPaintRunner(paintRunner);
	}

	public void setFocused(){
		focused = true;
		setFocusedItem(this);
		this.basicItem.redraw();
		setChanged();
		notifyObservers(this);
	}

	public void setUnFocused(){
		basicItem.redraw();
		focused = false;
	}

	public static void setFocusedItem(FocusBehavior target){
		if(focusedBehavior!=null){
			if(!(focusedBehavior==target))
				if(!focusedBehavior.basicItem.isDisposed()){
					focusedBehavior.setUnFocused();
				}
		}
		focusedBehavior = target;
		
	}

	public static FocusBehavior getFocusedItem(){
		return focusedBehavior;
	}
	
	public static BasicItem getFocusedBasicItem(){
		return focusedBehavior.basicItem;
	}
	
	public BasicItem getBasicItem(){
		return basicItem;
	}

}
