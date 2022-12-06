import java.io.*;
import java.util.*;

public class HuffmanTree {
	private PriorityQueue<HuffmanNode> tree = new PriorityQueue<HuffmanNode>(new NodeComparator());
	
	/* 
	 * Constructs a HuffmanTree using the given array of frequencies.
	 * count[i] is the number of occurrences of the character with ASCII value i.
	 */
	public HuffmanTree(int[] count) {
		// Given array of (ASCII, Frequency) pairs, temporarily sort these pairs in tree
		for (int ASCII = 0; ASCII < count.length; ASCII++) {
			if (count[ASCII] > 0) {
				tree.add(new HuffmanNode(ASCII, count[ASCII], null, null));
			}
		}

		// Add the "end-of-file" character
		tree.add(new HuffmanNode(256, 1, null, null));
		
		// Using ordered queue of nodes, create branches for tree structure
		// Base case: tree contains a single node: the root
		while (tree.size() > 1) {
			// Otherwise, remove 2 least frequent nodes from the tree
			HuffmanNode leastFrequentNode1 = tree.poll();
			HuffmanNode leastFrequentNode2 = tree.poll();
					
			// Add them back as children to a new branch node
			int combinedFrequency = leastFrequentNode1.getFrequency() + leastFrequentNode2.getFrequency();
			HuffmanNode[] branchChildren = new HuffmanNode[] { leastFrequentNode1, leastFrequentNode2 };
			HuffmanNode branchNode = new HuffmanNode(null, combinedFrequency, null, branchChildren);
			tree.add(branchNode);	
		}
		
		// Set locations for each node (relative to the root)
		setLocations(getRootNode());
	}
	
	/*
	 * Set locations for each node
	 */
	public void setLocations(HuffmanNode currentNode) {
		// Base case: current node has no children to locate
		HuffmanNode[] currentChildren = currentNode.getChildren(); 
		if (currentChildren != null) {
			// For each of this node's children:
			for (int childIndex = 0; childIndex < currentChildren.length; childIndex++) {
				HuffmanNode childNode = currentChildren[childIndex];
				
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
	 * Writes the current tree to the given output stream in standard format.
	 */
	public void write(PrintStream output, HuffmanNode currentNode) {
		// Base case: node has no children to locate
		if (currentNode.getChildren() != null) {
			// For each of this node's children:
			for (int childIndex = 0; childIndex < currentNode.getChildren().length; childIndex++) {
				HuffmanNode childNode = currentNode.getChildren()[childIndex];
				// If not a branch node
				if (childNode.getASCII() != null) {
					// Write its ASCII value and relative location
					output.println(childNode.getASCII() + "\n" + childNode.getLocation());	
				}
				// Recursion: Do ^^ for child's children
				write(output, childNode);
			}
		}
	}
	
	/*
	 * Alternative constructor: takes file created by write() method ^^
	 * 
	 * Creates a HuffmanTree from the Scanner.
	 * Assumes the Scanner contains a tree description in standard format.
	 */
	public HuffmanTree(Scanner input) {
		// Read input file to construct tree
		while(input.hasNext()) {
			int ASCII = Integer.parseInt(input.nextLine());
			String location = input.nextLine();
			tree.add(new HuffmanNode(ASCII, -1, location, null));
		}
	}
	 /*
	  * Reads bits from the given input stream and writes the corresponding characters to the output.
	  * 
	  * Stops reading when it encounters a character with value equal to eof.
	  * (This is a pseudo-eof character, so it should not be written to the output file.)
	  * 
	  * Assumes the input stream contains a legal encoding of characters for this tree’s Huffman code.
	  */
	public void decode(BitInputStream input, PrintStream output, int eof) {
		HuffmanNode currentNode = getRootNode();
		int readBit = input.readBit();
		while (readBit == 0 || readBit == 1) {
			if (currentNode.getChildren()[readBit] != null) {
				// We haven't reached a leaf node yet: Move down one level
				currentNode = currentNode.getChildren()[readBit];
			} else {
				// currentNode is a leaf node: write its ASCII into output
				output.write(currentNode.getASCII());
				
				// Start back at the top of the tree
				currentNode = getRootNode();
			}

			// Move on to the next bit
			readBit = input.readBit();
		}
		
		// Close input stream
		input.finalize();
	}
	
	/*
	 * Returns the node with highest frequency in tree
	 */
	public HuffmanNode getRootNode() {
		if (tree.size() == 0) {
			System.out.println("No root node for tree of zero size.");
			return null;
		} else if (tree.size() == 1) {
			return tree.peek();
		} else {
			return (HuffmanNode) tree.toArray()[-1];	
		}
	}
}

/*
 * Defines HuffmanNode comparison: by frequency
 */
class NodeComparator implements Comparator<HuffmanNode> {	
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
