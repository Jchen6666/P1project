import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class ResolutionTextBox extends Rectangle {
    private int state;  //0-640x360, 1-960x540, 2-1280x720, 3-1366x768, 4-1600x900, 5- 1920x1080
    private int screenWidth[]={640,960,1280,1366,1600,1920};
    private int screenHeigth[]={360,540,720,768,900,1080};
    ResolutionTextBox(float x, float y, float width, float height){
        super(x,y,width,height);
        setState(2);
    }
    public void setState(int state){
        this.state=state;
        Settings.setScreenWidth(screenWidth[state]);
        Settings.setScreenHeight(screenHeigth[state]);
    }
    public int getState() {
        return state;
    }
    public void draw(Graphics g){
        g.drawString(Integer.toString(screenWidth[state])+" x "+Integer.toString(screenHeigth[state]),x+width/4,y+height/4);
    }





}
