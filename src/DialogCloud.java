import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import java.security.SecureRandom;

public class DialogCloud extends Rectangle {
    private String[] rightAnswers={"This is harder than I expected..","I am just warming up. Just hold on.","This is not good...","I wasn't even trying anyways.","Impressive.","I was not expecting this kind of challenge","Finally someone who can put up a good fight.","I might have to call for assistance soon...","This is getting intense.","I can not let you win."};
    private String[] wrongAnswers={"Ha Ha. You are no challenge for me","Looks like this will be an easy one.","This is easier than I expected.","I haven't begun to use my full power. Come on, give me a challenge!","No matter how much you try, you still won't beat me.","You're going to have to do better than that.","I've done my homework! Looks like you haven't.","Do you think you can beat me?","How silly of you.. Thinking that you have a chance of beating me.","YOU SHALL NOT PASS!"};
    private String[] defeated={"I have fallen... But I shall return!","Aaaarghh! I have been defeated.","You won this time. But this will be your last victory!","I will return. Stronger than ever.","Oh no! How could I have been defeated?","This will not be the last time we meet...","I almost had you...","NOOOOOOOO!!!!","My perfect plan.... RUINED!","You are too much for me.","I did the math correctly... But I still lost."};
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
