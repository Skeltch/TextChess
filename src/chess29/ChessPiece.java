/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess29;

/**
 *
 * @author Sam
 */
public abstract class ChessPiece {
    public static enum Team{
        White,Black;
    }
    protected int x,y;
    protected String position;
    protected boolean alive;
    protected String piece;
    protected Team team;
    
    public void ChessPiece(){
        alive=true;
    }
    public String getPosition(){
        return position;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public Team getTeam(){
        return team;
    }
    public boolean isAlive(){
        return alive;
    }
    public String getPiece(){
        return piece;
    }
    static String toPosition(int x, int y){
        return Character.toString((char) (97+x)) + Integer.toString(y+1);
    }
    static int toX(String position){
        return ((int)position.charAt(0))%97;
    }
    static int toY(String position){
        return Character.getNumericValue(position.charAt(1))-1;
    }
    static boolean validBoard(String check){
        //System.out.println(check +"," + (int)check.charAt(0)+","+Character.getNumericValue(check.charAt(1)));
        if(check.length()!=2){
            return false;
        }
        if((int)check.charAt(0)<97 || (int)check.charAt(0)>104){
            return false;
        }
        if(Character.getNumericValue(check.charAt(1))<1 || Character.getNumericValue(check.charAt(1))>8){
            return false;
        }
        return true;
    }
    abstract boolean validPiece(String dest);
    private void setPosition(String dest){
        //97=a
        x=(int)dest.charAt(0)%97;
        y=Character.getNumericValue(dest.charAt(1))-1;    
        System.out.println(dest+","+x+","+y);
    }
    public boolean move(String dest){
        //Need to check if piece is blocked or if it can attack
        if(/*validBoard(dest) && */validPiece(dest)){
            position=dest;
            setPosition(dest);
            return true;
        }
        else{
            //invalid
            System.out.println("Illegal move, try again: Illegal Move");
            return false;
        }
    }
}
