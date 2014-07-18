package kalacool.kalaeditor.window;

import kalacool.kalaeditor.ui.ImageHolder;
import kalacool.kalaeditor.ui.MainBoard;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MainApplicationWindow {

	protected Shell shlKalaeditor;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainApplicationWindow window = new MainApplicationWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlKalaeditor.open();
		shlKalaeditor.layout();
		while (!shlKalaeditor.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlKalaeditor = new Shell();
		shlKalaeditor.setImage(ImageHolder.getInstance().getImage("KCE.png"));
		shlKalaeditor.setSize(1200, 600);
		shlKalaeditor.setText("KalaEditor");
		
		
		
		MainBoard mainBoard = new MainBoard(shlKalaeditor);
		mainBoard.setBounds(10, 10, 1164, 541);

	}
}
