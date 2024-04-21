import java.util.Comparator;
import java.util.Iterator;

public class MyLinkedList<T> implements MyList<T> {
    private static class Node<T> {
        T item;
        Node<T> next;

        Node(T item) {
            this.item = item;
            this.next = null;
        }
    }


    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void add(T newItem) {
        addLast(newItem);
    }

    @Override
    public void set(int index, T item) {
        Node<T> node = getNode(index);
        node.item = item;
    }

    @Override
    public void add(int index, T item) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        else {
            Node<T> newNode = new Node<>(item);
            Node<T> prevNode = getNode(index - 1);
            newNode.next = prevNode.next;
            prevNode.next = newNode;
            size++;
        }
    }

    @Override
    public void addFirst(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public T get(int index) {
        Node current = head;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        return (T) current.item;
    }

    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("List mistake");
        }
        return head.item;
    }

    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("List mistake");
        }
        return tail.item;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index mistake");
        }
        if (index == 0) {
            removeFirst();
        } else {
            Node<T> prevNode = getNode(index - 1);
            prevNode.next = prevNode.next.next;
            if (index == size - 1) {
                tail = prevNode;
            }
            size--;
        }
    }

    @Override
    public void removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        head = head.next;
        size--;
        if (isEmpty()) {
            tail = null;
        }
    }

    @Override
    public void removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("List mistake");
        }
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node<T> prevNode = getNode(size - 2);
            prevNode.next = null;
            tail = prevNode;
        }
        size--;
    }

    @Override
    public void sort(Comparator<T> comparator) {
        if (head == null) {
            return;
        }

        boolean swapped = true;
        while (swapped) {
            swapped = false;
            Node<T> current = head;
            while (current.next != null) {
                if (comparator.compare(current.item, current.next.item) > 0) {
                    T temp = current.item;
                    current.item = current.next.item;
                    current.next.item = temp;
                    swapped = true;
                }
                current = current.next;
            }
        }
    }

    @Override
    public int indexOf(Object object) {
        int index = 0;
        for (Node<T> current = head; current != null; current = current.next) {
            if (object.equals(current.item)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        int index = size - 1;
        for (Node<T> current = head; current != null; current = current.next) {
            if (object.equals(current.item)) {
                return index;
            }
            index--;
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        for (Node<T> current = head; current != null; current = current.next) {
            array[index++] = current.item;
        }
        return array;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index mistake");
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    boolean isEmpty() {
        return size == 0;
    }

    // Iterator implementation
    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    private class MyLinkedListIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            T item = current.item;
            current = current.next;
            return item;
        }
    }
}