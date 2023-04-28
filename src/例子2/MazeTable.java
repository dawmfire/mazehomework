package ����2;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

 class MazeTable extends JFrame {
    public MazeTable(int[][] maze) {
        // �������ģ��
        String[] columnNames = new String[maze[0].length];
        for (int i = 0; i < columnNames.length; i++) {
            columnNames[i] = "";
        }
        Object[][] data = new Object[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                data[i][j] = maze[i][j] == 1 ? "0" : "1";
            }
        }

        DefaultTableModel mode = new DefaultTableModel(data,columnNames){
            @Override
            public boolean isCellEditable(int row , int column){

                return false;
            }
        };
        JTable table = new JTable(mode);
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
        };
        table.setDefaultRenderer(Object.class, renderer);

        // �������ӵ����������
        JScrollPane scrollPane = new JScrollPane(table);

        // ��ӹ�����嵽������
        getContentPane().add(scrollPane);

        // ���ô�������
        setTitle("�Թ����");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        //���������ƶ���true��ʾ���ƶ���false��ʾ�����ƶ�
        table.getTableHeader().setReorderingAllowed(false);
        //�����������true��ʾ���ƶ���false��ʾ�����ƶ�
        table.getTableHeader().setResizingAllowed(false);
    }

    public static void main(String[] args) {
        int[][] maze = {
                {0, 0, 1, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 1, 0, 1},
                {0, 1, 1, 1, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 1},
                {0, 1, 1, 1, 1, 0, 0, 1},
                {1, 1, 0, 0, 0, 1, 0, 1},
                {1, 1, 0, 0, 0, 0, 0, 0}
        };
        new MazeTable(maze);
    }
}
