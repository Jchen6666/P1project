public class Obstacle {
    float x,y;
    static int width,height;

    public Obstacle(){

    }
    public Obstacle(float x, float y, int width,  int height)
    {
    this.x=x;
    this.y=y;
    this.width=50;
    this.height=100;
    }
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return 50;
    }



    public int getHeight() {
        return 100;
    }






}
