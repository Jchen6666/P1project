import java.util.Scanner;

public class Main {
    public static void main(String[] argv){
        QuestionGenerator question=new QuestionGenerator();
        int wrongAnswer1,wrongAnswer2;
        for(int i=0;i<20;i++){
            if(question.getQuestionNumber()==10)    System.out.println("In this place starts the hard part:");
            System.out.println("Question number: "+question.getQuestionNumber());
            System.out.println("Question is: "+question.toString());
            System.out.println("The right answer is: "+question.getRightAnswer());
            System.out.println("The wrong answers are:");
            wrongAnswer1=question.generateWrongAnswer();
            System.out.println("1. "+wrongAnswer1);
            wrongAnswer2=question.generateWrongAnswer();
            while(wrongAnswer1==wrongAnswer2){
                wrongAnswer2=question.generateWrongAnswer();
            }
            System.out.println("2. "+wrongAnswer2);
            System.out.println();
            question.regenerate();
        }
    }
}
