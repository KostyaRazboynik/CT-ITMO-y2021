package queue;

import java.util.function.Predicate;

public abstract class AbstractQueue implements Queue {

    protected int size = 0;

    protected abstract Object elementAbstarct();

    protected abstract void enqueueAbstract(Object element);

    protected abstract Object dequeueAbstarct();

    protected abstract void clearAbstract();

    protected abstract int countIfAbstract(Predicate<Object> p);

    @Override
    public Object element() {
        return elementAbstarct();
    }

    @Override
    public void enqueue(Object element) {
        enqueueAbstract(element);
        size++;
    }

    @Override
    public Object dequeue() {
        Object temp = dequeueAbstarct();
        size--;
        return temp;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        clearAbstract();
        size = 0;
    }

    @Override
    public int countIf(Predicate<Object> p) {
        return countIfAbstract(p);
    }
}