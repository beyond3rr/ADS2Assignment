import java.util.Iterator;

public class MyArrayList<T> implements MyList{

    private Object[] array;
    private int size = 0;
    private int capacity = 5;

    public MyArrayList() {
        array = new Object[capacity];
    }

    public T get(int index){
        return (T)array[index];
    }

    public void add(Object newItem){
        if(size == capacity){
            IncreaseSize();
        }
        array[size++] = newItem;
    }
    public void IncreaseSize(){
        capacity = (int) 2 * capacity;
        Object[] array2 = new Object[capacity];
        for (int i = 0; i < size; i++){
            array2[i] = array[i];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
