import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.Image;



public class OptionsScreen extends BasicGameState {

    Image background;
    Image menuMenuBackground;
    ResolutionTextBox resTextBox;
    Button rightResButton;
    Button leftResButton;
    Button backButton;
    Button leftVolumeButton;
    Button rightVolumeButton;
    Button applyButton;
    MusicBar musicBar;
    public OptionsScreen(int state) {

    }
    /**
     * initialize images and objects
     * @param gc gamecontainer object passed from the gamestate at runtime
     * @param sbg StateBasedGame object
     * @throws SlickException A generic exception thrown by everything in the library
     */
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        menuMenuBackground=new Image("lib/res/img/menuMenuBackground.png");
        background=new Image("/lib/res/img/menuBackground.png");
        resTextBox=new ResolutionTextBox();
        rightResButton=new Button(Settings.getScreenWidth()/5*3,Settings.getScreenHeight()/9*3,26,26,new Image("lib/res/img/changeButton.png").getSubImage(32,0,32,32));
        leftResButton=new Button(Settings.getScreenWidth()/5*2,Settings.getScreenHeight()/9*3,26,26,rightResButton.getTexture().getFlippedCopy(true,false));
        musicBar=new MusicBar((Settings.getScreenWidth()-Settings.getScreenWidth()/4)/2,Settings.getScreenHeight()/9*5,Settings.getScreenWidth()/4,Settings.getScreenHeight()/18);
        leftVolumeButton=new Button(musicBar.getX()-musicBar.getHeight(),musicBar.getY(),musicBar.getHeight(),musicBar.getHeight(),rightResButton.getTexture().getFlippedCopy(true,false));
        rightVolumeButton=new Button(musicBar.getX()+musicBar.getWidth(),musicBar.getY(),musicBar.getHeight(),musicBar.getHeight(),rightResButton.getTexture().copy());
        applyButton=new Button(Settings.getScreenWidth()/2-Settings.getScreenWidth()/8,Settings.getScreenHeight()/9*6,Settings.getScreenWidth()/8,Settings.getScreenWidth()/16,new Image("/lib/res/img/menuButtons.png").getSubImage(128,32,64,32));
        backButton=new Button(Settings.getScreenWidth()/2,applyButton.getY(),applyButton.getWidth(),applyButton.getHeight(),new Image("lib/res/img/menuButtons.png").getSubImage(128,0,64,32));
        applyButton.setHoveredTexture(new Image("lib/res/img/hoveredMenuButtons.png").getSubImage(128,32,64,32));
        backButton.setHoveredTexture(new Image("lib/res/img/hoveredMenuButtons.png").getSubImage(128,0,64,32));
    }
    /**
     * draw the background, buttons, resolution text box and music bar
     * @param gc gamecontainer object passed from the gamestate at runtime
     * @param sbg StateBasedGame object
     * @param g  Graphics object from game state at run time
     * @throws SlickException A generic exception thrown by everything in the library
     */
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.black);
        background.draw(0,0,Settings.getScreenWidth(),Settings.getScreenHeight());
        menuMenuBackground.draw((Settings.getScreenWidth()-menuMenuBackground.getWidth())/2,Settings.getScreenHeight()/9,menuMenuBackground.getWidth(),Settings.getScreenHeight()/9*7);
        g.setFont(OurFonts.getFont26B());
        g.drawString("Settings:",(Settings.getScreenWidth()-g.getFont().getWidth("Settings:"))/2,Settings.getScreenHeight()/9*2);
        g.drawString("Music volume:",(Settings.getScreenWidth()-g.getFont().getWidth("Music volume:"))/2,Settings.getScreenHeight()/9*4);
        resTextBox.draw(g);
        leftResButton.draw();
        rightResButton.draw();
        leftVolumeButton.draw();
        rightVolumeButton.draw();
        musicBar.draw();
        if(applyButton.isSelected()){
            applyButton.drawHovered();
        }else {
            applyButton.draw();
        }
        if(backButton.isSelected()){
            backButton.drawHovered();
        }else {
            backButton.draw();
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        input.disableKeyRepeat();

        if(applyButton.isHovered(input)){
            applyButton.setSelected(true);
        }else{
            applyButton.setSelected(false);
        }
        if(backButton.isHovered(input)){
            backButton.setSelected(true);
        }else{
            backButton.setSelected(false);
        }

        if(rightResButton.isClicked(input)){
            if(resTextBox.getState()<5) {
                resTextBox.setState(resTextBox.getState() + 1);
            }
        }
        if(leftResButton.isClicked(input)){
            if(resTextBox.getState()>0){
                resTextBox.setState(resTextBox.getState()-1);
            }
        }
        if(rightVolumeButton.isClicked(input)){
            musicBar.increaseVolume();
        }
        if(leftVolumeButton.isClicked(input)){
            musicBar.decreaseVolume();
        }
        if(applyButton.isClicked(input)){
            Settings.setScreenWidthChanged(resTextBox.getScreenWidth()[resTextBox.getState()]);
            Settings.setScreenHeightChanged(resTextBox.getScreenHeigth()[resTextBox.getState()]);
            Settings.setMusicVolumeChanged(musicBar.getVolume());
        }
        if(backButton.isClicked(input)){
            sbg.enterState(0);
        }
        musicBar.update(input);
        System.out.println(Settings.getMusicVolumeChanged());
    }

    public int getID() { return 3; }

}
