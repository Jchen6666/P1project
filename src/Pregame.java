import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.Image;

import java.util.ArrayList;


/**
 * This class is used to show the leaderboards screen
 */
public class Pregame extends BasicGameState {
    Image menuMenuBackground;
    Image background;
    Button backButton;
    Button playButton;
    Button advancedButton;
    Button messageButton;
    Button applyButton;
    ArrayList<Button> leftChangeButtons;
    ArrayList<Button> rightChangeButtons;
    int currentBottomSpan;
    int currentTopSpan;
    int time;
    int[][] currentSpan;
    int selectedOperation;
    ArrayList<Button> types;
    ArrayList<Button> typesSettings;
    DifficultyButton difficultyButton;
    DifficultyButton difficultyButtonSettings;
    String message,advancedMessage,confirmMessage;
    int messageWidth;
    boolean advanced;
    boolean typeSet;
    public Pregame(int state) {
    }

    /**
     * initialize images and objects
     * @param gc gamecontainer object passed from the gamestate at runtime
     * @param sbg StateBasedGame object
     * @throws SlickException A generic exception thrown by everything in the library
     */
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        menuMenuBackground=new Image("lib/res/img/menuMenuBackground.png");
        message="Choose types of questions";
        confirmMessage="Changes saved";
        advancedMessage="Change the span of numbers";
        leftChangeButtons=new ArrayList<>(2);
        rightChangeButtons=new ArrayList<>(2);
        messageWidth=OurFonts.getFont18().getWidth(message);
        backButton=new Button((Settings.getScreenWidth()-Settings.getScreenWidth()/8)/2,Settings.getScreenHeight()/9*6,Settings.getScreenWidth()/8,Settings.getScreenWidth()/16,new Image("/lib/res/img/menuButtons.png").getSubImage(128,0,64,32));
        backButton.setHoveredTexture(new Image("lib/res/img/hoveredMenuButtons.png").getSubImage(128,0,64,32));
        advancedButton=new Button((Settings.getScreenWidth()/2),Settings.getScreenHeight()/9*5,Settings.getScreenWidth()/8,Settings.getScreenWidth()/16,new Image("/lib/res/img/menuButtons.png").getSubImage(0,32,64,32));
        advancedButton.setHoveredTexture(new Image("lib/res/img/hoveredMenuButtons.png").getSubImage(0,32,64,32));
        playButton=new Button((Settings.getScreenWidth()/2-Settings.getScreenWidth()/8),Settings.getScreenHeight()/9*5,Settings.getScreenWidth()/8,Settings.getScreenWidth()/16,new Image("/lib/res/img/menuButtons.png").getSubImage(0,0,64,32));
        playButton.setHoveredTexture(new Image("lib/res/img/hoveredMenuButtons.png").getSubImage(0,0,64,32));
        background=new Image("/lib/res/img/menuBackground.png");
        messageButton=new Button((Settings.getScreenWidth()-(messageWidth*(float)1.5))/2,Settings.getScreenHeight()/9*2,messageWidth*(float)1.5,Settings.getScreenWidth()/16,new Image("lib/res/img/dialogCloud.png"),message);
        difficultyButton=new DifficultyButton((Settings.getScreenWidth()-Settings.getScreenWidth()/8)/2,Settings.getScreenHeight()/9*4,Settings.getScreenWidth()/8,Settings.getScreenWidth()/16,new Image("lib/res/img/difficultyButton.png"));
        difficultyButtonSettings=new DifficultyButton((Settings.getScreenWidth()/2),Settings.getScreenHeight()/9*5,Settings.getScreenWidth()/8,Settings.getScreenWidth()/16,new Image("lib/res/img/difficultyButton.png"));
        applyButton=new Button((Settings.getScreenWidth()/2-Settings.getScreenWidth()/8),Settings.getScreenHeight()/9*5,Settings.getScreenWidth()/8,Settings.getScreenWidth()/16,new Image("/lib/res/img/menuButtons.png").getSubImage(128,32,64,32));
        applyButton.setHoveredTexture(new Image("lib/res/img/hoveredMenuButtons.png").getSubImage(128,32,64,32));
        for(int i=0;i<2;i++){
            leftChangeButtons.add(new Button(Settings.getScreenWidth()/2-150+200*i,Settings.getScreenHeight()/9*4+10,26,26,new Image("lib/res/img/changeButton.png").getSubImage(32,0,32,32).getFlippedCopy(true,false)));
        }
        for(int i=0;i<2;i++){
            rightChangeButtons.add(new Button(Settings.getScreenWidth()/2-50+200*i,Settings.getScreenHeight()/9*4+10,26,26,new Image("lib/res/img/changeButton.png").getSubImage(32,0,32,32)));
        }
        advanced=false;
        types=new ArrayList<>(5);
        typesSettings=new ArrayList<>(4);
        for(int i=0;i<5;i++){
            types.add(new Button((Settings.getScreenWidth()-Settings.getScreenHeight()/9)/2+(i-2)*Settings.getScreenHeight()/9,Settings.getScreenHeight()/9*3,Settings.getScreenHeight()/9,Settings.getScreenHeight()/9,new Image("lib/res/img/projectiles.png").getSubImage(i*64,0,64,64)));
        }
        for(int i=0;i<4;i++){
            typesSettings.add(new Button((Settings.getScreenWidth()-Settings.getScreenHeight()/9)/2+(i-2)*Settings.getScreenHeight()/9,Settings.getScreenHeight()/9*3,Settings.getScreenHeight()/9,Settings.getScreenHeight()/9,new Image("lib/res/img/projectiles.png").getSubImage(i*64,0,64,64)));
        }
        typesSettings.get(0).setSelected(true);
        currentBottomSpan=Settings.getEasySpan()[0][0];
        currentTopSpan=Settings.getEasySpan()[0][1];
        selectedOperation=0;
        types.get(4).setSelected(true);
        typeSet=false;
        time=0;
    }
    /**
     * draw the background, buttons and the statistics
     * @param gc gamecontainer object passed from the gamestate at runtime
     * @param sbg StateBasedGame object
     * @param g  Graphics object from game state at run time
     * @throws SlickException A generic exception thrown by everything in the library
     */
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.black);
        g.setFont(OurFonts.getFont18());
        background.draw(0,0,Settings.getScreenWidth(),Settings.getScreenHeight());
        menuMenuBackground.draw((Settings.getScreenWidth()-menuMenuBackground.getWidth())/2,Settings.getScreenHeight()/9,menuMenuBackground.getWidth(),Settings.getScreenHeight()/9*7);
        if(!advanced){
            for(int i=0;i<5;i++){
                types.get(i).draw();
                types.get(i).drawHighlight();
            }
            if(advancedButton.isSelected()){
                advancedButton.drawHovered();
            }else{
                advancedButton.draw();
            }
            if(playButton.isSelected()){
                playButton.drawHovered();
            }else{
                playButton.draw();
            }
            difficultyButton.draw();
        }else{
            //if advanced options on
            for(int i=0;i<2;i++){
                leftChangeButtons.get(i).draw();
                rightChangeButtons.get(i).draw();
            }
            if(applyButton.isSelected()) {
                applyButton.drawHovered();
            }else{
                applyButton.draw();
            }
            difficultyButtonSettings.draw();
            for(int i=0;i<4;i++){
                typesSettings.get(i).draw();
                typesSettings.get(i).drawHighlight();
            }
            OurFonts.getFont26B().drawString((Settings.getScreenWidth()/2-100-OurFonts.getFont26B().getWidth(Integer.toString(currentBottomSpan))),Settings.getScreenHeight()/9*4+10,Integer.toString(currentBottomSpan),Color.black);
            OurFonts.getFont26B().drawString(Settings.getScreenWidth()/2+100,Settings.getScreenHeight()/9*4+10,Integer.toString(currentTopSpan),Color.black);
        }
        messageButton.draw();
        messageButton.drawText(g);
        if(backButton.isSelected()){
            backButton.drawHovered();
        }else{
            backButton.draw();
        }
    }
    /**
     * handles user's interaction with buttons
     * @param gc gamecontainer object passed from the gamestate at runtime
     * @param sbg StateBasedGame object
     * @param delta amount of time that passed from a last call of game state's method update(in milliseconds)
     */
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        time+=delta;
        Input input = gc.getInput();
        input.disableKeyRepeat();
        if(backButton.isHovered(input)){
            backButton.setSelected(true);
        }else{
            backButton.setSelected(false);
        }
        if(backButton.isClicked(input)){
            if(!advanced) {
                sbg.enterState(0);
            }else{
                advanced=false;
            }
        }
        if(!advanced) {
            for(int i=0;i<5;i++){
                if(types.get(i).isClicked(input)){
                    if(!types.get(i).isSelected()){
                        types.get(i).setSelected(true);
                        for(int k=0;k<5;k++){
                            if(k!=i){
                                types.get(k).setSelected(false);
                            }
                        }
                    }
                }
            }
            if (difficultyButton.isClicked(input)) {
                difficultyButton.update();
                Settings.setDifficulty(difficultyButton.getState());
            }
            if (advancedButton.isHovered(input)) {
                advancedButton.setSelected(true);
            } else {
                advancedButton.setSelected(false);
            }
            if (advancedButton.isClicked(input)) {
                advanced = true;
                messageButton.setText(advancedMessage);
            }
            if (playButton.isHovered(input)) {
                playButton.setSelected(true);
            } else {
                playButton.setSelected(false);
            }
            if (playButton.isClicked(input)) {
                for(int i=0;i<5;i++){
                    if(types.get(i).isSelected()){
                        if(i!=4) {
                            Settings.setOperationsMode(i+1);
                        }else{
                            Settings.setOperationsMode(0);
                        }
                    }
                }
                sbg.getState(1).init(gc, sbg);
                sbg.enterState(1);
            }
        }else{
            //if advanced options on
            if(time>2000&&messageButton.getText().equals(confirmMessage)){
                messageButton.setText(advancedMessage);
            }
            for(int i=0;i<2;i++){
                if(leftChangeButtons.get(i).isClicked(input)){
                    if(i==0){
                        currentBottomSpan--;
                    }else{
                        currentTopSpan--;
                    }
                }
                if(rightChangeButtons.get(i).isClicked(input)){
                    if(i==0){
                        currentBottomSpan++;
                    }else{
                        currentTopSpan++;
                    }
                }
            }
            for(int i=0;i<4;i++){
                if(typesSettings.get(i).isSelected()){
                    if(!typeSet) {
                        typeSet=true;
                        switch (difficultyButtonSettings.getState()) {
                            case 0:
                                currentBottomSpan = Settings.getEasySpan()[i][0];
                                currentTopSpan = Settings.getEasySpan()[i][1];
                                break;
                            case 1:
                                currentBottomSpan = Settings.getNormalSpan()[i][0];
                                currentTopSpan = Settings.getNormalSpan()[i][1];
                                break;
                            case 2:
                                currentBottomSpan = Settings.getHardSpan()[0][0];
                                currentTopSpan = Settings.getHardSpan()[i][1];
                                break;
                        }
                    }
                }
            }
            for(int i=0;i<4;i++){
                if(typesSettings.get(i).isClicked(input)){
                    if(!typesSettings.get(i).isSelected()){
                        typesSettings.get(i).setSelected(true);
                        selectedOperation=i;
                        typeSet=false;
                        for(int k=0;k<4;k++){
                            if(k!=i){
                                typesSettings.get(k).setSelected(false);
                            }
                        }
                    }
                }
            }
            if(applyButton.isHovered(input)){
                applyButton.setSelected(true);
            }else{
                if(applyButton.isSelected()){
                    applyButton.setSelected(false);
                }
            }
            if(applyButton.isClicked(input)){
                switch(difficultyButtonSettings.getState()){
                    case 0: currentSpan=Settings.getEasySpan();
                        currentSpan[selectedOperation][0]=currentBottomSpan;
                        currentSpan[selectedOperation][1]=currentTopSpan;
                        Settings.setEasySpan(currentSpan);
                        break;
                    case 1: currentSpan=Settings.getNormalSpan();
                        currentSpan[selectedOperation][0]=currentBottomSpan;
                        currentSpan[selectedOperation][1]=currentTopSpan;
                        Settings.setNormalSpan(currentSpan);
                        break;
                    case 2: currentSpan=Settings.getHardSpan();
                        currentSpan[selectedOperation][0]=currentBottomSpan;
                        currentSpan[selectedOperation][1]=currentTopSpan;
                        Settings.setHardSpan(currentSpan);
                        break;
                }
                messageButton.setText(confirmMessage);
                time=0;
            }
            if (difficultyButtonSettings.isClicked(input)) {
                difficultyButtonSettings.update();
                for(int i=0;i<4;i++){
                    if(typesSettings.get(i).isSelected()){
                            switch (difficultyButtonSettings.getState()) {
                                case 0:
                                    currentBottomSpan = Settings.getEasySpan()[i][0];
                                    currentTopSpan = Settings.getEasySpan()[i][1];
                                    break;
                                case 1:
                                    currentBottomSpan = Settings.getNormalSpan()[i][0];
                                    currentTopSpan = Settings.getNormalSpan()[i][1];
                                    break;
                                case 2:
                                    currentBottomSpan = Settings.getHardSpan()[0][0];
                                    currentTopSpan = Settings.getHardSpan()[i][1];
                                    break;
                            }
                    }
                }
            }

        }
    }
    public int getID() { return 6; }

}
