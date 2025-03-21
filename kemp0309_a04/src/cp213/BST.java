package cp213;

import java.util.ArrayList;

/**
 * Implements a Binary Search Tree.
 *
 * @author your name here
 * @author David Brown
 * @version 2025-01-05
 * @param <T> the data structure data type
 */
public class BST<T extends Comparable<T>> {

    // Attributes.
    /**
     * Count of comparisons performed by tree.
     */
    protected int comparisons = 0;
    /**
     * Root node of the tree.
     */
    protected TreeNode<T> root = null;
    /**
     * Number of nodes in the tree.
     */
    protected int size = 0;

    /**
     * Auxiliary method for {@code equals}. Determines whether two subtrees are
     * identical in stored objects and height.
     *
     * @param source Node of this BST.
     * @param target Node of that BST.
     * @return true if source and target are identical in stored objects and height.
     */
    protected boolean equalsAux(final TreeNode<T> source, final TreeNode<T> target) {
    	if (source == null && target == null) {
    		return true;
    		}
    	if (source == null || target == null) {
    		return false;
    		}
    	
	
	if(source.getCountedEntity() != target.getCountedEntity()) {
		return false;
	}

	return this.equalsAux(source.getLeft(), target.getLeft()) && this.equalsAux(source.getRight(), target.getRight());
    }

