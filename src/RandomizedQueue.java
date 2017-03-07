import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int itemsInserted = 0;
    private Item[] items;

    public RandomizedQueue() {
        items = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return this.itemsInserted == 0;
    }

    public int size() {
        return itemsInserted;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("cannot enqueue null");
        }
        if (itemsInserted == items.length) {
            resize(2 * items.length);
        }
        items[itemsInserted++] = item;
    }

    public Item dequeue() {
        if (itemsInserted == 0) {
            throw new NoSuchElementException("Cannot dequeue from empty queue");
        }

        StdRandom.shuffle(items, 0, itemsInserted);
        Item item = items[itemsInserted - 1];
        items[itemsInserted - 1] = null;
        itemsInserted--;

        if (itemsInserted > 0 && itemsInserted == items.length /4) {
            resize(items.length / 2);
        }

        return item;
    }

    public Item sample() {
        if (itemsInserted == 0) {
            throw new NoSuchElementException("Cannot sample from empty queue");
        }

        StdRandom.shuffle(items, 0, itemsInserted - 1);
        return items[itemsInserted - 1];
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < itemsInserted; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int indice = 0;
        private int[] intrandomIndices;

        public RandomizedQueueIterator() {
            intrandomIndices = StdRandom.permutation(itemsInserted);
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item item = items[intrandomIndices[indice]];
            indice++;
            return item;
        }

        @Override
        public boolean hasNext() {
            return indice < itemsInserted;
        }
    }
}
