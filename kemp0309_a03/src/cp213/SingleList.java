package cp213;

/**
 * A single linked list structure of <code>Node T</code> objects. These data
 * objects must be Comparable - i.e. they must provide the compareTo method.
 * Only the <code>T</code> object contained in the priority queue is visible
 * through the standard priority queue methods. Extends the
 * <code>SingleLink</code> class.
 *
 * @author David Brown
 * @version 2025-01-05
 * @param <T> this SingleList data type.
 */
public class SingleList<T extends Comparable<T>> extends SingleLink<T> {

	 /**
     * Searches for the first occurrence of key in this SingleList. Private helper
     * methods - used only by other ADT methods.
     *
     * @param key The object to look for.
     * @return A pointer to the node previous to the node containing key.
     */
    private SingleNode<T> linearSearch(final T key) {

	SingleNode<T> prev = null;
	SingleNode<T> node = this.front;

	while (node != null && node.getEntity().compareTo(key) != 0) {
	    prev = node;
	    node = node.getNext();
	}

	return prev;
    }

    /**
     * Appends data to the end of this SingleList.
     *
     * @param entity The object to append.
     */
    public void append(final T data) {

	SingleNode<T> node = new SingleNode<>(data, null);

	if (this.front == null) {
	    this.front = node;
		this.rear = node;
	} else {
	    this.rear.setNext(node);
		this.rear = node;
	}
	this.length++;
    }

    /**
     * Removes duplicates from this SingleList. The list contains one and only one
     * of each object formerly present in this SingleList. The first occurrence of
     * each object is preserved.
     */
    public void clean() {

	SingleNode<T> node = this.front;

	while (node != null) {
	    SingleNode<T> iter = node;
	    while (iter.getNext() != null) {
		if (iter.getNext().getEntity().compareTo(node.getEntity()) == 0) {
		    iter.setNext(iter.getNext().getNext());
		    this.length--;
		    if (iter.getNext() == null) {
			this.rear = iter;
		    }
		} else {
		    iter = iter.getNext();
		}
	    }
	    node = node.getNext();
		}
    }

    /**
     * Combines contents of two lists into a third. Values are alternated from the
     * origin lists into this SingleList. The origin lists are empty when finished.
     * NOTE: data must not be moved, only nodes.
     *
     * @param left  The first list to combine with this SingleList.
     * @param right The second list to combine with this SingleList.
     */
    public void combine(final SingleList<T> left, final SingleList<T> right) {

	while (!left.isEmpty() || !right.isEmpty()) {
	    if (!left.isEmpty()) {
		this.moveFrontToRear(left);
	    	}
	    if (!right.isEmpty()) {
		this.moveFrontToRear(right);
	    	}
		}
    }

    /**
     * Determines if this SingleList contains key.
     *
     * @param key The key object to look for.
     * @return true if key is in this SingleList, false otherwise.
     */
    public boolean contains(final T key) {

	SingleNode<T> previous = this.linearSearch(key);
	return (previous == null && this.front != null && this.front.getEntity().compareTo(key) == 0)
		|| (previous != null && previous.getNext() != null);
    }

    /**
     * Finds the number of times key appears in list.
     *
     * @param key The object to look for.
     * @return The number of times key appears in this SingleList.
     */
    public int count(final T key) {

	int count = 0;
	SingleNode<T> node = this.front;

	while (node != null) {
	    if (node.getEntity().compareTo(key) == 0) {
		count++;
	    }
	    node = node.getNext();
	}
	return count;
    }
    /**
     * Finds and returns the object in list that matches key.
     *
     * @param key The object to search for.
     * @return The object that matches key, null otherwise.
     */
    public T find(final T key) {

	SingleNode<T> prev = this.linearSearch(key);
	T value = null;

	if (prev == null && this.front != null && this.front.getEntity().compareTo(key) == 0) {
	    value = this.front.getEntity();
	} else if (prev != null && prev.getNext() != null) {
	    value = prev.getNext().getEntity();
	}
	return value;
    }

    /**
     * Get the nth object in this SingleList.
     *
     * @param n The index of the object to return.
     * @return The nth object in this SingleList.
     * @throws ArrayIndexOutOfBoundsException if n is not a valid index.
     */
    public T get(final int n) throws ArrayIndexOutOfBoundsException {

	if (n < 0 || n >= this.length) {
	    throw new ArrayIndexOutOfBoundsException("Invalid index: " + n);
	}

	SingleNode<T> node = this.front;
	for (int i = 0; i < n; i++) {
	    node = node.getNext();
	}
	return node.getEntity();
    }

