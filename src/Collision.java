import java.awt.*;
import java.util.ArrayList;

public class Collision {
    public static   void up( ArrayList<Rectangle>obstacles,ArrayList<Rectangle>movingObstacles,ArrayList<Button>buttons,int bounce){
        for (int i=0;i<obstacles.size();i++) {
            obstacles.get(i).y-=bounce;
        }
        for (int i=0;i<movingObstacles.size();i++) {
            movingObstacles.get(i).y-=bounce;
        }
        for (int i=0;i<buttons.size();i++){
            buttons.get(i).setY(buttons.get(i).getY()-bounce);
        }
    }
    public static void down( ArrayList<Rectangle>obstacles,ArrayList<Rectangle>movingObstacles,ArrayList<Button>buttons,int bounce) {
        for (int i=0;i<obstacles.size();i++) {
            obstacles.get(i).y+=bounce;
        }
        for (int i=0;i<movingObstacles.size();i++) {
            movingObstacles.get(i).y+=bounce;
        }
        for (int i=0;i<buttons.size();i++){
            buttons.get(i).setY(buttons.get(i).getY()+bounce);
        }
     }
    public static void left( ArrayList<Rectangle>obstacles,ArrayList<Rectangle>movingObstacles,ArrayList<Button>buttons,int bounce) {
        for (int i=0;i<obstacles.size();i++) {
            obstacles.get(i).x-=bounce;
        }
        for (int i=0;i<movingObstacles.size();i++) {
            movingObstacles.get(i).x-=bounce;
        }
        for (int i=0;i<buttons.size();i++){
            buttons.get(i).setX(buttons.get(i).getX()-bounce);
        }
    }
    public static void right( ArrayList<Rectangle>obstacles,ArrayList<Rectangle>movingObstacles,ArrayList<Button>buttons,int bounce) {
        for (int i=0;i<obstacles.size();i++) {
            obstacles.get(i).x+=bounce;
        }
        for (int i=0;i<movingObstacles.size();i++) {
            movingObstacles.get(i).x+=bounce;
        }
        for (int i=0;i<buttons.size();i++){
            buttons.get(i).setX(buttons.get(i).getX()+bounce);
        }
    }
    }
