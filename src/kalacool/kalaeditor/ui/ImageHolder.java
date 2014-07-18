package kalacool.kalaeditor.ui;

import java.util.HashMap;

import org.eclipse.swt.graphics.Image;

public class ImageHolder {

	private HashMap<String,Image> imageMap;
	private static ImageHolder imageHolder;

	public void addImage(String imageName){
		Image image = new Image(null,imageName);
		this.imageMap.put(imageName,image);
	}

	public Image getImage(String imageName){
		if(this.imageMap.get(imageName)==null){
			Image image = new Image(null,imageName);
			this.imageMap.put(imageName,image);
			return image;
		}else
		return this.imageMap.get(imageName);
	}

	private static ImageHolder newInstance(){
		imageHolder = new ImageHolder();
		imageHolder.imageMap =  new HashMap<String,Image>();
		return imageHolder;
	}

	public static ImageHolder getInstance(){
		if(imageHolder!=null)
			return imageHolder;
		else
			return newInstance();
	}
}
