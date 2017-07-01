package mazeGenerator;

import dungeon.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GrowingTree extends MazeGen {

    private GTtype type;
    private List<Point> points;

    public GrowingTree(CellState[][] maze, GTtype type, int x, int y) {
        this.map = maze;
        this.type = type;
        Point start = new Point(x, y);
        points = new ArrayList<>();
        points.add(start);
        this.carve(start);
    }

    @Override
    public void nextStep() {
        Point cur = null;
        List<Direction> dirs = null;
        while(dirs == null ||dirs.isEmpty()){
            switch (type) {
                case RANDOM:
                    cur = points.get(ThreadLocalRandom.current().nextInt(0, points.size()));
                    break;
                case NEWEST:
                    cur = points.get(points.size() - 1);
                    break;
                case ELDEST:
                    cur = points.get(0);
                    break;
            }

            //randomly select a valid direction and carve the next point
            dirs = this.getAllValidNeighbours(cur);
            if (dirs.isEmpty()) {
                points.remove(cur);
                if(points.isEmpty()){
                    this.finished = true;
                    return;
                }
            }
        }
        Point next = getPoint(cur, dirs.get(ThreadLocalRandom.current().nextInt(0, dirs.size())));
        carve(next);
        points.add(next);
    }

    private Point getPoint(Point cur, Direction direction) {
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
        return new Point(x, y);
    }

    private List<Direction> getAllValidNeighbours(Point cur) {
        List<Direction> dirs = new ArrayList<>();
        List<Direction> res = new ArrayList<>();
        if (cur.y > 1 && map[cur.y - 1][cur.x] == CellState.WALL) {
            dirs.add(Direction.N);
        }
        if (cur.y < map.length - 2 && map[cur.y + 1][cur.x] == CellState.WALL) {
            dirs.add(Direction.S);
        }
        if (cur.x < map[cur.y].length - 2 && map[cur.y][cur.x + 1] == CellState.WALL) {
            dirs.add(Direction.E);
        }
        if (cur.x > 1 && map[cur.y][cur.x - 1] == CellState.WALL) {
            dirs.add(Direction.W);
        }
        for (Direction d : dirs) {
            if(!(pointGetNumOpenNeighbours(getPoint(cur,d)) > 1)){
                res.add(d);
            }
        }
        return res;
    }

    private int pointGetNumOpenNeighbours(Point point) {
        int open = 0;
        Point test;
        if (!(point.x == 0)) {
            test = getPoint(point, Direction.W);
            if (map[test.y][test.x] == CellState.OPEN) open++;
        }
        if (!(point.x == map[0].length - 1)) {
            test = getPoint(point, Direction.E);
            if (map[test.y][test.x] == CellState.OPEN) open++;
        }
        if (!(point.y == 0)) {
            test = getPoint(point, Direction.N);
            if (map[test.y][test.x] == CellState.OPEN) open++;
        }
        if (!(point.y == map.length -1 )) {
            test = getPoint(point, Direction.S);
            if (map[test.y][test.x] == CellState.OPEN) open++;
        }
        return open;
    }
}


