import java.io.*;
import java.util.*;

public class HuffmanTree {
	ArrayList<HuffmanNode> charFrequencyNodes;
	

	HuffmanTree(int[] count) { // count: Array with 256 indices representing ASCII. Values represent ASCII frequencies
		// Create a leaf node for each character/frequency pair
		// Use Merge Sort to put leaf nodes in priority queue based on frequency (low -> high)
		for (int charIndex = 0; charIndex < count.length; charIndex++) { // TODO: Change to merge sort
			for(int j = charIndex; j < count.length - charIndex; j++) {
				if (count[charIndex] >= count[j]) {
					charFrequencyNodes.add(new HuffmanNode(charIndex, count[charIndex], null));	
				}
			}
		}
		
		// Create binary tree using sorted nodes
		for (int charIndex = 0; charIndex < charFrequencyNodes.size(); charIndex += 2) {
			int sumFrequency = charFrequencyNodes.get(charIndex).getFrequency() + charFrequencyNodes.get(charIndex + 1).getFrequency();
			HuffmanNode[] sumFrequencyChildren = new HuffmanNode[] { charFrequencyNodes.get(charIndex), charFrequencyNodes.get(charIndex + 1) };
			HuffmanNode sumNode = new HuffmanNode(null, sumFrequency, sumFrequencyChildren);
		}
	}
	
	private HuffmanNode[] createBinaryTree(HuffmanNode[] charFrequencyNodes) {
		// Base case: there are no more
	}
	
	public void write(PrintStream output) {
		
	}
}
