public abstract class Score {
    public static int score;
    public static int wrongAnswers;
    public static int timeElapsed;  //in milliseconds

    public static void resetScore(){
        score=0;
        wrongAnswers=0;
        timeElapsed=0;
    }
    public static String timeToString(){
        int minutes=timeElapsed/60000;
        int seconds=(timeElapsed%60000)/1000;
        return Integer.toString(minutes)+" minutes "+Integer.toString(seconds)+" seconds.";
    }
    public static void update(int delta){
        timeElapsed+=delta;
    }
}
