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
public class EmptyPiece extends ChessPiece{
    public EmptyPiece(int x, int y){
        this.x=x;
        this.y=y;
        if(x%2==0 && y%2==1 || x%2==1 && y%2==0){
            piece="##";
        }
        else{
            piece="  ";
        }
    }
    boolean validPiece(String dest){
        return false;
    }
}
