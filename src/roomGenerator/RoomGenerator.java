package roomGenerator;

import dungeon.*;


public abstract class RoomGenerator {
    protected boolean finished;
    protected CellState[][] map;

    public CellState[][] getMap(){
        return map;
    }

    public void completeRoomGen() {
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

    protected void carveRoom(Room room){
        Point nw = room.getNorthWestCorner();
        Point se = room.getSouthEastCorner();

        for(int curX = nw.x; curX < se.x + 1; curX++){
            for(int curY = nw.y; curY < se.y + 1; curY++){
                carve(new Point(curX,curY));
            }
        }
    }

    protected void carveBorder(Room room){
        //TODO
    }
    protected boolean intersects(Room room){
        //TODO
        return true;
    }

    public abstract void nextStep();
}

class Room{
    private Point nw;
    private Point se;

    Room(Point a,Point b,Point c,Point d){
        //TODO
    }

    public Point getNorthWestCorner(){
        return nw;
    }
    public Point getSouthEastCorner(){
        return se;
    }
}
