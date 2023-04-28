package ����2;

import java.awt.Color;

/**
 *
 * @author DELL
 * �Թ���Ϸ
 * �����ж��Թ�����·������������ϸ��Թ��������Ϣ(�ϸ��Թ�ֻ��1��·��)��
 *
 */
public class Path {
    //���ô����Թ���
    CreateMaze newMaze;
    //�����Թ�·��
    boolean[] path;
    //����ϸ��Թ�
    Place[] maze=null;
    int entrance;
    int exit;
    private int searchPathNumber(){
        maze=newMaze.getMaze();
        int pathAll=0;
        //���浱ǰ·��
        Place [][] path=new Place [maze.length][];
        for(int i=1;i<path.length;i++){
            path [i] = new Place [5];
        }
        //��ǰ·�������±�
        int pathTop=0;
        //��ǰλ�õ���һλ�õĿ������±�
        int [] top=new int [maze.length];
        for(int i=1;i<top.length;i++){
            top[i]=-1;
        }
        //Ѱ���Թ�·����
        if(maze[entrance].getWall()==0){
            pathTop++;
            top[pathTop]++;
            path[pathTop][top[pathTop]]=maze[entrance];
            while(pathTop>0){
                //�жϵ�ǰλ���Ƿ�Ϊ����λ�ã��ǣ������Թ�·�����˻���һλ�ã���Ѱ����һ���ظ�λ��
                if(path[pathTop][0]==maze[exit]){
                    pathAll++;
                    top[pathTop]--;
                    pathTop--;
                }else if(!path[pathTop][top[0]].isSearch()){//Ѱ�ҵ�ǰλ�õ���һλ�õĿ�����
                    if(path[pathTop][0].getEast()!=null&&path[pathTop][0].getEast()!=path[pathTop][0].getLast()&&!path[pathTop][0].getEast().isSearch()){
                        path[pathTop][++top[pathTop]]=path[pathTop][0].getEast();
                    }
                    if(path[pathTop][0].getSouth()!=null&&path[pathTop][0].getSouth()!=path[pathTop][0].getLast()&&!path[pathTop][0].getSouth().isSearch()){
                        path[pathTop][++top[pathTop]]=path[pathTop][0].getSouth();
                    }
                    if(path[pathTop][0].getWest()!=null&&path[pathTop][0].getWest()!=path[pathTop][0].getLast()&&!path[pathTop][0].getWest().isSearch()){
                        path[pathTop][++top[pathTop]]=path[pathTop][0].getWest();
                    }
                    if(path[pathTop][0].getNorth()!=null&&path[pathTop][0].getNorth()!=path[pathTop][0].getLast()&&!path[pathTop][0].getNorth().isSearch()){
                        path[pathTop][++top[pathTop]]=path[pathTop][0].getNorth();
                    }
                    path[pathTop][0].setSearch(true);
                }//��ǰλ�õ���һλ�õ����п������β�ѯ������һλ������˵���һλ��
                if(top[pathTop]==0){
                    path[pathTop][0].setLast(null);
                    path[pathTop][0].setSearch(false);
                    top[pathTop]--;
                    pathTop--;
                }else{
                    pathTop++;
                    top[pathTop]++;
                    path[pathTop][0]=path[pathTop-1][top[pathTop-1]--];
                    path[pathTop][0].setLast(path[pathTop-1][0]);
                }
            }
        }
        return pathAll;
    }

    private void setPath(){
        //���浱ǰ·��
        Place [][] path=new Place [maze.length][];
        for(int i=1;i<path.length;i++){
            path [i] = new Place [5];
        }
        //��ǰ·�������±�
        int pathTop=0;
        //��ǰλ�õ���һλ�õĿ������±�
        int [] top=new int [maze.length];
        for(int i=1;i<top.length;i++){
            top[i]=-1;
        }
        //Ѱ���Թ�·����
        if(maze[entrance].getWall()==0){
            pathTop++;
            top[pathTop]++;
            path[pathTop][top[pathTop]]=maze[entrance];
            while(pathTop>0){
                //�жϵ�ǰλ���Ƿ�Ϊ����λ�ã��ǣ������Թ�·�����˻���һλ�ã���Ѱ����һ���ظ�λ��
                if(path[pathTop][0]==maze[exit]){
                    for(int i=1;i<=pathTop;i++){
                        this.path[path[i][0].getIndex()]=true;
                    }
                    top[pathTop]--;
                    pathTop--;
                    break;
                }else if(!path[pathTop][top[0]].isSearch()){//Ѱ�ҵ�ǰλ�õ���һλ�õĿ�����
                    if(path[pathTop][0].getEast()!=null&&path[pathTop][0].getEast()!=path[pathTop][0].getLast()&&!path[pathTop][0].getEast().isSearch()){
                        path[pathTop][++top[pathTop]]=path[pathTop][0].getEast();
                    }
                    if(path[pathTop][0].getSouth()!=null&&path[pathTop][0].getSouth()!=path[pathTop][0].getLast()&&!path[pathTop][0].getSouth().isSearch()){
                        path[pathTop][++top[pathTop]]=path[pathTop][0].getSouth();
                    }
                    if(path[pathTop][0].getWest()!=null&&path[pathTop][0].getWest()!=path[pathTop][0].getLast()&&!path[pathTop][0].getWest().isSearch()){
                        path[pathTop][++top[pathTop]]=path[pathTop][0].getWest();
                    }
                    if(path[pathTop][0].getNorth()!=null&&path[pathTop][0].getNorth()!=path[pathTop][0].getLast()&&!path[pathTop][0].getNorth().isSearch()){
                        path[pathTop][++top[pathTop]]=path[pathTop][0].getNorth();
                    }
                    path[pathTop][0].setSearch(true);
                }//��ǰλ�õ���һλ�õ����п������β�ѯ������һλ������˵���һλ��
                if(top[pathTop]==0){
                    path[pathTop][0].setLast(null);
                    path[pathTop][0].setSearch(false);
                    top[pathTop]--;
                    pathTop--;
                }else{
                    pathTop++;
                    top[pathTop]++;
                    path[pathTop][0]=path[pathTop-1][top[pathTop-1]--];
                    path[pathTop][0].setLast(path[pathTop-1][0]);
                }
            }
        }
    }

    private void searchPath(){
        while(true){
            if(searchPathNumber()==1){
                setPath();
                break;
            }
        }
    }

    public Path(){
        newMaze=new CreateMaze();
        path=new boolean [newMaze.getSize()*newMaze.getSize()+1];
        this.entrance=newMaze.getEntrance();
        this.exit=newMaze.getExit();
    }

    public Path(int size,int entrance,int exit){
        newMaze=new CreateMaze(size,entrance,exit);
        path=new boolean [newMaze.getSize()*newMaze.getSize()+1];
        this.entrance=newMaze.getEntrance();
        this.exit=newMaze.getExit();
    }

    public Place[] getMaze() {
        searchPath();
        return maze;
    }

    public int getSize(){
        return newMaze.getSize();
    }

    public int getEntrance() {
        return entrance;
    }

    public int getExit() {
        return exit;
    }

    public boolean[] getPath() {
        return path;
    }

    public CreateMaze getNewMaze() {
        return newMaze;
    }

}

