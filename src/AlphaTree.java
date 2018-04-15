/**
 * A implementation of a binary tree data structure designed to alphabetize it's
 * input.
 * 
 * @author Sean P. Hanly
 * @date 5-18-15
 */
class AlphaTree {
	private Node root;

	/**
	 * @param word
	 *            - String to be alphabetized.
	 */
	AlphaTree(String word) {
		root = new Node(word);
		root.setKey(root.getWord()[1]);
	}

	/**
	 * @return The root of the tree.
	 */
	Node getRoot() {
		return root;
	}

	/**
	 * @param word
	 *            - The String for the new node. The key will be generated from
	 *            the String.
	 * @return True if the word in unique, false otherwise.
	 */
	boolean addNode(String word) {
		Node branch = new Node(word);

		return order(root, branch);
	}

	/**
	 * Constructs the tree.
	 * 
	 * @param parent
	 *            - The focus node.
	 * @param sort
	 *            - The node to be sorted.
	 */
	private boolean order(Node parent, Node sort) {

		if (compare(sort, parent)) {
			if (sort.getKey() < parent.getKey()) {
				if (parent.getLeft() == null) {
					parent.setLeft(sort);
				} else {
					return order(parent.getLeft(), sort);
				}
			} else {
				if (parent.getRight() == null) {
					parent.setRight(sort);
				} else {
					return order(parent.getRight(), sort);
				}

			}

			return true;
		}

		return false;
	}

	/**
	 * Compares two nodes and assigns keys.
	 * 
	 * @param sort
	 *            - The node yet to be sorted.
	 * @param sorted
	 *            - The node already sorted.
	 * @return True if the words are not the same or false if they are.
	 */
	private boolean compare(Node sort, Node sorted) {
		int i;
		char[] st = sort.getWord(), sd = sorted.getWord();

		for (i = 1; i < st.length && i < sd.length; i++) {
			if (st[i] != sd[i]) {
				sort.setKey(st[i]);
				sorted.setKey(sd[i]);
				return true;
			}
		}

		if (st.length == sd.length) {
			return false;
		} else if (st.length > sd.length) {
			sort.setKey(st[i]);
			sorted.setKey(0);
		} else {
			sort.setKey(0);
			sorted.setKey(sd[i]);
		}

		return true;
	}

}

/**
 * Node for the Alpha Tree
 * 
 * @author Sean P. Hanly
 * @date 5-18-15
 */
class Node {
	private int key;
	private final char[] word;
	private Node left;
	private Node right;

	/**
	 * @param word
	 *            - String for new node.
	 */
	Node(String word) {
		this.word = word.toCharArray();
	}

	/**
	 * @return The current key value.
	 */
	int getKey() {
		return key;
	}

	/**
	 * @param key
	 *            - The new key value to be set.
	 */
	void setKey(int key) {
		this.key = key;
	}

	/**
	 * @return The word of the node.
	 */
	char[] getWord() {
		return word;
	}

	/**
	 * @return The left child of the node if present else null.
	 */
	Node getLeft() {
		return left;
	}

	/**
	 * @param left
	 *            - Node to be made the left child of the Node.
	 */
	void setLeft(Node left) {
		this.left = left;
	}

	/**
	 * @return The right child of the node if present else null.
	 */
	Node getRight() {
		return right;
	}

	/**
	 * @param right
	 *            - Node to be made the right child of the Node.
	 */
	void setRight(Node right) {
		this.right = right;
	}

}