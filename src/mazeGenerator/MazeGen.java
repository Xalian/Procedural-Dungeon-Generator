package mazeGenerator;

public abstract class MazeGen {

    protected boolean finished;
    protected int[][] maze;

    public int[][] getMaze(){
        return maze;
    }

    public void completeMaze() {
        while(!this.finished) {
            this.nextStep();
        }
    }

    public boolean completed() {
        return finished;
    }

    public abstract void nextStep();

}