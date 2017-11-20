import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;

import java.awt.*;
import java.util.ArrayList;


public class Play extends BasicGameState {
  Image map;
  Animation hero,movingUp,movingDown,movingLeft,movingRight;
  ArrayList<Rectangle>obstacles;
  boolean quit=false;
  int[] duration={200,200};
  float heroPositionX=0;
  float heroPositionY=0;
  float shitX=heroPositionX+320;
  float shitY=heroPositionY+160;
    Rectangle obstacle;
  public Play(int state) {
      obstacle=new Rectangle();
      obstacles=new ArrayList<Rectangle>();
      addObstacles();

    }
    public void loadMap(String ref)throws SlickException{


    }

    public void addObstacles(){
      int width=100;
      int height=200;
      obstacles.add(new Rectangle(400,350,width,height));
    }

    public void paintObstacles(Graphics g,Rectangle obstacle){
        g.setColor(Color.darkGray);
        g.fillRect(obstacle.x,obstacle.y,obstacle.width,obstacle.height);

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
      obstacle=obstacles.get(0);
        g.setColor(Color.darkGray);
        g.fillRect(obstacle.x,obstacle.y,obstacle.width,obstacle.height);

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
