import org.newdawn.slick.*;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Core extends BasicGame{
    private Image img;

    public Core(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        img = new Image("lib/res/img/sosig.png");
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
           img.draw();
    }

    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Core("Mathness 2D"));
            appgc.setDisplayMode(1280,720,false);
            appgc.start();
        }catch (SlickException ex){
            Logger.getLogger(Core.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}
