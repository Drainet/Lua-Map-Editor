package kalacool.kalaeditor.ui;

import kalacool.kalaeditor.uibehavior.behavior.DragBehavior;
import kalacool.kalaeditor.uibehavior.behavior.FocusBehavior;
import kalacool.kalaeditor.uibehavior.behavior.ImageBehavior;
import kalacool.kalaeditor.uibehavior.behavior.RotateBehavior;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

public class ClassItem extends BasicItem{

	public ImageBehavior imageBehavior;
	public RotateBehavior rotateBehavior;
	public FocusBehavior focusBehavior;
	public DragBehavior dragBehavior;

	public ClassItem(Composite parent, int style) {
		super(parent, style);
		imageBehavior = new ImageBehavior(this,ImageHolder.getInstance().getImage("shuffle.png"));
		rotateBehavior = new RotateBehavior(this);
		focusBehavior = new FocusBehavior(this);
		dragBehavior = new DragBehavior(this);
//		overlapBehavior = new OverlapBehavior(this);
	}

	@Override
	public Point getRecSize() {
		return imageBehavior.getRecSize();
	}

	@Override
	public Point getDisplaySize() {
		return rotateBehavior.getDisplaySize();
	}

}
