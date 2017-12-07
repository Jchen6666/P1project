import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.Image;


public class OptionsScreen extends BasicGameState {

    Image background;

    public OptionsScreen(int state) {

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background=new Image("/lib/res/img/background.png");
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        background.draw(0,0,Settings.getScreenWidth(),Settings.getScreenHeight());
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();

    }

    public int getID() { return 3; }

}
