import java.util.*;

public class HuffmanNode{
	private Integer ASCII;
	private int frequency;
	private HuffmanNode[] childNodes;
	
	public HuffmanNode(Integer ASCII, int frequency, HuffmanNode[] childNodes) {
		this.ASCII = ASCII;
		this.frequency = frequency;
		this.childNodes = childNodes;
	}
	
	public int getASCII() {
		return ASCII;
	}
	
	public int getFrequency() {
		return frequency;
	}
}
