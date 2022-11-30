
public class HuffmanNode {
	private int ASCIIVal;
	private int frequency;
	public String location;
	private HuffmanNode[] childrenNodes;
	
	HuffmanNode(Integer ASCIIVal, int frequency, String location, HuffmanNode[] childrenNodes) {
		this.ASCIIVal = ASCIIVal; // Nullable, for nodes representing frequency sums
		this.frequency = frequency;
		this.location = location;
		this.childrenNodes = childrenNodes;
	}
	
	public int getFrequency() {
		return frequency;
	}
	
	public String getLocation() {
		return location;
	}
	
	public HuffmanNode[] getChildrenNodes() {
		return childrenNodes;
	}
}
