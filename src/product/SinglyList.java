package product;



/**
 * �������ͷ���Ҳ�е�ַ��next��ָ�������0��Ԫ�ؽ��
 * @param <T>
 */
//��ͷ���ĵ�����
public class SinglyList<T>{
    public SinglyListNode<T> head;

    public SinglyList() {

        this.head = new SinglyListNode();       //����յ�����

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
    public void clear()                          //ɾ�����������н��
    {
        this.head.next = null;                   //Java�Զ��ջ����н��ռ�õĴ洢�ռ�
    }
    public SinglyList(T[] values) {
        this();     //�����յ�����ֻ��ͷ���
        SinglyListNode<T> rear = this.head;     //rear��next��ָ�����һ�����

        for(int i = 0; i < values.length; ++i) {
            if (values[i] != null) {
                rear.next = new SinglyListNode(values[i], (SinglyListNode)null);
                rear = rear.next;
            }
        }
    }

    public boolean isEmpty() {

        return this.head.next == null;      //�ж��Ƿ��
    }

    public T get(int i)                          //���ص�i��Ԫ�أ�0��i<�������ȡ���iԽ�磬�򷵻�null��
    {
        SinglyListNode<T> p=this.head.next;
        for(int j=0;  p!=null && j<i;  j++)      //����������Ѱ�ҵ�i����㣨pָ��
        {
            p = p.next;
        }
        return (i>=0 && p!=null) ? p.data : null;   //��pָ���i����㣬�򷵻���Ԫ��ֵ
    }

    //���õ�i��Ԫ��Ϊx��0��i<����������x!=null��
    //��x==null���׳��ն����쳣����i���Խ�磬�׳����Խ���쳣��
    // û�з���ֵ
    public void set(int i, T x)
    {
        if(x==null) {
            throw new NullPointerException("x==null");     //�׳��ն����쳣
        } else
        {
            SinglyListNode<T> p=this.head.next;
            for(int j=0;  p!=null && j<i;  j++)  //����Ѱ�ҵ�i����㣨pָ��
            {
                p = p.next;
            }
            if(i>=0 && p!=null) {
                p.data = x;                       //pָ���i�����
            } else {
                throw new IndexOutOfBoundsException(i+"");//�׳����Խ���쳣
            }
        }
    }

    public int size()                            //���ص������ȣ�O(n)
    {
        int i=0;
        //p����������
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
            return null;               ////û�в����㡣����һ��ִ�н�������Ǵ��󣬲��׳��쳣
        }
        SinglyListNode<T> front=this.head;                 //frontָ��ͷ���
        for(int j=0;  front.next!=null && j<i;  j++)  //Ѱ�ҵ�i-1�������һ����㣨frontָ��
        {
            front = front.next;
        }
        front.next = new SinglyListNode<T>(x, front.next); //��front֮�����ֵΪx��㣬����ͷ���롢�м�/β����
        return front.next;
    }

    /**
     * ����insert(i,x)�����������ֵָ����������󣬱���һ�Σ�i�����ݴ�
     * Integer.MAX_VALUE��0x7fffffff�����������ֵ
     * return insert(this.size(), x);��������������Σ�Ч�ʽϵ�
     * @param x
     * @return
     */
    public SinglyListNode<T> insert(T x)                   //������β����x������
    {
        return insert(Integer.MAX_VALUE, x);

    }

    public T remove(int i)         //ɾ����i��Ԫ�أ�0��i<�������ȣ����ر�ɾ��Ԫ�ء���iԽ�磬�򷵻�null��
    {
        SinglyListNode<T> front=this.head;                 //frontָ��ͷ���
        for(int j=0;  front.next!=null && j<i;  j++)    //����Ѱ�ҵ�i-1��㣨frontָ��
        {
            front = front.next;
        }
        if(i>=0 && front.next!=null)                //��front�ĺ�̽����ڣ���ɾ��֮
        {
            T x = front.next.data;                  //��ô�ɾ��������õĶ���
            //ɾ��front�ĺ�̽�㣬����ͷɾ�����м�/βɾ������Java������Ժ��ͷŽ��ռ�ô洢��Ԫ
            front.next = front.next.next;
            return x;
        }
        return null;                             //��i<0��i>�����򷵻�null

    }

    @Override
    public String toString()
    {
        String str="(";
        for(SinglyListNode<T> p=this.head.next;  p!=null;  p=p.next) //p����������
        {
            str += p.data.toString()+(p.next!=null?",":"");//�������һ�����ʱ���ӡ�,���ָ���
        }
        return str+")";                          //�ձ���()
    }
}
