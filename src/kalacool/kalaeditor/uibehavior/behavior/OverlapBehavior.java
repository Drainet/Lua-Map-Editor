package kalacool.kalaeditor.uibehavior.behavior;

import java.util.Collections;
import java.util.LinkedList;

import kalacool.kalaeditor.ui.BasicItem;
import kalacool.kalaeditor.uibehavior.MouseUpRunner;

import org.eclipse.swt.widgets.Event;

public class OverlapBehavior implements Comparable<OverlapBehavior>{
	
	private int sequence = 0;
	private BasicItem basicItem;
	private LinkedList<OverlapBehavior> frontIntersectItemList = new LinkedList<OverlapBehavior>();
	private LinkedList<OverlapBehavior> backIntersectItemList  = new LinkedList<OverlapBehavior>();
	
	
	private LinkedList<OverlapBehavior> overlapList; 
	

	public OverlapBehavior(BasicItem basicItem,LinkedList<OverlapBehavior> overlapList,int sequence){
		init(basicItem,overlapList,sequence);
	}
	
	public OverlapBehavior(BasicItem basicItem,LinkedList<OverlapBehavior> overlapList){
		if(overlapList.isEmpty()){
			init(basicItem,overlapList,1);
		}else{
			Collections.sort(overlapList);
			init(basicItem,overlapList,overlapList.get(overlapList.size()-1).sequence+1);
		}
	}
	
	void init(BasicItem basicItem,LinkedList<OverlapBehavior> overlapList,int sequence){
		this.basicItem = basicItem;
		this.overlapList = overlapList;
		this.sequence = sequence;
		overlapList.add(this);
		Collections.sort(overlapList);
		
		
		if(overlapList.indexOf(this)==0){
			if(!(overlapList.size()<=1)){
				basicItem.moveBelow(overlapList.get(1).basicItem);
			}
		}else{
			basicItem.moveAbove(overlapList.get(overlapList.indexOf(this)-1).basicItem);
		}
		
		basicItem.registerMouseUpRunner(new MouseUpRunner() {
			
			@Override
			public void run(Event event) {
				checkIntersectItems();
				
			}
		});
	}
	

	public void checkIntersectItems(){
		frontIntersectItemList.clear();
		backIntersectItemList.clear();
		for(OverlapBehavior behavior:overlapList){
			if(!behavior.basicItem.equals(this.basicItem)){
				if(this.basicItem.getBounds().intersects(behavior.basicItem.getBounds())){
					if(behavior.sequence>this.sequence){
						frontIntersectItemList.add(behavior);
					}else{
						backIntersectItemList.add(behavior);
					}
				}
			}
		}
		Collections.sort(backIntersectItemList);
		Collections.sort(frontIntersectItemList);
	}
	
	public void refreshList(){
		for(OverlapBehavior behavior:overlapList){
			behavior.sequence = overlapList.indexOf(behavior)+1;
		}
	}
	
	public void moveUp(){
		if(!(overlapList.size()<=1)&&!frontIntersectItemList.isEmpty()){
			OverlapBehavior front = frontIntersectItemList.get(0);
			basicItem.moveAbove(front.basicItem);
			overlapList.remove(this);
			overlapList.add(overlapList.indexOf(front)+1, this);
			refreshList();
			checkIntersectItems();
		}
	}
	
	public void moveDown(){
		if(!(overlapList.size()<=1)&&!backIntersectItemList.isEmpty()){
			OverlapBehavior back = backIntersectItemList.get(backIntersectItemList.size()-1);
			basicItem.moveBelow(back.basicItem);
			overlapList.remove(this);
			overlapList.add(overlapList.indexOf(back), this);
			refreshList();
			checkIntersectItems();
		}
	}

	@Override
	public int compareTo(OverlapBehavior ov) {
		if(ov.sequence > this.sequence){
			return -1;
		}else if(ov.sequence == this.sequence){
			return 0;
		}else{
			return 1;
		}
	}
	
}
