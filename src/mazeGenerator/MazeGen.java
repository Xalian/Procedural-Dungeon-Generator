package mazeGenerator;
import dungeon.*;

import java.util.ArrayList;
import java.util.List;

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

    protected boolean disconnected(Point p){
        if ((p.y > 1) && (map[p.y - 1][p.x] != CellState.WALL) && (p.y > 1 && map[p.y - 1][p.x] != CellState.ROOM_BORDER)) {
            return false;
        }
        if ((p.y < map.length - 2) && (map[p.y + 1][p.x] != CellState.WALL) && (p.y < map.length - 2 && map[p.y + 1][p.x] != CellState.ROOM_BORDER)) {
            return false;
        }
        if ((p.x < map[p.y].length - 2) &&( map[p.y][p.x + 1] != CellState.WALL) && (p.x < map[p.y].length - 2 && map[p.y][p.x + 1] != CellState.ROOM_BORDER)) {
            return false;
        }
        if ((p.x > 1) && (map[p.y][p.x - 1] != CellState.WALL)&& (p.x > 1 && map[p.y][p.x - 1] != CellState.ROOM_BORDER)) {
            return false;
        }
        return true;
        }
}