public class MyQueue<T> {
    private MyLinkedList<T> list;

    public MyQueue() {
        list = new MyLinkedList<>();
    }

    public boolean empty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public T peek() {
        if (empty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return list.getFirst();
    }

    public T enqueue(T item) {
        list.addLast(item);
        return item;
    }

    public T dequeue() {
        if (empty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T item = list.getFirst();
        list.removeFirst();
        return item;
    }
}