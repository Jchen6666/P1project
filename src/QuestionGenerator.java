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
    private int operation;  //1-4 where 1- addition, 2- subtraction, 3- multiplication, 4- division
    private final SecureRandom generator=new SecureRandom();    //SecureRandom generator object, do not touch!
    private int questionNumber;
    private ArrayList<Integer> wrongAnswers;

    //

    /**
     * Constructor initializing all of the initial values and generating a question
     */
    public QuestionGenerator(){
        questionNumber=0;
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
    public void regenerate(){
        wrongAnswers=new ArrayList<Integer>(0);
        if(questionNumber<10){
            operation=generator.nextInt(4)+1;   //Generating the operation
            switch(operation){      //Easy levels
                case 1: firstNumber=generator.nextInt(20)+1;
                        secondNumber=generator.nextInt(20)+1;
                        break;
                case 2: do {
                    firstNumber = generator.nextInt(20) + 1;
                    secondNumber = generator.nextInt(20) + 1;
                }while(firstNumber<secondNumber);
                        break;
                case 3: firstNumber=generator.nextInt(9)+1;
                        secondNumber=generator.nextInt(9)+1;
                        break;
                case 4: secondNumber=generator.nextInt(6)+1;
                        firstNumber=secondNumber*(generator.nextInt(10)+1);
                        break;
            }
        }else{                      //Hard levels
            operation=generator.nextInt(4)+1;
            switch(operation){
                case 1: firstNumber=generator.nextInt(50)+1;
                    secondNumber=generator.nextInt(50)+1;
                    break;
                case 2: do {
                    firstNumber = generator.nextInt(30) + 1;
                    secondNumber = generator.nextInt(30) + 1;
                }while(firstNumber<secondNumber);
                    break;
                case 3: firstNumber=generator.nextInt(15)+1;
                    secondNumber=generator.nextInt(15)+1;
                    break;
                case 4: secondNumber=generator.nextInt(10)+1;
                    firstNumber=secondNumber*(generator.nextInt(10)+1);
                    break;
            }
        }
        calculateRightAnswer();
        questionNumber++;
    }   //Method generating all the variables

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
    }   //Returns the content of the question ex. " 2x2=? "

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
    public void reset(){
        questionNumber=0;
    }   //Resets the number of questions (resets the game)

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
    public int getQuestionNumber() {
        return questionNumber;
    }
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }
}