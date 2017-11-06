import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Core extends StateBasedGame{
    public static final String gamename = "Mathness";
    public static final int menu = 0;
    public static final int play = 1;

    public Core(String gamename){
        super(gamename);
        this.addState(new Menu(menu));
        this.addState(new Play(play));
    }

    public void initStatesList(GameContainer gc) throws SlickException{ // Give it a list of states
            this.getState(menu).init(gc,this);
            this.getState(play).init(gc,this);
            this.enterState(menu); // first screen the user sees
    }

    public static void main(String[] args) {
        AppGameContainer appgc;
        try {
            appgc = new AppGameContainer(new Core(gamename));
            appgc.setDisplayMode(640,360,false);
            appgc.start();
        }catch (SlickException e){
            e.printStackTrace();
        }

    }


   /* private Image img;

    public Core(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        img = new Image("lib/res/img/play.png");
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

    */

}