package 例子1;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Stack;
import javax.swing.*;

/* 迷宫由一个一个格子组成，格子之间的边为迷宫的墙。
 * 将迷宫视为在这些格子上构造的树，每个格子都是树上的一个节点。
 * 则任意两个格子间有且只有一条无环的路径。
 * 迷宫的构建过程即是在这些格子上随机构造一棵树的过程。
 * */

// 格子
class Lattice {
    static final int INTREE = 1;
    static final int NOTINTREE = 0;
    private int x = -1; // 格子的位置，在第几行
    private int y = -1; // 第几列
    private int flag = NOTINTREE; // flag，标识格子是否已加入树中
    private Lattice father = null; // 格子的父亲节点

    public Lattice(int xx, int yy) {
        x = xx;
        y = yy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getFlag() {
        return flag;
    }

    public Lattice getFather() {
        return father;
    }//获取格子的父亲结点

    public void setFather(Lattice f) {
        father = f;
    }//设置父亲结点

    public void setFlag(int f) {
        flag = f;
    }//标识格子加入数中
}

public class Maze extends JPanel {
    private static final long serialVersionUID = -8300339045454852626L;
    private int NUM, width, padding; // NUM：迷宫大小；width：每个格子的宽度和高度
    private Lattice[][] maze;
    private int ballX, ballY; // 球的位置，在第几行第几列格子上
    private boolean drawPath = false; // flag，标识是否画出路径

    Maze(int m, int wi, int p) {
        NUM = m;
        width = wi;
        padding = p;
        maze = new Lattice[NUM][NUM];
        for (int i = 0; i < NUM; i++) {
            for (int j = 0; j < NUM ; j++) {
                maze[i][j] = new Lattice(i, j);
            }
        }
        createMaze();
        setKeyListener();
        this.setFocusable(true);
    }

    // 初始化游戏，重开一局时使用
    private void init() {
        for (int i = 0; i <= NUM - 1; i++) {
            for (int j = 0; j <= NUM - 1; j++) {
                maze[i][j].setFather(null);
                maze[i][j].setFlag(Lattice.NOTINTREE);
            }
        }
        ballX = 0;
        ballY = 0;
        drawPath = false;
        createMaze();
        this.setFocusable(true);
        repaint();
    }

    // 由格子的行数，得到格子中心点的像素X座标
    public int getCenterX(int x) {
        return padding + x * width + width / 2;
    }

    // 由格子的列数，得到格子中心点的像素Y座标
    public int getCenterY(int y) {
        return padding + y * width + width / 2;
    }

    public int getCenterX(Lattice p) {
        return padding + p.getY() * width + width / 2;
    }

    public int getCenterY(Lattice p) {
        return padding + p.getX() * width + width / 2;
    }

    // 检查是否到达最后一个格子，若是则走出了迷宫，重开一局游戏
    private void checkIsWin() {
        if (ballX == NUM - 1 && ballY == NUM - 1) {
            JOptionPane.showMessageDialog(null, "你走出了迷宫。", "YOU WIN !", JOptionPane.PLAIN_MESSAGE);
            init();
        }
    }

    // 移动小球，c为按键码
    synchronized private void move(int c) {
        int tx = ballX, ty = ballY;
        switch (c) {
            case KeyEvent.VK_LEFT:
                ty--;
                break;
            case KeyEvent.VK_RIGHT:
                ty++;
                break;
            case KeyEvent.VK_UP:
                tx--;
                break;
            case KeyEvent.VK_DOWN:
                tx++;
                break;
            case KeyEvent.VK_R:
                init();
                break;
            case KeyEvent.VK_SPACE:
                if (drawPath == true) {
                    drawPath = false;
                } else {
                    drawPath = true;
                }
                break;
        }
        // 若移动后未出界且格子之间有路径，则进行移动，更新小球位置，否则移动非法
        if (!isOutOfBorder(tx, ty) && (maze[tx][ty].getFather() == maze[ballX][ballY] || maze[ballX][ballY].getFather() == maze[tx][ty])) {
            ballX = tx;
            ballY = ty;
        }
    }

