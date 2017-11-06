import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.*;


public class Menu extends BasicGameState {
    Image playNow;
    Image extGame;

    public Menu(int startMenu) {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        playNow = new Image("lib/res/img/play.PNG");



    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawString("Math Game", 100,50);
        playNow.draw(100,100);

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        int posX = Mouse.getX();
        int posY = Mouse.getY();

        if ((posX > 100 && posX < 311) && (posY > 209 && posY < 260)) {
            if (Mouse.isButtonDown(0)) {
                sbg.enterState(1);
            }
        }

    }

    public int getID() {
        return 0;
    }
}
