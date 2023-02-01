import java.util.ArrayList;

public class Tile {//1 by 1 square
    String name;
    int[] coordinates;
    String type;
    public Tile(String name,String type,int[] coordinates){
        this.name = name;
        this.coordinates = coordinates;
        this.type = type;
    }
    public String getName(){
        return name;
    }
    public int[] getCoordinates(){
        return coordinates;
    }
    public void setCoords(int[] newCoords1){
        this.coordinates = newCoords1;
    }
    public void move(String direction, Map map){//moves tile in a direction
        int[] newCoords = {};
        switch(direction){
            case "left":
                //System.out.println("moving "+direction);
                map.changeValue(coordinates[0],coordinates[1]-1, getName());
                map.changeValue(coordinates[0],coordinates[1], "");
                newCoords = new int[]{coordinates[0], coordinates[1] - 1};
                break;
            case "right":
                //System.out.println("moving "+direction);
                map.changeValue(coordinates[0],coordinates[1]+1, getName());
                map.changeValue(coordinates[0],coordinates[1],"");
                newCoords = new int[]{coordinates[0], coordinates[1] + 1};
                break;
            case "up":
                //System.out.println("moving "+direction);
                map.changeValue(coordinates[0]-1,coordinates[1], getName());
                map.changeValue(coordinates[0],coordinates[1], "");
                newCoords = new int[]{coordinates[0]-1, coordinates[1]};
                break;
            case "down":
                //System.out.println("moving "+direction);
                map.changeValue(coordinates[0]+1,coordinates[1], getName());
                map.changeValue(coordinates[0],coordinates[1], "");
                newCoords = new int[]{coordinates[0]+1, coordinates[1]};
                break;
        }
        setCoords(newCoords);
    }
    public ArrayList<String> getMoves(int i,ArrayList<String> moves,Map mainMap){//gets all possible moves for the tile (all moves not blocked)
        if(verticalCheck("up",mainMap)){
            moves.add(i+"up");
        }
        if(verticalCheck("down",mainMap)){
            moves.add(i+"down");
        }
        if(horizontalCheck("left",mainMap)){
            moves.add(i+"left");
        }
        if(horizontalCheck("right",mainMap)){
            moves.add(i+"right");
        }
        return moves;
    }

    public boolean horizontalCheck(String direction, Map mainMap){//checks if can move left/right
        //System.out.println("1b1");
        String[][] map = mainMap.getMap();
        switch(direction) {
            case "left":
                //System.out.println("checking "+direction);
                if (coordinates[1] - 1 >= 0 && map[coordinates[0]][coordinates[1] - 1] == "") {
                    return true;
                }
                break;
            case "right":
                //System.out.println("checking "+direction);
                if (coordinates[1] + 1 < 4 && map[coordinates[0]][coordinates[1] + 1] == "") {
                    return true;
                }
                break;
        }
        return false;
    }
    public boolean verticalCheck(String direction, Map mainMap){//checks if can move up/down
        String[][] map = mainMap.getMap();
        switch(direction) {
            case "up":
                //System.out.println("checking "+direction);
                if(coordinates[0]-1>=0&&map[coordinates[0]-1][coordinates[1]]==""){
                    return true;
                }
                break;
            case "down":
                //System.out.println("checking "+direction);
                if(coordinates[0]+1<=4&&map[coordinates[0]+1][coordinates[1]]==""){
                    return true;
                }
                break;
        }
        return false;
    }
    public void checkAndMove(String direction, Map mainMap){//checks if the tile can move in a particular direction. if it can, moves it
        switch (direction){
            case "up":
            case "down":
                if(verticalCheck(direction,mainMap)){
                    move(direction,mainMap);
                }
                break;
            case "left":
            case "right":
                if(horizontalCheck(direction,mainMap)){
                    move(direction,mainMap);
                }
                break;
        }
    }
}
class Tile1by2 extends Tile{//horizontal line
    Tile[] tiles;
    public Tile1by2(String name, String type,Tile[] tiles) {
        super(name, type,tiles[0].coordinates);
        this.tiles = tiles;
    }
    public Tile getTile(int index){
        return tiles[index];
    }

