import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (size >= items.length) {
            resize(items.length * 2);
        }

        items[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (size == items.length / 4) {
            resize(items.length / 2);
        }

        int rand = StdRandom.uniform(size);

        Item i = items[rand];
        items[rand] = items[--size];
        items[size] = null;
        return i;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int upperbound = size;
        int rand = StdRandom.uniform(upperbound);

        Item i = items[rand];
        return i;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {

        private int curr;
        private final Item[] shuffledArray;

        public RandomizedIterator() {
            curr = 0;
            shuffledArray = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                shuffledArray[i] = items[i];
            }
            StdRandom.shuffle(shuffledArray); 
        }

        public void remove() { 
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return curr < size;
        }

        @Override
        public Item next() {
            // TODO Auto-generated method stub
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return shuffledArray[curr++];
        }

    }

    private void resize(int newCapacity) {
        Item[] newItemArray = (Item[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newItemArray[i] = items[i];
        }
        items = newItemArray;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        for (int i = 0; i < 10; ++i) 
            rq.enqueue(i);
        Iterator<Integer> it = rq.iterator();
        while (it.hasNext()) StdOut.print(it.next() + " ");

    }

}