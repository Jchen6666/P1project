import java.util.Scanner;

public class hello {


    public static void main(String[] args) {
        Scanner bubi = new Scanner(System.in);

        String sentence = "Welcome to Java!";
        int number = 0;
        number = bubi.nextInt();

        for (int i = 0; i < number; i++) {
            System.out.println(sentence);

        }

    }

}
