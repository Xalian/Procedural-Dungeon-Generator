import dungeon.CellState;
import mazeGenerator.*;
public class SimpleGrowingTreeDemo {
    public static void main(String args[]){
        CellState[][] maze = new CellState[40][40];
        for(int row = 0; row < maze.length; row++){
            for(int col = 0; col <maze[row].length; col++){
                maze[row][col] = CellState.WALL;
            }
        }
        MazeGen gen =  new GrowingTree(maze,GTtype.NEWEST,1,1);
        gen.completeMaze();
        StringBuilder output = new StringBuilder();
        for(int row = 0; row < maze.length; row++){
            for(int col = 0; col <maze[row].length; col++){
                if(maze[row][col] == CellState.WALL){
                    output.append("W");
                }
                else if(maze[row][col] == CellState.OPEN){
                    output.append(".");
                }
                else if(maze[row][col] == CellState.ROOM_BORDER){
                    output.append("B");
                }
                else if(maze[row][col] == CellState.ROOM){
                    output.append("R");
                }
            }
            System.out.println(output.toString());
            output = new StringBuilder();
        }
    }
}