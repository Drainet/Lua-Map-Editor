package kalacool.kalaeditor.uibehavior.behavior;

import kalacool.kalaeditor.ui.BasicItem;
import kalacool.kalaeditor.uibehavior.IrregularShape;
import kalacool.kalaeditor.uibehavior.TransformRunner;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Display;

public class RotateBehavior implements IrregularShape{
	private BasicItem basicItem;
	private Display display;
	private Transform transform;
	Region region = new Region();
	int offArray[] = {};
	int regionArray[] = {};
	public float angle = 0;
	private Point displaySize = new Point(100,100);
	
	public RotateBehavior(BasicItem basicItem){

		this.basicItem = basicItem;
		init();
	}
	
	private void init(){
		display = basicItem.getDisplay();
		basicItem.registerTransformRunner(new TransformRunner() {
			@Override
			public void run(PaintEvent paintEvent) {
				if(transform==null)
					transformRotate(angle);
				paintEvent.gc.setTransform(transform);
			}
		});
	}
	private int rounding(double d){
		int inNum = (int)(d*10);
		if(inNum%10>4)
			return (int)d+1;
		else
			return (int)d;
	}
	
	private void transformRotate(float angle){
		double theta = Math.PI*angle/180;
		int widthSin =rounding(basicItem.getRecSize().x*Math.abs(Math.sin(theta)));
		int widthCos =rounding(basicItem.getRecSize().x*Math.abs(Math.cos(theta)));
		int heightSin =rounding(basicItem.getRecSize().y*Math.abs(Math.sin(theta)));
		int heightCos =rounding(basicItem.getRecSize().y*Math.abs(Math.cos(theta)));
		if(transform!=null)
		if(!transform.isDisposed())
		transform.dispose();
		transform = new Transform(display);
		
		if(region!=null||!region.isDisposed())
			region.dispose();
		region = new Region();
		if(angle>=0&&angle<=90){
			transform.translate(heightSin,0);
			int pointArray[] = {0,heightCos,heightSin,0,heightSin+widthCos,widthSin,widthCos,heightCos+ widthSin};
			region.add(pointArray);
			regionArray = pointArray;
		}else if(angle>90&&angle<=180){
			transform.translate(widthCos+heightSin,heightCos);
			int pointArray[] = {0,widthSin,widthCos,0,heightSin+widthCos,heightCos,heightSin,heightCos+ widthSin};
			region.add(pointArray);
			regionArray = pointArray;
		}else if(angle>180&&angle<=270){
			transform.translate(widthCos,heightCos+ widthSin);
			int pointArray[] = {0,heightCos,heightSin,0,heightSin+widthCos,widthSin,widthCos,heightCos+ widthSin};
			region.add(pointArray);
			regionArray = pointArray;
		}else if(angle>270&&angle<360){
			transform.translate(0,widthSin);
			int pointArray[] = {0,widthSin,widthCos,0,heightSin+widthCos,heightCos,heightSin,heightCos+ widthSin};
			region.add(pointArray);
			regionArray = pointArray;
		}
		offArray = regionArray;
		regionArray[0] = regionArray[0] + basicItem.getLocation().x;
		regionArray[2] = regionArray[2] + basicItem.getLocation().x;
		regionArray[4] = regionArray[4] + basicItem.getLocation().x;
		regionArray[6] = regionArray[6] + basicItem.getLocation().x;
		regionArray[1] = regionArray[1] + basicItem.getLocation().y;
		regionArray[3] = regionArray[3] + basicItem.getLocation().y;
		regionArray[5] = regionArray[5] + basicItem.getLocation().y;
		regionArray[7] = regionArray[7] + basicItem.getLocation().y;
		basicItem.setRegion(region);
		
		displaySize.x = widthCos  + heightSin;
		displaySize.y = heightCos + widthSin;
		basicItem.redraw();
		transform.rotate(angle);
	}
	
	public Point getDisplaySize() {
		if(displaySize!=null)
		return displaySize;
		return new Point(100,100);
	}
	
	public void RotateLeft(){
		if(!(angle>=360)){
			if(angle==0){
				angle = 330;
			}else if(angle==30){
				angle = 0;
			}else if(angle==45){
				angle = 30;
			}else if(angle ==60){
				angle = 45;
			}else if(angle ==90){
				angle = 60;
			}else if(angle ==120){
				angle = 90;
			}else if(angle ==135){
				angle = 120;
			}else if(angle ==150){
				angle = 135;
			}else if(angle ==180){
				angle = 150;
			}else if(angle ==210){
				angle = 180;
			}else if(angle ==225){
				angle = 210;
			}else if(angle ==240){
				angle = 225;
			}else if(angle ==270){
				angle = 240;
			}else if(angle ==300){
				angle = 270;
			}else if(angle ==315){
				angle = 300;
			}else if(angle ==330){
				angle = 315;
			}
		}
		transformRotate(angle);
	}
	
	public void RotateRight(){
		if(!(angle>=360)){
			if(angle==0){
				angle = 30;
			}else if(angle==30){
				angle = 45;
			}else if(angle==45){
				angle = 60;
			}else if(angle ==60){
				angle = 90;
			}else if(angle ==90){
				angle = 120;
			}else if(angle ==120){
				angle = 135;
			}else if(angle ==135){
				angle = 150;
			}else if(angle ==150){
				angle = 180;
			}else if(angle ==180){
				angle = 210;
			}else if(angle ==210){
				angle = 225;
			}else if(angle ==225){
				angle = 240;
			}else if(angle ==240){
				angle = 270;
			}else if(angle ==270){
				angle = 300;
			}else if(angle ==300){
				angle = 315;
			}else if(angle ==315){
				angle = 330;
			}else if(angle ==330){
				angle = 0;
			}
		}
			transformRotate(angle);
	}
	
	@Override
	public int[] getRegion() {
		return regionArray;
	}
}
