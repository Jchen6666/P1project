import java.security.SecureRandom;

public class QuestionGenerator{
    private int firstNumber;    //First number in equation
    private int secondNumber;   //Second number in equation
    private int rightAnswer; //Right answer
    private int operation;  //1-4 where 1- addition, 2- subtraction, 3- multiplication, 4- division
    private final SecureRandom generator=new SecureRandom();    //SecureRandom generator object, do not touch!
    private int questionNumber;

    //
    public QuestionGenerator(){
        questionNumber=0;
        regenerate();
    }   //Constructor generating all the variables
    public int generateWrongAnswer(){
        int wrongAnswer=rightAnswer+generator.nextInt(10)-5;
        while(wrongAnswer==rightAnswer){
            wrongAnswer=rightAnswer+generator.nextInt(10)-5;
        }
        return wrongAnswer;
    }   //Generates the wrong answer
    public void regenerate(){
        if(questionNumber<10){
            operation=generator.nextInt(4)+1;
            switch(operation){      //Easy levels
                case 1: firstNumber=generator.nextInt(10)+1;
                        secondNumber=generator.nextInt(40)+1;
                        break;
                case 2: firstNumber=generator.nextInt(50)+1;
                        secondNumber=generator.nextInt(50)+1;
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
                case 1: firstNumber=generator.nextInt(100)+11;
                    secondNumber=generator.nextInt(300)+11;
                    break;
                case 2: firstNumber=generator.nextInt(100)+1;
                    secondNumber=generator.nextInt(100)+1;
                    break;
                case 3: firstNumber=generator.nextInt(15)+1;
                    secondNumber=generator.nextInt(9)+1;
                    break;
                case 4: secondNumber=generator.nextInt(9)+1;
                    firstNumber=secondNumber*(generator.nextInt(10)+1);
                    break;
            }
        }
        calculateRightAnswer();
        questionNumber++;
    }   //Method generating all the variables
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
        String question=Integer.toString(firstNumber)+" "+sign+" "+Integer.toString(secondNumber)+" =?";
        return question;
    }   //Returns the content of the question ex. " 2x2=? "
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
    public void reset(){
        questionNumber=0;
    }   //Resets the number of questions (resets the game)

    //Getters
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
}