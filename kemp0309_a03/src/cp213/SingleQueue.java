package cp213;

/**
 * A single linked queue structure of <code>Node T</code> objects. Only the
 * <code>T</code> object contained in the queue is visible through the standard
 * queue methods. Extends the <code>SingleLink</code> class.
 *
 * @author David Brown
 * @version 2025-01-05
 * @param <T> the SingleQueue data type.
 */
public class SingleQueue<T> extends SingleLink<T> {

    /**
     * Combines the contents of the left and right SingleQueues into the current
     * SingleQueue. Moves nodes only - does not refer to objects in any way, or call
     * the high-level methods insert or remove. left and right SingleQueues are
     * empty when done. Nodes are moved alternately from left and right to this
     * SingleQueue.
     *
     * You have two source queues named left and right. Move all nodes from these
     * two queues to the current queue. It does not make a difference if the current
     * queue is empty or not, just get nodes from the right and left queues and add
     * them to the current queue. You may use any appropriate SingleLink helper
     * methods available.
     *
     * Do not assume that both right and left queues are of the same length.
     *
     * @param left  The first SingleQueue to extract nodes from.
     * @param right The second SingleQueue to extract nodes from.
     */
    public void combine(final SingleQueue<T> left, final SingleQueue<T> right) {
    	boolean swap = false;
    	
    	while(left.front != null || right.front != null) {
    		if(swap && left.front != null) {
    	    	if(this.front == null) {
    	    		SingleNode node = left.front;
    	    		this.front = node;
    	    		this.rear = node;
    	    	} else {
    	    		SingleNode node = left.front;
    	    		this.rear.setNext(node);
    	    		this.rear = node;
    	    	}
    	    	
    	    	this.length++;
    			left.front = left.front.getNext();
    		} else if(!swap && right.front != null) {
    			SingleNode node = right.front;
    	    	if(this.front == null) {
    	    		this.front = node;
    	    		this.rear = node;
    	    	} else {
    	    		this.rear.setNext(node);
    	    		this.rear = node;
    	    	}
    	    	
    	    	this.length++;
    			right.front = right.front.getNext();

    		}
    		
    		swap = !swap;
    	}
	
	return;
    }

    /**
     * Adds object to the rear of the queue. Increments the queue length.
     *
     * @param entity The object to added to the rear of the queue.
     */
    public void insert(final T entity) {

    	if(this.front == null) {
    		SingleNode node = new SingleNode(entity, null);
    		this.front = node;
    		this.rear = node;
    	} else {
    		SingleNode node = new SingleNode(entity, null);
    		this.rear.setNext(node);
    		this.rear = node;
    	}
    	
	
    	this.length++;

	return;
    }

    /**
     * Returns the front object of the queue and removes that object from the queue.
     * The next node in the queue becomes the new first node. Decrements the queue
     * length.
     *
     * @return The object at the front of the queue.
     */
    public T remove() {

	T entity = this.front.getEntity();
	if(this.front.getNext() == this.rear) {
		
	}
	
	this.front = this.front.getNext();
	
	this.length--;

	return entity;
    }

    /**
     * Splits the contents of the current SingleQueue into the left and right
     * SingleQueues. Moves nodes only - does not move object or call the high-level
     * methods insert or remove. this SingleQueue is empty when done. Nodes are
     * moved alternately from this SingleQueue to left and right. left and right may
     * already contain objects.
     *
     * This is the opposite of the combine method.
     *
     * @param left  The first SingleQueue to move nodes to.
     * @param right The second SingleQueue to move nodes to.
     */
    public void splitAlternate(final SingleQueue<T> left, final SingleQueue<T> right) {

	boolean swap = true;
	
	while(this.front != null) {
		SingleNode node = this.front;
		this.front = this.front.getNext();
		node.setNext(null);
		
		if(swap) {
			 if (left.rear == null) {
	                left.front = node;  
	                left.rear = node;   
	            } else {
	                left.rear.setNext(node);  
	                left.rear = node;        
	            }
			 left.length++;
		} else {
			if (right.rear == null) {
				right.front = node;  
				right.rear = node;   
            } else {
            	right.rear.setNext(node);  
            	right.rear = node;        
            }
			right.length++;
		}
		swap = !swap;
	}
	
	this.length = 0;
	return;
    }
}
