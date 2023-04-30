package product;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Arithmetic {

    int[][] maze;   //�Թ�����
    boolean[][] visit; // ����Ƿ���ʹ�
    LinkedQueue<LinkedStack>queue;
    LinkedStack<position> stack = new LinkedStack<>();//����pass�Ľ��

    Arithmetic(int[][] maze) {
        this.maze = maze;
        int m = maze.length;   //y ����
        int n = maze[0].length;     //x ����
        visit = new boolean[m][n];   // ������ʱ��
        queue = new LinkedQueue<>();
        init();
    }

    /**
     * �ݹ�������������п��ܵĵ�·
     * ͳһ���ó������Ϊ��0,0��������Ϊ���½�
     * �����³���
     */
    void init() {
        findAllPaths(maze, visit, 0, 0, maze[0].length, maze.length);
    }

    public void findAllPaths(int[][] maze, boolean[][] visit, int startX, int startY, int endX, int endY) {

            // ������Խ�硢�Ѿ����ʹ������߲���ͨ·���򷵻�
            if (startX < 0 || startX >= maze[0].length || startY < 0 || startY >= maze.length
                    || visit[startY][startX] || maze[startY][startX] != 0) {

                return;
            }
            //�������귽��
            if(visit[startY][startX]==false&&(!(startX==0&&startY==0)) ){
                position pas=stack.peek();
                int direction=0;
                /**
                 * ��ǰ������������һ������������жԱȣ��������з�������
                 * ��x��ͬ���Ƚ�y���ж����·���
                 * ��y��ͬ���Ƚ�x���ж����ҷ���
                 * 1 ��ʾ ����
                 * 2 ��ʾ ����
                 * 3 ��ʾ ����
                 * 4 ��ʾ ����
                 */
                //��x��ͬ�����
                if(pas.getX()-startX==0) {
                    int dre = pas.getY() - startY;
                    switch (dre) {
                        case -1:
                            direction = 1;
                            break;
                        case 1:
                            direction = 3;
                            break;
                        case 0:
                            break;
                    }
                }else {
                    //��y��ͬ�������
                    int dre = pas.getX() - startX;
                    switch (dre) {
                        case -1:
                            direction = 2;
                            break;
                        case 1:
                            direction = 4;
                            break;
                    }

                }
                //����������ϸ������
                pas.setD(direction);

            }stack.push(new position(startX, startY, 0));// ��������

        if (startX == endX - 1 && startY == endY - 1) {
                // ��������յ㣬����������ǰ·��
                printPath();
            /**
             * ע�⵱��֤������Ϊ�յ��ʱ���ٵ�����֮��Ҫ��pop����
             * ��������ݹ��ʱ�����д�λ����ջ����
             */
            stack.pop();
                return;
            }

            visit[startY][startX] = true; // �������Ѿ����ʹ�

            // �ֱ����ĸ�����ݹ�
            findAllPaths(maze, visit, startX, startY + 1, endX, endY); // ����
            findAllPaths(maze, visit, startX + 1, startY, endX, endY ); // ����
            findAllPaths(maze, visit, startX, startY - 1, endX, endY); // ����
            findAllPaths(maze, visit, startX - 1, startY, endX, endY); // ����
            stack.pop();
            visit[startY][startX] = false; // �ָ���ǣ��Ա���һ�η���




    }
    // ��ÿ��·��������
    void printPath() {
        position pas;
        LinkedStack<position> stack1=new LinkedStack<>(stack); // ��stack����ȫ����������
        queue.add(stack1);
  /*      LinkedStack<position> stack2=new LinkedStack<>();

        List<String> path = new LinkedList<>();


            while (!stack1.isEmpty()) {
                pas=stack1.pop();
                stack2.push(pas);
                path.add("(" + (pas.getX()+1 ) + "," + (pas.getY() +1) + "," + (pas.getD()  ) + ")");
            }
            Collections.reverse(path);
            //���б���
            Iterator<String> iterator = path.iterator();
            while (iterator.hasNext()) {
              //  text.append(iterator.next());
                System.out.print(iterator.next());
            }
            System.out.println("");


   */
    }

    LinkedQueue<LinkedStack> getQueue(){
        return  queue;
    }

}