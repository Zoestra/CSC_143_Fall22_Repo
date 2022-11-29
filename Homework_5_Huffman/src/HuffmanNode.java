
public class HuffmanNode {
	private int ASCIIVal;
	private int frequency;
	private HuffmanNode[] childrenNodes;
	
	HuffmanNode(Integer ASCIIVal, int frequency, HuffmanNode[] childrenNodes) {
		this.ASCIIVal = ASCIIVal; // Nullable, for nodes representing frequency sums
		this.frequency = frequency;
		this.childrenNodes = childrenNodes;
	}
	
	public int getFrequency() {
		return frequency;
	}
}
