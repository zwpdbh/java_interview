package DataStructureAndAlgorithms.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<T> implements Iterable<T> {
    private SLLNode<T> first;
    private SLLNode<T> last;

    public SinglyLinkedList() {
        this.first = null;
        this.last = null;
    }

    public String toString() {
        StringBuilder result = new StringBuilder("<SLL>: ");
        SLLNode current = this.first;
        while (current != null) {
            result.append(current.value);
            result.append(" --> ");
            current = current.next;
        }
        return result.toString();
    }

    public void add(T value) {
        SLLNode<T> newNode = new SLLNode<>(value, this.first);
        this.first = newNode;
    }

    public void addLast(T value) {
        if (this.first == null) {
            add(value);
            this.last = this.first;
            return;
        }
//        SLLNode<T> current = this.first;
//        while (current.next != null) {
//            current = current.next;
//        }
//        current.next = new SLLNode<>(value, null);

        this.last.next = new SLLNode<>(value, null);
        this.last = this.last.next;
    }

    private class SLLNode<T> {
        private T value;
        private SLLNode<T> next;

        private SLLNode(T value, SLLNode<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    /** Any class implement Iterable interface must need an iterator() method
     *  which in turn returns a Iterator<T> object*/
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private SLLNode<T> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T result = current.value;
                current = current.next;
                return result;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
