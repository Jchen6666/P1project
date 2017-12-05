import java.awt.*;
import org.newdawn.slick.geom.Rectangle;

public class Obstacle extends Rectangle {
    float x,y;
    static int width,height;


    public Obstacle(float x, float y, float width,  float height)
    {
        super(x,y,width,height);
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

    public float getWidth() {
        return 50;
    }



    public float getHeight() {
        return 100;
    }






}
