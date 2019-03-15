import java.util.ArrayList;

/**
 * @Classname RandomQueue
 * @Description TODO
 * @Date 2019-03-13 11:16
 * @Created by guo
 */
public class RandomQueue<E> {
    ArrayList<E> queue;

    public RandomQueue() {
        queue = new ArrayList<E>();
    }
    public void add(E e){
        queue.add(e);
    }
    public E remove(){
        if (queue.size() == 0)
            throw new IllegalArgumentException("There's no element to remove in this queue");
        int randIndex = (int)(Math.random()*queue.size());
        E randElement = queue.get(randIndex);
        queue.set(randIndex,queue.get(queue.size()-1));
        queue.remove(queue.size()-1);
        return randElement;
    }
    public int size(){
        return queue.size();
    }
    public boolean empty(){
        return queue.size()==0;
    }
}
