package product;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Arithmetic {

    int[][] maze;   //迷宫数据
    boolean[][] visit; // 标记是否访问过
    LinkedQueue<LinkedStack>queue;
    LinkedStack<position> stack = new LinkedStack<>();//储存pass的结点

    Arithmetic(int[][] maze) {
        this.maze = maze;
        int m = maze.length;   //y 行数
        int n = maze[0].length;     //x 列数
        visit = new boolean[m][n];   // 储存访问标记
        queue = new LinkedQueue<>();
        init();
    }

    /**
     * 递归遍历，搜索所有可能的道路
     * 统一设置出发点均为（0,0），出口为右下角
     * 先向下出发
     */
    void init() {
        findAllPaths(maze, visit, 0, 0, maze[0].length, maze.length);
    }

    public void findAllPaths(int[][] maze, boolean[][] visit, int startX, int startY, int endX, int endY) {

            // 如果起点越界、已经访问过、或者不是通路，则返回
            if (startX < 0 || startX >= maze[0].length || startY < 0 || startY >= maze.length
                    || visit[startY][startX] || maze[startY][startX] != 0) {

                return;
            }
            //设置坐标方向
            if(visit[startY][startX]==false&&(!(startX==0&&startY==0)) ){
                position pas=stack.peek();
                int direction=0;
                /**
                 * 当前结点的坐标与上一个结点的坐标进行对比，进而进行方向设置
                 * 当x相同，比较y，判断上下方向
                 * 当y相同，比较x，判断左右方向
                 * 1 表示 向下
                 * 2 表示 向右
                 * 3 表示 向上
                 * 4 表示 向左
                 */
                //当x相同的情况
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
                    //当y相同的情况下
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
                //将方向放入上个结点中
                pas.setD(direction);

            }stack.push(new position(startX, startY, 0));// 载入数据

        if (startX == endX - 1 && startY == endY - 1) {
                // 如果到达终点，则遍历输出当前路径
                printPath();
            /**
             * 注意当验证到坐标为终点的时候，再调用完之后要用pop方法
             * 否则后续递归的时候会进行错位，出栈错结点
             */
            stack.pop();
                return;
            }

            visit[startY][startX] = true; // 标记起点已经访问过

            // 分别向四个方向递归
            findAllPaths(maze, visit, startX, startY + 1, endX, endY); // 向下
            findAllPaths(maze, visit, startX + 1, startY, endX, endY ); // 向右
            findAllPaths(maze, visit, startX, startY - 1, endX, endY); // 向上
            findAllPaths(maze, visit, startX - 1, startY, endX, endY); // 向左
            stack.pop();
            visit[startY][startX] = false; // 恢复标记，以便下一次访问




    }
    // 将每条路储存起来
    void printPath() {
        position pas;
        LinkedStack<position> stack1=new LinkedStack<>(stack); // 将stack内容全部复制下来
        queue.add(stack1);
  /*      LinkedStack<position> stack2=new LinkedStack<>();

        List<String> path = new LinkedList<>();


            while (!stack1.isEmpty()) {
                pas=stack1.pop();
                stack2.push(pas);
                path.add("(" + (pas.getX()+1 ) + "," + (pas.getY() +1) + "," + (pas.getD()  ) + ")");
            }
            Collections.reverse(path);
            //进行遍历
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