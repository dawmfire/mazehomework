package product;
/**
 * @author Dawn
 */
// 非递归算法
public class Figure {
    private   int[][] directions = {{0, 1}, {1, 0},{0, -1},{-1, 0} }; // 下 右 上 左 四个方向
    LinkedStack<position> stack;
    public  void solve(int[][] maze) {
        int m = maze.length;   //y 行数
        int n = maze[0].length;     //x 列数
        /**
         * 数字1表示下，数字2表示右，数字3表示上，数字4表示左
         * 设置初始点（0,0）先走下
         */
        position pos = new position(0,0);
        boolean[][] visited = new boolean[m][n];   // 标记是否访问过
        stack = new LinkedStack<>(); // 存储路径的栈
        stack.push(pos); // 起点入栈
        visited[0][0] = true;
        while (!stack.isEmpty()) {
            position poss = stack.peek(); // 当前位置提取栈顶
            int i = poss.getX(), j = poss.getY(), d = poss.getD(); // 当前位置和方向
            visited[j][i] = true;   // 走过的点记为true
            if (i == n-1 && j == m-1 ) { // 到达终点
                poss.setD(-1);
                return;
            }
             boolean found = false;
            for (int k = 0; k < directions.length; k++) { // 尝试四个方向

                int nowX = i + directions[k][0], nowY = j + directions[k][1];
                /**
                 * 判断顺时针的方向的点是否符合标准，刚好在路上，判断该点是否已经走过
                 */
                if (nowX >= 0 && nowX < n && nowY >= 0 && nowY < m && maze[nowY][nowX] == 0 && !visited[nowY][nowX]) {
                    poss.setD(k);
                    stack.push(new position(nowX, nowY)); // 新位置入栈
                    found = true;
                    break;
                }
            }
                if (!found) {
                    stack.pop(); // 四个方向都不能走，弹出当前栈顶元素
                }
        }
        System.out.println("No path found!");
        stack=null ;//当没有终点的时候
    }
     public LinkedStack<position> printPath() {
            return stack;
    }
}
