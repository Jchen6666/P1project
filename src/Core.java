import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.geom.Vector2f;

public class Core extends StateBasedGame{
    public static final String gamename = "Mathness";
    public static final int menu = 0;
    public static final int play = 1;
    public static final int bossfight = 2;
    public static final int optionsScreen = 3;
    public static final int leaderboardsScreen=4;
    public static final int victoryScreen=5;
    public static final int preGame=6;

    public Core(String gamename){
        super(gamename);
        this.addState(new Menu(menu));
        this.addState(new Play(play));
        this.addState(new BossFight(bossfight));
        this.addState(new OptionsScreen(optionsScreen));
        this.addState(new LeaderboardsScreen(leaderboardsScreen));
        this.addState(new VictoryScreen(victoryScreen));
        this.addState(new Pregame(preGame));
    }

    public void initStatesList(GameContainer gc) throws SlickException{ // Give it a list of states
            this.getState(menu).init(gc,this);
            this.getState(play).init(gc,this);
            this.getState(bossfight).init(gc,this);
            this.getState(leaderboardsScreen).init(gc,this);
            this.getState(optionsScreen).init(gc,this);
            this.getState(victoryScreen).init(gc,this);
            this.getState(preGame).init(gc,this);
            this.enterState(menu); // first screen the user sees
    }

    public static void main(String[] args) {
        AppGameContainer appgc;
        try {
            Settings.setScreenWidth(1280);
            Settings.setScreenHeight(720);
            Settings.setFULLSCREEN(false);
            Settings.setMusicVolume(0);
            Settings.setMusicVolumeChanged(0);
            Settings.setEasySpan(new int[][]{{1,20},{1,20},{1,10},{1,10}});
            Settings.setNormalSpan(new int[][]{{1,50},{1,30},{1,15},{1,10}});
            Settings.setHardSpan(new int[][]{{1,100},{1,40},{1,20},{1,15}});
            Settings.setDifficulty(1);
            Settings.setOperationsMode(0);
            Score.resetScore();
            appgc = new AppGameContainer(new Core(gamename));
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