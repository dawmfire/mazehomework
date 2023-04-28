package product;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Arithmetic {

    int[][] maze;   //�Թ�����
    boolean[][] visit; // ����Ƿ���ʹ�
    position pass;  // ��������
    LinkedQueue<LinkedStack>queue;
    LinkedStack<position> stack = new LinkedStack<>();//����pass�Ľ��

    Arithmetic(int[][] maze) {
        this.maze = maze;
        int m = maze.length;   //y ����
        int n = maze[0].length;     //x ����
        visit = new boolean[m][n];
        queue = new LinkedQueue<>();
        init();
    }

    /**
     * �ݹ�������������п��ܵĵ�·
     * ͳһ���ó������Ϊ��0,0��������Ϊ���½�
     * �����³���
     */
    void init() {
        position pos = new position(0,0,1);
        stack.push(pos);
        findAllPaths(maze, visit, pos.getX(), pos.getY(), maze[0].length, maze.length, pos.getD());
    }

    public void findAllPaths(int[][] maze, boolean[][] visit, int startX, int startY, int endX, int endY, int direction) {

            // ������Խ�硢�Ѿ����ʹ������߲���ͨ·���򷵻�
            if (startX < 0 || startX >= maze[0].length || startY < 0 || startY >= maze.length
                    || visit[startY][startX] || maze[startY][startX] != 0) {

                return;
            }
            if(visit[startY][startX]==false&&(!(startX==0&&startY==0)) ){
                stack.push(new position(startX, startY, direction)); // ��������
            }

            if (startX == endX - 1 && startY == endY - 1) {
                // ��������յ㣬����������ǰ·��
                printPath();
                return;
            }
           /* if(pass.getD()==direction&&startX!=0&&startY!=0){
                return;
            }*/
            visit[startY][startX] = true; // �������Ѿ����ʹ�

            // �ֱ����ĸ�����ݹ�
            findAllPaths(maze, visit, startX, startY + 1, endX, endY, 1); // ����
            findAllPaths(maze, visit, startX + 1, startY, endX, endY, 2); // ����
            findAllPaths(maze, visit, startX, startY - 1, endX, endY, 3); // ����
            findAllPaths(maze, visit, startX - 1, startY, endX, endY, 4); // ����
            stack.pop();
            visit[startY][startX] = false; // �ָ���ǣ��Ա���һ�η���




    }

    void printPath() {
        /*
    // ��ӡ��ǰ·��
    for (int i = 0; i < visit.length; i++) {
        for (int j = 0; j < visit[0].length; j++) {
            if (visit[i][j]) {
                System.out.print("O "); // O ��ʾͨ·
            } else {
                System.out.print("# "); // # ��ʾ�ϰ���δ���ʹ���ͨ·
            }
        }
        System.out.println();
    }
    System.out.println();
}

         */
        position pas;
        LinkedStack<position> stack2=new LinkedStack<>();
        queue.add(stack);
 //       System.out.println(queue.toString());
        List<String> path = new LinkedList<>();
        LinkedStack<position> stack1 = new LinkedStack<>(stack);


            stack1=new LinkedStack<>(queue.peek());

            while (!stack1.isEmpty()) {
                pas=stack1.pop();
                stack2.push(pas);
                path.add("(" + (pas.getX()+1 ) + "," + (pas.getY() +1) + "," + (pas.getD()  +1 ) + ")");
            }
            Collections.reverse(path);
            //���б���
            Iterator<String> iterator = path.iterator();
            while (iterator.hasNext()) {
              //  text.append(iterator.next());
                System.out.print(iterator.next());
            }
            System.out.println("");

    }
}