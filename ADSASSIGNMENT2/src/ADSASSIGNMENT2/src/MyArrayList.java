import java.util.Comparator;
import java.util.Iterator;

public class MyArrayList<T> implements MyList<T> {
    private Object[] array;
    private int size = 0;
    private int capacity = 5;

    public MyArrayList() {
        array = new Object[capacity];
    }

    @Override
    public void add(T item) {
        if (size == capacity) {
            increaseSize();
        }
        array[size++] = item;
    }

    @Override
    public void set(int index, T item) {
        checkIndex(index);
        array[index] = item;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (size == capacity) {
            increaseSize();
        }
        System.arraycopy(array, index, array,  + 1, size - index);
        array[index] = item;
        size++;
    }

    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    @Override
    public void addLast(T item) {
        add(item);
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return (T) array[0];
    }

    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return (T) array[size - 1];
    }

    @Override
    public void remove(int index) {
        checkIndex(index);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
    }

    @Override
    public void removeFirst() {
        remove(0);
    }

    @Override
    public void removeLast() {
        remove(size - 1);
    }

    @Override
    public void sort(Comparator<T> comparator) {
        int n = size();
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(get(j), get(j + 1)) > 0) {
                    T temp = get(j);
                    set(j, get(j + 1));
                    set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (object.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (object.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size];
        System.arraycopy(array, 0, newArray, 0, size);
        return newArray;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void increaseSize() {
        capacity = capacity * 2;
        Object[] newArray = new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index Mistake");
        }
    }

    boolean isEmpty() {
        return size == 0;
    }

    private class MyArrayListIterator implements Iterator<T> {
        private int currentIndex;

        public MyArrayListIterator() {
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }
            return (T) array[currentIndex++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Bad operation");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }
}