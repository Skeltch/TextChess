/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess29;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Sam
 */
public class Chess29 {
    
    /**
     * TODO
     * Castling (And castling problem?)
     * En Passant
     * Promotion
     * Check & Checkmate
     * Draws
     * Stalemate
     */

    public static void main(String[] args) {
        // TODO code application logic here
        GameController.start();
        boolean active=true;
        Scanner input = new Scanner(System.in);
        while(active){
            GameController.printBoard();
            System.out.print(GameController.getTurn()+"'s move: ");
            String move = input.nextLine();
            GameController.playerMove(move);
        }
    }
    
}
