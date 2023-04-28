package ����1;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Stack;
import javax.swing.*;

/* �Թ���һ��һ��������ɣ�����֮��ı�Ϊ�Թ���ǽ��
 * ���Թ���Ϊ����Щ�����Ϲ��������ÿ�����Ӷ������ϵ�һ���ڵ㡣
 * �������������Ӽ�����ֻ��һ���޻���·����
 * �Թ��Ĺ������̼�������Щ�������������һ�����Ĺ��̡�
 * */

// ����
class Lattice {
    static final int INTREE = 1;
    static final int NOTINTREE = 0;
    private int x = -1; // ���ӵ�λ�ã��ڵڼ���
    private int y = -1; // �ڼ���
    private int flag = NOTINTREE; // flag����ʶ�����Ƿ��Ѽ�������
    private Lattice father = null; // ���ӵĸ��׽ڵ�

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
    }//��ȡ���ӵĸ��׽��

    public void setFather(Lattice f) {
        father = f;
    }//���ø��׽��

    public void setFlag(int f) {
        flag = f;
    }//��ʶ���Ӽ�������
}

public class Maze extends JPanel {
    private static final long serialVersionUID = -8300339045454852626L;
    private int NUM, width, padding; // NUM���Թ���С��width��ÿ�����ӵĿ�Ⱥ͸߶�
    private Lattice[][] maze;
    private int ballX, ballY; // ���λ�ã��ڵڼ��еڼ��и�����
    private boolean drawPath = false; // flag����ʶ�Ƿ񻭳�·��

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

    // ��ʼ����Ϸ���ؿ�һ��ʱʹ��
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

    // �ɸ��ӵ��������õ��������ĵ������X����
    public int getCenterX(int x) {
        return padding + x * width + width / 2;
    }

    // �ɸ��ӵ��������õ��������ĵ������Y����
    public int getCenterY(int y) {
        return padding + y * width + width / 2;
    }

    public int getCenterX(Lattice p) {
        return padding + p.getY() * width + width / 2;
    }

    public int getCenterY(Lattice p) {
        return padding + p.getX() * width + width / 2;
    }

    // ����Ƿ񵽴����һ�����ӣ��������߳����Թ����ؿ�һ����Ϸ
    private void checkIsWin() {
        if (ballX == NUM - 1 && ballY == NUM - 1) {
            JOptionPane.showMessageDialog(null, "���߳����Թ���", "YOU WIN !", JOptionPane.PLAIN_MESSAGE);
            init();
        }
    }

    // �ƶ�С��cΪ������
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
        // ���ƶ���δ�����Ҹ���֮����·����������ƶ�������С��λ�ã������ƶ��Ƿ�
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

    // �Ƿ����
    private boolean isOutOfBorder(Lattice p) {
        return isOutOfBorder(p.getX(), p.getY());
    }

    private boolean isOutOfBorder(int x, int y) {
        return (x > NUM - 1 || y > NUM - 1 || x < 0 || y < 0) ? true : false;
    }

    // ��ȡ���ӵ��ھӸ���
    private Lattice[] getNeis(Lattice p) {
        final int[] adds = {-1, 0, 1, 0, -1};
        if (isOutOfBorder(p)) {
            return null;
        }
        Lattice[] ps = new Lattice[4]; // �ĸ��ھӸ��ӣ�˳��Ϊ�������󣬳�����ھ�Ϊnull
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

    // ����������������Թ�
    private void createMaze() {
        // ���ѡһ��������Ϊ���ĸ�
        Random random = new Random();
        // ������ȱ���
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

    // Ĩ����������֮��ı�
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

    // ���Թ�
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // ��NUM*NUM������
        for (int i = 0; i <= NUM; i++) {
            g.drawLine(padding + i * width, padding, padding + i * width,
                    padding + NUM * width);
        }
        for (int j = 0; j <= NUM; j++) {
            g.drawLine(padding, padding + j * width, padding + NUM * width,
                    padding + j * width);
        }
        // ʹ�ñ���ɫ������·���ĸ���֮�仭�ߣ���ǽĨ��
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
        // �����Ͻǵ����
        g.drawLine(padding, padding + 1, padding, padding + width - 1);
        int last = padding + NUM * width;
        // �����½ǳ���
        g.drawLine(last, last - 1, last, last - width + 1);
        // ��С��
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
        JFrame frame = new JFrame("MAZE(���ո����ʾ������·��,��R���¿�ʼ��)");
        frame.getContentPane().add(p);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width + padding, width + padding + padding);
        frame.setLocation(LX, LY);
        frame.setVisible(true);
    }
}
