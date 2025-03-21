package cp213;

/**
 * Implements an AVL (Adelson-Velsky Landis) tree. Extends BST.
 *
 * @author James Kemp
 * @author David Brown
 * @version 2025-01-05
 * @param <T> the data structure data type
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

    /**
     * Returns the balance data of node. If greater than 1, then left heavy, if less
     * than -1, then right heavy. If in the range -1 to 1 inclusive, the node is
     * balanced. Used to determine whether to rotate a node upon insertion.
     *
     * @param node The TreeNode to analyze for balance.
     * @return A balance number.
     */
    private int balance(final TreeNode<T> node) {
    	if(node == null) return 0;
    	
    // this will automatically balance out by subtracting the node heights 
    // 1 - 0 = 1 -> left node is bigger
    // 0 - 1 = -1 -> Right node is bigger
    // 1 - 1 = 0 -> the tree is balanced
	return nodeHeight(node.getLeft()) - nodeHeight(node.getRight()); 
    }

    /**
     * Rebalances the current node if its children are not balanced.
     *
     * @param node the node to rebalance
     * @return replacement for the rebalanced node
     */
    private TreeNode<T> rebalance(TreeNode<T> node) {
    	if(node == null) return null;
    	int bal = this.balance(node); // going to store this as local variable
    	if(bal > 1) { // left ?
    		if(this.balance(node.getLeft()) < 0) { // if left is smaller (this shouldn't be happening)
    			node.setLeft(rotateLeft(node.getLeft())); // Rotate because something is wrong
    		}
    		return rotateRight(node); // we execute the rotation on call
    	}
    	
    	if(bal < -1) { // right ?
    		if(this.balance(node.getRight()) > 0) { // if left is smaller (this shouldn't be happening)
    			node.setRight(rotateRight(node.getRight())); // Rotate because something is wrong
    		}
    		return rotateLeft(node); // we execute the rotation on call
    	}
    	// precaution - I don't think my roate method does this
    	node.updateHeight(); 
    	 return node;
    }

    /**
     * Performs a left rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> node) {
    	
    	TreeNode<T> value = node.getRight(); // get bigger node
    	node.setRight(value.getLeft()); // set bigger node to left
    	value.setLeft(node); // rotate left
    	
    	// update if unbalanced
    	node.updateHeight();
    	value.updateHeight();

	return value;
    }

    /**
     * Performs a right rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> node) { // just opposite of set left
    	TreeNode<T> value = node.getLeft();
    	node.setLeft(value.getRight());
    	value.setRight(node);
    	node.updateHeight();
    	value.updateHeight();

	return value;
    }

    /**
     * Auxiliary method for insert. Inserts data into this AVL. Same as BST
     * insertion with addition of rebalance of nodes.
     *
     * @param node          The current node (TreeNode).
     * @param countedEntity Data to be inserted into the node.
     * @return The inserted node.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedEntity<T> countedEntity) {
    	// possibly?
    	// this won't work because we arn't balancing vvvv
    node = super.insertAux(node, countedEntity);
	return this.rebalance(node);
    	
    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An AVL must meet the BST validation conditions, and additionally be
     * balanced in all its subtrees - i.e. the difference in height between any two
     * children must be no greater than 1.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

	if(node == null) return true; // idk it could be both
	if(minNode != null && node.getCountedEntity().compareTo(minNode.getCountedEntity()) <= 0) { // lower bound
		return false;
	}
	if (maxNode != null && node.getCountedEntity().compareTo(maxNode.getCountedEntity()) >= 0) { // upper bound
		return false; 
		}
	
    if (Math.abs(this.balance(node)) > 1) { // if the two nodes subtracted are less than 1
        return false;
    }
	  
	  // recurse
	  return isValidAux(node.getLeft(), minNode, node) && isValidAux(node.getRight(), node, maxNode);
    }

    /**
     * Determines whether two AVLs are identical.
     *
     * @param target The AVL to compare this AVL against.
     * @return true if this AVL and target contain nodes that match in position,
     *         data, count, and height, false otherwise.
     */
    public boolean equals(final AVL<T> target) {
	return super.equals(target);
    }

    /**
     * Auxiliary method for remove. Removes data from this BST. Same as BST removal
     * with addition of rebalance of nodes.
     *
     * @param node          The current node (TreeNode).
     * @param countedEntity Data removed from the tree.
     * @return The replacement node.
     */
    @Override
    protected TreeNode<T> removeAux(TreeNode<T> node, final CountedEntity<T> countedEntity) {
    	node = super.removeAux(node, countedEntity);
	return this.rebalance(node);
    }

}
