import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node firstNode = null;
    private Node lastNode = null;

    private int size = 0;

    public boolean isEmpty() {
        return this.firstNode == null;
    }

    private void addVeryFirstItem(Item item) {
        this.firstNode = new Node();
        this.firstNode.item = item;
        this.firstNode.next = null;
        this.firstNode.previous = null;

        this.lastNode = firstNode;

        this.size++;

    }

    private Item removeVeryLastItem() {
        Item removedItem = firstNode.item;
        this.firstNode = null;
        this.lastNode = null;
        size--;
        return removedItem;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("Cannot addLast null");
        }
        if (this.isEmpty()) {
            this.addVeryFirstItem(item);
            return;
        }

        Node oldLastNode = this.lastNode;

        this.lastNode = new Node();
        this.lastNode.item = item;
        this.lastNode.next = null;
        this.lastNode.previous = oldLastNode;

        oldLastNode.next = this.lastNode;

        if(this.firstNode == null) {
            firstNode.next = this.lastNode;
        }

        size++;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("Cannot addFirst null");
        }

        if (this.isEmpty()) {
            this.addVeryFirstItem(item);
            return;
        }

        Node oldFirstNode = firstNode;

        firstNode = new Node();
        firstNode.item = item;
        firstNode.next = oldFirstNode;
        firstNode.previous = null;

        oldFirstNode.previous = firstNode;

        size++;
    }

    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Cannot remove first item from empty Deque");
        }

        if (this.size == 1) {
            return removeVeryLastItem();
        }

        Node oldFirstNode = this.firstNode;
        firstNode = oldFirstNode.next;
        firstNode.previous = null;
        Item removedItem = oldFirstNode.item;

        size--;

        return removedItem;
    }

    public Item removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Cannot remove last item from empty Deque");
        }

        if (this.size == 1) {
            return removeVeryLastItem();
        }

        Node oldLastNode = this.lastNode;

        this.lastNode = oldLastNode.previous;
        this.lastNode.next = null;

        Item removedItem = oldLastNode.item;

        size--;
        return removedItem;
    }

    public int size() {
        return this.size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = firstNode;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    @Override
    public String toString() {
        String first = this.firstNode != null ? this.firstNode.toString() : "null";
        String last = this.lastNode != null ? this.lastNode.toString() : "null";
        return "First node : " + first + "\n" + "Last node : " + last;
    }

    private class Node {
        Item item;
        Node next;
        Node previous;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            if (previous != null) {
                builder.append(previous.item);
            }
            else {
                builder.append("null");
            }
            builder.append("<-");
            builder.append(item);
            builder.append("->");
            if (next != null) {
                builder.append(next.item);
            }
            else {
                builder.append("null");
            }
            return builder.toString();
        }
    }
}

