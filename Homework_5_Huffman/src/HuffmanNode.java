import java.util.*;

public class HuffmanNode{
	private Integer ASCII;
	private int frequency;
	private String location;
	private HuffmanNode[] childNodes;
	
	public HuffmanNode(Integer ASCII, int frequency, String location, HuffmanNode[] childNodes) {
		this.ASCII = ASCII;
		this.frequency = frequency;
		this.location = location;
		this.childNodes = childNodes;
	}
	
	public Integer getASCII() {
		return ASCII;
	}
	
	public int getFrequency() {
		return frequency;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String locationSet) {
		location = locationSet;
	}
	
	public HuffmanNode[] getChildren() {
		return childNodes;
	}


	@Override
	public String toString() {
		String child0, child1;
		if(this.childNodes != null){
			if (this.childNodes[0].getASCII() != null){
				child0 = Character.toString(this.childNodes[0].getASCII());
			}
			else {
				child0 = "Node " + this.childNodes[0].getLocation();
			}
			if (this.childNodes[1].getASCII() != null){
				child1 = Character.toString(this.childNodes[1].getASCII());
			}
			else {
				child1 = "Node " + this.childNodes[1].getLocation();
			}
			return location +
					", ASCII=" + Character.toString(ASCII) +

					", child0= \"" + child0 +
					"\", child1= \"" + child1 +
					"\"}";
		}
		else {
			return location +", ASCII=" + Character.toString(ASCII) +

					", no children";
		}
	}


}
