import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DequeTest {

    @Test
    public void new_deque_should_be_empty() {
        Deque<String> deque = new Deque<>();
        assertThat(deque.isEmpty(), is(true));
    }

    @Test
    public void two_items_deque_should_not_be_empty() {
        Deque<String> deque = new Deque<>();
        deque.addFirst("A");
        deque.addFirst("B");

        assertThat(deque.isEmpty(), is(false));
    }

    @Test
    public void testFirst() {
        Deque<String> deque = new Deque<>();
        deque.addFirst("A");

        deque.addFirst("B");

        deque.addFirst("C");
        System.out.println(deque);
        System.out.println();

        deque.removeFirst();
        System.out.println(deque);
        System.out.println();

        deque.removeFirst();
        System.out.println(deque);
        System.out.println();

        deque.removeFirst();
        System.out.println(deque);
        System.out.println();

        deque.removeFirst();
        System.out.println(deque);
        System.out.println();

    }

    @Test
    public void testLast() {
        Deque<String> deque = new Deque<>();
        deque.addLast("A");
        deque.addLast("B");
        deque.addLast("C");
        System.out.println(deque);
        System.out.println();

        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
        System.out.println(deque);
        System.out.println();

    }


}