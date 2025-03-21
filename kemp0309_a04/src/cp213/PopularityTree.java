package cp213;

/**
 * Implements a Popularity Tree. Extends BST.
 *
 * @author James Kemp
 * @author David Brown
 * @version 2025-01-05
 * @param <T> the data structure data type
 */
public class PopularityTree<T extends Comparable<T>> extends BST<T> {

    /**
     * Auxiliary method for valid. May force node rotation if the retrieval count of
     * the located node data is incremented.
     *
     * @param node The node to examine for key.
     * @param key  The data to search for. Count is updated to count in matching
     *             node data if key is found.
     * @return The updated node.
     */
    private TreeNode<T> retrieveAux(TreeNode<T> node, final CountedEntity<T> key) {
    	 if (node == null) {
             return null;
         }
    	 
         int comparison = key.compareTo(node.getCountedEntity());
    	 this.comparisons++;
    	 
    	if (comparison == 0) { // match found
    		node.getCountedEntity().incrementCount();
    		return node;
    		} else if(comparison < 0) { // target < key (if target is bigger than our key parameter)
    			 TreeNode<T> tmp = retrieveAux(node.getRight(), key); // recursive 
    	            if (tmp == null) {
    	                return node;
    	            }
    	            
    	            node.setRight(tmp);
    	            
    	            if (node.getRight() != null && node.getRight().getCountedEntity().compareTo(key) == 0 &&
    	                node.getRight().getCountedEntity().getCount() > node.getCountedEntity().getCount()) {
    	                return rotateLeft(node);
    	            }
    		} else { // otherwise
   			 TreeNode<T> tmp = retrieveAux(node.getLeft(), key); // recursive 
	            if (tmp == null) {
	                return node;
	            }
	            
	            node.setLeft(tmp);
	            
	            if (node.getLeft() != null && node.getLeft().getCountedEntity().compareTo(key) == 0 &&
	                node.getLeft().getCountedEntity().getCount() > node.getCountedEntity().getCount()) {
	                return rotateRight(node);
	            }
    		}
    	
    	 node.updateHeight();
    	 return node;
    }

    /**
     * Performs a left rotation around node.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> parent) { // from AVL

    	TreeNode<T> value = parent.getRight(); // get bigger node
    	parent.setRight(value.getLeft()); // set bigger node to left
    	value.setLeft(parent); // rotate left
    	
    	// update if unbalanced
    	parent.updateHeight();
    	value.updateHeight();

	return value;
    }

    /**
     * Performs a right rotation around {@code node}.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> parent) {
    	TreeNode<T> value = parent.getLeft(); // from AVL
    	parent.setLeft(value.getRight());
    	value.setRight(parent);
    	parent.updateHeight();
    	value.updateHeight();

	return value;
    }

    /**
     * Replaces BST insertAux - does not increment count on repeated insertion.
     * Counts are incremented only on retrieve.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedEntity<T> data) {
    	if (node == null) {
    	    // Base case - add a new node containing the data.
    	    node = new TreeNode<T>(data);
    	    node.getCountedEntity().incrementCount();
    	    this.size++;
    	} else {
    	    // Compare the node data against the insert data.
    	    final int result = node.getCountedEntity().compareTo(data);

    	    if (result > 0) {
    		// General case - check the left subtree.
    		node.setLeft(this.insertAux(node.getLeft(), data));
    	    } else if (result < 0) {
    		// General case - check the right subtree.
    		node.setRight(this.insertAux(node.getRight(), data));
    	    } 
    	    // WE DON'T NEED A BASE CASE
    	}
    	
    	node.updateHeight();
    	return node;
    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An Popularity Tree must meet the BST validation conditions, and
     * additionally the counts of any node data must be greater than or equal to the
     * counts of its children.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

	//super.isValidAux(node, minNode, maxNode); 
    	// NOTE ^^^: DON'T DO THIS ITS RECURSING BEFORE HITTING OTHER CONDITIONS
	
    	if(node == null) {
    		return true;
    	}
    	// just manually paste from parent so we don't' recurse
    	if((minNode != null && node.getCountedEntity().compareTo(minNode.getCountedEntity()) <= 0)
    		    || (maxNode != null && node.getCountedEntity().compareTo(maxNode.getCountedEntity()) >= 0)) 
    				return false;
    	
	if(node.getLeft() != null && node.getCountedEntity().getCount() < node.getLeft().getCountedEntity().getCount()) {
		return false;
	}
	if(node.getRight() != null && node.getCountedEntity().getCount() < node.getRight().getCountedEntity().getCount()) {
		return false;
	}

	return isValidAux(node.getLeft(), minNode, node) && isValidAux(node.getRight(), node, maxNode);
    }

    /**
     * Determines whether two PopularityTrees are identical.
     *
     * @param target The PopularityTree to compare this PopularityTree against.
     * @return true if this PopularityTree and target contain nodes that match in
     *         position, data, count, and height, false otherwise.
     */
    public boolean equals(final PopularityTree<T> target) {
	return super.equals(target);
    }

    /**
     * Very similar to the BST retrieve, but increments the data count here instead
     * of in the insertion.
     *
     * @param key The key to search for.
     */
    @Override
    public CountedEntity<T> retrieve(CountedEntity<T> key) {
        this.resetComparisons();
        this.root = this.retrieveAux(this.root, key); 
        
        TreeNode<T> current = this.root;
        while (current != null) {
            int comparison = key.compareTo(current.getCountedEntity()); // update in loop
            
            if (comparison < 0) {
                current = current.getLeft();
            } else if (comparison > 0) {
                current = current.getRight();
            } else {
                return current.getCountedEntity();
            }
        }

    	return null;
    }

}