    /**
     * Determines whether two lists are identical.
     *
     * @param source The list to compare against this SingleList.
     * @return true if this SingleList contains the same objects in the same order
     *         as source, false otherwise.
     */
    public boolean equals(final SingleList<T> source) {

	boolean isEqual = this.length == source.length;
	SingleNode<T> current = this.front;
	SingleNode<T> sourceCurrent = source.front;

	while (isEqual && current != null) {
	    isEqual = current.getEntity().compareTo(sourceCurrent.getEntity()) == 0;
	    current = current.getNext();
	    sourceCurrent = sourceCurrent.getNext();
	}
	return isEqual;
    }

    /**
     * Finds the first location of a object by key in this SingleList.
     *
     * @param key The object to search for.
     * @return The index of key in this SingleList, -1 otherwise.
     */
    public int index(final T key) {

	int index = 0;
	SingleNode<T> current = this.front;

	while (current != null && current.getEntity().compareTo(key) != 0) {
	    current = current.getNext();
	    index++;
	}
	return current != null ? index : -1;
    }

    /**
     * Inserts object into this SingleList at index i. If i greater than the length
     * of this SingleList, append data to the end of this SingleList.
     *
     * @param i      The index to insert the new data at.
     * @param entity The new object to insert into this SingleList.
     */
    public void insert(int i, final T data) {

	if (i <= 0) {
	    this.prepend(data);
	} else if (i >= this.length) {
	    this.append(data);
	} else {
	    SingleNode<T> current = this.front;
	    for (int j = 1; j < i; j++) {
		current = current.getNext();
	    }
	    SingleNode<T> node = new SingleNode<>(data, current.getNext());
	    current.setNext(node);
	    this.length++;
	}
    }

    /**
     * Creates an intersection of two other SingleLists into this SingleList. Copies
     * data to this SingleList. left and right SingleLists are unchanged. Values
     * from left are copied in order first, then objects from right are copied in
     * order.
     *
     * @param left  The first SingleList to create an intersection from.
     * @param right The second SingleList to create an intersection from.
     */
    public void intersection(final SingleList<T> left, final SingleList<T> right) {

	SingleNode<T> leftCurrent = left.front;

	while (leftCurrent != null) {
	    SingleNode<T> rightCurrent = right.front;
	    while (rightCurrent != null) {
		if (leftCurrent.getEntity().compareTo(rightCurrent.getEntity()) == 0) {
		    this.append(leftCurrent.getEntity());
		    break;
		}
		rightCurrent = rightCurrent.getNext();
	    }
	    leftCurrent = leftCurrent.getNext();
	}
    }
    /**
     * Finds the maximum object in this SingleList.
     *
     * @return The maximum object.
     */
    public T max() {

	if (this.front == null) {
	    return null;
	}

	T max = this.front.getEntity();
	SingleNode<T> current = this.front.getNext();

	while (current != null) {
	    if (current.getEntity().compareTo(max) > 0) {
		max = current.getEntity();
	    }
	    current = current.getNext();
	}
	return max;
    }

    /**
     * Finds the minimum object in this SingleList.
     *
     * @return The minimum object.
     */
    public T min() {

	if (this.front == null) {
	    return null;
	}

	T min = this.front.getEntity();
	SingleNode<T> current = this.front.getNext();

	while (current != null) {
	    if (current.getEntity().compareTo(min) < 0) {
		min = current.getEntity();
	    }
	    current = current.getNext();
	}
	return min;
    }

    /**
     * Inserts object into the front of this SingleList.
     *
     * @param entity The object to insert into the front of this SingleList.
     */
    public void prepend(final T data) {

	SingleNode<T> node = new SingleNode<>(data, this.front);
	this.front = node;
	if (this.rear == null) {
	    this.rear = node;
	}
	this.length++;
    }
    /**
     * Finds, removes, and returns the object in this SingleList that matches key.
     *
     * @param key The object to search for.
     * @return The object matching key, null otherwise.
     */
    public T remove(final T key) {

	T result = null;
	SingleNode<T> previous = this.linearSearch(key);

	if (previous == null && this.front != null && this.front.getEntity().compareTo(key) == 0) {
	    result = this.front.getEntity();
	    this.front = this.front.getNext();
	    this.length--;
	    if (this.front == null) {
		this.rear = null;
	    }
	} else if (previous != null && previous.getNext() != null) {
	    result = previous.getNext().getEntity();
	    previous.setNext(previous.getNext().getNext());
	    this.length--;
	    if (previous.getNext() == null) {
		this.rear = previous;
	    }
	}
	return result;
    }

