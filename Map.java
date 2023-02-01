import java.util.*;

public class Map {
    // 2-d array for map class to set up the map
    int rows = 5;
    int columns = 4;


    HashMap<String, Integer> bestMoves = new HashMap<>();
    //this hashmap stores all possible moves in the format TileNumberDirection, rating
    //for example, 0up, 9 would mean the moving tile 0 up has a rating of 9

    Tile[] tiles = new Tile[10];

    String[][] map_arr = new String[rows][columns];
    public Map(){
        fillArrayMap();
    }
    public String[][] getMap(){
        return map_arr;
    }

    public void updateHashMapWin(ArrayList<String> goodMoves,int moves){
        int increment;
        if(moves<100){
            increment = 20;//more points for winning in less than 100 moves, ideal
        }else{
            increment = 10;
        }
        for (int i = 0; i<goodMoves.size();i++){
            if(bestMoves.containsKey(goodMoves.get(i))){
                bestMoves.put(goodMoves.get(i),bestMoves.get(goodMoves.get(i))+increment);
            }else{
                bestMoves.put(goodMoves.get(i),1);
            }
        }
    }
    public void updateHashMapLose(ArrayList<String> goodMoves){
        for (int i = 0; i<goodMoves.size();i++){
            if(bestMoves.containsKey(goodMoves.get(i))){
                bestMoves.put(goodMoves.get(i),bestMoves.get(goodMoves.get(i))-1);//loses points for losing the match
            }else{
                bestMoves.put(goodMoves.get(i),0);
            }
        }
    }
    public String getBestMove(ArrayList<String> possibleMoves){
        int highestNum = -100;
        String bestMove = "";
        for(int i=0; i<possibleMoves.size();i++){//find best move by highest rating
            if(bestMoves.containsKey(possibleMoves.get(i))){
                if(bestMoves.get(possibleMoves.get(i))>=highestNum){
                    highestNum = bestMoves.get(possibleMoves.get(i));
                    bestMove = possibleMoves.get(i);
                }
            }
        }
        if(bestMove==""){//if no best move (ie its the first time playing) select random
            int randomNum = (int)(Math.random() * possibleMoves.size());
            bestMove = possibleMoves.get(randomNum);
        }
        return bestMove;
    }
    public void printHashMap(){
        for (String x:bestMoves.keySet()){
            System.out.println(x+": "+bestMoves.get(x));
        }
    }
    public void changeValue(int x, int y, String newValue){
        map_arr[x][y] = newValue;
    }
    public void practiceFillMap(){
        for(int y=0; y<rows; y++){
            for(int x=0; x<columns; x++){
                map_arr[y][x] = "";
            }
        }
        map_arr[2][1] = "Guan Yu";
        map_arr[2][2] = "Guan Yu";
        map_arr[2][0] = "Ma Chao";
        map_arr[3][0] = "Ma Chao";

        Tile a = new Tile("Guan Yu","1x1",new int[]{2,1});
        Tile b = new Tile("Guan Yu","1x1",new int[]{2,2});
        Tile1by2 guanyu = new Tile1by2("Guan Yu","1x2",new Tile[] {a,b});

        a = new Tile("Ma Chao","1x1",new int[]{2,0});
        b = new Tile("Ma Chao","1x1",new int[]{3,0});
        Tile2by1 machao = new Tile2by1("Ma Chao","2x1",new Tile[] {a,b});

        tiles[0] = guanyu;
        tiles[1] = machao;
    }
    public void fillArrayMap(){
        for(int y=0; y<rows; y++){
            for(int x=0; x<columns; x++){
                map_arr[y][x] = "";
            }
        }
        // int[][] map_arr =

        map_arr[2][1] = "Guan Yu";
        map_arr[2][2] = "Guan Yu";
        //Zhang Fei
        map_arr[2][3] = "Zhang Fei";
        map_arr[3][3] = "Zhang Fei";
        //Zhao You
        map_arr[0][3] = "Zhao Yun";
        map_arr[1][3] = "Zhao Yun";
        //Ma Chao
        map_arr[2][0] = "Ma Chao";
        map_arr[3][0] = "Ma Chao";

        map_arr[4][0] = "Zu";
        map_arr[4][3] = "Zu";
        map_arr[3][1] = "Zu";
        map_arr[3][2] = "Zu";

        map_arr[0][0] = "Huang Zhong";
        map_arr[1][0] = "Huang Zhong";

        map_arr[0][1] = "Cao Cao";
        map_arr[0][2] = "Cao Cao";
        map_arr[1][1] = "Cao Cao";
        map_arr[1][2] = "Cao Cao";

        Tile a = new Tile("Guan Yu","1x1",new int[]{2,1});
        Tile b = new Tile("Guan Yu","1x1",new int[]{2,2});
        Tile1by2 guanyu = new Tile1by2("Guan Yu","1x2",new Tile[] {a,b});

        a = new Tile("Zhang Fei","1x1",new int[]{2,3});
        b = new Tile("Zhang Fei","1x1",new int[]{3,3});
        Tile2by1 zhangfei = new Tile2by1("Zhang Fei","2x1",new Tile[] {a,b});

        a = new Tile("Zhao Yun","1x1",new int[]{0,3});
        b = new Tile("Zhao Yun","1x1",new int[]{1,3});
        Tile2by1 zhaoyun = new Tile2by1("Zhao Yun","2x1",new Tile[] {a,b});

        a = new Tile("Ma Chao","1x1",new int[]{2,0});
        b = new Tile("Ma Chao","1x1",new int[]{3,0});
        Tile2by1 machao = new Tile2by1("Ma Chao","2x1",new Tile[] {a,b});

        a = new Tile("Huang Zhong","1x1",new int[]{0,0});
        b = new Tile("Huang Zhong","1x1",new int[]{1,0});
        Tile2by1 huangzhong = new Tile2by1("Huang Zhong","2x1",new Tile[] {a,b});

        Tile zu1 = new Tile("Zu","1x1",new int[]{4,0});
        Tile zu2 = new Tile("Zu","1x1",new int[]{4,3});
        Tile zu3 = new Tile("Zu","1x1",new int[]{3,1});
        Tile zu4 = new Tile("Zu","1x1",new int[]{3,2});

        a = new Tile("Cao Cao","1x1",new int[]{0,1});
        b = new Tile("Cao Cao","1x1",new int[]{0,2});
        Tile c = new Tile("Cao Cao","1x1",new int[]{1,1});
        Tile d = new Tile("Cao Cao","1x1",new int[]{1,2});
        Tile2by2 caocao = new Tile2by2("Cao Cao","2x2",new Tile[] {a,b,c,d});

        tiles[0] = guanyu;
        tiles[1] = zhangfei;
        tiles[2] = zhaoyun;
        tiles[3] = machao;
        tiles[4] = zu1;
        tiles[5] = zu2;
        tiles[6] = zu3;
        tiles[7] = zu4;
        tiles[8] = huangzhong;
        tiles[9] = caocao;

    }
    public ArrayList<String> getPossibleMoves(){
        ArrayList<String> moves = new ArrayList<>();
        for (int i = 0;i<tiles.length;i++){
            moves = tiles[i].getMoves(i,moves,this);
        }
        return moves;
    }

