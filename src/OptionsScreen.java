import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.Image;

import java.util.ArrayList;


public class OptionsScreen extends BasicGameState {

    Image background;
    ResolutionTextBox resTextBox;
    Button rightResButton;

    public OptionsScreen(int state) {

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background=new Image("/lib/res/img/background.png");
        resTextBox=new ResolutionTextBox(128,128,128,128);
        rightResButton=new Button(160,128,32,32,new Image("lib/res/img/changeButton.png"));
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.black);
        background.draw(0,0,Settings.getScreenWidth(),Settings.getScreenHeight());
        resTextBox.draw(g);
        rightResButton.draw();
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        if(rightResButton.isClicked(input)){
            if(resTextBox.getState()<5) {
                resTextBox.setState(resTextBox.getState() + 1);
            }
        }
    }

    public int getID() { return 3; }

}
