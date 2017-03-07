import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RandomizedQueueTest {
    @Test
    public void new_queue_should_be_empty() {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

        assertThat(randomizedQueue.isEmpty(), is(true));
    }

    @Test
    public void new_queue_shoud_have_size_of_zero() {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

        assertThat(randomizedQueue.size(), is(0));
    }

    @Test(expected = NullPointerException.class)
    public void enqueuing_null_item_should_throw_NullPointerException() {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void dequeuing_empty_queue_should_throw_NoSuchElementException() {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.dequeue();
    }
    @Test
    public void test() {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue("A");
        randomizedQueue.enqueue("B");
        randomizedQueue.enqueue("C");

        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());


        assertThat(randomizedQueue.size(), is (3));
        assertThat(randomizedQueue.isEmpty(), is(false));

        randomizedQueue.dequeue();
        assertThat(randomizedQueue.size(), is (2));

        randomizedQueue.dequeue();
        assertThat(randomizedQueue.size(), is (1));

        randomizedQueue.dequeue();
        assertThat(randomizedQueue.isEmpty(), is (true));

        try {
            randomizedQueue.dequeue();
            fail();
        } catch (NoSuchElementException ex) {
            assertThat(ex.getMessage(), is("Cannot dequeue from empty queue"));
        }
    }

    @Test
    public void test_iterator() {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue("A");
        randomizedQueue.enqueue("B");
        randomizedQueue.enqueue("C");
        randomizedQueue.enqueue("D");

        randomizedQueue.dequeue();
        randomizedQueue.sample();
        randomizedQueue.sample();
        randomizedQueue.sample();

        for (String item: randomizedQueue) {
            System.out.println(item);
        }
    }


}