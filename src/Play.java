import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;


public class Play extends BasicGameState {
  Image map;
  Animation hero,movingUp,movingDown,movingLeft,movingRight;
  boolean quit=false;
  int[] duration={200,200};
  float heroPositionX=0;
  float heroPositionY=0;
  float shitX=heroPositionX+320;
  float shitY=heroPositionY+160;
  public Play(int state) {

    }
    public void loadMap(String ref)throws SlickException{


    }
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        map=new Image("lib/res/img/test1.png");
       Image[] walkUp={new Image(("lib/res/img/mrio2.png")),new Image("lib/res/img/mrio2.png")};
       Image[] walkDown={new Image(("lib/res/img/mrio2.png")),new Image("lib/res/img/mrio2.png")};
       Image[] walkLeft={new Image(("lib/res/img/mrio2.png")),new Image("lib/res/img/mrio2.png")};
       Image[] walkRight={new Image(("lib/res/img/mrio2.png")),new Image("lib/res/img/mrio2.png")};

       movingUp=new Animation(walkUp,duration,false);
       movingDown=new Animation(walkDown,duration,false);
       movingLeft=new Animation(walkLeft,duration,false);
       movingRight=new Animation(walkRight,duration,false);
       hero=movingDown;
    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
       map.draw(heroPositionX,heroPositionY);
       hero.draw(shitX,shitY);
       g.drawString("Hero X: "+heroPositionX+"\nHero y: "+heroPositionY,600,600);
      if (quit==true){
        g.drawString("Resume(R)",250,200 );
        g.drawString("Main Menu(M)",250,250 );
        g.drawString("Quit Game(Q)",250,300);
        if (quit==false){
          g.clear();
        }
      }

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
      Input input = gc.getInput();

      if(input.isKeyDown(Input.KEY_UP)){
        hero = movingUp;
        heroPositionY += delta * .1f;
      }
      if(input.isKeyDown(Input.KEY_DOWN)){
        hero = movingDown;
        heroPositionY -= delta * .1f;
      }
      if(input.isKeyDown(Input.KEY_LEFT)){
        hero = movingLeft;
        heroPositionX += delta * .1f;
      }
      if(input.isKeyDown(Input.KEY_RIGHT)){
        hero = movingRight;
        heroPositionX -= delta * .1f;
      }
    }

    public int getID() {
        return 1;
    }

}
