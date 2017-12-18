public class StopWatch {

    private final long nsPerTick = 1000000;
    private final long nsPerMs = 1000000;
    private final long nsPerSs = 1000000000;
    private final long nsPerMm = 60000000000L;
    private final long nsPerHh = 3600000000000L;
    private long hour,minute,second;
    private long startTime = 0;
    private long stopTime = 0;
    private boolean running = false;

    public void start(){
        this.startTime=System.nanoTime();
        this.running=true;
    }
    public void stop(){
        this.stopTime=System.nanoTime();
        this.running=false;
    }
    public void reset(){
        this.startTime=0;
        this.stopTime=0;
        this.running=false;
    }
    public long getElapsedTicks(){
        long elapsed;
        if (running){
            elapsed=(System.nanoTime()-startTime);
        }
        else {
            elapsed=(stopTime-startTime);
        }
        return elapsed/nsPerTick;
    }
    public long getElapsedMilliseconds() {
        long elapsed;
        if (running) {
            elapsed = (System.nanoTime() - startTime);
        }
        else {
            elapsed = (stopTime - startTime);
        }
        return elapsed / nsPerMs;
    }
    public long getElapsedSeconds() {
        long elapsed;
        long seconds;
        if (running) {
            elapsed = (System.nanoTime() - startTime);
        }
        else {
            elapsed = (stopTime - startTime);
        }

        return elapsed / nsPerSs-getElapsedMinutes()*60;
    }
    public long getElapsedMinutes() {
        long elapsed;
        if (running) {
            elapsed = (System.nanoTime() - startTime);
        }
        else {
            elapsed = (stopTime - startTime);
        }

        return elapsed / nsPerMm-getElapsedHours()*60;
    }
    public long getElapsedHours() {
        long elapsed;
        if (running) {
            elapsed = (System.nanoTime() - startTime);
        }
        else {
            elapsed = (stopTime - startTime);
        }
        return elapsed / nsPerHh;
    }
    @Override
    public String toString(){
       return getElapsedHours()+" h "+getElapsedMinutes()+" min "+getElapsedSeconds()+" sec ";
    }
}
