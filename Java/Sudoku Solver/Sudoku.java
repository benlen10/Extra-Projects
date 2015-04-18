import java.util.Scanner;
import java.util.Arrays;

public class Sudoku {
    public int[][] game;
    
    
    public Sudoku() {
        int[][] game = new int[9][9];
        this.game = game;
    }
    
    
    public Sudoku(int[][] board) {  
        int[][] temp = new int[9][9];
        for(int i=0; i<9; i++) {
            for(int j=0; j <9; j++)
                temp[i][j]=board[i][j];
            this.game = temp;
        }
    }
    
    public boolean isSolved() {
        int[] array = new int[10];
        
         for (int i = 0; i < 9; i++) {
            for (int x = 0; x < 9; x++) {
                if (game[i][x] == 0) {
                    return false;
                }
            }
        }
         
        for (int i = 0; i < 9; i++) {
            for (int x = 0; x < 9; x++) {
                //Check Canidates for entire column
                
                for (int y = 0; y < 9; y++) {
                    array[game[y][x]] ++;
                }
                
                for (int z = 1; z < 10; z++) {
                    if (array[z] > 1) {
                        return false;
                    }
                }
                
                for (int h = 0; h < 10; h++) {
                    array[h] = 0;
                }
                
                for (int y = 0; y < 9; y++) {
                    array[game[i][y]] ++;
                }
                
                for (int z = 1; z < 10; z++) {
                    if (array[z] > 1) {
                        return false;
                    }
                }
                
                for (int h = 0; h < 10; h++) {
                    array[h] = 0;
                }
                
                int a = 0;
                int b = 0;
                
                if (i < 3 && x < 3) {
                    a = 0;
                    b = 0;
                }
                if ( i >= 3 && i < 6 && x < 3) {
                    a = 3;
                    b = 0;
                }
                if ( i >= 6 && i < 9 && x < 3) {
                    a = 6;
                    b = 0;
                }
                if (i < 3 && x >= 3 && x < 6) {
                    a = 0;
                    b = 3;
                }
                if ( i >= 3 && i < 6 && x >= 3 && x < 6) {
                    a = 3;
                    b = 3;
                }
                if ( i >= 6 && i < 9 && x >= 3 && x < 6) {
                    a = 6;
                    b = 3;
                }
                if (i < 3 && x >= 6 && x < 9) {
                    a = 0;
                    b = 6;
                }
                
                if ( i >= 3 && i < 6 && x >= 6 && x < 9) {
                    a = 3;
                    b = 6;
                }
                if ( i >= 6 && i < 9 && x >= 6 && x < 9) {
                    a = 6;
                    b = 6;
                }
                
                
                for (int y = a; y < (a +3); y++) {
                    for (int z = b; z < (b +3); z++) {
                        array[game[y][z]] ++;
                    }
                }
                
                for (int z = 1; z < 10; z++) {
                    if (array[z] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    
    public boolean[] candidates(int row, int column) {
        
        // Create temp number array and set all to TRUE
        boolean [] array = new boolean[10];
        if (game[row][column] != 0) {
            
            return array;
        }
        for (int i = 1; i < 10; i++) {
            array[i] = true;
        }
        //Check all COLUMNS and set array value to false to mark that number exists
        
        for (int i = 0; i < 9; i++) {
            if (game[row][i] != 0) {
                array[game[row][i]] = false;
            }
        }
        //Check all ROWS and set array value to false to mark that number exists
        for (int i = 0; i < 9; i++) {
            if (game[i][column] != 0) {
                array[game[i][column]] = false;
                
            }
        }
        
        
        int a = 0;
        int b = 0;
        
        if (row < 3 && column < 3) {
            a = 0;
            b = 0;
        }
        if ( row >= 3 && row < 6 && column < 3) {
            a = 3;
            b = 0;
        }
        if ( row >= 6 && row < 9 &&  column < 3) {
            a = 6;
            b = 0;
        }
        if (row < 3 && column >= 3 && column < 6) {
            a = 0;
            b = 3;
        }
        if (row >=3 && row < 6 && column >= 3 && column < 6) {
            a = 3;
            b = 3;
        }
        if ( row >= 6 && row < 9 && column >= 3 && column < 6) {
            a = 6;
            b = 3;
        }
        if (row < 3 && column >= 6 && column < 9) {
            a = 0;
            b = 6;
        }
        
        if ( row >= 3 && row < 6 && column >= 6 && column < 9) {
            a = 3;
            b = 6;
        }
        if ( row >= 6 && row < 9 && column >= 6 && column < 9) {
            a = 6;
            b = 6;
        }
        
        
        for (int y = a; y < (a +3); y++) {
            for (int z = b; z < (b +3); z++) {
                if (game[y][z] != 0) {
                    array[game[y][z]] = false;
                }
            }
        }
        
        return array;
        
    }
    
    public boolean nakedSingles(){
        int count = 0;
        int tmp = 0;
        boolean changed = false;
        boolean[] array = new boolean[10];
        for (int i = 0; i < 9; i++) {
            for (int x = 0; x < 9; x++) {
                array = candidates(i,x);
                count = 0;
                tmp = 0;
                for (int z = 0; z < 10; z++) {
                    if (array[z] == true) {
                        count++;
                        tmp = z;
                    } 
                }
                if (count == 1) {
                    game[i][x] = tmp;
                    changed = true;
                }
            }  
        }
        if (changed) {
            return true;
        }
        return false;
    }
    
    
    
    public boolean hiddenSingles() {
        int[] count = new int[10];
        boolean[] array = new boolean[10];
        boolean changed = false;
        for (int i = 0; i < 9; i++) {
            for (int x = 0; x < 9; x++) {
                
                //ACTUAL METHOD
                
                //Check Canidates for entire column
                for (int y = 0; y < 9; y++) {
                    array = candidates(y, x);
                    for (int z = 1; z < 10; z++) {
                        if (array[z] == true) {
                            count[z] ++;
                        }
                    }
                }
                
                
                // Make Changes
                for (int y = 1; y < 10; y++) {
                    if (count[i] == 1) {
                        game[i][x] = i;
                        changed = true;
                    }
                }
                
                for (int h = 1; h < 10; h++) {
                    count[h] = 0;
                }
                
                
                
                
                //Check Canidates for entire row.
                for (int y = 0; y < 9; y++) {
                    array = candidates(i, y);
                    for (int z = 1; z < 10; z++){
                        if (array[z] == true) {
                            count[z] ++;
                        }
                    }
                }
                //Make Changes
                for (int y = 1; y < 10; y++) {
                    if (count[i] == 1) {
                        game[i][x] = i;
                        changed = true;
                    }
                }
                
                for (int h = 1; h < 10; h++) {
                    count[h] = 0;
                }
                
                
                //Box Check
                int a = 0;
                int b = 0;
                
                if (i < 3 && x < 3) {
                    a = 0;
                    b = 0;
                }
                if ( i >= 3 && i < 6 && x < 3) {
                    a = 3;
                    b = 0;
                }
                if ( i >= 6 && i < 9 && x < 3) {
                    a = 6;
                    b = 0;
                }
                if (i < 3 && x >= 3 && x < 6) {
                    a = 0;
                    b = 3;
                }
                if ( i >= 3 && i < 6 && x >= 3 && x < 6) {
                    a = 3;
                    b = 3;
                }
                if ( i >= 6 && i < 9 && x >= 3 && x < 6) {
                    a = 6;
                    b = 3;
                }
                if (i < 3 && x >= 6 && x < 9) {
                    a = 0;
                    b = 6;
                }
                
                if ( i >= 3 && i < 6 && x >= 6 && x < 9) {
                    a = 3;
                    b = 6;
                }
                if ( i >= 6 && i < 9 && x >= 6 && x < 9) {
                    a = 6;
                    b = 6;
                }
                
                
                for (int y = a; y < (a +3); y++) {
                    for (int z = b; z < (b +3); z++) {
                        array = candidates(y, z);
                        for (int u = 0; u < 10; u++)
                            if (array[u] == true) {
                            count[u] ++;
                        }
                    }
                }
                
                //Check the count histogram array and make canges to board.
                
                for (int y = 1; y < 10; y++) {
                    if (count[i] == 1) {
                        game[i][x] = i;
                        changed = true;
                    }
                }
            }
        }
        if (changed) {
            return true; 
        }
        return false;
    }
    
    public void solve() {
        while (!isSolved() && (nakedSingles() || hiddenSingles()));
    }
    
    
    
    public int[][] board() {
        int[][] copy = new int[9][9];
        for(int i=0; i< 9; i++) {
            for(int j=0; j<9; j++) {
                copy[i][j] = game[i][j];
            }
        }
        return copy;
    }
}


