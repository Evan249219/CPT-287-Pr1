
//iterator interface
//copied from lecture_2 notes
public interface Iterator<T> {
    /** Tests whether there is a next item after the current position.
    @return: {true} if there exists a next item; {false} otherwise
	*/
	boolean hasNext();
	
	/** Moves the iterator forward and returns the item passed by.
	    @return: the item passed by during the iterator movement
	    @throws IllegalArgumentException: iterator has no next position.
	*/
	T next();
	
	/** Removes the next item at current iterator position.
	    @return: the item deleted
	    @throws IllegalArgumentException: iterator has no next position.
	*/
	T removeNext();

}
