import org.newdawn.slick.state.StateBasedGame;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame {

    public static final String gameName = "MathGame";
    public static final int startMenu = 0;
    public static final int worldMap = 1;

    public Game(String gameName){
        super(gameName);
        this.addState(new Menu(startMenu));
    }

    public void initStatesList(GameContainer gc) throws SlickException{
        this.getState(startMenu).init(gc, this);
        this.enterState(startMenu);
    }

    public static void main(String[] args) {
        AppGameContainer agc;
        try {
            agc = new AppGameContainer(new Game(gameName));
            agc.setDisplayMode(1920, 1080, false);
            agc.start();


        }
        catch (SlickException e){
            e.printStackTrace();
        }
    }
}
