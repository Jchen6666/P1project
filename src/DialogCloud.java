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
    private Image texture;
    private int state;  //0-transparent, 1-display question, 2-right answer, 3-wrong answer, 4-defeated
    private int rightAnswerVariable;
    private int wrongAnswerVariable;
    private int defeatedVariable;

    public DialogCloud(int x, int y, int width, int height, Image texture, String question) {
        super(x, y, width, height);
        setTexture(texture);
        setQuestion(question);
        setState(1);
        rightAnswerVariable=generator.nextInt(rightAnswers.length);
        wrongAnswerVariable=generator.nextInt(wrongAnswers.length);
        defeatedVariable=generator.nextInt(defeated.length);

    }

    public void draw(Graphics g){
        g.setColor(Color.black);
        switch(state){
            case 1: texture.draw(x,y,width,height);
                    g.drawString(question,x+width/8,y+height/2);
                    break;
            case 2: texture.draw(x,y,width,height);
                    g.drawString(rightAnswers[rightAnswerVariable],x+width/8,y+height/2);
                    break;
            case 3: texture.draw(x,y,width,height);
                    g.drawString(wrongAnswers[wrongAnswerVariable],x+width/8,y+height/2);
                    break;
            case 4: texture.draw(x,y,width,height);
                    g.drawString(defeated[defeatedVariable],x+width/8,y+height/2);
                    break;
            default:
                    break;
        }
    }

    public void setTexture(Image texture) {
        this.texture = texture;
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
        rightAnswerVariable=generator.nextInt(rightAnswers.length);
        wrongAnswerVariable=generator.nextInt(wrongAnswers.length);
        defeatedVariable=generator.nextInt(defeated.length);
    }


}
