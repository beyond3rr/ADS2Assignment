public class MyHeap<T extends Comparable<T>> {
    private MyArrayList<T> list;

    public MyHeap() {
        list = new MyArrayList<>();
    }

    public boolean empty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public T getMin() {
        if (empty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return list.get(0);
    }

    public T extractMin() {
        if (empty()) {
            throw new IllegalStateException("Heap is empty");
        }
        T min = list.get(0);
        int lastIndex = list.size() - 1;
        list.set(0, list.get(lastIndex));
        list.remove(lastIndex);
        heapify(0);
        return min;
    }

    public void insert(T item) {
        list.add(item);
        traverseUp(list.size() - 1);
    }

    private void heapify(int index) {
        int leftChildIndex = leftChildOf(index);
        int rightChildIndex = rightChildOf(index);
        int smallestIndex = index;

        if (leftChildIndex < list.size() && list.get(leftChildIndex).compareTo(list.get(smallestIndex)) < 0) {
            smallestIndex = leftChildIndex;
        }
        if (rightChildIndex < list.size() && list.get(rightChildIndex).compareTo(list.get(smallestIndex)) < 0) {
            smallestIndex = rightChildIndex;
        }
        if (smallestIndex != index) {
            swap(index, smallestIndex);
            heapify(smallestIndex);
        }
    }

    private void traverseUp(int index) {
        while (index > 0 && list.get(index).compareTo(list.get(parentOf(index))) < 0) {
            swap(index, parentOf(index));
            index = parentOf(index);
        }
    }

    private int leftChildOf(int index) {
        return 2 * index + 1;
    }

    private int rightChildOf(int index) {
        return 2 * index + 2;
    }

    private int parentOf(int index) {
        return (index - 1) / 2;
    }

    private void swap(int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}