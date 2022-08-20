package queue;

// Model: a[1] ... a[n]

// I: size >= 0

import java.util.function.Predicate;

public interface Queue {

    // Pred: size > 0 && && queue != null
    // Post: elements[head] & Immutable
    Object element();

    // Pred: element != null
    // Post: size' = size + 1 && elements[size] = element
    void enqueue(Object element);

    // Pred: size > 0 && queue != null
    // Post: elements[head] && elements[head]' = null && forall i = 0..(size - 1) elements[i] = elements[i + 1] && size--;
    Object dequeue();

    // Pred: queue != null
    // Post: size && Immutable
    int size();

    // Pred: queue != null
    // Post: R = (size() == 0) && Immutabe
    boolean isEmpty();

    // Pred: queue != null
    // Post: forall i = 0...size: elements[i] = null && size' == 0
    void clear();

    // Pred: true
    // Post: Immutable & count = 'the number of elements that satisfy the predicate'
    int countIf(Predicate<Object> p);
}