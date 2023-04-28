package product;
/**
 * @author Dawn
 */
public class Figure {
    private   int[][] directions = {{0, 1}, {1, 0},{0, -1},{-1, 0} }; // �� �� �� �� �ĸ�����
    LinkedStack<position> stack;
    public  void solve(int[][] maze) {
        int m = maze.length;   //y ����
        int n = maze[0].length;     //x ����
        /**
         * ����1��ʾ�£�����2��ʾ�ң�����3��ʾ�ϣ�����4��ʾ��
         * ���ó�ʼ�㣨0,0��������
         */
        position pos = new position(0,0);
        boolean[][] visited = new boolean[m][n];   // ����Ƿ���ʹ�
      //  System.out.println(m);
      //  System.out.println(n);
        stack = new LinkedStack<>(); // �洢·����ջ
        stack.push(pos); // �����ջ
        visited[0][0] = true;

        while (!stack.isEmpty()) {
            position poss = stack.peek(); // ��ǰλ�ó�ջ
            int i = poss.getX(), j = poss.getY(), d = poss.getD(); // ��ǰλ�úͷ���
            visited[j][i] = true;   // �߹��ĵ��Ϊtrue
            if (i == n-1 && j == m-1 ) { // �����յ�

                return;
            }
             boolean found = false;
            for (int k = 0; k < directions.length; k++) { // �����ĸ�����

                int nowX = i + directions[k][0], nowY = j + directions[k][1];
                /**
                 * �ж�˳ʱ��ķ���ĵ��Ƿ���ϱ�׼���պ���·�ϣ��жϸõ��Ƿ��Ѿ��߹�
                 */
                if (nowX >= 0 && nowX < n && nowY >= 0 && nowY < m && maze[nowY][nowX] == 0 && !visited[nowY][nowX]) {
                    poss.setD(k);
                    stack.push(new position(nowX, nowY)); // ��λ����ջ
                    found = true;
                    break;
                }
            }
                if (!found) {
                    stack.pop(); // �ĸ����򶼲����ߣ�������ǰջ��Ԫ��
                }

        }
        System.out.println("No path found!");
        stack=null ;//��û���յ��ʱ��
    }

     public LinkedStack<position> printPath() {
            return stack;
    }








}
