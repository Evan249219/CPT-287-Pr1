
//doubly-linked list class
//copied from lecture_2 notes

public class Linked_List<T> implements Iterable<T>{
    /** A doubly-linked list node */
    private class Node {
        T data;
        Node next, prev;
        Node(T value) { data = value; }
    }

    // Data fields
    private Node head, tail;
    private int numOfItems;

    // Constructors

    public Linked_List() {}  // Default constructor

    public Linked_List(Linked_List<T> other) {  // Copy constructor
        numOfItems = other.numOfItems;
        if (numOfItems != 0) {
            head = tail = new Node(other.head.data);
            Node q = other.head.next;
            while (q != null) {
                tail.next = new Node(q.data);
                tail.next.prev = tail;
                tail = tail.next;
                q = q.next;
            }
        }
    }

    // Methods

    /** Returns the size of the linked list.
        @return: the number of items stored in the linked list
    */
    public int size() { return numOfItems; }  // Time complexity: O(1)

    /** Tests whether the linked list is empty.
        @return: {true} if the list is empty; {false} otherwise
    */
    public boolean isEmpty() { return numOfItems == 0; }  // Time complexity: O(1)

    /** Returns the value stored in the head of the linked list.
        @throws IllegalArgumentException: list is empty.
        @return: the value stored in the head of the linked list
    */
    public T getFirst() {
        if (isEmpty()) { throw new IllegalArgumentException("Accessing item in empty list"); }
        return head.data;
    }  // Time complexity: O(1)

    /** Returns the value stored in the tail of the linked list.
        @throws IllegalArgumentException: list is empty.
        @return: the value stored in the tail of the linked list
    */
    public T getLast() {
        if (isEmpty()) { throw new IllegalArgumentException("Access item in empty list"); }
        return tail.data;
    }  // Time complexity: O(1)

