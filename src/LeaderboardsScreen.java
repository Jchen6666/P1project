import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.Image;


/**
 * This class is used to show the leaderboards screen
 */
public class LeaderboardsScreen extends BasicGameState {
    Image menuMenuBackground;
    Image background;
    Button backButton;
    Button messageButton;
    String message;
    int messageWidth;
    public LeaderboardsScreen(int state) {

    }

    /**
     * initialize images and objects
     * @param gc gamecontainer object passed from the gamestate at runtime
     * @param sbg StateBasedGame object
     * @throws SlickException A generic exception thrown by everything in the library
     */
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        menuMenuBackground=new Image("lib/res/img/menuMenuBackground.png");
        message="This part of the game is still in development";
        messageWidth=OurFonts.getFont18().getWidth(message);
        backButton=new Button((Settings.getScreenWidth()-Settings.getScreenWidth()/8)/2,Settings.getScreenHeight()/9*6,Settings.getScreenWidth()/8,Settings.getScreenWidth()/16,new Image("/lib/res/img/menuButtons.png").getSubImage(128,0,64,32));
        backButton.setHoveredTexture(new Image("lib/res/img/hoveredMenuButtons.png").getSubImage(128,0,64,32));
        background=new Image("/lib/res/img/menuBackground.png");
        messageButton=new Button((Settings.getScreenWidth()-(messageWidth*(float)1.5))/2,Settings.getScreenHeight()/9*2,messageWidth*(float)1.5,Settings.getScreenWidth()/16,new Image("lib/res/img/dialogCloud.png"),message);
    }
    /**
     * draw the background, buttons and the statistics
     * @param gc gamecontainer object passed from the gamestate at runtime
     * @param sbg StateBasedGame object
     * @param g  Graphics object from game state at run time
     * @throws SlickException A generic exception thrown by everything in the library
     */
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.black);
        g.setFont(OurFonts.getFont18());
        background.draw(0,0,Settings.getScreenWidth(),Settings.getScreenHeight());
        menuMenuBackground.draw((Settings.getScreenWidth()-menuMenuBackground.getWidth())/2,Settings.getScreenHeight()/9,menuMenuBackground.getWidth(),Settings.getScreenHeight()/9*7);
        //messageButton.draw();
        messageButton.drawText(g);
        if(backButton.isSelected()){
            backButton.drawHovered();
        }else{
            backButton.draw();
        }
    }
    /**
     * handles user's interaction with buttons
     * @param gc gamecontainer object passed from the gamestate at runtime
     * @param sbg StateBasedGame object
     * @param delta amount of time that passed from a last call of game state's method update(in milliseconds)
     */
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        input.disableKeyRepeat();
        if(backButton.isHovered(input)){
            backButton.setSelected(true);
        }else{
            backButton.setSelected(false);
        }
        if(backButton.isClicked(input)){
            sbg.enterState(0);
        }
    }
    public int getID() { return 4; }

}
