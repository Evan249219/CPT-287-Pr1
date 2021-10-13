
//list_iterator interface
//copied from lecture_2 notes

public interface List_Iterator<T> extends Iterator<T>{
    /** Tests whether there is a previous item before the current position.
    @return: {true} if iterator has not reached the beginning of the list; {false} otherwise
	*/
	boolean hasPrevious();
	
	/** Moves the iterator backward and returns the item passed by.
	    @return: the item passed by during the iterator movement
	    @throws IllegalArgumentException: iterator has reached the beginning of the list.
	*/
	T previous();
	
	/** Removes the previous item at current iterator position from the list.
	    @return: the item deleted
	    @throws IllegalArgumentException: iterator has reached the beginning of the list.
	*/
	T removePrevious();
	
	/** Adds a value at the current iterator position.
	    @param item: the item to be inserted
	*/
	void add(T item);
	
	/** Updates the value of the next item at current iterator position.
	    @param item: the updated value
	    @throws IllegalArgumentException: iterator has reached the end of the list.
	*/
	void setNext(T item);
	
	/** Updates the value of the previous item at current iterator position.
	    @param item: the updated value
	    @throws IllegalArgumentException: iterator has reached the beginning of the list.
	*/
	void setPrevious(T item);
	
	/** Moves the iterator to the beginning of the list. */
	void reset();

}