    /** Adds a new item to the front of the linked list.
        @param item: the new item to be added to the list
    */
    public void addFirst(T item) {
        if (numOfItems++ == 0) { head = tail = new Node(item); }
        else {
            Node newNode = new Node(item);
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }  // Time complexity: O(1)

    /** Adds a new item to the end of the linked list.
        @param item: the new item to be added to the list
    */
    public void addLast(T item) {
        if (numOfItems++ == 0) { head = tail = new Node(item); }
        else {
            Node newNode = new Node(item);
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
    }  // Time complexity: O(1)

    /** Deletes the first node (head) from the linked list.
        @throws IllegalArgumentException: list is empty.
        @return: the item deleted
    */
    public T removeFirst() {
        if (isEmpty()) { throw new IllegalArgumentException("Deleting item from empty list"); }
        T deleted = head.data;
        if (numOfItems-- == 1) { head = tail = null; }
        else {
            head = head.next;
            head.prev = null;
        }
        return deleted;
    }  // Time complexity: O(1)

    /** Deletes the last node (tail) from the linked list.
        @throws IllegalArgumentException: list is empty.
        @return: the item deleted
    */
    public T removeLast() {
        if (isEmpty()) { throw new IllegalArgumentException("Deleting item from empty list."); }
        T deleted = tail.data;
        if (numOfItems-- == 1) { head = tail = null; }
        else {
            tail = tail.prev;
            tail.next = null;
        }
        return deleted;
    }  // Time complexity: O(1)

    /** Tests whether a target value appears in the linked list.
        @param target: the value to search
        @return: {true} if target found in the list; {false} otherwise
    */
    public boolean contains(T target) {
        Node p = head;
        while (p != null) {
            if (p.data.equals(target)) { return true; }
            p = p.next;
        }
        return false;
    }  // Time complexity: O(n)

    /** Deletes all the items from the linked list. */
    public void clear() {
        head = tail = null;
        numOfItems = 0;
    }

    /** Generates a List_Iterator positioned at the beginning of the list.
        @return: a List_iterator positioned at the beginning of the list.
    */
    @Override
    public List_Iterator<T> iterator() {
        return new List_Iterator<T>() {
            // Data fields
            private Node nextNode = head, prevNode = null;

            /** Tests whether there is a next item at current iterator position.
                @return: {true} if current iterator is not at the end of the list; {false} otherwise.
            */
            @Override
            public boolean hasNext() { return nextNode != null; }

            /** Moves the iterator forward and returns the item passed by.
                @return: the next item at current iterator position
                @throws IllegalArgumentException: iterator has already reached the end of the list.
             */
            @Override
            public T next() {
                if (!hasNext()) { throw new IllegalArgumentException("Reaching end position"); }
                T value = nextNode.data;
                prevNode = nextNode;
                nextNode = nextNode.next;
                return value;
            }

            /** Deletes the next item at current iterator position.
                @return: the item deleted
                @throws IllegalArgumentException: iterator has already reached the end of the list.
            */
            @Override
            public T removeNext() {
                if (!hasNext()) { throw new IllegalArgumentException("Removing null reference"); }
                T value = nextNode.data;
                if (numOfItems-- == 1) { head = tail = prevNode = nextNode = null; }
                else if (prevNode == null) {  // The node to be removed is the head.
                    nextNode = nextNode.next;
                    nextNode.prev = null;
                    head = nextNode;
                } else if (nextNode.next == null) {  // The node to be removed is the tail.
                    nextNode = null;
                    prevNode.next = null;
                    tail = prevNode;
                } else {
                    prevNode.next = nextNode.next;
                    nextNode = nextNode.next;
                    nextNode.prev = prevNode;
                }
                return value;
            }

            /** Tests whether there is a previous item at current iterator position.
                @return: {true} if current iterator is not at the beginning of the list; {false} otherwise.
            */
            @Override
            public boolean hasPrevious() { return prevNode != null; }

            /** Moves the iterator backward and returns the item passed by.
                @return: the previous item at current iterator position
                @throws IllegalArgumentException: iterator has already reached the beginning of the list.
            */
            @Override
            public T previous() {
                if (!hasPrevious()) { throw new IllegalArgumentException("Reaching beginning position"); }
                T value = prevNode.data;
                nextNode = prevNode;
                prevNode = prevNode.prev;
                return value;
            }

            /** Deletes the previous item at current iterator position.
                @return: the item deleted
                @throws IllegalArgumentException: iterator has already reached the beginning of the list.
            */
            @Override
            public T removePrevious() {
                if (!hasPrevious()) { throw new IllegalArgumentException("Removing null reference"); }
                T value = prevNode.data;
                if (numOfItems-- == 1) { head = tail = nextNode = prevNode = null; }
                else if (prevNode.prev == null) {  // The node to be removed is the head.
                    prevNode = null;
                    nextNode.prev = null;
                    head = nextNode;
                } else if (nextNode == null) {  // The node to be removed is the tail.
                    prevNode = prevNode.prev;
                    prevNode.next = null;
                    tail = prevNode;
                } else {
                    nextNode.prev = prevNode.prev;
                    prevNode = prevNode.prev;
                    prevNode.next = nextNode;
                }
                return value;
            }

            /** Adds a new item at current iterator position.
                @param item: the item to add
            */
            @Override
            public void add(T item) {
                if (isEmpty()) {
                    head = tail = new Node(item);
                    prevNode = head;
                    numOfItems++;
                } else if (!hasPrevious()) {  // The new item will be the new head of the list.
                    addFirst(item);
                    prevNode = head;
                    nextNode = head.next;
                } else if (!hasNext()) {  // The new item will be the new tail of the list.
                    addLast(item);
                    prevNode = tail;
                    nextNode = null;
                } else {
                    Node newNode = new Node(item);
                    newNode.prev = prevNode;
                    newNode.prev.next = newNode;
                    newNode.next = nextNode;
                    newNode.next.prev = newNode;
                    prevNode = newNode;
                    numOfItems++;
                }
            }

            /** Updates the value of the next item at current iterator position.
                @param item: the updated value
                @throws IllegalArgumentException: iterator has already reached the end of the list.
            */
            @Override
            public void setNext(T item) {
                if (!hasNext()) { throw new IllegalArgumentException("Setting value for null reference"); }
                nextNode.data = item;
                next();  // Moves the iterator forward.
            }

            /** Updates the value of the previous item at current iterator position.
                @param item: the updated value
                @throws IllegalArgumentException: iterator has already reached the beginning of the list.
             */
            @Override
            public void setPrevious(T item) {
                if (!hasPrevious()) { throw new IllegalArgumentException("Setting value for null reference"); }
                prevNode.data = item;
                previous();
            }

            /** Moves the iterator to the beginning of the list. */
            @Override
            public void reset() {
                nextNode = head;
                prevNode = null;
            }
        };
    }
}
