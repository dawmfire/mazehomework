package product;


//单链表结点
public class SinglyListNode<T> {

    public T data;
    public SinglyListNode<T> next;
    public SinglyListNode(){
        this(null,null);
    }
    public SinglyListNode(T data, SinglyListNode<T> next) {
        this.data = data;
        this.next = next;
    }
    @Override
    public String toString() {

        return this.data.toString();
    }
}
