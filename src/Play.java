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
  ArrayList<Rectangle>movingObstacles;
  boolean quit=false;
  int[] duration={200,200};
  float heroPositionX=0;
  float heroPositionY=0;
  float shitX=heroPositionX+250;
  float shitY=heroPositionY+250;
  static int x,y;
  int moving;
  boolean collides=false;
  boolean movingCollides=false;
  Rectangle obstacle;
  Rectangle movingObstacle;

    public Play(int state) {
        obstacle=new Rectangle();
        movingObstacle=new Rectangle();
        obstacles=new ArrayList<Rectangle>();
        movingObstacles=new ArrayList<Rectangle>();
        addObstacles(true);
        addMovingObstacles(true);
        square=new Rectangle((int)shitX,(int)shitY,50,60);
  }
   public void addObstacles(boolean start){
      int width=50;
      int height=200;
     if (start) {
         obstacles.add(new Rectangle((int) heroPositionX + 500, (int) heroPositionY, width, height));
         obstacles.add(new Rectangle((int) heroPositionX + 500, (int) heroPositionY + 400, width, height));
         // obstacles.add(new Rectangle((int)heroPositionX+700,(int)heroPositionY+400,width,height));
     }
     else {
         obstacles.add(new Rectangle((int) heroPositionX + 500, (int) heroPositionY, width, height));
         obstacles.add(new Rectangle((int) heroPositionX + 500, (int) heroPositionY + 400, width, height));
     }
    }
    public void addMovingObstacles(boolean start){
        int width=20;
        int height=150;
        if (start=true) {
            movingObstacles.add(new Rectangle((int) heroPositionX + 700, (int) heroPositionY-50 , width, height));
           // movingObstacles.add(new Rectangle((int) heroPositionX + 700, (int) heroPositionY + 450, width, height));
        }
        else {
            movingObstacles.add(new Rectangle((int) heroPositionX + 700, movingObstacles.get(movingObstacles.size()-1).y-350, width, height));
          //  movingObstacles.add(new Rectangle((int) heroPositionX + 700, movingObstacles.get(movingObstacles.size()-1).y-850, width, height));


        }

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

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        map=new Image("lib/res/img/test1.png");
       Image[] walkUp={new Image(("lib/res/img/Hero back.png")),new Image("lib/res/img/Hero back.png")};
       Image[] walkDown={new Image(("lib/res/img/Hero front.png")),new Image("lib/res/img/Hero front.png")};
       Image[] walkLeft={new Image(("lib/res/img/Hero left.png")),new Image("lib/res/img/Hero left.png")};
       Image[] walkRight={new Image(("lib/res/img/Hero right.png")),new Image("lib/res/img/Hero right.png")};

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

            for (int i=0;i<movingObstacles.size();i++){
                paintObstacles(g,movingObstacles.get(i));
            }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        moving=1;
      Input input = gc.getInput();
        if (quit == true){
            if (input.isKeyDown(Input.KEY_Q)){
                gc.exit();
            }

            if (input.isKeyDown(Input.KEY_M)){
                sbg.enterState(0);

            }

            if (input.isKeyDown(Input.KEY_R)){
                quit = false;
            }
        }
        //obstacles moving up and down

        barriarsCollision(gc);
        movingCollision();
        for(int z=0;z<movingObstacles.size();z++){
            Rectangle obstacle=movingObstacles.get(z);
            if(obstacle.y>1000){
                movingObstacles.remove(obstacle);

                 addMovingObstacles(false);

            }
        }
       //movingobstacles
       for (int i=0;i<movingObstacles.size();i++) {

           movingObstacle = movingObstacles.get(i);
           movingObstacle.y++;

//           if (square.intersects(movingObstacle)) {
//               movingCollides = true;
//           } else {
//               movingCollides = false;
//           }
           if (input.isKeyDown(Input.KEY_UP)) {
               movingObstacle.y += moving;

           }
           if (input.isKeyDown(Input.KEY_DOWN)) {
               movingObstacle.y -= moving;
           }
           if (input.isKeyDown(Input.KEY_LEFT)) {
               movingObstacle.x += moving;
           }

           if (input.isKeyDown(Input.KEY_RIGHT)) {
               movingObstacle.x -= moving;
           }
       }
       //map moving
         if (input.isKeyDown(Input.KEY_UP)) {
               hero = movingUp;
             heroPositionY += moving;

           }
           if (input.isKeyDown(Input.KEY_DOWN)) {
               hero = movingDown;
               heroPositionY -= moving;

           }
           if (input.isKeyDown(Input.KEY_LEFT)) {
               hero = movingLeft;
               heroPositionX += moving;
           }
           if (input.isKeyDown(Input.KEY_RIGHT)) {

               hero = movingRight;
               heroPositionX -= moving;}
        }


    public void barriarsCollision(GameContainer gc){
        Input input=gc.getInput();
        for (int i=0;i<obstacles.size();i++) {

            obstacle=obstacles.get(i);
            if (square.intersects(obstacle)){
                collides=true;
            }
            else {
                collides=false;
            }
            if (input.isKeyDown(Input.KEY_UP)) {
                obstacle.y += moving;
                if (collides){
                    heroPositionY-=20;
                    for (int z=0;z<obstacles.size();z++) {
                        obstacles.get(z).y-=20;
                    }
                    for (int x=0;x<movingObstacles.size();x++) {
                        movingObstacles.get(x).y-=20;
                    }
                }
            }
            if (input.isKeyDown(Input.KEY_DOWN)) {
                obstacle.y -= moving;
                if (collides){
                    heroPositionY+=20;
                    for (int z=0;z<obstacles.size();z++) {
                        obstacles.get(z).y+=20;
                    }
                    for (int x=0;x<movingObstacles.size();x++) {
                        movingObstacles.get(x).y+=20;
                    }
                }
            }
            if (input.isKeyDown(Input.KEY_LEFT)) {
                obstacle.x+=moving;
                if (collides && heroPositionX<0-(obstacle.x-50)){
                    heroPositionX-=20;
                    for (int z=0;z<obstacles.size();z++) {
                        obstacles.get(z).x-=20;
                    }
                    for (int x=0;x<movingObstacles.size();x++) {
                        movingObstacles.get(x).x-=20;
                    }
                }
            }
            if (input.isKeyDown(Input.KEY_RIGHT)) {
                obstacle.x -= moving;
                if (collides&&heroPositionX>0-(obstacle.x+50) ){
                    heroPositionX+=20;
                    for (int z=0;z<obstacles.size();z++) {
                        obstacles.get(z).x+=20;
                    }
                    for (int x=0;x<movingObstacles.size();x++) {
                        movingObstacles.get(x).x+=20;
                    }
                }
            }
        }
    }
    public void movingCollision(){
        for (int i=0;i<movingObstacles.size();i++) {
            Rectangle movingObstacle=movingObstacles.get(i);
            if (square.intersects(movingObstacle)) {
              heroPositionX=0;
              heroPositionY=0;
              obstacles.clear();
              addObstacles(false);
              movingObstacles.clear();
              addMovingObstacles(false);
           } else {
              continue;
           }
        }
    }

    public int getID() {
        return 1;
    }

}
