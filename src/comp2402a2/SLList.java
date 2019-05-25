package comp2402a2;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.Queue;
import java.util.NoSuchElementException;

/**
 * An implementation of a FIFO Queue as a singly-linked list.
 * This also includes the stack operations push and pop, which
 * operate on the head of the queue
 * @author morin
 *
 * @param <T> the class of objects stored in the queue
 */
public class SLList<T> extends AbstractList<T> implements Queue<T> {
	class Node {
		T x;
		Node next;
	}

	/**
	 * Front of the queue
	 */
	Node head;

	/**
	 * Tail of the queue
	 */
	Node tail;

	/**
	 * The number of elements in the queue
	 */
	int n;

	public T get(int i) {
		// TODO: Implement this
		if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
		if (n == 0 ) {
			return null;
		}
		return getNode(i).x;
	}

	public T set(int i, T x) {
		// TODO: Implement this
		if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
		if (n == 0 ) {
			return null;
		}
		Node a = getNode(i);
		T b = a.x;
		getNode(i).x = x;
		return b;
	}

	public void add(int i, T x) {
		// TODO: Implement this
		if (i < 0 || i > n) throw new IndexOutOfBoundsException();
		Node a = new Node();
		a.x = x;
		Node p = null;
		Node c = head;
		if (i == 0 || head == null){
			push(x);
		}
		else{
			int count = i;
			while (count > 0) {
				p = c;
				c = c.next;
				count--;
			}
			p.next = a;
			a.next = c;
			if (n - i ==  0) {
				tail = a;
			}
			n++;
		}
	}

	public T remove(int i) {
		// TODO: Implement this
		if (n == 0 || head == null) {
			return null;
		}
		if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
		Node c = head;
		if (i == 0) {
			Node a = head;
			head = c.next;
			n--;
			return a.x;
		}
		int count = 0;
		while (c != null && count < i - 1) {
			c = c.next;
			count++;
		}
		if (c == null || c.next == null){
			return null;
		}
		Node d = c.next;
		c.next = c.next.next;
		n--;
		if (count == n - 1) {
			tail = c;
		}
		return d.x;
	}

	public void reverse() {
		// TODO: Implement this
		Node a;
		Node p = null;
		Node c = head;
		while (c != null) {
			a = c.next;
			c.next = p;
			p = c;
			c = a;
		}
		head = p;
		tail = getNode(n - 1);
	}

	public Iterator<T> iterator() {
		class SLIterator implements Iterator<T> {
			protected Node p;

			public SLIterator() {
				p = head;
			}
			public boolean hasNext() {
				return p != null;
			}
			public T next() {
				T x = p.x;
				p = p.next;
				return x;
			}
			public void remove() {
				throw new UnsupportedOperationException();
			}
		}
		return new SLIterator();
	}

	public int size() {
		return n;
	}

	public boolean add(T x) {
		Node u = new Node();
		u.x = x;
		if (n == 0) {
			head = u;
		} else {
			tail.next = u;
		}
		tail = u;
		n++;
		return true;
	}

	public boolean offer(T x) {
		return add(x);
	}

	public T peek() {
		if (n == 0) return null;
		return head.x;
	}

	public T element() {
		if (n == 0) throw new NoSuchElementException();
		return head.x;
	}

	public T poll() {
		if (n == 0)
			return null;
		T x = head.x;
		head = head.next;
		if (--n == 0)
			tail = null;
		return x;
	}

	/**
	 * Stack push operation - push x onto the head of the list
	 * @param x the element to push onto the stack
	 * @return x
	 */
	public T push(T x) {
		Node u = new Node();
		u.x = x;
		u.next = head;
		head = u;
		if (n == 0)
			tail = u;
		n++;
		return x;
	}

	protected void deleteNext(Node u) {
		if (u.next == tail)
			tail = u;
		u.next = u.next.next;
	}

	protected void addAfter(Node u, Node v) {
		v = u.next.next;
		u.next = v;
		if (u == tail)
			tail = v;
	}

	protected Node getNode(int i) {
		Node u = head;
		for (int j = 0; j < i; j++)
			u = u.next;
		return u;
	}

	/**
	 * Stack pop operation - pop off the head of the list
	 * @return the element popped off
	 */
	public T remove() {
		if (n == 0)	return null;
		T x = head.x;
		head = head.next;
		if (--n == 0) tail = null;
		return x;
	}

	public T pop() {
		if (n == 0)	return null;
		T x = head.x;
		head = head.next;
		if (--n == 0) tail = null;
		return x;
	}


	public static void main(String[] args) {


	}
}
