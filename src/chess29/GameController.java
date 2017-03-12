/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess29;

import java.util.ArrayList;

/**
 *
 * @author Sam
 */
public class GameController {
    private enum Turn{
        White, Black;
    }
    private static Turn turn;
    private static ArrayList<ArrayList<ChessPiece>> board = new ArrayList();
    static Turn getTurn(){
        return turn;
    }
    public static ArrayList<ArrayList<ChessPiece>> getBoard(){
        return board;
    }
    static void start(){
        turn=Turn.White;
        for(int i=0; i<8; i++){
            ArrayList<ChessPiece> temp = new ArrayList();
            for(int j=0; j<8; j++){
                if(i==6){
                    temp.add(new Pawn(i,j, ChessPiece.Team.Black));
                }
                else if(i==1){
                    temp.add(new Pawn(i,j, ChessPiece.Team.White));
                }
                else{
                    temp.add(new EmptyPiece(i,j));
                }
            }
            board.add(temp);
        }
    }
    static void playerMove(String move){
        if(move.length()!=5 || !ChessPiece.validBoard(move.substring(0,2))
                            || !ChessPiece.validBoard(move.substring(3,5))){
            System.out.println("Illegal move, try again");
        }
        else{
            String originalPos=move.substring(0,2);        
            String newPos=move.substring(3,5);
            ChessPiece curPiece = getPiece(originalPos);
            boolean validTeam=true;
            ChessPiece curPos = GameController.getPiece(originalPos);
            if(curPos instanceof EmptyPiece){
                System.out.println("Illegal move, try again: Piece not found");
                return;
            }
            switch(turn){
                case White:
                    if(curPiece.getTeam()!=ChessPiece.Team.White){
                        System.out.println("Illegal move, try again: Wrong Team");
                        validTeam=false;
                    }
                    break;
                case Black:
                    if(curPiece.getTeam()!=ChessPiece.Team.Black){
                        System.out.println("Illegal move, try again: Wrong Team");
                        validTeam=false;
                    }
                    break;
            }
            if(!validTeam){
                return;
            }
            //System.out.println(curPos.getClass().getName());
            //System.out.println(curPos.getPiece()+","+curPos.getPosition()+","+curPos.getX()+","+curPos.getY());
            else{
                if(curPos.move(newPos)){
                    //Move in arraylist replace, replace with emptypiece
                    //System.out.println(ChessPiece.toY(originalPos)+","+ChessPiece.toX(originalPos)+":"+ChessPiece.toY(newPos)+","+ChessPiece.toX(newPos));
                    board.get(ChessPiece.toY(newPos)).set(ChessPiece.toX(newPos),curPiece);
                    //Maybe have a returnedPiece for the case of castling instead of EmptyPiece
                    //Or swap places and if teams are not the same use emptypiece
                    board.get(ChessPiece.toY(originalPos)).set(ChessPiece.toX(originalPos),new EmptyPiece(ChessPiece.toY(originalPos),ChessPiece.toX(originalPos)));
                    switch(turn){
                        case White:
                            turn=Turn.Black;
                            break;
                        case Black:
                            turn=Turn.White;
                    }
                }
            }
        }
    }
    static void printBoard(){
        for(int i=7; i>=0; i--){
            for(int j=0; j<8; j++){
                System.out.print(board.get(i).get(j).getPiece()+" ");
            }
            System.out.println(i+1);
        }
        for(int i=0; i<8; i++){
            System.out.print(" "+(char)(97+i)+" ");
        }
        System.out.println();
    }
    static ChessPiece getPiece(String location){
        //System.out.println(((int)location.charAt(0))%97+","+(Character.getNumericValue(location.charAt(1))-1));
        return board.get(ChessPiece.toY(location)).get(ChessPiece.toX(location));
    }
}
