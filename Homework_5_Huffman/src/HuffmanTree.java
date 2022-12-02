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
	
	public void write(PrintStream output) {
		
	}
}

/*
 * Defines a special way to compare HuffmanNodes: by frequency
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
