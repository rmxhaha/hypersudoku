/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hypersudoku;

import static hypersudoku.Sudoku.width;
import java.util.ArrayList;
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
        fillBlank( board );
        
        board.print();        
    }
    
    public static void fillBlankR( Sudoku cboard, ArrayList<Integer> search_space, int idx ){
        int k = search_space.get(idx);
        
        
    }
    
    public static void fillBlank( Sudoku board ){
        ArrayList<Integer> search_space = new ArrayList<Integer>();
        for( int i = 0; i < board.width * board.height; ++ i )
            if( !board.isFilled(i%width, i/width))
                search_space.add(i);
        

        int idx = 0;
        int n,i;
        int x,y;
        int maxlevel = -1;
        
        while( 0 <= idx && idx < search_space.size() )
        {
            i = search_space.get(idx);
            
            if( idx > maxlevel ){
                maxlevel = idx;
                System.out.println(maxlevel);
            }

            x = i % width;
            y = i / width;
            
            n = board.getNum(x,y); // this works under the assumption the default value is 0
            board.remove(x,y);
            do {
                ++ n;
//                System.out.println(i + " " + n);
//                board.print();
            }
            while( n <=9 && !board.putIn(n,x,y) );

            if( n == 10 ){
                idx--;
                board.remove(x,y);
            }
            else
                idx++;
        }
        
        if( idx == -1 ) 
            System.out.println("solution not found");
    }
    
}
