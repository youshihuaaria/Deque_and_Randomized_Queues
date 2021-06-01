import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node first;
    private Node last;

    // construct an empty deque
    public Deque() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    private class Node {
        Item item;
        Node next;
        Node prev;

        public Node(Item item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        // Throw an IllegalArgumentException if the client calls either 
        // addFirst() or addLast() with a null argument.
        if (item == null) {
            throw new IllegalArgumentException();
        }
        // case one: size = 0
        if (size() == 0) {
            first = new Node(item, null, null);
            last = first;
        } else {
            Node oldFirst = first;
            first = new Node(item, null, null);
            oldFirst.prev = first;
            first.next = oldFirst;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (size() == 0) {
            last = new Node(item, null, null);
            first = last;
        } else {
            Node oldLast = last;
            last = new Node(item, null, null);
            oldLast.next = last;
            last.prev = oldLast;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item i = first.item;
        if (size() == 1) {
            first = null;
            last = null;
        } else {
            Node oldFirst = first;
            first = first.next;
            oldFirst.next = null;
            first.prev = null;
        }
        size--;
        return i;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item i = last.item;
        if (size() == 1) {
            first = null;
            last = null;
        } else {
            Node oldLast = last;
            last = oldLast.prev;
            last.next = null;
            oldLast.prev = null;
        }
        size--;
        return i;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node curr;

        public DequeIterator() {
            curr = first;
        }

        public boolean hasNext() {
            return curr != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item i = curr.item;
            curr = curr.next;
            return i;
        }

        public void remove() { 
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        for (int i = 0; i < 6; i += 2) {
            deque.addFirst(i); 
            deque.addLast(i+1);
        }
        Iterator<Integer> it = deque.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
    }
}