    public String getOppositeMove(String move){//gets the opposite. used to make sure the program does not get stuck in a loop of endlessly moving a tile back and forth
        int index = Character.getNumericValue(move.charAt(0));
        String direction = move.substring(1);
        String newDirection = "";
        switch (direction){
            case "up":
                newDirection = "down";
                break;
            case "down":
                newDirection = "up";
                break;
            case "left":
                newDirection = "right";
                break;
            case "right":
                newDirection = "left";
                break;
        }
        newDirection = index + newDirection;
        return newDirection;
    }
    public double average(int[] ar){
        double sum = 0;
        double numOfElements = 0;
        for (int i = 0; i<ar.length;i++){
            if(ar[i]>0){
                numOfElements++;
                sum += ar[i];
            }
        }
        return sum/numOfElements;
    }
    public int getFewest(int[] ar){//used to get fewest moves
        int min = 0;
        for (int i = 0; i<ar.length;i++){
            if(ar[i]>0){
                if(min==0){
                    min = ar[i];
                }else{
                    min = Math.min(min,ar[i]);
                }
            }
        }
        return min;
    }
    public void playNTimes(int n){//play n times and get the average and min moves
        int wins = 0;
        int losses = 0;
        int[] movesTaken = new int[n];
        for (int i = 0;i<n;i++){
            int moves = playGame();
            if(moves>0){
                wins++;
                movesTaken[i] = moves;
            }else{
                losses++;
            }
            fillArrayMap();
        }
        System.out.println("The game was played "+n+" times. The computer won "+wins+" times and lost "+losses+" times.");
        System.out.println("On average, the computer took "+average(movesTaken)+" moves to win.");
        System.out.println("The fastest the computer was able to solve the game was in "+getFewest(movesTaken)+" moves.");
    }
    public int playGame(){//play the game until it wins, runs out of moves to make, or gets stuck in a loop
        ArrayList<String> movesMade = new ArrayList<>();
        int moves = 0;
        boolean lost = false;
        String previousMove = "";
        while(!winCheck()||lost){
            ArrayList<String> possibleMoves = getPossibleMoves();
            if(previousMove!=""&&possibleMoves.size()>0){
                possibleMoves.remove(getOppositeMove(previousMove));
            }
            if(possibleMoves.size()>0){
                String move = getBestMove(possibleMoves);
                int index = Character.getNumericValue(move.charAt(0));
                String direction = move.substring(1);
                tiles[index].checkAndMove(direction,this);
                previousMove = move;
                movesMade.add(move);
                moves++;
                if(moves>10000){
                    lost = true;
                    break;
                }
            }else{
                lost = true;
                break;
            }
        }
        if(lost){
            updateHashMapLose(movesMade);
            return 0;
        }else{
            updateHashMapWin(movesMade,moves);
            return moves;
        }
    }
    public boolean winCheck(){//check if the game has been won
        if (map_arr[3][1]=="Cao Cao" && map_arr[3][2]=="Cao Cao" && map_arr[4][1]=="Cao Cao" && map_arr[4][1]=="Cao Cao"){
            return true;
        }else{
            return false;
        }
    }
    public void printMapToConsole(){
        for (int y=0;y<rows; y++){
            for (int x=0; x<columns; x++){
                if(map_arr[y][x]==""){
                    System.out.print("empty ");
                }else{
                    System.out.print(map_arr[y][x]+" ");
                }

            }
            System.out.println();
        }
    }

}