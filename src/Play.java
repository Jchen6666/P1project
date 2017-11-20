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
  Rectangle square;
  ArrayList<Rectangle>obstacles;
  boolean quit=false;
  int[] duration={200,200};
  float heroPositionX=0;
  float heroPositionY=0;
  float shitX=heroPositionX;
  float shitY=heroPositionY+250;
  static int x,y;
  boolean collides=false;
  Rectangle obstacle;

    public Play(int state) {
        obstacle=new Rectangle();
        obstacles=new ArrayList<Rectangle>();
        addObstacles();
        square=new Rectangle((int)shitX,(int)shitY,50,50);
  }

    public void addObstacles(){
      int width=50;
      int height=100;

      obstacles.add(new Rectangle((int)heroPositionX+500,(int)heroPositionY+100,width,height));
      obstacles.add(new Rectangle((int)heroPositionX+500,(int)heroPositionY+400,width,height));

    }
   public void paintSquare(Graphics g,Rectangle square){

        Color myColor=new Color(255,2,2,127);
        g.setColor(myColor);
        g.fillRect(square.x,square.y,square.width,square.height);
   }
    public void paintObstacles(Graphics g, Rectangle obstacle){

        g.setColor(Color.darkGray);
        g.fillRect(obstacle.x, obstacle.y,obstacle.width,obstacle.height);
       // g.fillRect(heroPositionX+300,heroPositionY,width,height);
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
        paintSquare(g,square);
       g.drawString("Hero X: "+heroPositionX+"\nHero y: "+heroPositionY +"\nCollides: ",600,600);
      if (quit==true){
        g.drawString("Resume(R)",250,200 );
        g.drawString("Main Menu(M)",250,250 );
        g.drawString("Quit Game(Q)",250,300);
        if (quit==false){
          g.clear();
        }
      }
            for (int i=0;i<obstacles.size();i++) {
                paintObstacles(g,obstacles.get(i));
            }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
      Input input = gc.getInput();
       for (int i=0;i<obstacles.size();i++) {

           obstacle=obstacles.get(i);
           y=obstacle.y-(int)heroPositionY;
           x=obstacle.x-(int)heroPositionX;

           if (square.intersects(obstacle)){
               collides=true;
           }
           else {
               collides=false;
           }
           if (input.isKeyDown(Input.KEY_UP)) {
                obstacle.y += 1;
                if (collides){
                    heroPositionY-=10;
                    for (int z=0;z<obstacles.size();z++) {
                        obstacles.get(z).y-=10;

                    }
                }
           }
           if (input.isKeyDown(Input.KEY_DOWN)) {
                obstacle.y -= 1;
               if (collides){
                   heroPositionY+=10;
                   for (int z=0;z<obstacles.size();z++) {
                       obstacles.get(z).y+=10;

                   }

               }
           }
           if (input.isKeyDown(Input.KEY_LEFT)) {
               obstacle.x+=1;
               if (collides){
                   heroPositionX-=10;
                   for (int z=0;z<obstacles.size();z++) {
                       obstacles.get(z).x-=10;

                   }

               }
           }
           if (input.isKeyDown(Input.KEY_RIGHT)) {
                obstacle.x -= 1;
               if (collides){
                   heroPositionX+=10;
                   for (int z=0;z<obstacles.size();z++) {
                       obstacles.get(z).x+=10;

                   }
               }
           }
       }


         if (input.isKeyDown(Input.KEY_UP)) {
               hero = movingUp;
               heroPositionY +=1;
           }
           if (input.isKeyDown(Input.KEY_DOWN)) {
               hero = movingDown;
               heroPositionY -= 1;
           }
           if (input.isKeyDown(Input.KEY_LEFT)) {
               hero = movingLeft;
               heroPositionX += 1;
           }
           if (input.isKeyDown(Input.KEY_RIGHT)) {
               hero = movingRight;
               heroPositionX -= 1;
           }

        }



    public int getID() {
        return 1;
    }

}
