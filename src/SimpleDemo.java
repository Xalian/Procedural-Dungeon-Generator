import dungeon.CellState;
import roomGenerator.*;
import mazeGenerator.*;

public class SimpleDemo {
    public static void main(String args[]) {
        CellState[][] map = new CellState[40][40];
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                map[row][col] = CellState.WALL;
            }
        }
        RoomGenerator rGen = new BruteRoomGenerator(map, 100);
        rGen.completeRoomGen();

        //Try to make a new maze on every empty tile
        for(int row = 1; row < map.length - 1; row++){
            for(int col = 1; col <map[row].length - 1; col++){
                if(map[row][col] == CellState.WALL){
                    MazeGen mGen =  new GrowingTree(map, GTtype.NEWEST,col,row);
                    mGen.completeMaze();
                }
            }
        }

        //TODO: connect rooms
        //TODO: remove some dead ends

        StringBuilder output = new StringBuilder();
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] == CellState.WALL) {
                    output.append("W");
                } else if (map[row][col] == CellState.OPEN) {
                    output.append(".");
                } else if (map[row][col] == CellState.ROOM_BORDER) {
                    output.append("B");
                } else if (map[row][col] == CellState.ROOM) {
                    output.append("'");
                }
            }
            System.out.println(output.toString());
            output = new StringBuilder();
        }
    }
}
