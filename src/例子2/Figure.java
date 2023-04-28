package ����2;

import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author DELL
 * �Թ���Ϸ
 * ����Ϊ�Թ���Ϸ�ṩͼ�λ����档
 *
 */
public class Figure {
    Path path;
    Place[] maze=null;
    Button[] button=null;
    boolean[] isPath=null;
    class MazeGameFigure extends Frame implements ActionListener{
        public MazeGameFigure(){
            super("�Թ���Ϸ");
        }
        public void init(){
            this.setSize(250, 250);
            this.setBackground(Color.WHITE);
            Toolkit kit =Toolkit.getDefaultToolkit();
            Dimension screenSize=kit.getScreenSize();
            int screenWidth=screenSize.width;
            int screenHeight=screenSize.height;
            int windowWidth=this.getWidth();
            int windowHeight=this.getHeight();
            this.setLocation((screenWidth-windowWidth)/2,(screenHeight-windowHeight)/2 );
            this.setLayout(new GridLayout(4,1));
            Label welcom=new Label("��ӭ�����Թ���Ϸ!");
            Button start=new Button("��ʼ��Ϸ");
            Button set=new Button("��Ϸ����");
            Button end=new Button("�˳���Ϸ");
            start.setBackground(Color.WHITE);
            set.setBackground(Color.WHITE);
            end.setBackground(Color.WHITE);
            add(welcom);
            add(start);
            add(set);
            add(end);
            start.addActionListener(this);
            set.addActionListener(this);
            end.addActionListener(this);
            addWindowListener(new closeWin());
            this.setVisible(true);
        }
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("��ʼ��Ϸ")){
                MazeFigure mazeFigure=new MazeFigure();
                mazeFigure.init();
                dispose();
            }
            if(e.getActionCommand().equals("��Ϸ����")){
                MazeSetFigure mazeSetFigure=new MazeSetFigure();
                mazeSetFigure.init();
                dispose();
            }
            if(e.getActionCommand().equals("�˳���Ϸ")){
                dispose();
            }
        }
    }
    class MazeFigure extends Frame implements ActionListener{
        public MazeFigure(){
            super("�Թ�");
        }
        public void init(){
            this.setSize(500, 500);
            this.setBackground(Color.BLACK);
            Toolkit kit =Toolkit.getDefaultToolkit();
            Dimension screenSize=kit.getScreenSize();
            int screenWidth=screenSize.width;
            int screenHeight=screenSize.height;
            int windowWidth=this.getWidth();
            int windowHeight=this.getHeight();
            this.setLocation((screenWidth-windowWidth)/2,(screenHeight-windowHeight)/2 );
            this.setLayout(new GridLayout(path.getSize(),path.getSize()));
            maze=path.getMaze();
            int entrance=path.getEntrance();
            int exit=path.getExit();
            button=new Button[maze.length];
            for(int i=1;i<maze.length;i++){
                if(maze[i].getWall()==0){
                    button[i]=new Button("");
                    button[i].setActionCommand("·");
                    button[i].setBackground(Color.WHITE);
                }
                if(maze[i].getWall()==1){
                    button[i]=new Button("ǽ");
                    button[i].setActionCommand("ǽ");
                    button[i].setBackground(Color.LIGHT_GRAY);
                }
            }
            button[entrance].setLabel("���");
            button[exit].setLabel("����");
            for(int i=1;i<button.length;i++){
                button[i].addActionListener(this);
                add(button[i]);
            }
            addWindowListener(new closeWin());
            this.setVisible(true);
        }
        private boolean isComplete(){
            isPath=path.getPath();
            for(int i=1;i<isPath.length;i++){
                if(isPath[i]&&button[i].getBackground()!=Color.RED){
                    return false;
                }
            }
            return true;
        }
        public void actionPerformed(ActionEvent e){
            Button button=(Button)e.getSource();
            if(button.getActionCommand().equals("·")){
                if(button.getBackground()==Color.WHITE){
                    button.setBackground(Color.RED);
                }else if(button.getBackground()==Color.RED){
                    button.setBackground(Color.WHITE);
                }
            }
            if(isComplete()){
                CongratulationFigure congratulationFigure=new CongratulationFigure();
                congratulationFigure.init();
                this.dispose();
            }
        }
    }
    class MazeSetFigure extends Frame implements ActionListener ,TextListener{
        String newSize,newEntrance,newExit;
        TextField setMaze,setEntrance,setExit;
        int size,entrance,exit;
        public MazeSetFigure(){
            super("�Թ�����");
        }
        public void init(){
            this.setSize(250, 150);
            this.setBackground(Color.WHITE);
            Toolkit kit =Toolkit.getDefaultToolkit();
            Dimension screenSize=kit.getScreenSize();
            int screenWidth=screenSize.width;
            int screenHeight=screenSize.height;
            int windowWidth=this.getWidth();
            int windowHeight=this.getHeight();
            this.setLocation((screenWidth-windowWidth)/2,(screenHeight-windowHeight)/2 );
            GridLayout layout=new GridLayout(4,2);
            this.setLayout(layout);
            Label size=new Label("�Թ���ģ");
            Label entrance=new Label("�Թ����");
            Label exit=new Label("�Թ�����");
            Button menu=new Button("���ز˵�");
            Button set=new Button("�������");
            setMaze= new TextField("10");
            setEntrance= new TextField("���Ͻ�");
            setExit= new TextField("���½�");
            add(size);
            add(setMaze);
            add(entrance);
            add(setEntrance);
            add(exit);
            add(setExit);
            add(menu);
            add(set);
            menu.addActionListener(this);
            set.addActionListener(this);
            setMaze.addTextListener(this);
            setEntrance.addTextListener(this);
            setExit.addTextListener(this);
            addWindowListener(new closeWin());
            this.setVisible(true);
        }
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("���ز˵�")){
                dispose();
                Figure figure=new Figure();
                figure.init();
            }
            if(e.getActionCommand().equals("�������")){
                boolean isSizeReasonable=true;
                boolean isEntranceReasonable=true;
                boolean isExitReasonable=true;
                newSize=setMaze.getText();
                newEntrance=setEntrance.getText();
                newExit=setExit.getText();
                try{
                    size=Integer.parseInt(newSize);
                }catch(Exception ex){
                    isSizeReasonable=false;
                }
                if(isSizeReasonable==true){
                    if(newEntrance.equals("���Ͻ�")){
                        entrance=1;
                    }else if(newEntrance.equals("���Ͻ�")){
                        entrance=size;
                    }else if(newEntrance.equals("���½�")){
                        entrance=size*(size-1)+1;
                    }else if(newEntrance.equals("���½�")){
                        entrance=size*size;
                    }else{
                        isEntranceReasonable=false;
                    }

                    if(newExit.equals("���Ͻ�")){
                        exit=1;
                    }else if(newExit.equals("���Ͻ�")){
                        exit=size;
                    }else if(newExit.equals("���½�")){
                        exit=size*(size-1)+1;
                    }else if(newExit.equals("���½�")){
                        exit=size*size;
                    }else{
                        isExitReasonable=false;
                    }

                    if(isEntranceReasonable==true&&isExitReasonable==true){
                        if(entrance==exit){
                            isEntranceReasonable=false;
                            isExitReasonable=false;
                        }
                    }
                }
                if(isSizeReasonable==true&&isEntranceReasonable==true&&isExitReasonable==true){
                    dispose();
                    Figure figure=new Figure(size,entrance,exit);
                    figure.init();
                }else{
                    SetErrorFigure setErrorFigure=new SetErrorFigure();
                    setErrorFigure.init();
                    dispose();
                }
            }
        }
        public void textValueChanged(TextEvent e){

        }
    }
    class CongratulationFigure extends Frame implements ActionListener{
        public CongratulationFigure(){
            super("��ϲ");
        }
        public void init(){
            this.setSize(220, 100);
            this.setBackground(Color.WHITE);
            Toolkit kit =Toolkit.getDefaultToolkit();
            Dimension screenSize=kit.getScreenSize();
            int screenWidth=screenSize.width;
            int screenHeight=screenSize.height;
            int windowWidth=this.getWidth();
            int windowHeight=this.getHeight();
            this.setLocation((screenWidth-windowWidth)/2,(screenHeight-windowHeight)/2 );
            this.setLayout(new GridLayout(2,1));
            Label text=new Label("��ϲ���ɹ��߳��Թ�!");
            Button button=new Button("ȷ��");
            button.setBackground(Color.WHITE);
            add(text);
            add(button);
            button.addActionListener(this);
            addWindowListener(new closeWin());
            this.setVisible(true);
        }
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("ȷ��")){
                dispose();
                Figure figure=new Figure();
                figure.init();
            }
        }
    }
    class SetErrorFigure extends Frame implements ActionListener{
        public SetErrorFigure(){
            super("����");
        }
        public void init(){
            this.setSize(230, 100);
            this.setBackground(Color.WHITE);
            Toolkit kit =Toolkit.getDefaultToolkit();
            Dimension screenSize=kit.getScreenSize();
            int screenWidth=screenSize.width;
            int screenHeight=screenSize.height;
            int windowWidth=this.getWidth();
            int windowHeight=this.getHeight();
            this.setLocation((screenWidth-windowWidth)/2,(screenHeight-windowHeight)/2 );
            this.setLayout(new GridLayout(2,1));
            Label text=new Label("����������ݲ�����,����ʧ��!");
            Button button=new Button("ȷ��");
            button.setBackground(Color.WHITE);
            add(text);
            add(button);
            button.addActionListener(this);
            addWindowListener(new closeWin());
            this.setVisible(true);
        }
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("ȷ��")){
                dispose();
                Figure figure=new Figure();
                figure.init();
            }
        }
    }
    class closeWin extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            Window w=e.getWindow();
            w.dispose();
        }
    }

    public Figure(){
        path=new Path();
    }

    public Figure(int size,int entrance,int exit){
        path=new Path(size,entrance,exit);
    }

    public void init(){
        MazeGameFigure mazeGameFigure=new MazeGameFigure();
        mazeGameFigure.init();
    }

}


