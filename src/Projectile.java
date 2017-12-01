import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Projectile {
    float startingX;
    float startingY;
    float currentX;
    float currentY;
    float endingX;
    float endingY;
    float width;
    float heigth;
    Image texture;
    int time;   //in milliseconds
    float xSpeed;
    float ySpeed;
    float speed;
    boolean atTarget;
    int timePassed;

    public Projectile(float startingX, float startingY, float endingX, float endingY, float width, float heigth, int time) {
        this.startingX = startingX;
        this.startingY = startingY;
        currentX=startingX;
        currentY=startingY;
        this.endingX = endingX;
        this.endingY = endingY;
        this.width = width;
        this.heigth = heigth;
        this.time = time;
        xSpeed=(endingX-startingX)/time;  //delta in X line/one millisecond
        ySpeed=(endingY-startingY)/time;
        atTarget=false;
        try {
            setTexture(new Image("lib/res/img/additionProjectile.png"));
        }catch(SlickException e){
            System.out.println("Couldn't load projectileTexture");
        }
        timePassed=0;
    }

    public boolean isAtTarget() {
        return atTarget;
    }

    public void setAtTarget(boolean atTarget) {
        this.atTarget = atTarget;
    }

    public float getCurrentX() {
        return currentX;
    }

    public float getCurrentY() {
        return currentY;
    }

    void update(int delta){
        if(timePassed<time) {
            currentX += delta*xSpeed;
            currentY += delta*ySpeed;
            timePassed+=delta;
        }else{
            System.out.println("Projectile already at target");
            setAtTarget(true);
        }
    }

    void draw(){
        texture.drawFlash(currentX,currentY,width,heigth);
    }

    public void setTexture(Image texture) {
        this.texture = texture;
    }
}
