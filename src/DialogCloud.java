import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;

import java.awt.*;
import java.security.SecureRandom;

public class DialogCloud extends Rectangle {
    private String[] rightAnswers={"This is harder than I expected..","I am just warming up. Just hold on.","This is not good...","I wasn't even trying anyways.","Impressive.","I was not expecting this kind of challenge","Finally someone who can put up a good fight.","I might have to call for assistance soon...","This is getting intense.","I can not let you win."};
    private String[] wrongAnswers={"Ha Ha. You are no challenge for me","Looks like this will be an easy one.","This is easier than I expected.","Come on, give me a challenge!","You will never beat me.","You're going to have to do better than that.","I've done my homework! Looks like you haven't.","Do you think you can beat me?","How silly of you...","YOU SHALL NOT PASS!"};
    private String[] defeated={"I have fallen... But I shall return!","Aaaarghh! I have been defeated.","You won this time. But this will be your last victory!","I will return. Stronger than ever.","Oh no! How could I have been defeated?","This will not be the last time we meet...","I almost had you...","NOOOOOOOO!!!!","My perfect plan.... RUINED!","You are too much for me.","I did the math correctly... But I still lost."};
    private String[] greeting={"Welcome to the arena!","Hey there"};
    private String question;
    private final SecureRandom generator=new SecureRandom();
    private Image texture;
    private int state;  //0-transparent, 1-display question, 2-right answer, 3-wrong answer, 4-defeated, 5-greeting
    private int rightAnswerVariable;
    private int wrongAnswerVariable;
    private int defeatedVariable;
    private int greetingVariable;
    private int fontSize;

    public DialogCloud(int x, int y, int width, int height, Image texture, String question) {
        super(x, y, width, height);
        try{setTexture(texture);}
        catch(Exception e){
            System.out.println("Could not load DialogCloud texture.");
        }
        setQuestion(question);
        setState(5);
        rightAnswerVariable=generator.nextInt(rightAnswers.length);
        wrongAnswerVariable=generator.nextInt(wrongAnswers.length);
        defeatedVariable=generator.nextInt(defeated.length);
        greetingVariable=generator.nextInt(greeting.length);

    }

    public void draw(Graphics g){
        g.setColor(Color.black);
        try {
            switch (state) {
                case 1:
                    texture.draw(x, y, width, height);
                    g.drawString(question, x+((width-g.getFont().getWidth(question))/2), y +( (height-g.getFont().getHeight(question))/ 2));
                    break;
                case 2:
                    texture.draw(x, y, width, height);
                    g.drawString(rightAnswers[rightAnswerVariable], x+((width-g.getFont().getWidth(rightAnswers[rightAnswerVariable]))/2), y +( (height-g.getFont().getHeight(rightAnswers[rightAnswerVariable]))/ 2));
                    break;
                case 3:
                    texture.draw(x, y, width, height);
                    g.drawString(wrongAnswers[wrongAnswerVariable], x+((width-g.getFont().getWidth(wrongAnswers[wrongAnswerVariable]))/2), y +( (height-g.getFont().getHeight(wrongAnswers[wrongAnswerVariable]))/ 2));
                    break;
                case 4:
                    texture.draw(x, y, width, height);
                    g.drawString(defeated[defeatedVariable], x+((width-g.getFont().getWidth(defeated[defeatedVariable]))/2), y +( (height-g.getFont().getHeight(defeated[defeatedVariable]))/ 2));
                    break;
                case 5:
                    texture.draw(x, y, width, height);
                    g.drawString(greeting[greetingVariable], x+((width-g.getFont().getWidth(greeting[greetingVariable]))/2), y +( (height-g.getFont().getHeight(greeting[greetingVariable]))/ 2));
                    break;
                default:
                    break;
            }
        }catch(Exception e){
            System.out.println("DialogCloud texture could not be loaded.");
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
        greetingVariable=generator.nextInt(greeting.length);
    }


}
