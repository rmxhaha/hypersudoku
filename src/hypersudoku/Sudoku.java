/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hypersudoku;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author rmxhaha
 */
public class Sudoku {
    public Sudoku(){
        boardnum = new int[ width * height ];
        boardfill = new boolean[ width * height ];
        Arrays.fill( boardfill, false );
        Arrays.fill( boardnum, 0 );
    }
    
    public int getNum( int x, int y ){
        assert(0<=x && x<width);
        assert(0<=y && y<height);
        return boardnum[y*width+x];
    }
    
    public boolean isFilled( int x, int y){
        assert(0<=x && x<width);
        assert(0<=y && y<height);
        return boardfill[y*width+x];
    }
    
    public boolean isValidToPutIn(int v, int x, int y){
        assert(0<=x && x<width);
        assert(0<=y && y<height);
        assert(1<=v && v<=9);
        
        
        
        // horizontal check
        for( int _x = 0; _x < width; ++ _x)
            if( isFilled(_x,y) && getNum(_x,y) == v )
                return false;
        
        // vertical check
        for( int _y = 0; _y < height;++ _y )
            if( isFilled(x,_y) && getNum(x,_y) == v )
                return false;
        
        // 3x3 white block check
        int bx = x / 3;
        int by = y / 3;
        for( int dx = 0; dx < 3; ++ dx ){
            for( int dy = 0; dy < 3; ++ dy ){
                int _x = bx * 3 + dx;
                int _y = bx * 3 + dy;
                
                if( isFilled(_x,_y) && getNum(_x,_y) == v )
                    return false;
            }
        }
        
        
        return true;
    }
    
    public boolean putIn( int v, int x, int y){
        // fail to put in
        if( isFilled(x,y) ) return false;
        if( !isValidToPutIn(v,x,y)) return false; 
        
        setNum(x,y,v);
        return true;
    }
    
    public boolean remove( int x, int y ){
        if( !isFilled(x,y)) return false;
        
        boardnum[y*width+x] = 0;
        boardfill[y*width+x] = false;

        return true;
    }
    
    public void print(){
        for( int k=0;k < height; ++ k){
            for( int i = 0; i < width;++ i){
                if(i%3==0) 
                    System.out.print("|"); 
                else
                    System.out.print(" ");

                if( isFilled(i,k) )
                    System.out.print(getNum(i,k));
                else
                    System.out.print("0");
                
            }
            System.out.println("|");
        }
    }
    
    public void printBare(){
        for( int k=0;k < height; ++ k){
            for( int i = 0; i < width;++ i){
                System.out.print(getNum(i,k));
            }
            System.out.println();
        }
    }
    
    public void parseBare( Scanner scanner ){
        String str;
        for( int i = 0; i < 9; ++ i ){
            str = scanner.next();
            for( int k = 0; k < 9; ++ k ){
                setNum( k,i, str.charAt(k)-'0');
            }
        }
    }
    
    private void setNum( int x, int y, int v){
        boardnum[y*width+x] = v;
        boardfill[y*width+x] = true;
    }
    
    protected int boardnum[]; // which number is inside a cell
    protected boolean boardfill[]; // is cell in the board filled yet or not
    static int width = 9;
    static int height = 9;
}
