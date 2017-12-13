import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.Image;



public class VictoryScreen extends BasicGameState {
    Image menuMenuBackground;
    Image background;
    Button backButton;

    public VictoryScreen(int state) {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        menuMenuBackground=new Image("lib/res/img/menuMenuBackground.png");
        background=new Image("/lib/res/img/menuBackground.png");
        backButton=new Button((Settings.getScreenWidth()-Settings.getScreenWidth()/8)/2,Settings.getScreenHeight()/9*6,Settings.getScreenWidth()/8,Settings.getScreenWidth()/16,new Image("lib/res/img/menuButtons.png").getSubImage(128,0,64,32));
        backButton.setHoveredTexture(new Image("lib/res/img/hoveredMenuButtons.png").getSubImage(128,0,64,32));
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        background.draw(0,0,Settings.getScreenWidth(),Settings.getScreenHeight());
        menuMenuBackground.draw((Settings.getScreenWidth()-menuMenuBackground.getWidth())/2,Settings.getScreenHeight()/9,menuMenuBackground.getWidth(),Settings.getScreenHeight()/9*7);
        g.setFont(OurFonts.getFont26B());
        g.setColor(Color.black);
        g.drawString("Victory!",(Settings.getScreenWidth()-g.getFont().getWidth("Settings:"))/2,Settings.getScreenHeight()/9*2);
        g.setFont(OurFonts.getFont22B());
        g.drawString("Your score:",(Settings.getScreenWidth()/2-g.getFont().getWidth("Your score")),Settings.getScreenHeight()/9*3);
        g.drawString("Wrong answers:",(Settings.getScreenWidth()/2-g.getFont().getWidth("Wrong answers:")),Settings.getScreenHeight()/9*4);
        g.drawString("Time:",(Settings.getScreenWidth()/2-g.getFont().getWidth("Time:")),Settings.getScreenHeight()/9*5);
        g.setFont(OurFonts.getFont18());

        g.drawString(Integer.toString(Score.score),(Settings.getScreenWidth()/2)+20,Settings.getScreenHeight()/9*3+5);
        g.drawString(Integer.toString(Score.wrongAnswers),(Settings.getScreenWidth()/2)+20,Settings.getScreenHeight()/9*4+5);
        g.drawString(Score.timeToString(),(Settings.getScreenWidth()/2)+20,Settings.getScreenHeight()/9*5+5);
        if(backButton.isSelected()){
            backButton.drawHovered();
        }else{
            backButton.draw();
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        input.disableKeyRepeat();

        if(input.isKeyPressed(Input.KEY_ESCAPE)){
            sbg.enterState(0);
        }
        if(backButton.isClicked(input)){
            sbg.enterState(0);
        }
        if(backButton.isHovered(input)){
            backButton.setSelected(true);
        }else{
            backButton.setSelected(false);
        }

    }
    public int getID() { return 5; }

}
