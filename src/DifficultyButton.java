import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import java.util.ArrayList;

public class DifficultyButton extends Button {
    Image texture;
    int state;
    public DifficultyButton(float x, float y, float width, float height, Image texture) {
        super(x, y, width, height);
        this.texture=texture;
        state=1;
    }

    @Override
    public void draw() {
        texture.getSubImage(state*64,0,64,32).draw(x,y,width,height);
    }
    public void update(){
        if(state+1==3){
            state=0;
        }else{
            state++;
        }
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
