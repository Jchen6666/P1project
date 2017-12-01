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
    int sign;

    public Projectile(float startingX, float startingY, float endingX, float endingY, float width, float heigth, int time,int sign) {
        this.startingX = startingX;
        this.startingY = startingY;
        currentX=startingX;
        currentY=startingY;
        this.endingX = endingX;
        this.endingY = endingY;
        this.width = width;
        this.heigth = heigth;
        this.time = time;
        this.sign=sign;
        xSpeed=(endingX-startingX)/time;  //delta in X line/one millisecond
        ySpeed=(endingY-startingY)/time;
        atTarget=false;
        try {
            setTexture(new Image("lib/res/img/projectiles.png"));
        }catch(SlickException e){
            System.out.println("Couldn't load projectiles Texture");
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

    void draw() {
        switch (sign) {
            case 1:texture.getSubImage(0,0,32,32).draw(currentX, currentY, width, heigth);
                    break;
            case 2:texture.getSubImage(32,32,32,32).draw(currentX, currentY, width, heigth);
                break;
            case 3:texture.getSubImage(32,0,32,32).draw(currentX, currentY, width, heigth);
                break;
            case 4:texture.getSubImage(0,32,32,32).draw(currentX, currentY, width, heigth);
                break;
        }
    }

    public void setTexture(Image texture) {
        this.texture = texture;
    }
}
