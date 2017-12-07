import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.Color;


public class Button extends Rectangle{
    private static Image highlight;
    private Image texture;
    private String text;
    private boolean isSelected;
    private boolean isTheAnswerRight;
    public Button(float x, float y, float width, float height) {
        super(x, y, width, height);
        setText("");
        setSelected(false);
        setTheAnswerRight(false);
    }
    public Button(float x, float y, float width, float height, String text) {
        super(x, y, width, height);
        setText(text);
        setSelected(false);
        setTheAnswerRight(false);
    }
    public Button(float x, float y, float width, float height,Image texture){
        super(x,y,width,height);
        setTexture(texture);
    }

    public void drawText(Graphics g){
        g.drawString(text,x+(width/2),y+(height/2));

    }
    public void drawHighlight(){
        if(isSelected){
           highlight.draw(this.getX(),this.getY(),width,height);
        }
    }
    public void draw(){
        texture.draw(x,y,width,height);
    }
    public Image getTexture() {
        return texture;
    }
    public void setTexture(Image texture) {
        this.texture = texture;
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
    public boolean isHovered(Input input){
        if(input.getMouseX()>=x&&input.getMouseX()<=x+width){
            if(input.getMouseY()>=y&&input.getMouseY()<=y+height)
                return true;
        }
        return false;
    }
    public boolean isClicked(Input input){
        if(isHovered(input)&&input.isMouseButtonDown(0)){
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
        Color myColor=new Color(255,2,2,80);
        g.setColor(myColor);
        g.fillRect(button.getX(), button.getY(),button.getWidth(),button.getHeight());
    }
    public static void paintRightAnswer(Graphics g,Button button){
        Color myColor=new Color(119,249,136,127);
        g.setColor(myColor);
        g.fillRect(button.getX(), button.getY(),button.getWidth(),button.getHeight());

    }
}
