import dungeon.*;

import roomGenerator.*;

public class SimpleBruteRoomGeneratorDemo {
    public static void main(String args[]) {
        CellState[][] map = new CellState[40][40];
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                map[row][col] = CellState.WALL;
            }
        }
        RoomGenerator gen = new BruteRoomGenerator(map, 100);
        gen.completeRoomGen();

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
