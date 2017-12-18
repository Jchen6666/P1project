public abstract class Score {
    public static int score;
    public static int wrongAnswers;
    public static int timeElapsed;  //in milliseconds

    /**
     * Resets the score
     */
    public static void resetScore(){
        score=0;
        wrongAnswers=0;
        timeElapsed=0;
    }

    /**
     * Returns a string representation of time elapsed
     * @return
     */
    public static String timeToString(){
        int minutes=timeElapsed/60000;
        int seconds=(timeElapsed%60000)/1000;
        return Integer.toString(minutes)+" minutes "+Integer.toString(seconds)+" seconds.";
    }

    /**
     * Updates the time elapsed
     * @param delta parameter of init method which states for amount of time that passed from a last call of game state's init method (in milliseconds)
     */
    public static void update(int delta){
        timeElapsed+=delta;
    }
}