    @Override
    public void move(String direction, Map map) {
        if(direction=="right"){
            getTile(1).move(direction,map);
            getTile(0).move(direction,map);
        }else{
            getTile(0).move(direction,map);
            getTile(1).move(direction,map);
        }
    }

    @Override
    public boolean verticalCheck(String direction, Map mainMap) {
        return getTile(0).verticalCheck(direction,mainMap)&&getTile(1).verticalCheck(direction,mainMap);
    }
    @Override
    public boolean horizontalCheck(String direction, Map mainMap) {
        //System.out.println("1b2 no!");
        switch(direction) {
            case "left":
                return getTile(0).horizontalCheck(direction,mainMap);
            case "right":
                return getTile(1).horizontalCheck(direction,mainMap);
        }
        return false;
    }
}
class Tile2by1 extends Tile{//vertical line
    Tile[] tiles;
    public Tile2by1(String name, String type,Tile[] tiles) {
        super(name, type,tiles[0].getCoordinates());
        this.tiles = tiles;
    }
    public Tile getTile(int index){
        return tiles[index];
    }
    @Override
    public void move(String direction, Map map) {
        if(direction=="down"){
            getTile(1).move(direction,map);
            getTile(0).move(direction,map);
        }else{
            getTile(0).move(direction,map);
            getTile(1).move(direction,map);
        }
    }
    @Override
    public boolean horizontalCheck(String direction, Map mainMap) {
        if(getTile(0).horizontalCheck(direction, mainMap) && getTile(1).horizontalCheck(direction, mainMap)){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public boolean verticalCheck(String direction, Map mainMap) {
        switch(direction) {
            case "up":
                return getTile(0).verticalCheck(direction,mainMap);
            case "down":
                return getTile(1).verticalCheck(direction,mainMap);
        }
        return false;
    }
}
class Tile2by2 extends Tile{//big square
    Tile[] tiles;
    public Tile2by2(String name, String type,Tile[] tiles) {
        super(name, type,tiles[0].getCoordinates());
        this.tiles = tiles;
    }
    public Tile getTile(int index){
        return tiles[index];
    }
    @Override
    public void move(String direction, Map map) {
        switch(direction){
            case "up":
                getTile(0).move(direction,map);
                getTile(1).move(direction,map);
                getTile(2).move(direction,map);
                getTile(3).move(direction,map);
                break;
            case "down":
                getTile(2).move(direction,map);
                getTile(3).move(direction,map);
                getTile(0).move(direction,map);
                getTile(1).move(direction,map);
                break;
            case "left":
                getTile(0).move(direction,map);
                getTile(2).move(direction,map);
                getTile(1).move(direction,map);
                getTile(3).move(direction,map);
                break;
            case "right":
                getTile(1).move(direction,map);
                getTile(3).move(direction,map);
                getTile(0).move(direction,map);
                getTile(2).move(direction,map);
                break;
        }
    }
    @Override
    public boolean horizontalCheck(String direction, Map mainMap) {
        //System.out.println("2b2??");
        switch (direction) {
            case "left":
                return getTile(0).horizontalCheck(direction,mainMap)&&getTile(2).horizontalCheck(direction,mainMap);
            case "right":
                return getTile(1).horizontalCheck(direction,mainMap)&&getTile(3).horizontalCheck(direction,mainMap);
        }
        return false;
    }
    @Override
    public boolean verticalCheck(String direction, Map mainMap) {
        switch (direction) {
            case "up":
                return getTile(0).verticalCheck(direction,mainMap)&&getTile(1).verticalCheck(direction,mainMap);
            case "down":
                return getTile(2).verticalCheck(direction,mainMap)&&getTile(3).verticalCheck(direction,mainMap);
        }
        return false;
    }

}