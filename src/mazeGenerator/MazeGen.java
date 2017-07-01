package mazeGenerator;
import dungeon.CellState;
public abstract class MazeGen {

    protected boolean finished;
    protected CellState[][] maze;

    public CellState[][] getMaze(){
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

    public void carve(Point p){
        maze[p.y][p.x] = CellState.OPEN;
    }

    public abstract void nextStep();

}

class Point{
    int x;
    int y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}