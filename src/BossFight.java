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
        QuestionGenerator question;
        ArrayList<Button> buttonList;
        int rightAnswerPosition;
        int selectedPosition;
        boolean questionAnswered;
        boolean gameWon;
        int bossHp;   //Starting amount of bosses lifes
        float tabPos; //variable helping to draw the interface

        public BossFight(int state) {

        }

        public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background=new Image("lib/res/img/newBossBackground.png");
        table=new Image("lib/res/img/bossFightTable.png");
        bossInterface= new Image("lib/res/img/bossFightTable.png");
        buttonsTable= new Image("lib/res/img/bossFightButtons.png");
        hpHeart= new Image("lib/res/img/hpHeart.png");
        question=new QuestionGenerator();
        buttonList=new ArrayList<Button>(4);
        rightAnswerPosition=(question.getGenerator().nextInt(4)+1);     //Determine the position of the right answer
        Button.setHighlight(new Image("lib/res/img/highlight.png"));
        buttonList=generateTheLevel(question,rightAnswerPosition,selectedPosition);     //Method returning the array of Buttons(4 of them);
        bossHp=10;
        gameWon=false;
        questionAnswered=false;
        selectedPosition=0;
        tabPos=(4*Settings.getScreenHeight())/5;
        }

        public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        background.draw(0,0,Settings.getScreenWidth(),tabPos);
        table.draw(0,tabPos,Settings.getScreenWidth(),Settings.getScreenHeight()-tabPos);
        buttonsTable.draw(Settings.getScreenWidth()/2,tabPos,Settings.getScreenWidth()/2,Settings.getScreenHeight()-tabPos);
        g.setColor(Color.black);
        if(gameWon){
            g.drawString("I just got defeated, press ESC to go back", Settings.getScreenWidth()*(float)0.35, Settings.getScreenHeight()*(float)0.18);
        }else {
            g.drawString(question.toString(), Settings.getScreenWidth()*(float)0.35, Settings.getScreenHeight()*(float)0.18);
            for (int i = 0; i < bossHp; i++) {
                hpHeart.draw(i * 64 + 10, 10);
            }
            for(int i=0;i<4;i++){
                buttonList.get(i).drawText(g);
                buttonList.get(i).drawHighlight();
            }
        }
        }

        public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input=gc.getInput();
        if(!gameWon) {
            if (questionAnswered) {
                rightAnswerPosition = (question.getGenerator().nextInt(4) + 1);
                question.regenerate();
                buttonList = generateTheLevel(question, rightAnswerPosition, selectedPosition);
                questionAnswered = false;
            }
            if (input.isKeyDown(Input.KEY_DOWN)) {
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
            if (input.isKeyDown(Input.KEY_UP)) {
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
            if (input.isKeyDown(Input.KEY_LEFT)) {
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
            if (input.isKeyDown(Input.KEY_RIGHT)) {
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
            if (((input.isKeyDown(Input.KEY_ENTER))||input.isMouseButtonDown(0))&&(buttonList.get(selectedPosition).isTheAnswerRight())) {
                    if (!questionAnswered) {
                        bossHp--;
                        questionAnswered = true;
                        if(bossHp<=0)   gameWon=true;
                    }
            }
            for(int i=0;i<4;i++){
                if(buttonList.get(i).isHovered(Mouse.getX(),Mouse.getY())&&!buttonList.get(i).isSelected()){
                    for(int l=0;l<4;l++){
                        if(buttonList.get(l).isSelected()){
                            buttonList.get(l).setSelected(false);
                        }
                    }
                    buttonList.get(i).setSelected(true);
                    selectedPosition=i;
                }
            }

        }else if(input.isKeyDown(Input.KEY_ESCAPE)){
            sbg.enterState(0);
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

