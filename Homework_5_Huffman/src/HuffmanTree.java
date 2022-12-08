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
				
				// Make location opposite childIndex (to match prof's tree)
				int direction = 0;
				if (childIndex == 0) {
					direction = 1;
				}
				
				// Set child's relative location
				if (currentNode.getLocation() == null) {
					// Current node is root node
					// Child's location = path from root
					childNode.setLocation(direction + "");
					
					// Recursion: Repeat for each child's child
					setLocations(childNode);
				} else {
					// Current node is somewhere down the tree
					// Child's location = current location + path from current location
					String location = currentNode.getLocation() + direction;
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
			// Frequency is ignored: set to -1
			tree.add(new HuffmanNode(ASCII, -1, location, null));
		}
		
		// Based on the location, organize parents / children
		createBranchesFromLocations(furthestNodeByLocation());
	}
	
	private void createBranchesFromLocations(HuffmanNode furthestNode) {
		// Base case: tree has no leaf nodes. Just a root
		if (tree.size() > 1) {
			// Each node should have a sibling in its branch
			HuffmanNode siblingNode  = getSiblingNode(furthestNode.getLocation());
			
			// Find out which sibling is left / right
			HuffmanNode[] childNodes = new HuffmanNode[] { furthestNode, siblingNode };
			String[] siblingLocation = siblingNode.getLocation().split("");
			if (siblingLocation[siblingLocation.length - 1].equals("1")) {
				childNodes = new HuffmanNode[] { siblingNode, furthestNode };
			}
			
			// Get location of parent node
			ArrayList<String> locationStringSplit = new ArrayList<String> (Arrays.asList(furthestNode.getLocation().split("")));			
			locationStringSplit.remove(locationStringSplit.size() - 1);
			String parentLocation = String.join("", locationStringSplit);
			
			// Create parent node
			tree.add(new HuffmanNode(null, -1, parentLocation, childNodes));
			
			// Remove children, since they are now part of organized tree
			tree.remove(furthestNode);
			tree.remove(siblingNode);
			
			// Recursion: Repeat ^^ for the next furthest node
			createBranchesFromLocations(furthestNodeByLocation());
		}
	}
	
	private HuffmanNode furthestNodeByLocation() {
		// Find the furthest location
		String furthestLocation = "";
		for (HuffmanNode node : tree) {
			if (node.getLocation().length() > furthestLocation.length()) {
				furthestLocation = node.getLocation();
			}
		}
		
		// Return the node associated with that location
		for (HuffmanNode node : tree) {
			if (node.getLocation().equals(furthestLocation)) {
				return node;
			}
		}
		return null;
	}
	
	private HuffmanNode getSiblingNode(String currentLocation) {
		// Get corresponding location
		ArrayList<String> locationStringSplit = new ArrayList<String> (Arrays.asList(currentLocation.split("")));
		if (locationStringSplit.get(locationStringSplit.size() - 1).equals("0")) {
			locationStringSplit.remove(locationStringSplit.size() - 1);
			locationStringSplit.add("1");
		} else {
			locationStringSplit.remove(locationStringSplit.size() - 1);
			locationStringSplit.add("0");
		}
		String siblingLocation = String.join("", locationStringSplit);
		
		// Check if location matches any node in tree
		Object[] arrayFromTree = tree.toArray();
		for (Object object : arrayFromTree) {
			if (object instanceof HuffmanNode) {
				if (((HuffmanNode) object).getLocation().equals(siblingLocation)) {
					// Location matches node: Return it
					return (HuffmanNode) object;
				}
			}
		}
		return null;
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
        // loop until the byte value = eof
        int readBit = input.readBit();
        while(true) {
            HuffmanNode currentNode = getRootNode();
            int byteValue = 0;
            
            // find the node that contains byte value from our tree
            while(currentNode != null) {
                if(currentNode.getASCII() != null) {
                    // by design, leaf nodes will have byte value more than 1
                    byteValue = currentNode.getASCII();
                    break;
                }
                
                int reverseBit = 1;
                if (readBit == 1) {
                	reverseBit = 0;
                }
                
                currentNode = currentNode.getChildren()[reverseBit];
                readBit = input.readBit();
            }
            
            if(byteValue == eof) { // end the file here and don't write our eof to the file
                break;
            }
            
            //write this byte to output
            output.write(byteValue);
        }
    }
	
	/*
	 * Returns the node with highest frequency in tree
	 */
	public HuffmanNode getRootNode() {
		if (tree.size() == 0) {
			System.out.println("No root node for tree of zero size.");
			return null;
		} else if (tree.size() == 1) {
			HuffmanNode rootNode = tree.peek(); 
			return rootNode;
		} else {
			Object[] arrayFromTree = tree.toArray();
			if (arrayFromTree[arrayFromTree.length - 1] instanceof HuffmanNode) {
				return (HuffmanNode) arrayFromTree[arrayFromTree.length - 1];	
			} else {
				return null;
			}
		}
	}

	public void printTree(){
		printTree(getRootNode());
	}

	public void printTree(HuffmanNode node){
		System.out.println(node.toString());

		if (node.getChildren() != null) {
			for (HuffmanNode childNode : node.getChildren()) {
				printTree(childNode);
			}
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
