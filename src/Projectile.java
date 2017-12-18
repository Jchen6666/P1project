import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Projectile {
    private float startingX;
    private float startingY;
    private float currentX;
    private float currentY;
    private float endingX;
    private float endingY;
    private float width;
    private float heigth;
    private Image texture;
    private int time;   //in milliseconds
    private float xSpeed;
    private float ySpeed;
    private boolean atTarget;
    private boolean start;
    private boolean modified;
    private int timePassed;
    private int sign;

    /**
     * Constructor, initializes the projectile object and loading predefined projectile textures
     * @param startingX starting x position of a projectile
     * @param startingY starting y position of a projectile
     * @param endingX target ending x position
     * @param endingY target ending y position
     * @param width the width of a projectile
     * @param heigth the height of a projectile
     * @param time time projectile takes to get to its destination in milliseconds
     * @param sign specify the type of a projectile (1-addition 2-subtraction 3-multiplication 4-division)
     */
    public Projectile(float startingX, float startingY, float endingX, float endingY, float width, float heigth, int time,int sign) {
        this.startingX = startingX;
        this.startingY = startingY;
        this.currentX=startingX;
        this.currentY=startingY;
        this.endingX = endingX;
        this.endingY = endingY;
        this.width = width;
        this.heigth = heigth;
        this.time = time;
        this.sign=sign;
        modified=false;
        xSpeed=(endingX-startingX)/time;  //delta in X line/one millisecond
        ySpeed=(endingY-startingY)/time;
        start=false;
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

    public void start(){
        start=true;
        }

    public float getCurrentX() {
        return currentX;
    }

    public float getCurrentY() {
        return currentY;
    }

    /**
     * Updates the position of a projectile
     * @param delta parameter of init method which states for amount of time that passed from a last call of game state's init method (in milliseconds)
     */
    void update(int delta){
        if(start) {
            if (timePassed < time) {
                currentX += delta * xSpeed;
                currentY += delta * ySpeed;
                timePassed += delta;
            } else {
                System.out.println("Projectile already at target");
                setAtTarget(true);
            }
        }
    }

    /**
     * Draws a projectile at current position
     */
    void draw() {
        switch (sign) {
            case 1:texture.getSubImage(0,0,64,64).draw(currentX, currentY, width, heigth);
                    break;
            case 2:texture.getSubImage(64,64,64,64).draw(currentX, currentY, width, heigth);
                break;
            case 3:texture.getSubImage(64,0,64,64).draw(currentX, currentY, width, heigth);
                break;
            case 4:texture.getSubImage(0,64,64,64).draw(currentX, currentY, width, heigth);
                break;
        }
    }

    /**
     * Draws a flash of a projectile at current position
     */
    void drawFlash() {
        switch (sign) {
            case 1:texture.getSubImage(0,0,64,64).drawFlash(currentX, currentY, width, heigth);
                break;
            case 2:texture.getSubImage(64,64,64,64).drawFlash(currentX, currentY, width, heigth);
                break;
            case 3:texture.getSubImage(64,0,64,64).drawFlash(currentX, currentY, width, heigth);
                break;
            case 4:texture.getSubImage(0,64,64,64).drawFlash(currentX, currentY, width, heigth);
                break;
        }
    }

    /**
     * Resets the starting position of a projectile and recalculates its speed and direction
     * @param startingX new starting x
     * @param startingY new starting y
     */
    void setNewStartingPosition(float startingX,float startingY){
        this.startingX = startingX;
        this.startingY = startingY;
        xSpeed=(endingX-startingX)/time;  //delta in X line/one millisecond
        ySpeed=(endingY-startingY)/time;
        currentX=startingX;
        currentY=startingY;
        timePassed=0;
        modified=true;
    }
    public void setTexture(Image texture) {
        this.texture = texture;
    }
    public boolean isModified() {
        return modified;
    }
    public boolean isStart() {
        return start;
    }
}
