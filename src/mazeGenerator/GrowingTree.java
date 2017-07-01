package mazeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GrowingTree extends MazeGen{

    private GTtype type;
    private List<Point> points;

    GrowingTree(int[][] maze, GTtype type, int x, int y){
        this.maze = maze;
        this.type = type;
        points = new ArrayList<>();
        points.add(new Point(x,y));
    }

    @Override
    public void nextStep() {
        Point current;
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


    }
}


class Point{
    int x;
    int y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

enum GTtype{
    RANDOM,ELDEST,NEWEST
}