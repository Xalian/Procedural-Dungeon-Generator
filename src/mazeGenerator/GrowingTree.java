package mazeGenerator;

import dungeon.CellState;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GrowingTree extends MazeGen{

    private GTtype type;
    private List<Point> points;

    GrowingTree(CellState[][] maze, GTtype type, int x, int y){
        this.maze = maze;
        this.type = type;
        Point start = new Point(x,y);
        points = new ArrayList<>();
        points.add(start);
        this.carve(start);
    }

    @Override
    public void nextStep() {
        Point current = null;
        switch (type) {
            case RANDOM:
                current = points.get(ThreadLocalRandom.current().nextInt(0, points.size()));
                break;
            case NEWEST:
                current = points.get(points.size()-1);
                break;
            case ELDEST:
                current = points.get(0);
                break;
        }

        //randomly select a valid direction and carve the next point
        List<Direction> dirs = this.getAllValidNeighbours(current);
        Point next = getPoint(current,dirs.get(ThreadLocalRandom.current().nextInt(0,dirs.size())));
        carve(next);
        if(dirs.size() == 1){
            points.remove(current);
        }
        points.add(next);
    }

    private Point getPoint(Point cur,Direction direction){
        int x = 0;
        int y = 0;
        switch (direction) {
            case N:
                x = cur.x;
                y = cur.y - 1;
                break;
            case E:
                x = cur.x + 1;
                y = cur.y;
                break;
            case S:
                x = cur.x;
                y = cur.y + 1;
                break;
            case W:
                x = cur.x - 1;
                y = cur.y;
                break;
        }
        return new Point(x,y);
    }

    private List<Direction> getAllValidNeighbours(Point cur){
        List<Direction> dirs = new ArrayList<>();
        if(cur.y > 1 && maze[cur.y - 1][cur.x] == CellState.WALL){
            dirs.add(Direction.N);
        }
        if(cur.y < maze.length && maze[cur.y + 1][cur.x] == CellState.WALL){
            dirs.add(Direction.S);
        }
        if(cur.x < maze[cur.y].length && maze[cur.y][cur.x + 1] == CellState.WALL){
            dirs.add(Direction.E);
        }
        if(cur.x > 1 && maze[cur.y][cur.x - 1] == CellState.WALL){
            dirs.add(Direction.W);
        }
        return dirs;
    }
}

enum GTtype{
    RANDOM,ELDEST,NEWEST
}

enum Direction{
    N,E,S,W
}
