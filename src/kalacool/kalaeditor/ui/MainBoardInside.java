package kalacool.kalaeditor.ui;

import kalacool.kalaeditor.uibehavior.behavior.GridBehavior;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

public class MainBoardInside extends BasicItem {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	
	GridBehavior gridBehavior;
	public MainBoardInside(Composite parent, int style) {
		super(parent, style);
		gridBehavior = new GridBehavior(this);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	@Override
	public Point getRecSize() {
		return this.getSize();
	}

	@Override
	public Point getDisplaySize() {
		return this.getSize();
	}

}
