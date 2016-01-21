/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hypersudoku;

/**
 *
 * @author rmxhaha
 */

public class Hypersudoku {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Sudoku board = new Sudoku();
        
        
        
        System.out.println( board.putIn(1, 2, 2) );
        System.out.println( board.putIn(2, 2, 3) );
        board.printBare();
        
    }
    
}
