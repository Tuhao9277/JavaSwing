import java.util.LinkedList;

/**
 * @Classname RandomQueue
 * @Description TODO
 * @Date 2019-03-13 11:16
 * @Created by guo
 */
public class RandomQueue<E> {
    LinkedList<E> queue;

    public RandomQueue() {
        queue = new LinkedList<E>();
    }

    public void add(E e) {
        if (Math.random() < 0.5)
            queue.addFirst(e);
        else
            queue.addLast(e);
    }

    public E remove() {
        if (queue.size() == 0) {
            throw new IllegalArgumentException("There's no element to remove in these queue");
        }
        if (Math.random() < 0.5)
            return queue.removeFirst();
        else
            return queue.removeLast();
    }

    public int size() {
        return queue.size();
    }

    public boolean empty() {
        return queue.size() == 0;
    }
}
