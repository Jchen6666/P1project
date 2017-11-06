import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.*;

public class GameMain extends StateBasedGame {

    public static final String gameName = "MathGame";
    public static final int startMenu = 0;
    public static final int worldMap = 1;

    public GameMain(String gameName){
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
            agc = new AppGameContainer(new GameMain(gameName));
            agc.setDisplayMode(1920, 1080, false);
            agc.start();


        }
        catch (SlickException e){
            e.printStackTrace();
        }
    }
}
