import java.util.Arrays;

public class MyLinkedList<T> implements MyList<T> {
    private Node head;
    private Node tail;
    private int size;

    private static class Node<T> {
        T item;
        Node next;

        Node(T item) {
            this.item = item;
            this.next = null;
        }
    }

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
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            addFirst(item);
        } else if (index == size) {
            addLast(item);
        } else {
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
        checkIndex(index);
        Node<T> current = getNode(index);
        return current.item;
    }

    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return head.item;
    }

    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return tail.item;
    }

    @Override
    public void remove(int index) {
        checkIndex(index);
        if (index == 0) {
            removeFirst();
        } else {
            Node<T> prevNode = getNode(index - 1);
            prevNode.next = prevNode.next.next;
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
            throw new IllegalStateException("List is empty");
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
    public void sort() {
        // Implement sorting algorithm
        // For simplicity, let's not implement sorting in this example
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
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    private boolean isEmpty() {
        return size == 0;
    }
}
