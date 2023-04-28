package Р§зг1;

class Gezi {
    private int x;
    private int y;
    private boolean filled;
    private boolean searched;
    private boolean colored;
    private boolean go;
    private boolean start;
    private boolean end;

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public boolean isGo() {
        return go;
    }

    public void setGo(boolean go) {
        this.go = go;
    }

    public boolean isColored() {
        return colored;
    }

    public void setColored(boolean colored) {
        this.colored = colored;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public boolean isSearched() {
        return searched;
    }

    public void setSearched(boolean searched) {
        this.searched = searched;
    }

    public Gezi() {

    }

    public Gezi(int x, int y, boolean filled, boolean searched) {
        this.filled = filled;
        this.x = x;
        this.y = y;
        this.searched = searched;
    }
}
