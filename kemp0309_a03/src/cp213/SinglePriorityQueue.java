package cp213;

import java.util.Objects;

/**
 * A single linked priority queue structure of <code>Node T</code> objects.
 * These data objects must be Comparable - i.e. they must provide the compareTo
 * method. Only the <code>T</code> data contained in the priority queue is
 * visible through the standard priority queue methods. Extends the
 * <code>SingleLink</code> class.
 *
 * @author David Brown
 * @version 2025-01-05
 * @param <T> the SinglePriorityQueue data type.
 */
public class SinglePriorityQueue<T extends Comparable<T>> extends SingleLink<T> {

    /**
     * Combines the contents of the left and right SinglePriorityQueues into the
     * current SinglePriorityQueue. Moves nodes only - does not move object or call
     * the high-level methods insert or remove. left and right SinglePriorityQueues
     * are empty when done. Nodes are moved alternately from left and right to this
     * SinglePriorityQueue. When finished all nodes must be in priority order from
     * front to rear.
     *
     * Do not use the SinglePriorityQueue insert and remove methods.
     *
     * Do not assume that both right and left priority queues are of the same
     * length.
     *
     * @param left  The first SinglePriorityQueue to extract nodes from.
     * @param right The second SinglePriorityQueue to extract nodes from.
     */
    public void combine(final SinglePriorityQueue<T> left, final SinglePriorityQueue<T> right) {
	assert this.front == null : "May combine into an empty Priority Queue only";

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
     * Adds object to the SinglePriorityQueue. Data is stored in priority order,
     * with highest priority object at the front of the SinglePriorityQueue, and
     * lowest at the rear. Priority is determined by simple comparison - lower
     * objects have higher priority. For example, 1 has a higher priority than 2
     * because 1 is a lower object than 2. (Think of the phrase, "We're number one!"
     * as an indication of priority.)
     *
     * When inserting object to the priority queue, the queue must remain sorted.
     * Hence you need to find the proper location of inserting object. use the head
     * pointer to go through the queue. e.g., use SingleNode&lt;T&gt; current =
     * this.head;
     *
     * use current = current.getNext(); to traverse the queue.
     *
     * To get access to the object inside a node of queue use current.getValue().
     *
     * @param entity object to insert in sorted order in priority queue.
     */
    public void insert(final T entity) {
		SingleNode node = new SingleNode(entity, null);
	if(this.front == null) {
		this.front = node;
		this.rear = node;
	} else {
		 SingleNode<T> current = this.front;
	     SingleNode<T> previous = null; // scuffed dequeue
		while(current.getNext() != null && current.getEntity().compareTo(entity) < 0) { // >=
			  previous = current;
	          current = current.getNext();
		}
		
		if(previous == null) {
			node.setNext(this.front);
			this.front = node; // I think?
		} else {
            previous.setNext(node);
            node.setNext(current);
        }
		
	}

	this.length++;
	return;
    }

    /**
     * Returns the highest priority object in the SinglePriorityQueue. Decrements
     * the count.
     *
     * @return the highest priority object currently in the SinglePriorityQueue.
     */
    public T remove() {

	// if we already set it in order then the first thing should be the highest ?
    	T entity = this.front.getEntity();
    	if(this.front.getNext() == this.rear) {
    		this.front = null;
            this.rear = null;
    	} else {
        	this.front = this.front.getNext();
    	}
    	
    	
    	this.length--;

    	return entity;
    }

    /**
     * Splits the contents of this SinglePriorityQueue into the higher and lower
     * SinglePriorityQueue. Moves nodes only - does not move object or call the
     * high-level methods insert or remove. this SinglePriorityQueue is empty when
     * done. Nodes with priority object higher than key are moved to the
     * SinglePriorityQueue higher. Nodes with a priority object lower than or equal
     * to key are moved to the SinglePriorityQueue lower.
     *
     * Do not use the SinglePriorityQueue insert and remove methods.
     *
     * @param key    object to compare against node objects in SinglePriorityQueue
     * @param higher an initially empty SinglePriorityQueue queue that ends up with
     *               all objects with priority higher than key.
     * @param lower  an initially empty SinglePriorityQueue queue that ends up with
     *               all objects with priority lower than or equal to key.
     */
    public void splitByKey(final T key, final SinglePriorityQueue<T> higher, final SinglePriorityQueue<T> lower) {
        
    	while (this.front != null) {
    	    T value = this.front.getEntity();
    	    SingleNode<T> next = this.front.getNext();
    	    this.front.setNext(null);

    	    if (value.compareTo(key) < 0) {
    		if (higher.front == null) {
    		    higher.front = this.front;
    		} else {
    		    SingleNode<T> iter = higher.front;
    		    while (iter.getNext() != null) {
    			iter = iter.getNext();
    		    }
    		    iter.setNext(this.front);
    		}
    		higher.length++;
    	    } else {
    		if (lower.front == null) {
    		    lower.front = this.front;
    		} else {
    		    SingleNode<T> iter = lower.front;
    		    while (iter.getNext() != null) {
    			iter = iter.getNext();
    		    }
    		    iter.setNext(this.front);
    		}
    		lower.length++;
    	    }
    	    this.front = next;
    	}
    	this.length = 0;
       
    }
    
    
}
