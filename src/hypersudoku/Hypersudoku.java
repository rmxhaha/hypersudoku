/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hypersudoku;

import static hypersudoku.Sudoku.width;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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
        File file = new File("tc1");
        Sudoku board = new Sudoku();
        try {
            Scanner scanner = new Scanner(file);

            board.parseBare(scanner);
            
            long startTime = System.currentTimeMillis();
            int c = fillBlank( board );
            long endTime = System.currentTimeMillis();
            long dt = endTime - startTime;
            
            board.print();    
            System.out.println("Jumlah assignment = " + c );
            System.out.println("Execution Time = " + dt + "ms" );
            
            scanner.close();
        }
        catch(FileNotFoundException e ){
            e.printStackTrace();
        }
    }

    
    // output jumlah assignment
    public static int  fillBlank( Sudoku board ){
        ArrayList<Integer> search_space = new ArrayList<Integer>();
        for( int i = 0; i < board.width * board.height; ++ i )
            if( !board.isFilled(i%width, i/width))
                search_space.add(i);
        

        int idx = 0;
        int n,i;
        int x,y;
        
        int c = 0;
        while( 0 <= idx && idx < search_space.size() )
        {
            ++ c ;
            i = search_space.get(idx);

            x = i % width;
            y = i / width;
            
            n = board.getNum(x,y); // this works under the assumption the default value is 0
            board.remove(x,y);
            do {
                ++ n;
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
        
        return c;
    }
    
}
