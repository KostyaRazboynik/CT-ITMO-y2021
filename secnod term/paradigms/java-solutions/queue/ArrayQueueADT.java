package queue;

import java.util.Arrays;

// Model: a[1] ... a[n]

// I: forall i: head <= i < head + size && elements[i] != null && size <= elements.length

public class ArrayQueueADT {
    private int head = 0;
    private int size;
    private static final int DEF_CAPACITY = 2;
    private Object[] elements = new Object[DEF_CAPACITY];

    // Pred: size > 0 && queue != null
    // Post: elements[head] & Immutable
    public static Object element(ArrayQueueADT queue) {
        assert queue != null && queue.size > 0;
        return queue.elements[queue.head];
    }

    // Pred: el != null && size < elements.length && queue != null
    // Post: size = size' + 1 && ((elements[(head + size) % elements.length] = el) ||
    // || (elements.length' == size()' && elements.length = elements.length' * 10))
    public static void enqueue(ArrayQueueADT queue, Object el) {
        assert el != null && queue != null;
        ensureCapacity(queue, queue.size + 1);

        queue.elements[(queue.head + queue.size) % queue.elements.length] = el;
        queue.size++;
    }

    // Pred: size > 0 && queue != null
    // Post: R = elements[head']' && elements[head'] = null && ((size' == 1 && head == 0) || (size' > 1 && head = (head' + 1) % elements.length));
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue != null && queue.size > 0;
        Object el = element(queue);
        queue.elements[queue.head] = null;
        queue.head++;
        queue.size--;
        if (queue.head == queue.elements.length) {
            queue.head = 0;
        }
        return el;
    }

    // Pred: capacity == N && queue != null
    // Post: elements.length = (elements.length)' * 10 || (head' >= (head' + size') % capacity && head == 0)
    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity <= queue.elements.length) {
            return;
        }
        queue.elements = queueToArray(queue, new Object[capacity * DEF_CAPACITY]);
        queue.head = 0;
    }

    // Pred: queue != null
    // Post: size
    public static int size(ArrayQueueADT queue) {
        assert queue != null;
        return queue.size;
    }

    // Pred: queue != null
    // Post: R = (size() == 0) && Immutabe
    public static boolean isEmpty(ArrayQueueADT queue) {
        assert queue != null;
        return queue.size == 0;
    }

    // Pred: queue != null
    // Post: forall i = 1...n: elements[i] = null && size == 0
    public static void clear(ArrayQueueADT queue) {
        assert queue != null;
        queue.size = queue.head = 0;
        Arrays.fill(queue.elements, null);
    }

    // Pred: queue != null
    // Post: Очередь стала массивом (элементы упорядочены по моменту добавления)
    public static Object[] toArray(ArrayQueueADT queue) {
        /*Object[] queueToArray = new Object[queue.size];
        for (int i = 0; i < queue.size; i++) {
            queueToArray[i] = queue.elements[(queue.head + i) % queue.elements.length];
        }
        return queueToArray;*/
        return queueToArray(queue, new Object[queue.size]);
    }

    // Pred: queue != null
    // Post: Очередь стала массивом (элементы упорядочены по моменту добавления)
    private static Object[] queueToArray(ArrayQueueADT queue, Object[] newQueue) {
        int headPart = Math.min(queue.elements.length, queue.head + queue.size) - queue.head;
        System.arraycopy(queue.elements, queue.head, newQueue, 0, headPart);
        System.arraycopy(queue.elements, (queue.head + headPart) % queue.elements.length, newQueue, headPart, queue.size - headPart);
        return newQueue;
    }

    // Pred: queue != null && element != null
    // Post: count' = число вхождений элемента в очередь && Ummutable
    public static int count(ArrayQueueADT queue, Object el) {
        Object[] newQueue = queueToArray(queue, new Object[queue.size]);;
        int count = 0;
        for (int i = 0; i < newQueue.length; i++) {
            if (newQueue[i].equals(el)) {
                count++;
            }
        }
        return count;
    }
}