    /**
     * Auxiliary method for insert. Inserts data into this BST.
     *
     * @param node          The current node (TreeNode).
     * @param countedEntity Data to be inserted into the tree.
     * @return The inserted node.
     */
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedEntity<T> countedEntity) {

	if (node == null) {
	    // Base case - add a new node containing the data.
	    node = new TreeNode<T>(countedEntity);
	    node.getCountedEntity().incrementCount();
	    this.size++;
	} else {
	    // Compare the node data against the insert data.
	    final int result = node.getCountedEntity().compareTo(countedEntity);

	    if (result > 0) {
		// General case - check the left subtree.
		node.setLeft(this.insertAux(node.getLeft(), countedEntity));
	    } else if (result < 0) {
		// General case - check the right subtree.
		node.setRight(this.insertAux(node.getRight(), countedEntity));
	    } else {
		// Base case - data is already in the tree, increment its count
		node.getCountedEntity().incrementCount();
	    }
	}
	node.updateHeight();
	return node;
    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree.
     *
     * @param node    The root of the subtree to test for validity.
     * @param minNode The node of the minimum data in the current subtree.
     * @param maxNode The node of the maximum data in the current subtree.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

	if(node == null) return false; // break point
	
	if((minNode != null && node.getCountedEntity().compareTo(minNode.getCountedEntity()) <= 0)
    || (maxNode != null && node.getCountedEntity().compareTo(maxNode.getCountedEntity()) >= 0)) 
		return false; // break point
	// I can't tell if this is better or worse than CP164

		// iterate
		return isValidAux(node.getLeft(), minNode, node) && isValidAux(node.getRight(), node, maxNode);
    }

    /**
     * Returns the height of a given TreeNode. Required for when TreeNode is null.
     *
     * @param node The TreeNode to determine the height of.
     * @return The height attribute of node, 0 if node is null.
     */
    protected int nodeHeight(final TreeNode<T> node) {
	return node != null ? node.getHeight() : 0;
    }

    /**
     * Auxiliary method for remove. Removes data from this BST.
     *
     * @param node          The current node (TreeNode).
     * @param countedEntity Data removed from the tree.
     * @return The replacement node.
     */
    protected TreeNode<T> removeAux(TreeNode<T> node, final CountedEntity<T> countedEntity) {

	if(node == null) { // can't search a null node
		return null;
	} else if(countedEntity.compareTo(node.getCountedEntity()) < 0) { // bigger than value
		node.setLeft(removeAux(node.getLeft(), countedEntity));
	} else if(countedEntity.compareTo(node.getCountedEntity()) > 0) { // less than value
		node.setRight(removeAux(node.getRight(), countedEntity));
	} else { // we found a match
		 if (node.getLeft() == null) {
	            return node.getRight(); 
	        } else if (node.getRight() == null) {
	            return node.getLeft();
	        } else {
	    		TreeNode<T> node1 = node;
	    		TreeNode<T> node2 = node.getRight();

	    		while (node2.getLeft() != null) {
	    		    node1 = node2;
	    		    node2 = node2.getLeft();
	    		}

	    		if (node1 != node) {
	    		    node1.setLeft(node2.getRight());
	    		    node2.setRight(node.getRight());
	    		}

	    		node2.setLeft(node.getLeft());
	    		node = node2;
	        }
	}
	
	

	return node;
    }

    /**
     * Determines if this BST contains key.
     *
     * @param key The key to search for.
     * @return true if this contains key, false otherwise.
     */
    public boolean contains(final CountedEntity<T> key) {
	return this.retrieve(key) != null;
    }

    /**
     * Determines whether two trees are identical.
     *
     * @param target The tree to compare this BST against.
     * @return true if this and target contain nodes that match in position, data,
     *         count, and height, false otherwise.
     */
    public boolean equals(final BST<T> target) {
	boolean isEqual = false;

	if (this.size == target.size) {
	    isEqual = this.equalsAux(this.root, target.root);
	}
	return isEqual;
    }

    /**
     * Get number of comparisons executed by the retrieve method.
     *
     * @return comparisons
     */
    public int getComparisons() {
	return this.comparisons;
    }

    /**
     * Returns the height of the root node of this tree.
     *
     * @return height of root node, 0 if the root node is null.
     */
    public int getHeight() {
	return this.root != null ? this.root.getHeight() : 0;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in this tree.
     */
    public int getSize() {
	return this.size;
    }

    /**
     * Returns a list of the data in the current tree. The list contents are in
     * order from smallest to largest.
     *
     * Not thread safe as it assumes contents of the tree are not changed by an
     * external thread during the loop.
     *
     * @return The contents of this tree as a list of data.
     */
    public ArrayList<CountedEntity<T>> inOrder() {
	return this.root.inOrder();
    }

    /**
     * Inserts data into this tree.
     *
     * @param countedEntity Data to store.
     */
    public void insert(final CountedEntity<T> countedEntity) {
	this.root = this.insertAux(this.root, countedEntity);
	return;
    }

    /**
     * Determines if this tree is empty.
     *
     * @return true if this tree is empty, false otherwise.
     */
    public boolean isEmpty() {
	return this.size == 0;
    }

    /**
     * Determines if this tree is a valid BST; i.e. a node's left child data is
     * smaller than its data, and its right child data is greater than its data, and
     * a node's height is equal to the maximum of the heights of its two children
     * (empty child nodes have a height of 0), plus 1.
     *
     * @return true if this tree is a valid BST, false otherwise.
     */
    public boolean isValid() {
	return this.isValidAux(this.root, null, null);
    }

    /**
     * Returns a list of the data in the current tree. The list contents are in node
     * level order starting from the root node. Helps determine the structure of the
     * tree.
     *
     * Not thread safe as it assumes contents of the tree are not changed by an
     * external thread during the loop.
     *
     * @return this tree data as a list of data.
     */
    public ArrayList<CountedEntity<T>> levelOrder() {
	return this.root.levelOrder();
    }

    /**
     * Returns a list of the data in the current tree. The list contents are in node
     * preorder.
     *
     * Not thread safe as it assumes contents of the tree are not changed by an
     * external thread during the loop.
     *
     * @return The contents of this tree as a list of data.
     */
    public ArrayList<CountedEntity<T>> preOrder() {
	return this.root.preOrder();
    }

    /**
     * Removes data from the tree. Decrements the node count, and if the count is 0,
     * removes the node entirely.
     *
     * @param countedEntity Data to decrement or remove.
     */
    public void remove(final CountedEntity<T> countedEntity) {
	this.root = this.removeAux(this.root, countedEntity);
	return;
    }

    /**
     * Resets the comparison count to 0.
     */
    public void resetComparisons() {
	this.comparisons = 0;
	return;
    }

    /**
     * Retrieves a copy of data matching key (key should have data count of 0).
     * Returning a complete CountedEntity gives access to the data and its count.
     *
     * @param key The key to look for.
     * @return The complete CountedEntity that matches key, null otherwise.
     */
    public CountedEntity<T> retrieve(final CountedEntity<T> key) {

	//if(root == null) return null; recursion error or whatever
	TreeNode<T> current = this.root;

    while (current != null) {
        int cmp = key.compareTo(current.getCountedEntity());

        if (cmp < 0) { // bigger than
            current = current.getLeft(); // Search left
        } else if (cmp > 0) { // less than
            current = current.getRight(); // Search right
        } else {
            return current.getCountedEntity(); // Found match
        }
    }
	return null;
    }
}
