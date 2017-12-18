import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class ResolutionTextBox{
    private int state;  //0-640x360, 1-960x540, 2-1280x720, 3-1366x768, 4-1600x900, 5- 1920x1080
    private int screenWidth[]={640,960,1280,1366,1600,1920};
    private int screenHeigth[]={360,540,720,768,900,1080};
    private float x;
    private float y;

    /**
     * Constructor, sets the state of entry resolution
     */
    ResolutionTextBox(){
        setState(2);    //set the state of entry resolution
    }
    public void setState(int state){
        this.state=state;
    }
    public int getState() {
        return state;
    }
    /**
     * Draws the object
     * @param g Graphics object passed by game state on the runtime
     */
    public void draw(Graphics g){
        calculatePosition(g);
        g.drawString(toString(),x,y);
    }
    /**
     * Sets the predefined position of the object on the screen
     * @param g
     */
    public void calculatePosition(Graphics g){
        x=(Settings.getScreenWidth()-g.getFont().getWidth(toString()))/2;
        y=Settings.getScreenHeight()/9*3;

    }
    /**
     * Returns a string representation of current resolution
     * @return
     */
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
