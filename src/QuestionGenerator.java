import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * This class is used to generate:
 * question
 * right answer
 * wrong answers
 */
public class QuestionGenerator{
    private int firstNumber;    //First number in equation
    private int secondNumber;   //Second number in equation
    private int rightAnswer; //Right answer
    private int operation;  //0-4 where 0-random 1- addition, 2- subtraction, 3- multiplication, 4- division
    private final SecureRandom generator=new SecureRandom();    //SecureRandom generator object, do not touch!
    private int operationsMode;
    private int difficulty; //0-easy,1-normal,2-hard
    private int[][] easySpan;
    private int[][] normalSpan;
    private int[][] hardSpan;
    private ArrayList<Integer> wrongAnswers;

    //

    public int getOperationsMode() {
        return operationsMode;
    }

    public void setOperationsMode(int operationsMode) {
        this.operationsMode = operationsMode;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Constructor initializing all of the initial values and generating a question
     */
    public QuestionGenerator(){
        update();
        regenerate();

    }   //Constructor generating all the variables

    /**
     * Generates an unique integer answer different from the right one by adding a number from a span of -5 (included) to 5(excluded) to it
     * @return  Wrong answer of type integer
     */
    public int generateWrongAnswer(){
        int wrongAnswer=rightAnswer+generator.nextInt(10)-5;
        while(wrongAnswer==rightAnswer||wrongAnswers.contains(wrongAnswer)){
            wrongAnswer=rightAnswer+generator.nextInt(10)-5;
        }
        wrongAnswers.add(wrongAnswer);
        return wrongAnswer;
    }   //Generates the wrong answer

    /**
     * clears an arraylist of previously generated wrong answers
     */
    public void clearWrongAnswers(){
        wrongAnswers.clear();
    }
    /**
     * Generates the question, operation sign and calculates the right answer
     */
    public void regenerate() {
        wrongAnswers = new ArrayList<Integer>(0);
        operation = operationsMode;
        int[][] currentSpan;
        if (operation == 0) {
            operation = generator.nextInt(4) + 1;   //Generating the operation
        }
        switch(difficulty){
            case 0: currentSpan=easySpan;
            break;
            case 1: currentSpan=normalSpan;
            break;
            case 2: currentSpan=hardSpan;
            break;
            default: currentSpan=null;
            break;
        }
        int delta=currentSpan[operation-1][1]-currentSpan[operation-1][0];
        switch(operation){
                case 1:
                    firstNumber = generator.nextInt(delta) + currentSpan[operation-1][0];
                    secondNumber = generator.nextInt(delta) + currentSpan[operation-1][0];
                    break;
                case 2:
                    do {
                        firstNumber = generator.nextInt(delta) + currentSpan[operation-1][0];
                        secondNumber = generator.nextInt(delta) + currentSpan[operation-1][0];
                    } while (firstNumber <= secondNumber);
                    break;
                case 3:
                    firstNumber = generator.nextInt(delta) + currentSpan[operation-1][0];
                    secondNumber = generator.nextInt(delta) + currentSpan[operation-1][0];
                    break;
                case 4:
                    secondNumber = generator.nextInt(delta) + currentSpan[operation-1][0];
                    firstNumber = secondNumber * (generator.nextInt(10) + 1);
                    break;
            }
            calculateRightAnswer();
    }//Method generating all the variables

    public int[][] getEasySpan() {
        return easySpan;
    }

    /**
     * Returns the content of the question as a string ex. "2x2=?"
     * @return  Question of type String
     */
    @Override
    public String toString(){
        String sign="";
        switch(operation){
            case 1:
                sign="+";
                break;
            case 2:
                sign="-";
                break;
            case 3:
                sign="x";
                break;
            case 4:
                sign="/";
                break;
        }
        String question=Integer.toString(firstNumber)+" "+sign+" "+Integer.toString(secondNumber)+" = ?";
        return question;
    }
    //Returns the content of the question ex. " 2x2=?

    /**
     * calculates the right answer
     */
    public void calculateRightAnswer(){
        switch(operation){
            case 1: rightAnswer=firstNumber+secondNumber;
                break;
            case 2: rightAnswer=firstNumber-secondNumber;
                break;
            case 3: rightAnswer=firstNumber*secondNumber;
                break;
            case 4: rightAnswer=firstNumber/secondNumber;
                break;
            default:
                break;
        }
    }   //Calculates the right answer

    /**
     * resets the number of answered questions
     */
    public void update(){
        easySpan=Settings.getEasySpan();
        normalSpan=Settings.getNormalSpan();
        hardSpan=Settings.getHardSpan();
        difficulty=Settings.getDifficulty();
        operationsMode=Settings.getOperationsMode();
    }
    //Getters
    public SecureRandom getGenerator() {
        return generator;
    }
    public int getFirstNumber() {
        return firstNumber;
    }
    public int getSecondNumber() {
        return secondNumber;
    }
    public int getOperation() {
        return operation;
    }
    public int getRightAnswer() {
        return rightAnswer;
    }
}