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
					charFrequencyNodes.add(new HuffmanNode(charIndex, count[charIndex], null, null));	
				}
			}
		}
		
		// Create binary tree using sorted nodes
		charFrequencyNodes = createBinaryTree(charFrequencyNodes);
	}
	
	private ArrayList<HuffmanNode> createBinaryTree(ArrayList<HuffmanNode> charFrequencyNodes) {

		HuffmanNode[] leastFrequentNodes = new HuffmanNode[] { charFrequencyNodes.get(0), charFrequencyNodes.get(1) };
		int leastFrequenciesSum = leastFrequentNodes[0].getFrequency() + leastFrequentNodes[1].getFrequency();
		HuffmanNode sumNode = new HuffmanNode(null, leastFrequenciesSum, null, leastFrequentNodes);
		
		// Base case: the two smallest nodes are the last nodes
		if (charFrequencyNodes.size() == leastFrequentNodes.length) {

			// Assign each node a location
			assignLocations(sumNode);
			
			// Return organized tree
			ArrayList<HuffmanNode> returnTree = new ArrayList<HuffmanNode>();
			returnTree.add(sumNode);
			return returnTree;
		}
		
		// Otherwise, the two smallest nodes are not the last nodes
		// Position sumNode in the new list of nodes
		charFrequencyNodes.remove(0);
		charFrequencyNodes.remove(1);
		for (int i = 0; i < charFrequencyNodes.size(); i++) {
			if (sumNode.getFrequency() <= charFrequencyNodes.get(i).getFrequency()) {
				charFrequencyNodes.add(i, sumNode);
			}
		}
		return createBinaryTree(charFrequencyNodes);
	}
	
	/*
	 * Given a node with a certain location, add a location to each of it's children
	 */
	private void assignLocations(HuffmanNode node) {
		// Base case: this node has no children
		if (!node.getChildrenNodes().equals(null)) {
			for (int i = 0; i < node.getChildrenNodes().length; i++) {
				HuffmanNode childNode = node.getChildrenNodes()[i];
				String childPath = Integer.toString(i);
				
				// Before adding current node's location to child location, check if current node is root
				if (node.getLocation() != null) {
					// node is not the root: child's location is current location + child's path from current location
					String currentLocation = node.getLocation();
					String childLocation = currentLocation + childPath;
					childNode.location = childLocation;
				} else {
					// node is the root: child's location is it's path from the root
					childNode.location = childPath;
				}
				
				// Recursion: Do ^^ for all this node's children
				assignLocations(childNode);
			}
		}
	}
	
	public void write(PrintStream output) {
		
	}
}
