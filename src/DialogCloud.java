import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import java.security.SecureRandom;

public class DialogCloud extends Rectangle {
    private String[] rightAnswers={"Ahh, that was correct. It hurts","That was an easy one. Try this one!"};
    private String[] wrongAnswers={"That is not correct. You will never beat me!","Wrong, wrong, wrong!"};
    private String[] defeated={"This cannot be. I got defeated.","I got defeated. Maybe next time"};
    private String question;
    private final SecureRandom generator=new SecureRandom();
    private static Image texture;
    private int state;  //0-transparent, 1-display question, 2-right answer, 3-wrong answer, 4-defeated

    public DialogCloud(int x, int y, int width, int height) {
        super(x, y, width, height);
        setState(1);
    }

    public void draw(Graphics g){
        g.setColor(Color.black);
        switch(state){
            case 1: texture.draw(x,y,width,height);
                    g.drawString(question,x+width/8,y-height/4);
                    break;
            case 2: texture.draw(x,y,width,height);
                    g.drawString(rightAnswers[generator.nextInt(rightAnswers.length-1)],x+width/8,y-height/4);
                    break;
            case 3: texture.draw(x,y,width,height);
                    g.drawString(wrongAnswers[generator.nextInt(wrongAnswers.length-1)],x+width/8,y-height/4);
                    break;
            case 4: texture.draw(x,y,width,height);
                    g.drawString(defeated[generator.nextInt(defeated.length-1)],x+width/8,y-height/4);
                    break;
            default:
                    break;
        }
    }

    public static void setTexture(Image texture) {
        DialogCloud.texture = texture;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


}
