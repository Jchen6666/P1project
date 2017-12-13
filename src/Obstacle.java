import java.awt.*;
import org.newdawn.slick.geom.Rectangle;

public class Obstacle extends Rectangle {
    float x,y;
    static float width,height;
public Obstacle(){
super(0,0,0,0);

}

    public Obstacle(float x, float y, float width,  float height)
    {
        super(x,y,width,height);
        this.width=width;
        this.height=height;


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


//    public boolean intersects(java.awt.Rectangle r) {
//        float tw = this.width;
//        float th = this.height;
//        float rw = r.width;
//        float rh = r.height;
//        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
//            return false;
//        }
//        float tx = this.x;
//        float ty = this.y;
//        float rx = r.x;
//        float ry = r.y;
//        rw += rx;
//        rh += ry;
//        tw += tx;
//        th += ty;
//        //      overflow || intersect
//        return ((rw < rx || rw > tx) &&
//                (rh < ry || rh > ty) &&
//                (tw < tx || tw > rx) &&
//                (th < ty || th > ry));
//    }



}
