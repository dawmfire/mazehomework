package product;



/**
 * 单链表的头结点也有地址域，next域指向单链表第0个元素结点
 * @param <T>
 */
//带头结点的单链表
public class SinglyList<T>{
    public SinglyListNode<T> head;

    public SinglyList() {

        this.head = new SinglyListNode();       //构造空单链表

    }
    public SinglyList(SinglyList<T> list) {
        this();
        this.copy(list);
    }

    public void copy(SinglyList<T> list) {
        this.clear();
        SinglyListNode<T> rear = this.head;

        for(SinglyListNode p = list.head.next; p != null; p = p.next) {
            rear.next = new SinglyListNode(p.data, (SinglyListNode)null);
            rear = rear.next;
        }

    }
    public void clear()                          //删除单链表所有结点
    {
        this.head.next = null;                   //Java自动收回所有结点占用的存储空间
    }
    public SinglyList(T[] values) {
        this();     //创建空单链表，只有头结点
        SinglyListNode<T> rear = this.head;     //rear的next域指向最后一个结点

        for(int i = 0; i < values.length; ++i) {
            if (values[i] != null) {
                rear.next = new SinglyListNode(values[i], (SinglyListNode)null);
                rear = rear.next;
            }
        }
    }

    public boolean isEmpty() {

        return this.head.next == null;      //判断是否空
    }

    public T get(int i)                          //返回第i个元素，0≤i<单链表长度。若i越界，则返回null。
    {
        SinglyListNode<T> p=this.head.next;
        for(int j=0;  p!=null && j<i;  j++)      //遍历单链表，寻找第i个结点（p指向）
        {
            p = p.next;
        }
        return (i>=0 && p!=null) ? p.data : null;   //若p指向第i个结点，则返回其元素值
    }

    //设置第i个元素为x，0≤i<单链表长度且x!=null。
    //若x==null，抛出空对象异常；若i序号越界，抛出序号越界异常。
    // 没有返回值
    public void set(int i, T x)
    {
        if(x==null) {
            throw new NullPointerException("x==null");     //抛出空对象异常
        } else
        {
            SinglyListNode<T> p=this.head.next;
            for(int j=0;  p!=null && j<i;  j++)  //遍历寻找第i个结点（p指向）
            {
                p = p.next;
            }
            if(i>=0 && p!=null) {
                p.data = x;                       //p指向第i个结点
            } else {
                throw new IndexOutOfBoundsException(i+"");//抛出序号越界异常
            }
        }
    }

    public int size()                            //返回单链表长度，O(n)
    {
        int i=0;
        //p遍历单链表
        SinglyListNode<T> p=this.head.next;
        while (p!=null) {
            i++;
            p = p.next;
        }
        return i;
    }

    public SinglyListNode<T> insert(int i, T x)
    {
        if(x==null) {
            return null;               ////没有插入结点。返回一种执行结果，不是错误，不抛出异常
        }
        SinglyListNode<T> front=this.head;                 //front指向头结点
        for(int j=0;  front.next!=null && j<i;  j++)  //寻找第i-1个或最后一个结点（front指向）
        {
            front = front.next;
        }
        front.next = new SinglyListNode<T>(x, front.next); //在front之后插入值为x结点，包括头插入、中间/尾插入
        return front.next;
    }

    /**
     * 调用insert(i,x)，用整数最大值指定插入在最后，遍历一次，i必须容错
     * Integer.MAX_VALUE（0x7fffffff）是整数最大值
     * return insert(this.size(), x);需遍历单链表两次，效率较低
     * @param x
     * @return
     */
    public SinglyListNode<T> insert(T x)                   //单链表尾插入x，重载
    {
        return insert(Integer.MAX_VALUE, x);

    }

    public T remove(int i)         //删除第i个元素，0≤i<单链表长度，返回被删除元素。若i越界，则返回null。
    {
        SinglyListNode<T> front=this.head;                 //front指向头结点
        for(int j=0;  front.next!=null && j<i;  j++)    //遍历寻找第i-1结点（front指向）
        {
            front = front.next;
        }
        if(i>=0 && front.next!=null)                //若front的后继结点存在，则删除之
        {
            T x = front.next.data;                  //获得待删除结点引用的对象
            //删除front的后继结点，包括头删除、中间/尾删除。由Java虚拟机稍后释放结点占用存储单元
            front.next = front.next.next;
            return x;
        }
        return null;                             //若i<0或i>表长，则返回null

    }

    @Override
    public String toString()
    {
        String str="(";
        for(SinglyListNode<T> p=this.head.next;  p!=null;  p=p.next) //p遍历单链表
        {
            str += p.data.toString()+(p.next!=null?",":"");//不是最后一个结点时，加“,”分隔符
        }
        return str+")";                          //空表返回()
    }
}
