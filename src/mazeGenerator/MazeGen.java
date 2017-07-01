package mazeGenerator;
import dungeon.*;
public abstract class MazeGen {

    protected boolean finished;
    protected CellState[][] map;

    public CellState[][] getMap(){
        return map;
    }

    public void completeMaze() {
        while(!this.finished) {
            this.nextStep();
        }
    }

    public boolean completed() {
        return finished;
    }

    protected void carve(Point p){
        map[p.y][p.x] = CellState.OPEN;
    }

    public abstract void nextStep();

}