package product;

public class position {
    private int x,y,d;
    position(int x,int y){
        this.x = x;
        this.y = y;
    }
    position(int x,int y,int d){
        this.x = x;     //x轴
        this.y = y;     //y轴
        this.d = d; // 方向
    }
    public void setD(int d) {
        this.d = d;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getD() {
        return d;
    }

}
