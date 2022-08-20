package queue;

import java.util.Arrays;

// Model: a[1] ... a[n]

// I: forall i: head <= i < head + size && elements[i] != null && size <= elements.length

public class ArrayQueueModule {
    private static int head = 0;
    private static int size;
    private static final int DEF_CAPACITY = 2;
    private static Object[] elements = new Object[DEF_CAPACITY];

    // Pred: size() > 0
    // Post: elements[head] & Immutable
    public static Object element() {
        assert size > 0;
        return elements[head];
    }

    // Pred: el != null
    // Post: size = size' + 1 && ((elements[(head + size) % elements.length] = el) ||
    // || (elements.length' == size()' && elements.length = elements.length' * 10))
    public static void enqueue(Object el) {
        assert el != null;
        ensureCapacity(size + 1);

        elements[(head + size) % elements.length] = el;
        size++;
    }

    // Pred: size() > 0
    // Post: R = elements[head']' && elements[head'] = null && ((size' == 1 && head == 0) || (size' > 1 && head = (head' + 1) % elements.length));
    public static Object dequeue() {
        assert size > 0;
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
    private static void ensureCapacity(int capacity) {
        if (capacity <= elements.length) {
            return;
        }
        elements = queueToArray(new Object[capacity * DEF_CAPACITY]);
        head = 0;
    }

    // Pred: true
    // Post: size
    public static int size() {
        return size;
    }

    // Pred: true
    // Post: R = (size() == 0) && Immutabe
    public static boolean isEmpty() {
        return size == 0;
    }

    // Pred: true
    // Post: forall i = 1...n: elements[i] = null && size == head == 0
    public static void clear() {
        size = head = 0;
        Arrays.fill(elements, null);
    }

    // Pred: queue != null
    // Post: Очередь стала массивом (элементы упорядочены по моменту добавления)
    public static Object[] toArray() {
        /*Object[] queueToArray = new Object[size];
        for (int i = 0; i < size; i++) {
            queueToArray[i] = elements[(head + i) % elements.length];
        }
        return queueToArray;*/
        return queueToArray(new Object[size]);
    }

    // Pred: queue != null
    // Post: Очередь стала массивом (элементы упорядочены по моменту добавления)
    private static Object[] queueToArray(Object[] newQueue) {
        int headPart = Math.min(elements.length, head + size) - head;
        System.arraycopy(elements, head, newQueue, 0, headPart);
        System.arraycopy(elements, (head + headPart) % elements.length, newQueue, headPart, size - headPart);
        return newQueue;
    }

    // Pred: queue != null && element != null
    // Post: count' = число вхождений элемента в очередь && Ummutable
    public static int count(Object el) {
        Object[] newQueue = queueToArray(new Object[size]);;
        int count = 0;
        for (int i = 0; i < newQueue.length; i++) {
            if (newQueue[i].equals(el)) {
                count++;
            }
        }
        return count;
    }
}