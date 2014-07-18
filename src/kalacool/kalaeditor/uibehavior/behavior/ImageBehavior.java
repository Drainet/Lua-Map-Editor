package kalacool.kalaeditor.uibehavior.behavior;

import kalacool.kalaeditor.ui.BasicItem;
import kalacool.kalaeditor.uibehavior.PaintRunner;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

public class ImageBehavior {
	private Float scale = (float) 1;
	private Image mImage;
	public ImageBehavior(final BasicItem basicItem,Image image){
		mImage = image;
		basicItem.setSize((int)(mImage.getImageData().width*scale*1/2),(int)(mImage.getImageData().height*scale*1/2));
		PaintRunner paintRunner = new PaintRunner(){
			@Override
			public void run(PaintEvent paintEvent) {
				
				paintEvent.gc.drawImage(mImage,
						                0,0, mImage.getImageData().width             , mImage.getImageData().height, 
						                0,0, (int)(mImage.getImageData().width*scale*1/2), (int)(mImage.getImageData().height*scale*1/2));
		
			}
		};
		basicItem.registerPaintRunner(paintRunner);
	}
	
	public Point getRecSize(){
		if(mImage!=null)
			return new Point((int)(mImage.getImageData().width*scale*1/2), (int)(mImage.getImageData().height*scale*1/2));
		return null;
	}
}
