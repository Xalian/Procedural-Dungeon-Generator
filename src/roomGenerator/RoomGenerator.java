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
        map[p.y][p.x] = CellState.ROOM;
    }
    protected void carveB(Point p){
        map[p.y][p.x] = CellState.ROOM_BORDER;
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
        Point nw = room.getNorthWestCorner();
        nw.x -= 1;
        nw.y -= 1;
        Point se = room.getSouthEastCorner();
        se.x += 1;
        se.y += 1;
        for(int curX = nw.x; curX < se.x + 1; curX++){
            carveB(new Point(curX,nw.y));
            carveB(new Point(curX,se.y));
        }
        for(int curY = nw.y; curY < se.y + 1; curY++){
            carveB(new Point(nw.x,curY));
            carveB(new Point(se.x,curY));
        }
    }

        }
        if(se.x+2 >= map[0].length || se.y+2 >= map.length){
            return true;
        }
        for(int curX = nw.x; curX < se.x + 1; curX++){
            for(int curY = nw.y; curY < se.y + 1; curY++){
                if(map[curY][curX]!= CellState.WALL){
                    return true;
                }
            }
        }
    }

    protected boolean intersects(Room room){
        //TODO
        return true;
    }

    public abstract void nextStep();
}
