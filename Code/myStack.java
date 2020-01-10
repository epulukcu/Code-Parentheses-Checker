import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * @author Elif Pulukçu
 * November 16th, 2019
 * @param <Item>
 * Program Explanation: myStack is a generic class which creates a stack and checks its some properties.
 */
public class myStack<Item> implements Iterable<Item> {

	private Item[] a; // array of items
	private int n; // number of elements in stack
	/**
	 * Constructor creates an empty stack
	 */
	public myStack() {
		a = (Item[]) new Object[2];
		n = 0;
	}

	/**
	 *
	 * @return
	 * isEmpty() method returns true if the stack has no elements.
	 * On the other hand, method returns false if the stack has at least one element.
	 */
	public boolean isEmpty() { return n == 0; }

	/**
	 * 
	 * @return 
	 * size() method returns number of elements in the stack.
	 */
	public int size() { return n; }

	/**
	 * 
	 * @param capacity
	 * This method takes the capacity of the array as an parameter
	 * and copies the content of the array to another one.
	 * "assert" is a keyword in Java. Assertion mechanism works simple. If the corresponding
	 * assertion condition is not true, an error is thrown (Assertion error).
	 * Otherwise, the code flow will continue. 
	 */
	private void resize(int capacity) {
		assert capacity >= n;
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < n; i++) {
			temp[i] = a[i];
		}
		a = temp;
	}

	/**
	 * 
	 * @param item
	 * This method pushes items to the stack. However, if the stack is already full
	 * it makes stack double.
	 */
	public void push(Item item) {
		if (n == a.length) {
			resize(2 * a.length); // double size of array if necessary
			System.out.print("\n\n << stack doubled >> \n\n");
		}
		a[n++] = item; // add item
	}

	/**
	 * 
	 * @return
	 * This method, firstly, checks if the stack is empty or not. Trying to remove elements
	 * from an empty stack is called underflow. Then, return and removes the top element of the stack.
	 */
	public Item pop() {
		if (isEmpty())
			throw new NoSuchElementException("Stack underflow");
		Item item = a[n - 1];
		a[n - 1] = null; // to avoid loitering
		n--;
		// shrink size of array if necessary
		if (n > 0 && n == a.length / 4)
			resize(a.length / 2);
		return item;
	}

	/**
	 * 
	 * @return
	 * It returns which element is at the top of the stack. This method does not remove the top element.
	 */
	public Item peek() {
		if (isEmpty())
			throw new NoSuchElementException("Stack underflow"); //if you pop when the stack is empty, you get a “stack underflow” error
		return a[n - 1];
	}


	public Iterator<Item> iterator() {
		return new ReverseArrayIterator();
	}

	/**
	 * 
	 *This interface is a member of the Java Collections Framework.
	 */
	private class ReverseArrayIterator implements Iterator<Item> { // Item is the type of elements returned by this iterator
		private int i;
		public ReverseArrayIterator() { i = n - 1; }
		public boolean hasNext() { return i >= 0; } //Have the loop iterate as long as hasNext( ) returns true
		public void remove() { throw new UnsupportedOperationException(); } //// Remove the next element in the iteration
		public Item next() {
			if (!hasNext()) //If no more element present it throws exception
				throw new NoSuchElementException(); 
			return a[i--];
		}
	}
}