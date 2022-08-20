package queue;

import java.util.function.Predicate;

public class LinkedQueue extends AbstractQueue {

    private Node head;
    private Node tail;

    @Override
    protected Object elementAbstarct() {
        return head.value;
    }

    @Override
    protected void enqueueAbstract(Object el) {
        if (size != 0) {
            tail.next = new Node(el, null);
            tail = tail.next;
        } else {
            head = tail = new Node(el, null);
        }
    }

    @Override
    protected Object dequeueAbstarct() {
        Object el = head.value;
        head = head.next;
        return el;
    }

    @Override
    protected void clearAbstract() {
        head = null;
        tail = null;
    }

    @Override
    public int countIfAbstract(Predicate<Object> p) {
        int count = 0;
        Node temp = head;
        for (int i = 0; i < size; i++) {
            if (p.test(head.value)) {
                count++;
            }
            head = head.next;
        }
        head = temp;
        return count;
    }

    private static class Node {
        // :NOTE: value должно быть final
        private Object value;
        private Node next;

        public Node(Object value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}