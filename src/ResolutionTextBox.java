import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class ResolutionTextBox{
    private int state;  //0-640x360, 1-960x540, 2-1280x720, 3-1366x768, 4-1600x900, 5- 1920x1080
    private int screenWidth[]={640,960,1280,1366,1600,1920};
    private int screenHeigth[]={360,540,720,768,900,1080};
    private float x;
    private float y;
    ResolutionTextBox(){
        setState(2);    //set the state of entry resolution
    }
    public void setState(int state){
        this.state=state;
    }
    public int getState() {
        return state;
    }
    public void draw(Graphics g){
        calculatePosition(g);
        g.drawString(toString(),x,y);
    }
    public void calculatePosition(Graphics g){
        x=(Settings.getScreenWidth()-g.getFont().getWidth(toString()))/2;
        y=Settings.getScreenHeight()/9*3;

    }
    public String toString(){
        return Integer.toString(screenWidth[state])+" x "+Integer.toString(screenHeigth[state]);
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public int[] getScreenWidth() {
        return screenWidth;
    }
    public int[] getScreenHeigth() {
        return screenHeigth;
    }
}
