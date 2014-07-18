package kalacool.kalaeditor.ui;

import java.util.LinkedList;

import kalacool.kalaeditor.uibehavior.MouseDownRunner;
import kalacool.kalaeditor.uibehavior.MouseMoveRunner;
import kalacool.kalaeditor.uibehavior.MouseUpRunner;
import kalacool.kalaeditor.uibehavior.PaintRunner;
import kalacool.kalaeditor.uibehavior.TransformRunner;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public abstract class BasicItem extends Canvas{
	
	private BasicItem basicItem = this;
	private static int count = 0;
	private LinkedList<MouseDownRunner> mouseDownRunnerList = new LinkedList<MouseDownRunner>();
	private LinkedList<MouseMoveRunner> mouseMoveRunnerList = new LinkedList<MouseMoveRunner>();
	private LinkedList<MouseUpRunner>   mouseUpRunnerList   = new LinkedList<MouseUpRunner>();
	private LinkedList<PaintRunner>     paintRunnerList     = new LinkedList<PaintRunner>();
	private LinkedList<TransformRunner>     transformRunnerList     = new LinkedList<TransformRunner>();
		
	public BasicItem(Composite parent, int style) {
		super(parent, style);
		setCount(getCount() + 1);
		
		this.addListener(SWT.MouseDown,new Listener(){
			@Override
			public void handleEvent(Event arg0) {
				for(MouseDownRunner runner : mouseDownRunnerList){
					runner.run(arg0);
				}
			}});
		this.addListener(SWT.MouseMove,new Listener(){
			@Override
			public void handleEvent(Event arg0) {
				for(MouseMoveRunner runner : mouseMoveRunnerList){
					runner.run(arg0);
				}
			}});
		this.addListener(SWT.MouseUp,new Listener(){
			@Override
			public void handleEvent(Event arg0) {
				for(MouseUpRunner runner : mouseUpRunnerList){
					runner.run(arg0);
				}
			}});
		this.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent arg0) {

				for(TransformRunner runner:transformRunnerList){
					runner.run(arg0);
				}
				for(PaintRunner runner:paintRunnerList){
					runner.run(arg0);
				}
				if(getDisplaySize()!=null)
					basicItem.setSize(getDisplaySize());
				else
					basicItem.setSize(100,100);

			}
		});
	}
	
	public void registerMouseDownRunner(MouseDownRunner mouseDownRunner){
		mouseDownRunnerList.add(mouseDownRunner);
	}

	public void registerMouseMoveRunner(MouseMoveRunner mouseMoveRunner){
		mouseMoveRunnerList.add(mouseMoveRunner);
	}

	public void registerMouseUpRunner(MouseUpRunner mouseUpRunner){
		mouseUpRunnerList.add(mouseUpRunner);
	}
	
	public void registerPaintRunner(PaintRunner paintRunner){
		paintRunnerList.add(paintRunner);
	}
	
	public void registerTransformRunner(TransformRunner transformRunner){
		transformRunnerList.add(transformRunner);
	}
	
	public abstract Point getRecSize();
	
	public abstract Point getDisplaySize();

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		BasicItem.count = count;
	}
	
	
}
