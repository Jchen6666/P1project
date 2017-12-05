import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class Button extends Rectangle{
    private static Image highlight;
    private String text;
    private boolean isSelected;
    private boolean isTheAnswerRight;
    public Button(float x, float y, float width, float height) {
        super(x, y, width, height);
//        setText("");
//        setSelected(false);
//        setTheAnswerRight(false);
    }
    public Button(float x, float y, float width, float height, String text) {
        super(x, y, width, height);
        setText(text);
//        setSelected(false);
//        setTheAnswerRight(false);
    }
    public void drawText(Graphics g){
        g.drawString(text,x+(width/2),y+(height/2));
    }
    public void drawHighlight(){
        if(isSelected){
           highlight.draw(this.getX(),this.getY(),width,height);
        }
    }




    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public boolean isTheAnswerRight() {
        return isTheAnswerRight;
    }
    public void setTheAnswerRight(boolean theAnswerRight) {
        isTheAnswerRight = theAnswerRight;
    }
    public static Image getHighlight() {
        return highlight;
    }
    public static void setHighlight(Image highlight) {
        Button.highlight = highlight;
    }
    public boolean isHovered(int Xpos, int Ypos){
        if(Xpos>=x&&Xpos<=x+width){
            if(Ypos<=Settings.getScreenHeight()-y&&Ypos>=Settings.getScreenHeight()-(y+height))
                return true;
        }
        return false;
    }
    public boolean isClicked(Input input){
        if(isHovered(Mouse.getX(),Mouse.getY())&&input.isMouseButtonDown(0)){
            return true;
        }
        return false;
    }
    public boolean intersects(java.awt.Rectangle r) {
        float tw = this.width;
        float th = this.height;
        int rw = r.width;
        int rh = r.height;
        if (rw > 0 && rh > 0 && tw > 0 && th > 0) {
            float tx = this.x;
            float ty = this.y;
            int rx = r.x;
            int ry = r.y;
            rw += rx;
            rh += ry;
            tw += tx;
            th += ty;
            return (rw < rx || rw > tx) && (rh < ry || rh > ty) && (tw < tx || tw > rx) && (th < ty || th > ry);
        } else {
            return false;
        }
    }
    public static void paintObstacles(Graphics g, Button button){
        Color myColor=new Color(255,2,2,127);
        g.setColor(myColor);
        g.fillRect(button.getX(), button.getY(),button.getWidth(),button.getHeight());
    }
    public static void paintRightAnswer(Graphics g,Button button){
        Color myColor=new Color(119,249,136,127);
        g.setColor(myColor);
        g.fillRect(button.getX(), button.getY(),button.getWidth(),button.getHeight());

    }
}
