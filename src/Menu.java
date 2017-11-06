import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;


public class Menu extends BasicGameState {
    Image character;
    int characterX = 200;
    int characterY = 200;


    public Menu(int state) {

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        character = new Image("lib/res/img/mrio.png");
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(character, characterX, characterY);

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        if (input.isKeyDown(Input.KEY_UP)) {
            characterY -= 1;
        }
        if (input.isKeyDown(Input.KEY_DOWN)) {
            characterY += 1;
        }
        if (input.isKeyDown(Input.KEY_LEFT)) {
            characterX -= 1;
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            characterX += 1;
        }
    }

    public int getID() {
        return 0;
    }

}
