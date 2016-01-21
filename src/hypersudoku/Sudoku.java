/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hypersudoku;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author rmxhaha
 */
public class Sudoku {
    public Sudoku(){
        boardnum = new int[ width * height ];
        boardfill = new boolean[ width * height ];
        vfill = new boolean[width][10];
        hfill = new boolean[height][10];
        bfill = new boolean[3][3][10];
        sbfill = new boolean[4][10];
        
        for( int i = 0; i < width; ++ i)
            Arrays.fill(vfill[i], false);

        for( int i = 0; i < height; ++ i)
            Arrays.fill(hfill[i], false);
        for( int i = 0; i < 3; ++ i)
            for( int k = 0; k < 3; ++ k )
                Arrays.fill(bfill[i][k],false);
        for( int i = 0; i < 4; ++ i )
            Arrays.fill(sbfill[i], false);
        
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
        if( hfill[y][v] ) return false;
        
        // vertical check
        if( vfill[x][v] ) return false;
        
        // 3x3 white block check
        if( bfill[x/3][y/3][v] ) return false;
        
        // 3x3 special block check
        int sbidx = getSbIdx(x,y);
        if( sbidx != -1 )
            if( sbfill[sbidx][v] ) return false;
        
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

        int v = boardnum[y*width+x];
        boardnum[y*width+x] = 0;
        boardfill[y*width+x] = false;
        hfill[y][v] = false;
        vfill[x][v] = false;
        bfill[x/3][y/3][v] = false;
        int sbidx = getSbIdx(x,y);
        if( sbidx != -1 )
            sbfill[sbidx][v] = false;
        
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
        hfill[y][v] = true;
        vfill[x][v] = true;
        bfill[x/3][y/3][v] = true;
        int sbidx = getSbIdx(x,y);
        if( sbidx != -1 )
            sbfill[sbidx][v] = true;
    }
    
    private int getSbIdx( int x, int y){
        if( 1 <= x && x <= 3 ){
            if( 1 <= y && y <= 3 ){
                // sb 0
                return 0;
            }
            else if( 5 <= y && y <= 7 ){
                // sb 1 
                return 1;
            }
        }
        else if( 5 <= x && x <= 7 ){
            if( 1 <= y && y <= 3 ){
                // sb 2
                return 2;
            }
            else if( 5 <= y && y <= 7 ){
                // sb 3 
                return 3;
            }
            
        }
        
        return -1;
    }
    
    protected int boardnum[]; // which number is inside a cell
    protected boolean boardfill[]; // is cell in the board filled yet or not
    protected boolean hfill[][];
    protected boolean vfill[][];
    protected boolean bfill[][][];
    protected boolean sbfill[][];
    static int width = 9;
    static int height = 9;
}
