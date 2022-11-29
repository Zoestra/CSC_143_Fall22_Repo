
public class HuffmanNode {
	private int ASCIIVal;
	private int frequency;
	private int location;
	private HuffmanNode[] childrenNodes;
	
	HuffmanNode(Integer ASCIIVal, int frequency, int location, HuffmanNode[] childrenNodes) {
		this.ASCIIVal = ASCIIVal; // Nullable, for nodes representing frequency sums
		this.frequency = frequency;
		this.location = location;
		this.childrenNodes = childrenNodes;
	}
	
	public int getFrequency() {
		return frequency;
	}
}