    private void setKeyListener() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int c = e.getKeyCode();
                move(c);
                repaint();
                checkIsWin();
            }
        });
    }

    // 是否出界
    private boolean isOutOfBorder(Lattice p) {
        return isOutOfBorder(p.getX(), p.getY());
    }

    private boolean isOutOfBorder(int x, int y) {
        return (x > NUM - 1 || y > NUM - 1 || x < 0 || y < 0) ? true : false;
    }

    // 获取格子的邻居格子
    private Lattice[] getNeis(Lattice p) {
        final int[] adds = {-1, 0, 1, 0, -1};
        if (isOutOfBorder(p)) {
            return null;
        }
        Lattice[] ps = new Lattice[4]; // 四个邻居格子，顺序为上右下左，出界的邻居为null
        int xt;
        int yt;
        for (int i = 0; i <= 3; i++) {
            xt = p.getX() + adds[i];
            yt = p.getY() + adds[i + 1];
            if (isOutOfBorder(xt, yt)) {
                continue;
            }
            ps[i] = maze[xt][yt];
        }
        return ps;
    }

    // 构建随机树，创建迷宫
    private void createMaze() {
        // 随机选一个格子作为树的根
        Random random = new Random();
        // 深度优先遍历
        Stack<Lattice> s = new Stack<Lattice>();
        Lattice p = maze[0][0];
        Lattice neis[] = null;
        s.push(p);
        while (!s.isEmpty()) {
            p = s.pop();
            p.setFlag(Lattice.INTREE);
            neis = getNeis(p);
            int ran = Math.abs(random.nextInt()) % 4;
            for (int a = 0; a <= 3; a++) {
                ran++;
                ran %= 4;
                if (neis[ran] == null || neis[ran].getFlag() == Lattice.INTREE) {
                    continue;
                }
                s.push(neis[ran]);
                neis[ran].setFather(p);
            }
        }
    }

    // 抹掉两个格子之间的边
    private void clearFence(int i, int j, int fx, int fy, Graphics g) {
        int sx = padding + ((j > fy ? j : fy) * width),
                sy = padding + ((i > fx ? i : fx) * width),
                dx = (i == fx ? sx : sx + width),
                dy = (i == fx ? sy + width : sy);
        if (sx != dx) {
            sx++;
            dx--;
        } else {
            sy++;
            dy--;
        }
        g.drawLine(sx, sy, dx, dy);
    }

    // 画迷宫
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 画NUM*NUM条黑线
        for (int i = 0; i <= NUM; i++) {
            g.drawLine(padding + i * width, padding, padding + i * width,
                    padding + NUM * width);
        }
        for (int j = 0; j <= NUM; j++) {
            g.drawLine(padding, padding + j * width, padding + NUM * width,
                    padding + j * width);
        }
        // 使用背景色，在有路径的格子之间画边，把墙抹掉
        g.setColor(this.getBackground());
        for (int i = NUM - 1; i >= 0; i--) {
            for (int j = NUM - 1; j >= 0; j--) {
                Lattice f = maze[i][j].getFather();
                if (f != null) {
                    int fx = f.getX(), fy = f.getY();
                    clearFence(i, j, fx, fy, g);
                }
            }
        }
        // 画左上角的入口
        g.drawLine(padding, padding + 1, padding, padding + width - 1);
        int last = padding + NUM * width;
        // 画右下角出口
        g.drawLine(last, last - 1, last, last - width + 1);
        // 画小球
        g.setColor(Color.RED);
        g.fillOval(getCenterX(ballY) - width / 3, getCenterY(ballX) - width / 3, width / 2, width / 2);
        if (drawPath == true) {
            drawPath(g);
        }
    }
    private void drawPath(Graphics g) {
        Color PATH_COLOR = Color.red;
        if (drawPath == true) {
            g.setColor(PATH_COLOR);
        } else {
            g.setColor(this.getBackground());
        }
        Lattice p = maze[NUM - 1][NUM - 1];
        while (p.getFather() != null) {
            p.setFlag(2);
            p = p.getFather();
        }
        g.fillOval(getCenterX(p) - width / 3, getCenterY(p) - width / 3, width / 2, width / 2);
        p = maze[0][0];
        while (p.getFather() != null) {
            if (p.getFlag() == 2) {
                p.setFlag(3);
            }
            g.drawLine(getCenterX(p), getCenterY(p), getCenterX(p.getFather()), getCenterY(p.getFather()));
            p = p.getFather();
        }
        g.setColor(PATH_COLOR);
        p = maze[NUM - 1][NUM - 1];
        while (p.getFather() != null) {
            if (p.getFlag() == 3) {
                break;
            }
            g.drawLine(getCenterX(p), getCenterY(p), getCenterX(p.getFather()), getCenterY(p.getFather()));
            p = p.getFather();
        }
    }


    public static void main(String[] args) {
        final int n = 30, width = 600, padding = 20, LX = 450, LY = 100;
        JPanel p = new Maze(n, (width - padding - padding) / n, padding);
        JFrame frame = new JFrame("MAZE(按空格键显示或隐藏路径,按R重新开始。)");
        frame.getContentPane().add(p);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width + padding, width + padding + padding);
        frame.setLocation(LX, LY);
        frame.setVisible(true);
    }
}
