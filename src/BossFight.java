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
        DialogCloud dialogCloud;
        Animation heroAnimation;
        Animation heartAnimation;
        Animation bossAnimation;
        Animation bossDyeingAnimation;
        Animation bossShieldAnimation;
        QuestionGenerator question;
        ArrayList<Button> buttonList;
        SpriteSheet hearts;
        Projectile projectile;
        int rightAnswerPosition;
        int selectedPosition;
        boolean questionAnswered;
        boolean gameWon;
        boolean bossIsHit;
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
        bossShieldAnimation=new Animation(new SpriteSheet("lib/res/img/bossShieldAnimation.png",32,64),1200);
        bossAnimation=new Animation(new SpriteSheet("lib/res/img/bossAnimation.png",32,64),1000);
        bossShieldAnimation.setLooping(false);
        bossShieldAnimation.stop();
        heroAnimation=new Animation(new SpriteSheet("lib/res/img/hero.png",32,32),1000);
        bossDyeingAnimation=new Animation(new SpriteSheet("lib/res/img/bossDyingAnimation.png",32,64),1000);
        bossDyeingAnimation.setLooping(false);
        bossDyeingAnimation.stop();
        heroAnimation.setLooping(false);
        heroAnimation.stop();
        background=new Image("lib/res/img/background.png");
        table=new Image("lib/res/img/bossFightTable.png");
        bossInterface= new Image("lib/res/img/bossFightTable.png");
        buttonsTable= new Image("lib/res/img/bossFightButtons.png");
        platform = new Image("lib/res/img/platform.png");
        hpHeart=hearts.getSubImage(0,0,16,16);
        question=new QuestionGenerator();
        buttonList=new ArrayList<Button>(4);
        rightAnswerPosition=(question.getGenerator().nextInt(4)+1);     //Determine the position of the right answer
        Button.setHighlight(new Image("lib/res/img/highlight.png"));
        buttonList=generateTheLevel(question,rightAnswerPosition,selectedPosition);     //Method returning the array of Buttons(4 of them);
        gameWon=false;
        dialogCloud=new DialogCloud((int)Settings.getScreenWidth()/4,(int)Settings.getScreenHeight()/9,(int)Settings.getScreenWidth()/4+200,(int)Settings.getScreenHeight()/4,new Image("lib/res/img/dialogCloud.png"),question.toString());
        bossHp=5;
        questionAnswered=false;
        bossIsHit=false;
        selectedPosition=0;
        tabPos=(4*Settings.getScreenHeight())/5;
        numberQuestionsAnswered=0;
        time=0;
        }

        public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        background.draw(0,0,Settings.getScreenWidth(), Settings.getScreenHeight());
        platform.draw(-Settings.getScreenWidth()/10,tabPos-Settings.getScreenHeight()/7,Settings.getScreenWidth()/2,(float)0.28*Settings.getScreenHeight());
        platform.draw(Settings.getScreenWidth()/2,Settings.getScreenHeight()/2,Settings.getScreenWidth()/2,(float)0.28*Settings.getScreenHeight());
        heroAnimation.draw(0,tabPos-Settings.getScreenWidth()/5,Settings.getScreenWidth()/5,Settings.getScreenWidth()/5);
        if(bossIsHit&&((time>=1500&&time<=1600)||(time>=2000&&time<=2100))){
            bossAnimation.drawFlash(Settings.getScreenWidth()/2+Settings.getScreenWidth()/4-Settings.getScreenWidth()/10,Settings.getScreenHeight()/2+(float)0.14*Settings.getScreenHeight()-(float)0.71*Settings.getScreenHeight(),Settings.getScreenWidth()/5,(float)0.71*Settings.getScreenHeight());
            }else {
            if(gameWon&&time>3000){
                bossDyeingAnimation.draw(Settings.getScreenWidth() / 2 + Settings.getScreenWidth() / 4 - Settings.getScreenWidth() / 10, Settings.getScreenHeight() / 2 + (float) 0.14 * Settings.getScreenHeight() - (float) 0.71 * Settings.getScreenHeight(), Settings.getScreenWidth() / 5, (float) 0.71 * Settings.getScreenHeight());
            }else {
                if(dialogCloud.getState()==3){
                    bossShieldAnimation.draw(Settings.getScreenWidth() / 2 + Settings.getScreenWidth() / 4 - Settings.getScreenWidth() / 10, Settings.getScreenHeight() / 2 + (float) 0.14 * Settings.getScreenHeight() - (float) 0.71 * Settings.getScreenHeight(), Settings.getScreenWidth() / 5, (float) 0.71 * Settings.getScreenHeight());
                }else {
                    bossAnimation.draw(Settings.getScreenWidth() / 2 + Settings.getScreenWidth() / 4 - Settings.getScreenWidth() / 10, Settings.getScreenHeight() / 2 + (float) 0.14 * Settings.getScreenHeight() - (float) 0.71 * Settings.getScreenHeight(), Settings.getScreenWidth() / 5, (float) 0.71 * Settings.getScreenHeight());
                }
            }
        }
        g.setColor(Color.black);
        dialogCloud.draw(g);
        if(projectile!=null){
                if(!projectile.isAtTarget()) {
                    projectile.draw();
                }
                if(projectile.isAtTarget()&&dialogCloud.getState()==3){
                    projectile.drawFlash();
                }
        }
        for(int i=0;i<Settings.getScreenWidth();i++) {
                table.draw(i, tabPos,table.getWidth(), Settings.getScreenHeight() - tabPos);
        }
        buttonsTable.draw(Settings.getScreenWidth()/2,tabPos,Settings.getScreenWidth()/2,Settings.getScreenHeight()-tabPos);


            for (int i = 0; i < bossHp; i++) {
                hpHeart.draw(i*(Settings.getScreenWidth()/20)+(Settings.getScreenWidth()/128),(Settings.getScreenWidth()/128),Settings.getScreenWidth()/20,Settings.getScreenWidth()/20);
            }
            if(numberQuestionsAnswered!=0) {
                heartAnimation.draw((bossHp) *(Settings.getScreenWidth()/20)+(Settings.getScreenWidth()/128),(Settings.getScreenWidth()/128),Settings.getScreenWidth()/20,Settings.getScreenWidth()/20);
            }
            if(dialogCloud.getState()!=5&&dialogCloud.getState()!=2&&dialogCloud.getState()!=4) {
                for (int i = 0; i < 4; i++) {
                    buttonList.get(i).drawText(g);
                    buttonList.get(i).drawHighlight();
                }
            }

        }

        public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input=gc.getInput();
        input.disableKeyRepeat();
        bossAnimation.update(delta);
        heroAnimation.update(delta);
        bossShieldAnimation.update(delta);
        bossDyeingAnimation.update(delta);
        if(heroAnimation.getFrame()==1&&projectile!=null&&!projectile.isModified()){
            projectile.setNewStartingPosition(projectile.getCurrentX(),projectile.getCurrentY()-Settings.getScreenWidth()/10);
        }
        if(heroAnimation.getFrame()==2&projectile!=null){
            projectile.start();
        }
        if(!gameWon) {
            if(projectile!=null&&!projectile.isAtTarget()){
                projectile.update(delta);
            }
            if(projectile!=null&&projectile.isAtTarget()&&bossHp==0){
                gameWon=true;
            }
            if(time>3000&&((dialogCloud.getState()==2)||dialogCloud.getState()==3||dialogCloud.getState()==5)){
                dialogCloud.setState(1);
                bossIsHit=false;
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
            if(time>3000) {
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
                if (((input.isKeyPressed(Input.KEY_ENTER)) || input.isMousePressed(0))) {
                    if (buttonList.get(selectedPosition).isTheAnswerRight()) {
                        bossHp--;
                        numberQuestionsAnswered++;
                        questionAnswered = true;
                        heartAnimation.update(delta);
                        heartAnimation.restart();
                        heroAnimation.restart();
                        projectile = new Projectile(Settings.getScreenWidth()/10, tabPos-Settings.getScreenWidth()/20, Settings.getScreenWidth() / 2 + Settings.getScreenWidth() / 4 - Settings.getScreenWidth() / 10, Settings.getScreenHeight() / 2 + (float) 0.14 * Settings.getScreenHeight() - (float) 0.5 * Settings.getScreenHeight(), Settings.getScreenWidth()/10, Settings.getScreenWidth()/10, 500, question.getOperation());
                        bossIsHit = true;
                    } else {
                        bossShieldAnimation.restart();
                        dialogCloud.setState(3);
                        heroAnimation.restart();
                        projectile = new Projectile(Settings.getScreenWidth()/10, tabPos-Settings.getScreenWidth()/20, Settings.getScreenWidth() / 2 + Settings.getScreenWidth() / 4 - Settings.getScreenWidth() / 10, Settings.getScreenHeight() / 2 + (float) 0.14 * Settings.getScreenHeight() - (float) 0.5 * Settings.getScreenHeight(), Settings.getScreenWidth()/10, Settings.getScreenWidth()/10, 500, question.getOperation());
                        time = 0;
                    }
                }
                for (int i = 0; i < 4; i++) {
                    if (buttonList.get(i).isHovered() && !buttonList.get(i).isSelected()) {
                        for (int l = 0; l < 4; l++) {
                            if (buttonList.get(l).isSelected()) {
                                buttonList.get(l).setSelected(false);
                            }
                        }
                        buttonList.get(i).setSelected(true);
                        selectedPosition = i;
                    }
                }
            }
            if(time!=Integer.MAX_VALUE) {
                time += delta;
            }
        }else {
            time+=delta;
            if(bossDyeingAnimation.getFrame()==0){
                bossDyeingAnimation.start();
            }
            if(time>3000){
                if(dialogCloud.getState()!=4) {
                    dialogCloud.setState(4);
                }
                if(input.isKeyPressed(Input.KEY_ESCAPE)){
                    sbg.enterState(0);
                }
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

