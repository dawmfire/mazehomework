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
 * MazeTable类是一个表格格式的迷宫图形表示。它创建一个二维整数数组作为表格的数据，
 * 并使用TableCellRenderer给值为“1”的单元格着色。它还确保了表格单元格是不可编辑的，
 * 并且表格头不能被调整大小或重新排序。
 */
public class MazeTable extends JFrame {
    int[][] maze;    // 迷宫储存
    JTextArea text;  // 输出面板
    JTable table;
    LinkedStack<position> stack; // 存储道路坐标
    public MazeTable(int[][] maze) {
        // 创建表格模型
        this.maze = maze;
        String[] columnNames = new String[maze[0].length];
        for (int i = 0; i < columnNames.length; i++) {
            columnNames[i] = "";
        }
        /**
        *由于table组件中的JTable（Object data[][] , Object colunmName[])中的参数是Object类型
        *所以先将int类型转成object类型
         */
        //统一设置左上角为入口，右下角为出口。
        Object[][] data = new Object[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (i == 0 && j == 0) {
                    data[i][j]="0(入口)";
                } else if (i == maze.length-1 && j==maze[0].length-1) {
                    data[i][j]="0(出口)";
                } else {
                    data[i][j] = maze[i][j] == 1 ? "1" : "0";
                }
            }
        }
            //设置单元格不可编辑
        DefaultTableModel mode = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };
        table = new JTable(mode);




        // 设置单元格渲染器
        TableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (maze[row][column] == 1) {
                    c.setBackground(Color.GRAY);  // 墙的单元格用灰色背景
                } else {
                    c.setBackground(Color.WHITE);  // 路的单元格用白色背景
                }
                return c;
            }
            //设置表数据居中显示
            @Override
            public void setHorizontalAlignment(int alignment) {
                super.setHorizontalAlignment(SwingConstants.CENTER);
            }

        };

        table.setDefaultRenderer(Object.class, renderer);


        /**
         * 当有表格渲染器重写的时候，也一并重写setHorizontalAlignment方法，
         *  public void setHorizontalAlignment(int alignment) {
         *                 super.setHorizontalAlignment(SwingConstants.CENTER);
         *             }
         *  能够使数据居中。
         *  当没有表格渲染器重写的时候，可以直接用：
         *1,DefaultTableCellRenderer dc=new DefaultTableCellRenderer();//创建一个默认的表单元格渲染器
         *2.dc.setHorizontalAlignment(SwingConstants.CENTER);//setHorizontalAlignment设置标签内容沿着X轴的对齐方式,有以下常量LEFT,CENTER(仅用于图像的标签的默认值)RAGHT,LEADING(默认为纯文本的标记),或TRAILING
         *3.table.setDefaultRenderer(Object.class, dc);
         * --------OR-----------
         * DefaultTableCellRenderer cr = new DefaultTableCellRenderer ();
         * cr.setHorizontalAlignment (JLabel.CENTER);
         * table.setDefaultRenderer (Object.class, cr);
         */

        // 将表格添加到滚动面板中
        JScrollPane scrollPane = new JScrollPane(table);

        // 添加滚动面板到窗口中
        getContentPane().add(scrollPane,BorderLayout.NORTH);


        //将多行文件框放入中央
        text = new  JTextArea(40,40);
        getContentPane().add(text, BorderLayout.CENTER);
        //showCoordinatePanel(maze);

        //探索道路
        JButton jb = new JButton("找出一条道路");
        JPanel jp = new JPanel();
        jp.add(jb,BorderLayout.SOUTH);
        getContentPane().add(jp, BorderLayout.SOUTH);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 显示正确的道路
            Figure figure = new Figure();
            figure.solve(maze);
            stack=figure.printPath();
            showCoordinatePanel(stack);

            }
        });

        //探索多条可能道路
        JButton jb1 =new JButton("所有可能的道路");
        jp.add(jb1,BorderLayout.CENTER);
        jb1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Arithmetic arithmetic = new Arithmetic(maze);

            }
        });


        // 设置窗口属性
        setTitle("迷宫表格");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 1000);
        setLocationRelativeTo(null);
        setVisible(true);
        //不可整列移动，true表示可移动，false表示不可移动
        table.getTableHeader().setReorderingAllowed(false);
        //不可拉动表格，true表示可移动，false表示不可移动
        table.getTableHeader().setResizingAllowed(false);




    }
        //输出道路
        boolean ad =true;
        private void showCoordinatePanel(LinkedStack<position> stack) {
        text.setEditable(false);   // 不可编辑
        text.setLineWrap(true);         // 换行策略
        text.setFont(new Font("标楷体", Font.BOLD, 20));  //设置当前字体
        text.setText("(数字1表示下，数字2表示右，数字3表示上，数字4表示左)");
        text.append("道路:");

        /**
         * table.setValueAt("2",poss.getY(),poss.getX());//修改第poss.getY(行，第poss.getX()列为2,修改单元格数据
         * table.repaint();再重绘
         */

        if(stack!=null) {
            ad=false;
            List<String> path = new LinkedList <>();
            while (!stack.isEmpty()) {
                position poss = stack.pop();
                maze[poss.getY()][poss.getX()]=2;
                table.setValueAt("2",poss.getY(),poss.getX());//修改第3行，第4列为666
                table.repaint();
                path.add("(" + (poss.getX() + 1) + "," + (poss.getY() + 1) + "," + (poss.getD() + 1) + ")");
                //System.out.print("(" + (poss.getX() + 1) + "," + (poss.getY() + 1) + "," + (poss.getD() + 1) + ")" );

            }Collections.reverse(path);
            //进行遍历
            Iterator<String> iterator = path.iterator();
            while (iterator.hasNext()) {
                text.append(iterator.next());
            }
        }if(ad) {
            text.setText("没有道路");
        }

    }

}