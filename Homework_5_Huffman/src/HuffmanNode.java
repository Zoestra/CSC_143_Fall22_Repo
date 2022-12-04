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
	
	public int getASCII() {
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
}
