import org.lwjgl.input.Mouse;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import javax.swing.plaf.nimbus.State;
import java.awt.*;


public class Menu extends BasicGameState{

    public Menu(int startMenu) {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawRect(800,300,300,100);
        g.drawString("Play",800,300);
        g.drawString("Statistics",800,400);
        g.drawString("Settings",800,500);
        g.drawString("Credit",800,600);
        g.drawString("Quit",800,700);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        int posX = Mouse.getX();
        int posY = Mouse.getY();

        System.out.println(posX + "," + posY);
    }

    public int getID() {
        return 0;
    }
}
