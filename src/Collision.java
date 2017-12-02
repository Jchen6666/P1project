import java.awt.*;
import java.util.ArrayList;

public class Collision {
    public  void up( ArrayList<Rectangle>obstacles,ArrayList<Rectangle>movingObstacles,ArrayList<Button>buttons){
        for (int i=0;i<obstacles.size();i++) {
            obstacles.get(i).y-=20;
        }
        for (int i=0;i<movingObstacles.size();i++) {
            movingObstacles.get(i).y-=20;
        }
        for (int i=0;i<buttons.size();i++){
            buttons.get(i).setY(buttons.get(i).getY()-20);
        }
    }
    public  void down( ArrayList<Rectangle>obstacles,ArrayList<Rectangle>movingObstacles,ArrayList<Button>buttons) {
        for (int i=0;i<obstacles.size();i++) {
            obstacles.get(i).y+=20;
        }
        for (int i=0;i<movingObstacles.size();i++) {
            movingObstacles.get(i).y+=20;
        }
        for (int i=0;i<buttons.size();i++){
            buttons.get(i).setY(buttons.get(i).getY()+20);
        }
     }
    public  void left( ArrayList<Rectangle>obstacles,ArrayList<Rectangle>movingObstacles,ArrayList<Button>buttons) {
        for (int i=0;i<obstacles.size();i++) {
            obstacles.get(i).x-=20;
        }
        for (int i=0;i<movingObstacles.size();i++) {
            movingObstacles.get(i).x-=20;
        }
        for (int i=0;i<buttons.size();i++){
            buttons.get(i).setX(buttons.get(i).getX()-20);
        }
    }
    public  void right( ArrayList<Rectangle>obstacles,ArrayList<Rectangle>movingObstacles,ArrayList<Button>buttons) {
        for (int i=0;i<obstacles.size();i++) {
            obstacles.get(i).x+=20;
        }
        for (int i=0;i<movingObstacles.size();i++) {
            movingObstacles.get(i).x+=20;
        }
        for (int i=0;i<buttons.size();i++){
            buttons.get(i).setX(buttons.get(i).getX()+20);
        }
    }
    }
