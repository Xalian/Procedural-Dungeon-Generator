package dungeon;

public class Room{
    private Point nw;
    private Point se;

    public Room(Point nw, Point se){
        this.nw = nw;
        this.se = se;
    }

    public Point getNorthWestCorner(){
        return nw;
    }
    public Point getSouthEastCorner(){
        return se;
    }
}