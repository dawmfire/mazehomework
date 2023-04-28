package 例子2;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

 class MazeTable extends JFrame {
    public MazeTable(int[][] maze) {
        // 创建表格模型
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
        };
        table.setDefaultRenderer(Object.class, renderer);

        // 将表格添加到滚动面板中
        JScrollPane scrollPane = new JScrollPane(table);

        // 添加滚动面板到窗口中
        getContentPane().add(scrollPane);

        // 设置窗口属性
        setTitle("迷宫表格");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        //不可整列移动，true表示可移动，false表示不可移动
        table.getTableHeader().setReorderingAllowed(false);
        //不可拉动表格，true表示可移动，false表示不可移动
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
