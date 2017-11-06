import org.newdawn.slick.*;
import org.newdawn.slick.state.*;




public class Menu extends BasicGameState {

    public Menu(int state){

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)  throws SlickException{

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{

    }

    public int getID(){
        return 0;
    }







    /*
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

    */
}
