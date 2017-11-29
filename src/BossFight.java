import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class BossFight extends BasicGameState {

        Image background;
        Image hpHeart;
        Image table;
        Image buttonsTable;
        Image bossInterface;
        Image platform;
        Image boss;
        DialogCloud dialogCloud;
        Animation heartAnimation;
        QuestionGenerator question;
        ArrayList<Button> buttonList;
        SpriteSheet hearts;
        int rightAnswerPosition;
        int selectedPosition;
        boolean questionAnswered;
        boolean gameWon;
        int numberQuestionsAnswered;
        int bossHp;   //Starting amount of boss lifes
        float tabPos; //variable helping to draw the interface
        int time;

        public BossFight(int state) {

        }

        public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        SpriteSheet hearts=new SpriteSheet("lib/res/img/heartsNew.png",16,16);
        heartAnimation= new Animation(hearts,500);
        heartAnimation.setLooping(false);
        heartAnimation.stop();
        background=new Image("lib/res/img/background.png");
        table=new Image("lib/res/img/bossFightTable.png");
        bossInterface= new Image("lib/res/img/bossFightTable.png");
        buttonsTable= new Image("lib/res/img/bossFightButtons.png");
        platform = new Image("lib/res/img/platform.png");
        boss=new Image("lib/res/img/boss.png");
        hpHeart=hearts.getSubImage(0,0,16,16);
        question=new QuestionGenerator();
        buttonList=new ArrayList<Button>(4);
        rightAnswerPosition=(question.getGenerator().nextInt(4)+1);     //Determine the position of the right answer
        Button.setHighlight(new Image("lib/res/img/highlight.png"));
        buttonList=generateTheLevel(question,rightAnswerPosition,selectedPosition);     //Method returning the array of Buttons(4 of them);
        gameWon=false;
        dialogCloud=new DialogCloud(300,100,512,256,new Image("lib/res/img/dialogCloud.png"),question.toString());
        bossHp=5;
        questionAnswered=false;
        selectedPosition=0;
        tabPos=(4*Settings.getScreenHeight())/5;
        numberQuestionsAnswered=0;
        time=0;
        }

        public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

       // background.draw(0,0,Settings.getScreenWidth(), Settings.getScreenHeight());
        platform.draw(-Settings.getScreenWidth()/10,tabPos-Settings.getScreenHeight()/7,Settings.getScreenWidth()/2,(float)0.28*Settings.getScreenHeight());
        platform.draw(Settings.getScreenWidth()/2,Settings.getScreenHeight()/2,Settings.getScreenWidth()/2,(float)0.28*Settings.getScreenHeight());
        boss.draw(Settings.getScreenWidth()/2+Settings.getScreenWidth()/4-Settings.getScreenWidth()/10,Settings.getScreenHeight()/2+(float)0.14*Settings.getScreenHeight()-(float)0.71*Settings.getScreenHeight(),Settings.getScreenWidth()/5,(float)0.71*Settings.getScreenHeight());
        table.draw(0,tabPos,Settings.getScreenWidth(),Settings.getScreenHeight()-tabPos);
        buttonsTable.draw(Settings.getScreenWidth()/2,tabPos,Settings.getScreenWidth()/2,Settings.getScreenHeight()-tabPos);
        g.setColor(Color.black);
        dialogCloud.draw(g);
        if(gameWon){

        }else {
            for (int i = 0; i < bossHp; i++) {
                hpHeart.draw(i*(Settings.getScreenWidth()/20)+(Settings.getScreenWidth()/128),(Settings.getScreenWidth()/128),Settings.getScreenWidth()/20,Settings.getScreenWidth()/20);
            }
            if(numberQuestionsAnswered!=0) {
                heartAnimation.draw((bossHp) *(Settings.getScreenWidth()/20)+(Settings.getScreenWidth()/128),(Settings.getScreenWidth()/128),Settings.getScreenWidth()/20,Settings.getScreenWidth()/20);
            }
            for(int i=0;i<4;i++){
                buttonList.get(i).drawText(g);
                buttonList.get(i).drawHighlight();
            }
        }
        }

        public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input=gc.getInput();
        input.disableKeyRepeat();
        if(!gameWon) {
            System.out.println(time);
            if(time>3000&&dialogCloud.getState()!=1){
                dialogCloud.setState(1);
            }
            if (questionAnswered) {
                time=0;
                rightAnswerPosition = (question.getGenerator().nextInt(4) + 1);
                question.regenerate();
                buttonList = generateTheLevel(question, rightAnswerPosition, selectedPosition);
                questionAnswered = false;
                dialogCloud.setQuestion(question.toString());
                dialogCloud.setState(2);
            }
            if (input.isKeyPressed(Input.KEY_DOWN)) {
                if (selectedPosition == 2) {
                    buttonList.get(0).setSelected(true);
                    buttonList.get(selectedPosition).setSelected(false);
                    selectedPosition = 0;
                } else if (selectedPosition == 3) {
                    buttonList.get(1).setSelected(true);
                    buttonList.get(selectedPosition).setSelected(false);
                    selectedPosition = 1;
                }
            }
            if (input.isKeyPressed(Input.KEY_UP)) {
                if (selectedPosition == 0) {
                    buttonList.get(2).setSelected(true);
                    buttonList.get(selectedPosition).setSelected(false);
                    selectedPosition = 2;
                } else if (selectedPosition == 1) {
                    buttonList.get(3).setSelected(true);
                    buttonList.get(selectedPosition).setSelected(false);
                    selectedPosition = 3;
                }
            }
            if (input.isKeyPressed(Input.KEY_LEFT)) {
                if (selectedPosition == 1) {
                    buttonList.get(0).setSelected(true);
                    buttonList.get(selectedPosition).setSelected(false);
                    selectedPosition = 0;
                } else if (selectedPosition == 3) {
                    buttonList.get(2).setSelected(true);
                    buttonList.get(selectedPosition).setSelected(false);
                    selectedPosition = 2;
                }
            }
            if (input.isKeyPressed(Input.KEY_RIGHT)) {
                if (selectedPosition == 2) {
                    buttonList.get(3).setSelected(true);
                    buttonList.get(selectedPosition).setSelected(false);
                    selectedPosition = 3;
                } else if (selectedPosition == 0) {
                    buttonList.get(1).setSelected(true);
                    buttonList.get(selectedPosition).setSelected(false);
                    selectedPosition = 1;
                }
            }
            if (((input.isKeyPressed(Input.KEY_ENTER))||input.isMousePressed(0))) {
                    if (buttonList.get(selectedPosition).isTheAnswerRight()) {
                        bossHp--;
                        numberQuestionsAnswered++;
                        questionAnswered = true;
                        heartAnimation.update(delta);
                        heartAnimation.restart();
                        heartAnimation.start();
                        if(bossHp<=0)   gameWon=true;
                    }else{
                    }
            }
            for(int i=0;i<4;i++){
                if(buttonList.get(i).isHovered()&&!buttonList.get(i).isSelected()){
                    for(int l=0;l<4;l++){
                        if(buttonList.get(l).isSelected()){
                            buttonList.get(l).setSelected(false);
                        }
                    }
                    buttonList.get(i).setSelected(true);
                    selectedPosition=i;
                }
            }
            if(time!=Integer.MAX_VALUE) {
                time += delta;
            }
        }else {
            if(dialogCloud.getState()!=4) {
                dialogCloud.setState(4);
            }
            if(input.isKeyPressed(Input.KEY_ESCAPE)){
                sbg.enterState(0);
            }
        }

        }

        public static ArrayList<Button> generateTheLevel(QuestionGenerator question,int rightAnswerPosition, int selectedPosition){
            float tabPosY=(4*Settings.getScreenHeight())/5;
            float tabPosX=(Settings.getScreenWidth()/2);
            float tableWidth=tabPosX;
            float tableHeight=(Settings.getScreenHeight()-tabPosY)/2;  //y position in the middle of buttons
            ArrayList<Button> buttonList=new ArrayList<Button>(4);
            buttonList.add(new Button(tabPosX,tabPosY+tableHeight,tableWidth/2,tableHeight,Integer.toString(question.generateWrongAnswer())));
            buttonList.add(new Button(tabPosX+tabPosX/2,tabPosY+tableHeight,tableWidth/2,tableHeight,Integer.toString(question.generateWrongAnswer())));
            buttonList.add(new Button(tabPosX,tabPosY,tableWidth/2,tableHeight,Integer.toString(question.generateWrongAnswer())));
            buttonList.add(new Button(tabPosX+tabPosX/2,tabPosY,tableWidth/2,tableHeight,Integer.toString(question.generateWrongAnswer())));
            buttonList.get(rightAnswerPosition-1).setTheAnswerRight(true);
            buttonList.get(rightAnswerPosition-1).setText(Integer.toString(question.getRightAnswer()));
            buttonList.get(selectedPosition).setSelected(true);
            return buttonList;
        }
        public int getID() { return 2; }

}

