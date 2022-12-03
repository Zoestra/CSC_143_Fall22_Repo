import java.io.*;
import java.util.*;

public class HuffmanTree {
	private PriorityQueue<HuffmanNode> tree = new PriorityQueue<HuffmanNode>(new NodeComparator());
	
	public HuffmanTree(int[] count) {
		// Given array of (ASCII, Frequency) pairs, temporarily sort these pairs in tree
		for (int ASCII = 0; ASCII < count.length; ASCII++) {
			if (count[ASCII] > 0) {
				tree.add(new HuffmanNode(ASCII, count[ASCII], null));
			}
		}
		
		// Create branches for tree structure
		createBranches();
		
		// Set locations for each node (relative to the root)
		setLocations(tree.element());
	}
	
	/*
	 * Organize tree into branches as long as there are more than 1 nodes
	 */
	private void createBranches() {
		while (tree.size() > 1) {
			// Remove 2 least frequent nodes from the tree
			HuffmanNode leastFrequentNode1 = tree.poll();
			HuffmanNode leastFrequentNode2 = tree.poll();
			
			// Add them back as children to a new branch node
			int combinedFrequency = leastFrequentNode1.getFrequency() + leastFrequentNode2.getFrequency();
			HuffmanNode[] branchChildren = new HuffmanNode[] { leastFrequentNode1, leastFrequentNode2 };
			HuffmanNode branchNode = new HuffmanNode(null, combinedFrequency, branchChildren);
			tree.add(branchNode);	
		}
	}
	
	/*
	 * Set locations for each node
	 */
	public void setLocations(HuffmanNode currentNode) {
		// Base case: current node has no children to locate
		int childCount = currentNode.getChildren().length; 
		if (childCount > 0) {
			// For each of this node's children:
			for (int childIndex = 0; childIndex < childCount; childIndex++) {
				HuffmanNode childNode = currentNode.getChildren()[childIndex];
				
				// Set child's relative location
				if (currentNode.getLocation() == null) {
					// Current node is root node
					// Child's location = path from root
					String location = childIndex + "";
					childNode.setLocation(location);
					
					// Recursion: Repeat for each child's child
					setLocations(childNode);
				} else {
					// Current node is somewhere down the tree
					// Child's location = current location + path from current location
					String location = currentNode.getLocation() + childIndex;
					childNode.setLocation(location);
					
					// Recursion: Repeat for each child's child
					setLocations(childNode);
				}
			}
		}
	}
	
	/*
	 * Write info regarding each node: ASCII and tree location
	 */
	public void write(PrintStream output, HuffmanNode currentNode) {
		// Base case: node has no children to locate
		int childCount = currentNode.getChildren().length; 
		if (childCount > 0) {
			// For each of this node's children:
			for (int childIndex = 0; childIndex < childCount; childIndex++) {
				// Print its ASCII value and relative location
				HuffmanNode childNode = currentNode.getChildren()[childIndex];
				output.println(childNode.getASCII() + "\n" + childNode.getLocation());
			}
		}
	}
}

/*
 * Defines HuffmanNode comparison: by frequency
 */
class NodeComparator implements Comparator<HuffmanNode> {	
	@Override
	public int compare(HuffmanNode node1, HuffmanNode node2) {
		if (node1.getFrequency() > node2.getFrequency()) {
			return 1;
		} else if (node1.getFrequency() < node2.getFrequency()) {
			return -1;
		} else {
			return 0;
		}
	}
}
