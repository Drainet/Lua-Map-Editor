package kalacool.kalaeditor.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ScrollBar;

public class MainBoard extends ScrolledComposite{

	private ScrollBar xScrollBar;
	private ScrollBar yScrollBar;

	public MainBoard(Composite parent) {
		super(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		xScrollBar = this.getHorizontalBar();
		yScrollBar = this.getVerticalBar();
		
		xScrollBar.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		yScrollBar.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		MainBoardInside mainBoardInside = new MainBoardInside(this,SWT.PUSH | SWT.TRANSPARENT);
		this.setContent(mainBoardInside);
		mainBoardInside.setBounds(0, 0,5000,5000);
	}

}
