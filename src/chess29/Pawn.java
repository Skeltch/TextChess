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
public class Pawn extends ChessPiece{
    private boolean firstTurn=true;
    public Pawn(int y, int x, ChessPiece.Team team){
        //System.out.println(team);
        this.team=team;
        switch(team){
            case White:
                piece="wp";
                break;
            case Black:
                piece="bp";
                break;
        }
        this.x=x;
        this.y=y;
        position=ChessPiece.toPosition(x,y);
    }
    public String position(){
        return position;
    }
    @Override
    boolean validPiece(String dest){
        int newY = ChessPiece.toY(dest);
        int newX = ChessPiece.toX(dest);
        System.out.println(dest+","+y+","+newY+":"+x+","+newX);
        //Can only move "forward" based on team
        ChessPiece otherPiece = GameController.getBoard().get(newY).get(newX);
        int deltaX=newX-x;
        int deltaY=newY-y;
        if(((team==ChessPiece.Team.White && deltaY==1 && deltaX==0) || (team==ChessPiece.Team.Black && deltaY==-1 && deltaX==0)) && otherPiece instanceof EmptyPiece){
            firstTurn=false;
            return true;
        }
        //Possibly attacking as pawn diagonally
        else if((team==ChessPiece.Team.White && deltaY==1 && Math.abs(deltaX)==1) || (team==ChessPiece.Team.Black && deltaY==-1 && Math.abs(deltaX)==1)){
            if(otherPiece instanceof ChessPiece){
                switch(team){
                    case White:
                        if(otherPiece.getTeam()==ChessPiece.Team.Black){
                            firstTurn=false;
                            return true;
                        }
                        break;
                    case Black:
                        if(otherPiece.getTeam()==ChessPiece.Team.White){
                            firstTurn=false;
                            return true;
                        }
                        break;
                }
            }
            //or En Passant
            else if(otherPiece instanceof EmptyPiece){
                switch(team){
                    case White:
                        break;
                    case Black:
                        break;
                }
            }
        }
        //First move 2 spaces
        else if(firstTurn && ((deltaY==2 && deltaX==0 && team==ChessPiece.Team.White) || (deltaY==-2 && deltaX==0 && team==ChessPiece.Team.Black))){
            firstTurn=false;
            return true;
        }
        return false;
    }
}
