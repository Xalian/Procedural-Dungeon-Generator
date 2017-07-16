package roomGenerator;

import dungeon.*;

import java.util.concurrent.ThreadLocalRandom;

public class BruteRoomGenerator extends RoomGenerator{

    private int attempts;
    public BruteRoomGenerator(CellState[][] map ,int attempts){
        this.attempts = attempts;
        this.map = map;
    }

    @Override
    public void nextStep() {
        boolean placed = false;
        while(!placed && !finished){
            attempts--;
            int x = ThreadLocalRandom.current().nextInt(0, map[0].length - 1);
            int y = ThreadLocalRandom.current().nextInt(0, map.length - 1);
            int height = ThreadLocalRandom.current().nextInt(3, 7);
            int width = ThreadLocalRandom.current().nextInt(3, 7);
            Point ne = new Point(x,y);
            Point sw = new Point(x+width,y+height);
            Room room = new Room(ne,sw);
            if(!intersects(room)){
                carveRoom(room);
                carveBorder(room);
                placed = true;
            }
            if(attempts == 0){
                finished = true;
            }
        }



    }

}