    /**
     * Removes the object at the front of this SingleList.
     *
     * @return The object at the front of this SingleList.
     */
    public T removeFront() {

	T result = null;
	if (this.front != null) {
	    result = this.front.getEntity();
	    this.front = this.front.getNext();
	    this.length--;
	    if (this.front == null) {
		this.rear = null;
	    }
	}
	return result;
    }

    /**
     * Finds and removes all objects in this SingleList that match key.
     *
     * @param key The object to search for.
     */
    public void removeMany(final T key) {
	while (this.front != null && this.front.getEntity().compareTo(key) == 0) {
	    this.front = this.front.getNext();
	    this.length--;
	}

	if (this.front != null) {
	    SingleNode<T> node = this.front;
	    while (node.getNext() != null) {
		if (node.getNext().getEntity().compareTo(key) == 0) {
		    node.setNext(node.getNext().getNext());
		    this.length--;
		} else {
		    node = node.getNext();
		}
	    }
	    this.rear = node;
	} else {
	    this.rear = null;
	}
    }

    /**
     * Reverses the order of the objects in this SingleList.
     */
    public void reverse() {
	if (this.front != null && this.front.getNext() != null) {
	    this.rear = this.front;
	    SingleNode<T> prev = null;
	    SingleNode<T> cur = this.front;
	    SingleNode<T> node = null;

	    while (cur != null) {
		node = cur.getNext();
		cur.setNext(prev);
		prev = cur;
		cur = node;
	    }
	    this.front = prev;
	}
    }

    /**
     * Splits the contents of this SingleList into the left and right SingleLists.
     * Moves nodes only - does not move object or call the high-level methods insert
     * or remove. this SingleList is empty when done. The first half of this
     * SingleList is moved to left, and the last half of this SingleList is moved to
     * right. If the resulting lengths are not the same, left should have one more
     * object than right. Order is preserved.
     *
     * @param left  The first SingleList to move nodes to.
     * @param right The second SingleList to move nodes to.
     */
    public void split(final SingleList<T> left, final SingleList<T> right) {

	if (this.front != null) {
	    int lsz = (this.length + 1) / 2;
	    int rsz = this.length - lsz;

	    left.front = this.front;
	    SingleNode<T> current = this.front;

	    for (int i = 1; i < lsz; i++) {
		current = current.getNext();
	    }

	    right.front = current.getNext();
	    left.rear = current;
	    right.rear = this.rear;
	    current.setNext(null);

	    left.length = lsz;
	    right.length = rsz;

	    this.front = null;
	    this.rear = null;
	    this.length = 0;
	 }
    }

    /**
     * Splits the contents of this SingleList into the left and right SingleLists.
     * Moves nodes only - does not move object or call the high-level methods insert
     * or remove. this SingleList is empty when done. Nodes are moved alternately
     * from this SingleList to left and right. Order is preserved.
     *
     * @param left  The first SingleList to move nodes to.
     * @param right The second SingleList to move nodes to.
     */
    public void splitAlternate(final SingleList<T> left, final SingleList<T> right) {

	boolean swap = true;
	while (this.front != null) {
	    if (swap) {
		left.moveFrontToRear(this);
	    } else {
		right.moveFrontToRear(this);
	    }
	    swap = !swap;
	  }
    }

    /**
     * Creates a union of two other SingleLists into this SingleList. Copies object
     * to this list. left and right SingleLists are unchanged. Values from left are
     * copied in order first, then objects from right are copied in order.
     *
     * @param left  The first SingleList to create a union from.
     * @param right The second SingleList to create a union from.
     */
    public void union(final SingleList<T> left, final SingleList<T> right) {

	SingleNode<T> node = left.front;
	while (node != null) {
	    this.append(node.getEntity());
	    node = node.getNext();
	}

	node = right.front;
	while (node != null) {
	    if (!this.contains(node.getEntity())) {
		this.append(node.getEntity());
	    }
	    node = node.getNext();
	}
    }

}
