import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.Image;



public class OptionsScreen extends BasicGameState {

    Image background;
    ResolutionTextBox resTextBox;
    Button rightResButton;
    Button leftResButton;
    Button leftVolumeButton;
    Button rightVolumeButton;
    Button applyButton;
    MusicBar musicBar;
    public OptionsScreen(int state) {

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

//        background=new Image("/lib/res/img/background.png");
//        resTextBox=new ResolutionTextBox();
//       rightResButton=new Button(Settings.getScreenWidth()/5*3,Settings.getScreenHeight()/9*2,26,26,new Image("lib/res/img/changeButton.png"));
//       leftResButton=new Button(Settings.getScreenWidth()/5*2,Settings.getScreenHeight()/9*2,26,26,rightResButton.getTexture().getFlippedCopy(true,false));
//        musicBar=new MusicBar(Settings.getScreenWidth()/2-200,Settings.getScreenHeight()/9*4,400,Settings.getScreenHeight()/18);
//        leftVolumeButton=new Button(Settings.getScreenWidth()/2-200-Settings.getScreenHeight()/18,Settings.getScreenHeight()/9*4,Settings.getScreenHeight()/18,Settings.getScreenHeight()/18,rightResButton.getTexture().getFlippedCopy(true,false));
//        rightVolumeButton=new Button(Settings.getScreenWidth()/2+200+Settings.getScreenHeight()/18,Settings.getScreenHeight()/9*4,Settings.getScreenHeight()/18,Settings.getScreenHeight()/18,rightResButton.getTexture().copy());
//        applyButton=new Button(Settings.getScreenWidth()/2-200,Settings.getScreenHeight()/9*5,400,Settings.getScreenHeight()/18,new Image("/lib/res/img/bossFightTable.png"),"Apply the changes.");
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.black);
        background.draw(0,0,Settings.getScreenWidth(),Settings.getScreenHeight());
        g.setFont(OurFonts.getFont26B());
        g.drawString("Settings:",(Settings.getScreenWidth()-g.getFont().getWidth("Settings:"))/2,Settings.getScreenHeight()/9);
        g.drawString("Music volume:",(Settings.getScreenWidth()-g.getFont().getWidth("Music volume:"))/2,Settings.getScreenHeight()/9*3);
        resTextBox.draw(g);
        leftResButton.draw();
        rightResButton.draw();
        leftVolumeButton.draw();
        rightVolumeButton.draw();
        musicBar.draw();
        applyButton.draw();
        applyButton.drawText(g);

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        input.disableKeyRepeat();

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
        musicBar.update(input);
        System.out.println(Settings.getMusicVolumeChanged());
    }

    public int getID() { return 3; }

}
