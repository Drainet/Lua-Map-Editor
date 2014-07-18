package kalacool.kalaeditor.uibehavior.behavior;



import kalacool.kalaeditor.ui.BasicItem;
import kalacool.kalaeditor.uibehavior.PaintRunner;

import org.eclipse.swt.events.PaintEvent;

public class GridBehavior {
	
	public GridBehavior(final BasicItem basicItem){
		basicItem.registerPaintRunner(new PaintRunner() {
			
			@Override
			public void run(PaintEvent paintEvent) {
				for(int i = 0 ;i<=basicItem.getSize().x;i=i+10){
					if(i%640==0)
						paintEvent.gc.setAlpha(500);
					paintEvent.gc.drawLine(i,basicItem.getSize().y,i,0);
					paintEvent.gc.setAlpha(30);
				}
				for(int i = 0 ;i<=basicItem.getSize().y;i=i+10){
					if(i%360==0)
						paintEvent.gc.setAlpha(500);
					paintEvent.gc.drawLine(0,i,basicItem.getSize().x,i);
					paintEvent.gc.setAlpha(30);
				}	
			}
		});
	}

}
