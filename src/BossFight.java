import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.util.ArrayList;
import java.awt.Font;

/**
 * This class is used to implement and handle all the logic and rendering of the boss fight part of the game.
 */
public class BossFight extends BasicGameState {
        Image background;
        Image hpHeart;
        Image transparentPauseBackground;
        Image table;
        Image buttonsTable;
        Image bossInterface;
        Image platform;
        Image pauseButtons;
        Image pauseBackground;
        Image hoveredPauseButtons;
        Image menuMenuBackground;
        DialogCloud dialogCloud;
        Animation heroAnimation;
        Animation heartAnimation;
        Animation bossAnimation;
        Animation bossDyeingAnimation;
        Animation bossShieldAnimation;
        QuestionGenerator question;
        ArrayList<Button> buttonList;
        ArrayList<Button> pauseButtonList;
        SpriteSheet hearts;
        Projectile projectile;
        int rightAnswerPosition;
        int selectedPosition;
        boolean questionAnswered;
        boolean gameWon;
        boolean bossIsHit;
        boolean gamePaused;
        int numberQuestionsAnswered;
        int bossHp;   //Starting amount of boss lifes
        float tabPos; //variable helping to draw the interface
        int time;


        public BossFight(int state) {
            question=Play.question;
        }

    /**
     * initialize animations, images and objects
     * @param gc gamecontainer object passed from the gamestate at runtime
     * @param sbg StateBasedGame object
     * @throws SlickException A generic exception thrown by everything in the library
     */
        public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
            menuMenuBackground=new Image("lib/res/img/menuMenuBackground1.png");
        SpriteSheet hearts=new SpriteSheet("lib/res/img/heartsNew.png",16,16);
        pauseButtons=new Image("lib/res/img/menuButtons.png");
        pauseBackground=new Image("lib/res/img/menuBackground.png");
        hoveredPauseButtons=new Image("lib/res/img/hoveredMenuButtons.png");
        heartAnimation= new Animation(hearts,500);
        heartAnimation.setLooping(false);
        heartAnimation.stop();
        transparentPauseBackground=new Image("lib/res/img/transparentPauseBackground.png");
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
        buttonList=new ArrayList<>(4);
        rightAnswerPosition=(question.getGenerator().nextInt(4)+1);     //Determine the position of the right answer
        Button.setHighlight(new Image("lib/res/img/highlight.png"));
        buttonList=generateTheLevel(question,rightAnswerPosition,selectedPosition);     //Method returning the array of Buttons(4 of them);
        gameWon=false;
        dialogCloud=new DialogCloud((int)Settings.getScreenWidth()/8,(int)Settings.getScreenHeight()/9,(int)Settings.getScreenWidth()/2,(int)Settings.getScreenHeight()/4,new Image("lib/res/img/dialogCloud.png"),question.toString());
        bossHp=5;
        questionAnswered=false;
        bossIsHit=false;
        selectedPosition=0;
        tabPos=(4*Settings.getScreenHeight())/5;
        numberQuestionsAnswered=0;
        time=0;
        gamePaused=false;
        question.update();
        question.regenerate();
        }
    /**
     * Draw the background, buttons, animations, projectile, pause menu and health hearts on the screen
     * @param gc gamecontainer object passed from the gamestate at runtime
     * @param sbg StateBasedGame object
     * @param g  Graphics object from game state at run time
     * @throws SlickException A generic exception thrown by everything in the library
     */
        public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
                background.draw(0, 0, Settings.getScreenWidth(), Settings.getScreenHeight());
                platform.draw(-Settings.getScreenWidth() / 10, tabPos - Settings.getScreenHeight() / 7, Settings.getScreenWidth() / 2, (float) 0.28 * Settings.getScreenHeight());
                platform.draw(Settings.getScreenWidth() / 2, Settings.getScreenHeight() / 2, Settings.getScreenWidth() / 2, (float) 0.28 * Settings.getScreenHeight());
                heroAnimation.draw(0, tabPos - Settings.getScreenWidth() / 5, Settings.getScreenWidth() / 5, Settings.getScreenWidth() / 5);
                if (bossIsHit && ((time >= 1500 && time <= 1600) || (time >= 2000 && time <= 2100))) {
                    bossAnimation.drawFlash(Settings.getScreenWidth() / 2 + Settings.getScreenWidth() / 4 - Settings.getScreenWidth() / 10, Settings.getScreenHeight() / 2 + (float) 0.14 * Settings.getScreenHeight() - (float) 0.71 * Settings.getScreenHeight(), Settings.getScreenWidth() / 5, (float) 0.71 * Settings.getScreenHeight());
                } else {
                    if (gameWon && time > 3000) {
                        bossDyeingAnimation.draw(Settings.getScreenWidth() / 2 + Settings.getScreenWidth() / 4 - Settings.getScreenWidth() / 10, Settings.getScreenHeight() / 2 + (float) 0.14 * Settings.getScreenHeight() - (float) 0.71 * Settings.getScreenHeight(), Settings.getScreenWidth() / 5, (float) 0.71 * Settings.getScreenHeight());
                    } else {
                        if (dialogCloud.getState() == 3) {
                            bossShieldAnimation.draw(Settings.getScreenWidth() / 2 + Settings.getScreenWidth() / 4 - Settings.getScreenWidth() / 10, Settings.getScreenHeight() / 2 + (float) 0.14 * Settings.getScreenHeight() - (float) 0.71 * Settings.getScreenHeight(), Settings.getScreenWidth() / 5, (float) 0.71 * Settings.getScreenHeight());
                        } else {
                            bossAnimation.draw(Settings.getScreenWidth() / 2 + Settings.getScreenWidth() / 4 - Settings.getScreenWidth() / 10, Settings.getScreenHeight() / 2 + (float) 0.14 * Settings.getScreenHeight() - (float) 0.71 * Settings.getScreenHeight(), Settings.getScreenWidth() / 5, (float) 0.71 * Settings.getScreenHeight());
                        }
                    }
                }
                g.setColor(Color.black);
                if (Settings.getScreenWidth() > 1000) {
                    g.setFont(OurFonts.getFont18());
                } else {
                    g.setFont(OurFonts.getFont14());
                }
                dialogCloud.draw(g);
                g.setFont(OurFonts.getFont22B());
                if (projectile != null) {
                    if (!projectile.isAtTarget()) {
                        projectile.draw();
                    }
                    if (projectile.isAtTarget() && dialogCloud.getState() == 3 && bossShieldAnimation.getFrame() != 4) {
                        projectile.drawFlash();
                    }
                }
                for (int i = 0; i < Settings.getScreenWidth(); i++) {
                    table.draw(i, tabPos, table.getWidth(), Settings.getScreenHeight() - tabPos);
                }
                buttonsTable.draw(Settings.getScreenWidth() / 2, tabPos, Settings.getScreenWidth() / 2, Settings.getScreenHeight() - tabPos);


                for (int i = 0; i < bossHp; i++) {
                    hpHeart.draw(i * (Settings.getScreenWidth() / 20) + (Settings.getScreenWidth() / 128), (Settings.getScreenWidth() / 128), Settings.getScreenWidth() / 20, Settings.getScreenWidth() / 20);
                }
                if (numberQuestionsAnswered != 0) {
                    heartAnimation.draw((bossHp) * (Settings.getScreenWidth() / 20) + (Settings.getScreenWidth() / 128), (Settings.getScreenWidth() / 128), Settings.getScreenWidth() / 20, Settings.getScreenWidth() / 20);
                }
                if (dialogCloud.getState() != 5 && dialogCloud.getState() != 2 && dialogCloud.getState() != 4 && dialogCloud.getState()!=6) {
                    for (int i = 0; i < 4; i++) {
                        buttonList.get(i).drawText(g);
                        buttonList.get(i).drawHighlight();
                    }
                }
                if(gamePaused){
                    transparentPauseBackground.draw(0,0,Settings.getScreenWidth(),Settings.getScreenHeight());
                    menuMenuBackground.draw((Settings.getScreenWidth()-menuMenuBackground.getWidth())/2,Settings.getScreenHeight()/9,menuMenuBackground.getWidth(),Settings.getScreenHeight()/9*7);
                    OurFonts.getFont26B().drawString((Settings.getScreenWidth()-OurFonts.getFont26B().getWidth("Game Paused"))/2,Settings.getScreenHeight()/9*2,"Game Paused");
                    for(int i=0;i<pauseButtonList.size();i++){
                        if(pauseButtonList.get(i).isSelected()){
                            pauseButtonList.get(i).drawHovered();
                        }else{
                            pauseButtonList.get(i).draw();
                        }
                    }
                }
        }
    /**
     * This method updates the logic of the game before rendering it onto the screen
     * @param gc
     * @param sbg
     * @param delta
     * @throws SlickException
     */
        public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
            System.out.println("Operation mode: "+Settings.getOperationsMode()+" Difficulty: "+Settings.getDifficulty()+" [0][0] of easy span "+Settings.getEasySpan()[0][0]);
            System.out.println(question.getEasySpan()[0][0]);
            System.out.println(question.getOperationsMode());
            System.out.println(Settings.getOperationsMode());
            System.out.println(question.getDifficulty());
            System.out.println(Settings.getDifficulty());

        Input input=gc.getInput();  //object initialized to catch user's input
        input.disableKeyRepeat();
        if(!gamePaused) {           //if the game is not pause
            Score.update(delta);    //time elapsed is updated
            bossAnimation.update(delta);
            heroAnimation.update(delta);
            bossShieldAnimation.update(delta);
            bossDyeingAnimation.update(delta);
            //move the projectile to hero's hand
            if (heroAnimation.getFrame() == 1 && projectile != null && !projectile.isModified()) {
                projectile.setNewStartingPosition(projectile.getCurrentX(), projectile.getCurrentY() - Settings.getScreenWidth() / 10);
            }
            //throw the projectile if it's already in hero's hand
            if (heroAnimation.getFrame() == 2 & projectile != null) {
                projectile.start();
            }
            if (!gameWon) {         //if the game is not won
                //if the projectile is not already at target, move it
                if (projectile != null && !projectile.isAtTarget()) {
                    projectile.update(delta);
                }
                //if the projectile hits the boss and he has no life left
                if (projectile != null && projectile.isAtTarget() && bossHp <= 0) {
                    Play.sw.stop();                                                 //stops the timer
                    gameWon = true;
                }
                //if 3 seconds have passed and dialog cloud is not currently displaying the question
                if (time > 3000 && ((dialogCloud.getState() == 2) || dialogCloud.getState() == 3 || dialogCloud.getState() == 5)) {
                    dialogCloud.setState(1);    //display question of dialog cloud
                    bossIsHit = false;          //boss is no longer being hit
                }
                //if the question is answered correctly
                if (questionAnswered) {
                    time = 0;                   //reset the time variable measuring the time from a change of boss state
                    rightAnswerPosition = (question.getGenerator().nextInt(4) + 1); //generate the number of position (1-4) on which a new right answer will be
                    question.update();
                    question.regenerate();      //generate a new question
                    buttonList = generateTheLevel(question, rightAnswerPosition, selectedPosition);
                    questionAnswered = false;
                    dialogCloud.setQuestion(question.toString());   //set a new question for dialog cloud
                    dialogCloud.setState(2);                        //display congratulations message after answering right
                }
                //user's input is blocked for 3 seconds after every change of state of dialog cloud (after any action/animation)
                if (time > 3000) {
                    if (input.isKeyPressed(Input.KEY_DOWN)) {   //if arrow key down is pressed, select button below if there is one
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
                    if (input.isKeyPressed(Input.KEY_UP)) {    //if arrow key up is pressed, select button above if there is one
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
                    if (input.isKeyPressed(Input.KEY_LEFT)) {   //if arrow key left is pressed, select button to the left if there is one
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
                    if (input.isKeyPressed(Input.KEY_RIGHT)) {  //if arrow key left is pressed, select button on the left if there is one
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
                    //if the Enter key or the left mouse button is pressed
                    if (((input.isKeyPressed(Input.KEY_ENTER)) || input.isMousePressed(0))) {
                        //if the selected button has the right answer on it
                        if (buttonList.get(selectedPosition).isTheAnswerRight()) {
                            bossHp--;   //decrement boss's lives
                            Score.score++;  //increase score by 1
                            numberQuestionsAnswered++;  //increase a number of answered questions
                            questionAnswered = true;
                            heartAnimation.update(delta);   //health heart disappearing animation starts
                            heartAnimation.restart();
                            heroAnimation.restart();
                            //hero prepares to throw a projectile
                            projectile = new Projectile(Settings.getScreenWidth() / 10, tabPos - Settings.getScreenWidth() / 20, Settings.getScreenWidth() / 2 + Settings.getScreenWidth() / 4 - Settings.getScreenWidth() / 10, Settings.getScreenHeight() / 2 + (float) 0.14 * Settings.getScreenHeight() - (float) 0.5 * Settings.getScreenHeight(), Settings.getScreenWidth() / 10, Settings.getScreenWidth() / 10, 500, question.getOperation());
                            bossIsHit = true;
                        } else {    //if selected button has wrong answer on it
                            bossShieldAnimation.restart();  //start animation of boss shielding himself from projectile
                            dialogCloud.setState(3);        //display wrong answer message on dialog cloud
                            Score.wrongAnswers++;           //increment number of wrong answers
                            heroAnimation.restart();
                            //hero prepares to throw a projectile
                            projectile = new Projectile(Settings.getScreenWidth() / 10, tabPos - Settings.getScreenWidth() / 20, Settings.getScreenWidth() / 2 + Settings.getScreenWidth() / 4 - Settings.getScreenWidth() / 10, Settings.getScreenHeight() / 2 + (float) 0.14 * Settings.getScreenHeight() - (float) 0.5 * Settings.getScreenHeight(), Settings.getScreenWidth() / 10, Settings.getScreenWidth() / 10, 500, question.getOperation());
                            time = 0;
                        }
                    }
                    //selects the currently hovered button and unselects the ones that are not hovered on
                    for (int i = 0; i < 4; i++) {
                        if (buttonList.get(i).isHovered(input) && !buttonList.get(i).isSelected()) {
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
                //updates the time passed
                if (time != Integer.MAX_VALUE) {
                    time += delta;
                }
            } else {    //if the game is won
                time += delta;  //updates the time passed
                //if the animation of boss dyeing has not started yet, start it
                if (bossDyeingAnimation.getFrame() == 0) {
                    bossDyeingAnimation.start();
                }
                //if boss dyeing animation is over
                if(bossDyeingAnimation.isStopped()&&dialogCloud.getState()!=6){
                    dialogCloud.setState(6);    //display "press enter to proceed" message in dialog cloud
                }
                //if 3 seconds have passed
                if (time > 3000) {
                    //if the last projectile throw is over display boss defeater message in dialog cloud
                    if (dialogCloud.getState() != 4&&dialogCloud.getState()!=6) {
                        dialogCloud.setState(4);
                    }
                    //if the enter key is pressed, proceed to victory screen
                    if (input.isKeyPressed(Input.KEY_ENTER)) {
                        sbg.enterState(5);
                    }
                }
            }
            //if the escape key is pressed
            if(input.isKeyPressed(Input.KEY_ESCAPE)){
                gamePaused=true;    //pause the game
                pauseButtonList=new ArrayList<Button>(2);   //initialize buttons in pause menu
                for(int i=0;i<2;i++) {
                    pauseButtonList.add(new Button((Settings.getScreenWidth() - Settings.getScreenWidth() / 8) / 2, Settings.getScreenHeight() / 9 * (i+3), Settings.getScreenWidth() / 8, Settings.getScreenWidth() / 16, pauseButtons.getSubImage(128-64*i, 0, 64, 32)));
                    pauseButtonList.get(i).setHoveredTexture(hoveredPauseButtons.getSubImage(128-64*i,0,64,32));
                }
            }
        }else{              //if the game is paused
            //if the button is hovered, draw the hovered texture of it
            for(int i=0;i<2;i++){
                if(pauseButtonList!=null) {
                    if (pauseButtonList.get(i).isHovered(input)) {
                        pauseButtonList.get(i).setSelected(true);
                    } else {
                        pauseButtonList.get(i).setSelected(false);
                    }
                }
            }
            //if the escape key or back button is clicked
            if(input.isKeyPressed(Input.KEY_ESCAPE)||(pauseButtonList.get(0).isClicked(input)&&pauseButtonList!=null)){
                gamePaused=false;   //unpause the game
                pauseButtonList=null;   //clean the pause buttons list
            }
            //if the exit button is clicked
            if(pauseButtonList!=null&&pauseButtonList.get(1).isClicked(input)){
                gamePaused=false;
                pauseButtonList=null;
                sbg.enterState(0);  //exit to menu
            }
        }
        }
    /**
     * The purpose of this method is to generate a new set of buttons after generating a new question
     * This method generates and returns an array of buttons with predetermined position on the screen
     * Each of the buttons will have either a wrong answer or a right answer assigned as its text variable
     * The button at rightAnswerPosition of a list will be assigned with a rightAnswer as its text
     * The button at selectedPosition of a list will have its isSelected variable set to true
     * @param question current question
     * @param rightAnswerPosition position of a right answer button (1-4)
     * @param selectedPosition position of a previously selected button (1-4)
     * @return an array of buttons
     * @see Button
     */
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

