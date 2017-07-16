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
        carveLine(nw,new Point(nw.x, se.y),CellState.ROOM_BORDER);
        carveLine(nw,new Point(nw.y, se.x),CellState.ROOM_BORDER);
        carveLine(se,new Point(nw.x, se.y),CellState.ROOM_BORDER);
        carveLine(se,new Point(nw.y, se.x),CellState.ROOM_BORDER);
        }

    private void carveLine(Point a, Point b, CellState type) {
        if(a.y == b.y) {
            if(a.x > b.x){
                int tmp = a.x;
                a.x = b.x;
                b.x = tmp;
            }
            int y = a.y;
            for(int x = a.x; x < b.x + 1; x++) {
                if (x >= 0 && x < map[y].length && y >= 0 && y < map.length) {
                    map[y][x] = type;
                }
            }
        }
        else{
            if(a.y > b.y){
                int tmp = a.y;
                a.y = b.y;
                b.y = tmp;
            }
            int x = a.x;
            for(int y = a.y; y < b.y + 1; y++) {
                if (x >= 0 && x < map[y].length && y >= 0 && y < map.length) {
                    map[y][x] = type;
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
