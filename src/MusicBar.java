import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
/**
 * This class implements music bar
 */
public class MusicBar extends Rectangle {
    private float volume;
    private static Image table;
    private static Image filling;

    /**
     * Constructor initializes a music bar object with given position and predefined textures
     * @param x the x coordinate
     * @param y the y coordinate
     * @param width music bar's width
     * @param heigth music bar's height
     */
    public MusicBar(float x, float y, float width, float heigth){
        super(x,y,width,heigth);
        try {
            table = new Image("lib/res/img/highlight.png");
            filling = new Image ("lib/res/img/bossFightTable.png");
        }catch(SlickException e){
            System.out.println("Couldn't load the volume bar texture.");
        }
        setVolume(Settings.getMusicVolume());
    }

    /**
     * Draws the music bar
     */
    public void draw(){
        filling.draw(x,y,width*volume,height);
        table.draw(x,y,width,height);
    }

    /**
     * Changes volume variable depending on which part of the music bar user clicks on
     * @param input an Input object stating the coordinates of user's input (mouse click)
     */
    public void update(Input input){
        if(input.getMouseX()>x&&input.getMouseX()<x+width){
            if(input.getMouseY()>y&&input.getMouseY()<y+height){
                if(input.isMouseButtonDown(0)){
                    if((input.getMouseX()-x)/width<0){
                        setVolume(0);
                    }else   if(((input.getMouseX()-x)/width)>1){
                        setVolume(1);
                    }else {
                        setVolume((input.getMouseX() - x) / width);
                    }
                }
            }
        }
    }

    /**
     * Increases the volume by 0.1 ( maximum value is 1.0)
     */
    public void increaseVolume(){
        if((volume+0.1)>1){
            volume=1;
        }else {
            volume += 0.1;
        }
    }

    /**
     * Decreases the volume by 0.1 (minimum value is 0.0)
     */
    public void decreaseVolume(){
        if((volume-0.1)<0){
            volume=0;
        }else {
            volume -= 0.1;
        }
    }
    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        if(volume<0){
            this.volume=0;
        }else{
            if(volume>1){
                this.volume=1;
            }else{
                this.volume=volume;
            }
        }
    }

    public static Image getTable() {
        return table;
    }

    public static void setTable(Image table) {
        MusicBar.table = table;
    }

    public static Image getFilling() {
        return filling;
    }

    public static void setFilling(Image filling) {
        MusicBar.filling = filling;
    }
}
