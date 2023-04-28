import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.*;

import static java.awt.Toolkit.*;

public class test extends Frame {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        Object[][] a = {
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

        String[] s = new String[a[0].length];
        for (int i = 0; i <s.length; i++) {
            s[i]=String.valueOf(i);
        }

        DefaultTableModel model = new DefaultTableModel(a,s);
        JTable table = new JTable(model);
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer(){};
        cr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, cr);
        JPanel jPanel = new JPanel();
        jPanel.add(table);
        f.add(jPanel,BorderLayout.CENTER);
       //f.add(new JScrollPane(table),BorderLayout.CENTER);

        Toolkit kit =Toolkit.getDefaultToolkit();
        Dimension screenSize =kit.getScreenSize(); // 获取显示器屏幕大小

        int x=(screenSize.width-f.getWidth())/2; // x=(屏幕宽度 -窗口宽度)/2
        int y=(screenSize.height-f.getHeight())/2; // y=(屏幕高度 -窗口高度)/2

        f.setLocation(x,y);
        f.setSize(500, 500);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
class MazeTableCellRenderer implements TableCellRenderer {
    Object[][] y = {
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

    @Override
    public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        java.awt.Component c = new JPanel();
        c.setSize(50, 50);
        if ((int) y[row][column] == 0) {
            c.setBackground(Color.WHITE);
        } else {
            c.setBackground(Color.BLACK);
        }
        return c;
    }
}
 class MazeTableModel extends DefaultTableModel {

    private static final long serialVersionUID = 1L;

    public MazeTableModel(Object[][] maze) {
        for (Object[] row : maze) {
            addRow(row);
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
