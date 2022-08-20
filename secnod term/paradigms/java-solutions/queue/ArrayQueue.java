package queue;

import java.util.Arrays;
import java.util.function.Predicate;

public class ArrayQueue extends AbstractQueue {

    private int head = 0;
    private static final int DEF_CAPACITY = 2;
    private static Object[] elements = new Object[1];

    @Override
    protected Object elementAbstarct() {
        return elements[head];
    }

    @Override
    protected void enqueueAbstract(Object el) {
        ensureCapacity(size + 1);

        elements[(head + size) % elements.length] = el;
    }

    @Override
    protected Object dequeueAbstarct() {
        Object el = elementAbstarct();
        elements[head] = null;
        head++;
        if (head == elements.length) {
            head = 0;
        }
        return el;
    }

    private void ensureCapacity(int capacity) {
        if (capacity <= elements.length) {
            return;
        }
        elements = queueToArray(new Object[capacity * DEF_CAPACITY]);
        head = 0;
    }

    @Override
    protected void clearAbstract() {
        Arrays.fill(elements, null);
        head = 0;
    }

    private Object[] queueToArray(Object[] newQueue) {
        int headPart = Math.min(elements.length, head + size) - head;
        System.arraycopy(elements, head, newQueue, 0, headPart);
        System.arraycopy(elements, (head + headPart) % elements.length, newQueue, headPart, size - headPart);
        return newQueue;
    }

    @Override
    public int countIfAbstract(Predicate<Object> p) {
        Object[] newQueue = queueToArray(new Object[size]);
        int count = 0;
        for (Object o : newQueue) {
            if (p.test(o)) {
                count++;
            }
        }
        return count;
    }

    public int count(Object el) {
        Object[] newQueue = queueToArray(new Object[size]);
        int count = 0;
        for (Object o : newQueue) {
            if (o.equals(el)) {
                count++;
            }
        }
        return count;
    }
}


/**
 * HW3 ArrayQueue Version
 */
/*
package queue;

import java.util.Arrays;

// Model: a[1] ... a[n]

// I: forall i: head <= i < head + size && elements[i] != null && size <= elements.length

public class ArrayQueue {
    private int head = 0;
    private int size;
    private static final int DEF_CAPACITY = 2;
    private Object[] elements = new Object[DEF_CAPACITY];
    private static final int defCapacity = 2; // DEFAULT_CAPACITY
    private Object[] elements = new Object[1];

    // Pred: size() > 0
    // Post: elements[head] & Immutable
    public Object element() {
        return elements[head];
    }

    // Pred: el != null
    // Post: size = size' + 1 && ((elements[(head + size) % elements.length] = el) ||
    // || (elements.length' == size()' && elements.length = elements.length' * 10))
    public void enqueue(Object element) {
        ensureCapacity(size + 1);

        elements[(head + size) % elements.length] = element;
        size++;
    }

    // Pred: size() > 0
    // Post: R = elements[head']' && elements[head'] = null && ((size' == 1 && head == 0) || (size' > 1 && head = (head' + 1) % elements.length));
    public Object dequeue() {
        Object el = element();
        elements[head] = null;
        head++;
        size--;
        if (head == elements.length) {
            head = 0;
        }
        return el;
    }

    // Pred: capacity == N && queue != null
    // Post: elements.length = (elements.length)' * 10 || (head' >= (head' + size') % capacity && head == 0)
    private void ensureCapacity(int capacity) {
        if (capacity <= elements.length) {
            return;
        }
        elements = queueToArray(new Object[capacity * DEF_CAPACITY]);
        head = 0;
    }

    // Pred: true
    // Post: size
    public int size() {
        return size;
    }

    // Pred: true
    // Post: R = (size() == 0) && Immutabe
    public boolean isEmpty() {
        return size == 0;
    }

    // Pred: true
    // Post: forall i = 1...n: elements[i] = null && size == head == 0
    public void clear() {
        size = head = 0;
        Arrays.fill(elements, null);
    }

    // Pred: queue != null
    // Post: Очередь стала массивом (элементы упорядочены по моменту добавления)
    public Object[] toArray() {
        return queueToArray(new Object[size]);
    }

    // Pred: queue != null
    // Post: Очередь стала массивом (элементы упорядочены по моменту добавления)
    private Object[] queueToArray(Object[] newQueue) {
        int headPart = Math.min(elements.length, head + size) - head;
        System.arraycopy(elements, head, newQueue, 0, headPart);
        System.arraycopy(elements, (head + headPart) % elements.length, newQueue, headPart, size - headPart);
        return newQueue;
    }

    // Pred: queue != null && element != null
    // Post: count' = число вхождений элемента в очередь && Ummutable
    public int count(Object el) {
        Object[] newQueue = queueToArray(new Object[size]);
        int count = 0;
        for (int i = 0; i < newQueue.length; i++) {
            if (newQueue[i].equals(el)) {
                count++;
            }
        }
        return count;
    }
}
 */