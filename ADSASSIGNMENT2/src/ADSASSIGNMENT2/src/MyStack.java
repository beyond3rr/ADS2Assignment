public class MyStack<T> {
    private MyLinkedList<T> list;

    public MyStack() {
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
            throw new IllegalStateException("Stack is empty");
        }
        return list.getFirst();
    }

    public T push(T item) {
        list.addFirst(item);
        return item; // Return the pushed item
    }

    public T pop() {
        if (empty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T item = list.getFirst();
        list.removeFirst();
        return item;
    }
}