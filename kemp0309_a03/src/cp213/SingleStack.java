package cp213;

/**
 * A single linked stack structure of <code>Node T</code> objects. Only the
 * <code>T</code> object contained in the stack is visible through the standard
 * stack methods. Extends the <code>SingleLink</code> class. Note that the rear
 * attribute should be ignored as the rear is not used in a stack.
 *
 * @author David Brown
 * @version 2025-01-05
 * @param <T> the SingleStack data type.
 */
public class SingleStack<T> extends SingleLink<T> {

    /**
     * Combines the contents of the left and right SingleStacks into the current
     * SingleStack. Moves nodes only - does not refer to objects in any way, or call
     * the high-level methods pop or push. left and right SingleStacks are empty
     * when done. Nodes are moved alternately from left and right to this
     * SingleStack.
     *
     * You have two source stacks named left and right. Move all nodes from these
     * two stacks to the current stack. It does not make a difference if the current
     * stack is empty or not, just get nodes from the right and left stacks and add
     * them to the current stack. You may use any appropriate SingleLink helper
     * methods available.
     *
     * Do not assume that both right and left stacks are of the same length.
     *
     * @param left  The first SingleStack to extract nodes from.
     * @param right The second SingleStack to extract nodes from.
     */
    public void combine(final SingleStack<T> left, final SingleStack<T> right) {
    	
    	boolean leftS = false;
    	
    	while(left.front != null || right.front != null) {
    		if(leftS && left.length > 0) {
    			this.moveFrontToRear(left);
    		}
    		if(!leftS && right.length > 0) {
    			this.moveFrontToRear(right);
    		}
			leftS = !leftS;
    	}
    	
	return;
    }

    /**
     * Returns the top object of the stack and removes that object from the stack.
     * The next node in the stack becomes the new top node. Decrements the stack
     * length.
     *
     * @return The object at the top of the stack.
     */
    public T pop() {
    T entity = this.front.getEntity();

    this.front = this.front.getNext();
    
    if(this.front == null) {
    	this.rear = null;
    }
    
    this.length--;
    
	return entity;
    }

    /**
     * Adds data to the top of the stack. Increments the stack length.
     *
     * @param entity The object to add to the top of the stack.
     */
    public void push(final T entity) {

    SingleNode node = new SingleNode(entity, this.front);
    this.front = node;
    if(this.rear == null) {
    	this.rear = node;
    }
	
	this.length++;

	return;
    }

    /**
     * Splits the contents of the current SingleStack into the left and right
     * SingleStacks. Moves nodes only - does not move object or call the high-level
     * methods insert or remove. this SingleStack is empty when done. Nodes are
     * moved alternately from this SingleStack to left and right. left and right may
     * already contain objects.
     *
     * This is the opposite of the combine method.
     *
     * @param left  The first SingleStack to move nodes to.
     * @param right The second SingleStack to move nodes to.
     */
    public void splitAlternate(final SingleStack<T> left, final SingleStack<T> right) {
    	boolean swap = true;
	while(this.front != null) {
		SingleNode node = this.front;
		this.front = this.front.getNext();
		node.setNext(null);
		
		if(swap) {
			node.setNext(left.front);
			left.front = node;
			left.length++;
		} else {
			node.setNext(right.front);
			right.front = node;
			right.length++;	
		}
		
		swap = !swap;
	}

	this.length = 0;
	return;
    }
}