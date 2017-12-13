import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.Image;



public class VictoryScreen extends BasicGameState {
    Image menuMenuBackground;
    Image background;


    public VictoryScreen(int state) {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        menuMenuBackground=new Image("lib/res/img/menuMenuBackground.png");
        background=new Image("/lib/res/img/menuBackground.png");
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        background.draw(0,0,Settings.getScreenWidth(),Settings.getScreenHeight());
        menuMenuBackground.draw((Settings.getScreenWidth()-menuMenuBackground.getWidth())/2,Settings.getScreenHeight()/9,menuMenuBackground.getWidth(),Settings.getScreenHeight()/9*7);
        g.setFont(OurFonts.getFont26B());
        g.drawString("Victory!",(Settings.getScreenWidth()-g.getFont().getWidth("Settings:"))/2,Settings.getScreenHeight()/9*2);
        g.drawString("Music volume:",(Settings.getScreenWidth()-g.getFont().getWidth("Music volume:"))/2,Settings.getScreenHeight()/9*4);

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        input.disableKeyRepeat();

        if(input.isKeyPressed(Input.KEY_ESCAPE)){
            sbg.enterState(0);
        }

    }
    public int getID() { return 5; }

}
