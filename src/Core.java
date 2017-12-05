import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.geom.Vector2f;

public class Core extends StateBasedGame{
    public static final String gamename = "Mathness";
    public static final int menu = 0;
    public static final int play = 1;
    public static final int bossfight = 2;

    public Core(String gamename){
        super(gamename);
        this.addState(new Menu(menu));
        this.addState(new Play(play));
        this.addState(new BossFight(bossfight));
    }

    public void initStatesList(GameContainer gc) throws SlickException{ // Give it a list of states
            this.getState(menu).init(gc,this); //Initializes the menu state
            this.getState(play).init(gc,this); //Initializes the play state
            this.getState(bossfight).init(gc,this); //Initializes the bossfight state
            this.enterState(menu); // first screen the user sees
    }

    public static void main(String[] args) {
        AppGameContainer appgc;
        try {
            appgc = new AppGameContainer(new Core(gamename));
            Settings.setScreenWidth(800);
            Settings.setScreenHeight(700);
            Settings.setFULLSCREEN(false);
            appgc.setDisplayMode((int)Settings.getScreenWidth(),(int)Settings.getScreenHeight(),Settings.isFULLSCREEN());
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