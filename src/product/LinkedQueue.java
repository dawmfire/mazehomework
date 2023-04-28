package product;

public class LinkedQueue<T> {
    private SinglyListNode<T> front;
    private SinglyListNode<T> rear;

    public LinkedQueue() {
        this.front = this.rear = null;
    }

    public boolean isEmpty() {
        return this.front == null && this.rear == null;
    }

    public boolean add(T x) {
        if (x == null) {
            return false;
        } else {
            SinglyListNode<T> q = new SinglyListNode(x, (SinglyListNode)null);
            if (this.front == null) {
                this.front = q;
            } else {
                this.rear.next = q;
            }

            this.rear = q;
            return true;
        }
    }

    public T peek() {
        return this.isEmpty() ? null : this.front.data;
    }

    public T poll() {
        if (this.isEmpty()) {
            return null;
        } else {
            T x = this.front.data;
            this.front = this.front.next;
            if (this.front == null) {
                this.rear = null;
            }

            return x;
        }
    }

    @Override
    public String toString() {
        StringBuffer strbuf = new StringBuffer(this.getClass().getName() + "(");

        for(SinglyListNode p = this.front; p != null; p = p.next) {
            strbuf.append(p.data.toString() + (p.next != null ? "," : ""));
        }

        return new String(strbuf + ")");
    }
}
