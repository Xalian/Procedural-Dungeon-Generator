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

    protected void carve(Point p, CellState type){
        map[p.y][p.x] = type;
    }

    protected void carveRoom(Room room){
        Point nw = room.getNorthWestCorner();
        Point se = room.getSouthEastCorner();

        for(int curX = nw.x; curX < se.x + 1; curX++){
            for(int curY = nw.y; curY < se.y + 1; curY++){
                carve(new Point(curX,curY), CellState.ROOM);
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
            carve(new Point(curX,nw.y), CellState.ROOM_BORDER);
            carve(new Point(curX,se.y), CellState.ROOM_BORDER);
        }
        for(int curY = nw.y; curY < se.y + 1; curY++){
            carve(new Point(nw.x,curY), CellState.ROOM_BORDER);
            carve(new Point(se.x,curY), CellState.ROOM_BORDER);
        }
    }

    protected boolean intersects(Room room){
        Point nw = room.getNorthWestCorner();
        Point se = room.getSouthEastCorner();
        if(nw.x-1 <= 0 || nw.y-1<= 0){
            return true;
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
        return false;
    }

    public abstract void nextStep();
}
