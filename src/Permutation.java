import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {

    public static void main(String[] args) {
        int numberOfItemsToBePrinted = 0;
        if (args.length == 0) {
            numberOfItemsToBePrinted = 3;
        }
        else {
            numberOfItemsToBePrinted = Integer.parseInt(args[0]);
        }

        Deque<String> deque = new Deque<>();

        for (int i = 0; i < numberOfItemsToBePrinted; i++) {
            deque.addFirst(StdIn.readString());
        }

        for (String item: deque) {
            System.out.println(item);
        }

    }
}
