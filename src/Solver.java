import edu.princeton.cs.algs4.*;

import java.util.Comparator;

public class Solver {

    private final Board initial;
    private int moves = 0;

    public Solver(Board initial) {
        this.initial = initial;
    }

    public boolean isSolvable() {
        MinPQ<Board> priorityQueue = new MinPQ<>(new Comparator<Board>() {
            @Override
            public int compare(Board o1, Board o2) {
                return Integer.valueOf(o1.manhattan()).compareTo(Integer.valueOf(o2.manhattan()));
            }
        });

        priorityQueue.insert(initial);
        Board twin = initial.twin();
        priorityQueue.insert(twin);

        System.out.println(initial.manhattan());
        System.out.println(twin.manhattan());
        priorityQueue.delMin();

        priorityQueue.forEach(System.out::println);

        return true;
    }
}
