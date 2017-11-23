import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Image;


public class Menu extends BasicGameState {
    Image playNow;
    Image exitGame;
    float xPos,yPos;

    public Menu(int state) {

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        playNow = new Image("lib/res/img/playNow.png");
        exitGame = new Image("lib/res/img/exitGame.png");

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        xPos = Mouse.getX();
        yPos = Mouse.getY();
        g.drawString("Mathness", 100, 50);
        System.out.println(xPos+" "+yPos);
        playNow.draw(100, 100);
        exitGame.draw(100, 200);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {


        Input input = gc.getInput();
        if ((xPos > 106 && xPos < 296) && (yPos > 560 && yPos < 590)) {
            if (input.isMouseButtonDown(0)) { // Code 0 = left click, code 1 = right click etc.
                sbg.enterState(1); // id 1 = Takes you to  a new state/screen
            }

        }
        if  (input.isKeyDown(Input.KEY_B)) {
            sbg.enterState(2);
        }
        if ((xPos > 100 && xPos < 311) && (yPos > 109 && yPos < 160)) {
            if (Mouse.isButtonDown(0)) {
                System.exit(0);

            }
        }
    }

    public int getID() { return 0; }

}
