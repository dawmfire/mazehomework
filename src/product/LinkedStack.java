package product;

public class LinkedStack<T>{
    private SinglyList<T> list;

    public LinkedStack() {
        this.list = new SinglyList();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }
//»Î’ª
    public void push(T x) {
        this.list.insert(0, x);
    }

    public T peek() {
        return this.list.get(0);
    }

    public T pop() {
        return this.list.remove(0);
    }

    @Override
    public String toString() {
        return  this.list.toString();
    }

    public LinkedStack(LinkedStack<T> stack) {
        this.list = new SinglyList(stack.list);
    }

    public void copy(LinkedStack<T> stack) {
        this.list = new SinglyList(stack.list);
    }
}
