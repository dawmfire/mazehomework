package ����2;

import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author DELL
 * �Թ���Ϸ
 * ���ౣ���Թ���ÿһ�����ӵ���Ϣ��
 *
 */

public class Place {
    //���嵱ǰ�����Ƿ���ߣ���wallΪ0�����ʾ���ߣ���wallΪ1�����ʾ�����ߡ�
    private int wall;
    //��ʾ��ǰ�����Ƿ���������
    private boolean search=false;
    //��ʾ��ǰ���ӵ��ĸ�����ֱ�����Щ����,����ʱ����һ�����ӡ�
    private Place east=null,south=null,west=null,north=null,last=null;
    //�����Թ�����λ��
    private int index=0;
    public Place(int wall){
        this.wall=wall;
    }
    public int getWall() {
        return wall;
    }
    public void setWall(int wall) {
        this.wall = wall;
    }
    public boolean isSearch() {
        return search;
    }
    public void setSearch(boolean search) {
        this.search = search;
    }
    public Place getEast() {
        return east;
    }
    public void setEast(Place east) {
        this.east = east;
    }
    public Place getSouth() {
        return south;
    }
    public void setSouth(Place south) {
        this.south = south;
    }
    public Place getWest() {
        return west;
    }
    public void setWest(Place west) {
        this.west = west;
    }
    public Place getNorth() {
        return north;
    }
    public void setNorth(Place north) {
        this.north = north;
    }
    public Place getLast() {
        return last;
    }
    public void setLast(Place last) {
        this.last = last;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

}

