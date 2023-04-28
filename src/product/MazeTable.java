package product;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * MazeTable����һ������ʽ���Թ�ͼ�α�ʾ��������һ����ά����������Ϊ�������ݣ�
 * ��ʹ��TableCellRenderer��ֵΪ��1���ĵ�Ԫ����ɫ������ȷ���˱��Ԫ���ǲ��ɱ༭�ģ�
 * ���ұ��ͷ���ܱ�������С����������
 */
public class MazeTable extends JFrame {
    int[][] maze;    // �Թ�����
    JTextArea text;  // ������
    JTable table;
    LinkedStack<position> stack; // �洢��·����
    public MazeTable(int[][] maze) {
        // �������ģ��
        this.maze = maze;
        String[] columnNames = new String[maze[0].length];
        for (int i = 0; i < columnNames.length; i++) {
            columnNames[i] = "";
        }
        /**
        *����table����е�JTable��Object data[][] , Object colunmName[])�еĲ�����Object����
        *�����Ƚ�int����ת��object����
         */
        //ͳһ�������Ͻ�Ϊ��ڣ����½�Ϊ���ڡ�
        Object[][] data = new Object[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (i == 0 && j == 0) {
                    data[i][j]="0(���)";
                } else if (i == maze.length-1 && j==maze[0].length-1) {
                    data[i][j]="0(����)";
                } else {
                    data[i][j] = maze[i][j] == 1 ? "1" : "0";
                }
            }
        }
            //���õ�Ԫ�񲻿ɱ༭
        DefaultTableModel mode = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };
        table = new JTable(mode);




        // ���õ�Ԫ����Ⱦ��
        TableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (maze[row][column] == 1) {
                    c.setBackground(Color.GRAY);  // ǽ�ĵ�Ԫ���û�ɫ����
                } else {
                    c.setBackground(Color.WHITE);  // ·�ĵ�Ԫ���ð�ɫ����
                }
                return c;
            }
            //���ñ����ݾ�����ʾ
            @Override
            public void setHorizontalAlignment(int alignment) {
                super.setHorizontalAlignment(SwingConstants.CENTER);
            }

        };

        table.setDefaultRenderer(Object.class, renderer);


        /**
         * ���б����Ⱦ����д��ʱ��Ҳһ����дsetHorizontalAlignment������
         *  public void setHorizontalAlignment(int alignment) {
         *                 super.setHorizontalAlignment(SwingConstants.CENTER);
         *             }
         *  �ܹ�ʹ���ݾ��С�
         *  ��û�б����Ⱦ����д��ʱ�򣬿���ֱ���ã�
         *1,DefaultTableCellRenderer dc=new DefaultTableCellRenderer();//����һ��Ĭ�ϵı�Ԫ����Ⱦ��
         *2.dc.setHorizontalAlignment(SwingConstants.CENTER);//setHorizontalAlignment���ñ�ǩ��������X��Ķ��뷽ʽ,�����³���LEFT,CENTER(������ͼ��ı�ǩ��Ĭ��ֵ)RAGHT,LEADING(Ĭ��Ϊ���ı��ı��),��TRAILING
         *3.table.setDefaultRenderer(Object.class, dc);
         * --------OR-----------
         * DefaultTableCellRenderer cr = new DefaultTableCellRenderer ();
         * cr.setHorizontalAlignment (JLabel.CENTER);
         * table.setDefaultRenderer (Object.class, cr);
         */

        // �������ӵ����������
        JScrollPane scrollPane = new JScrollPane(table);

        // ��ӹ�����嵽������
        getContentPane().add(scrollPane,BorderLayout.NORTH);


        //�������ļ����������
        text = new  JTextArea(40,40);
        getContentPane().add(text, BorderLayout.CENTER);
        //showCoordinatePanel(maze);

        //̽����·
        JButton jb = new JButton("�ҳ�һ����·");
        JPanel jp = new JPanel();
        jp.add(jb,BorderLayout.SOUTH);
        getContentPane().add(jp, BorderLayout.SOUTH);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ��ʾ��ȷ�ĵ�·
            Figure figure = new Figure();
            figure.solve(maze);
            stack=figure.printPath();
            showCoordinatePanel(stack);

            }
        });

        //̽���������ܵ�·
        JButton jb1 =new JButton("���п��ܵĵ�·");
        jp.add(jb1,BorderLayout.CENTER);
        jb1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Arithmetic arithmetic = new Arithmetic(maze);

            }
        });


        // ���ô�������
        setTitle("�Թ����");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 1000);
        setLocationRelativeTo(null);
        setVisible(true);
        //���������ƶ���true��ʾ���ƶ���false��ʾ�����ƶ�
        table.getTableHeader().setReorderingAllowed(false);
        //�����������true��ʾ���ƶ���false��ʾ�����ƶ�
        table.getTableHeader().setResizingAllowed(false);




    }
        //�����·
        boolean ad =true;
        private void showCoordinatePanel(LinkedStack<position> stack) {
        text.setEditable(false);   // ���ɱ༭
        text.setLineWrap(true);         // ���в���
        text.setFont(new Font("�꿬��", Font.BOLD, 20));  //���õ�ǰ����
        text.setText("(����1��ʾ�£�����2��ʾ�ң�����3��ʾ�ϣ�����4��ʾ��)");
        text.append("��·:");

        /**
         * table.setValueAt("2",poss.getY(),poss.getX());//�޸ĵ�poss.getY(�У���poss.getX()��Ϊ2,�޸ĵ�Ԫ������
         * table.repaint();���ػ�
         */

        if(stack!=null) {
            ad=false;
            List<String> path = new LinkedList <>();
            while (!stack.isEmpty()) {
                position poss = stack.pop();
                maze[poss.getY()][poss.getX()]=2;
                table.setValueAt("2",poss.getY(),poss.getX());//�޸ĵ�3�У���4��Ϊ666
                table.repaint();
                path.add("(" + (poss.getX() + 1) + "," + (poss.getY() + 1) + "," + (poss.getD() + 1) + ")");
                //System.out.print("(" + (poss.getX() + 1) + "," + (poss.getY() + 1) + "," + (poss.getD() + 1) + ")" );

            }Collections.reverse(path);
            //���б���
            Iterator<String> iterator = path.iterator();
            while (iterator.hasNext()) {
                text.append(iterator.next());
            }
        }if(ad) {
            text.setText("û�е�·");
        }

    }

}