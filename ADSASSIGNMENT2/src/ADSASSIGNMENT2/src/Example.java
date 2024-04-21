public class Example {
    public static void main(String[] args){
        MyArrayList<String> arrayList = new MyArrayList<>();
        arrayList.add("126");
        arrayList.add("127");
        arrayList.add("128");

        System.out.println(arrayList.get(2));

        MyLinkedList<String> linkedList = new MyLinkedList<>();
        linkedList.add("123");
        linkedList.add("124");
        linkedList.add("125");

        System.out.println(linkedList.get(2));
    }
}
