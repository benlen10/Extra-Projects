

//this program will simulate the Minesweeper game in the terminal window

import java.util.Random;
import java.util.Scanner;

public class Minesweeper {
    
    public final boolean[][] minefield; 
    
    public final int[][] clueGrid; 
    
    public boolean[][] checked; 
    
    int rows;
    int columns;
    int mines;
    public Minesweeper(int rows, int columns, int mines) {
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
        minefield = new boolean[rows][columns];
        clueGrid = new int[rows][columns];
        checked = new boolean[rows][columns];
        generateMinefield(mines);
        generateClueGrid();
        for (int i = 0; i < checked.length; i++) {
            for (int u = 0; u < checked.length; u++) 
                checked[i][u] = false;
        }
    }
    
    
    public void generateMinefield(int mines) {
        for (int i = 0; i < minefield.length; i++)
            for (int j = 0; j < minefield[i].length; j++)
            minefield[i][j] = false;
        
        Random random = new Random(System.currentTimeMillis());
        
        while (mines > 0) { 
            int r = random.nextInt(minefield.length);
            int c = random.nextInt(minefield[r].length);
            
            minefield[r][c] = true;
            mines--;
        }
    }
    
    
    int row;
    int column;
    public int countMines(int row, int column) {
        int n = 0;
        for (int i = Math.max(row-1,0); i <= Math.min(row+1,rows-1); i++)
            for (int j = Math.max(column-1,0); j <= Math.min(column+1,columns-1); j++)
            if (!(i == row && j == column))
            n += minefield[i][j] ? 1 : 0;
        return n;
    }
    
    
    
    
    
    
    
    public void generateClueGrid() {
        for (int i = 0; i < rows; i++) 
            for (int j = 0; j < columns; j++) 
            clueGrid[i][j] = minefield[i][j] ? -1 : countMines(i,j);
    }
    

    public void printBoard() {    
        System.out.print("   "); 
        for (int j = 0; j < columns; j++) 
            System.out.print(" " + (j+1)); 
        System.out.println(); 
        
        System.out.print("  +-"); 
        for (int j = 0; j < columns; j++) 
            System.out.print("--"); 
        System.out.println();
        
        char row_letter = 'A';
        for (int i = 0; i < rows; i++) {
            System.out.print(row_letter + " |"); 
            for (int j = 0; j < columns; j++) { 
                char cell_symbol;
                if (!checked[i][j]) 
                    cell_symbol = '?';
                else if (minefield[i][j])
                    cell_symbol = '*';
                else if (clueGrid[i][j] > 0) 
                    cell_symbol = (char)('0' + clueGrid[i][j]);
                else  
                    cell_symbol = ' ';
                System.out.print(" " + cell_symbol);  
            }
            System.out.println(); 
            
            row_letter++;
        }
    }
    
    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean win = false; 
        while (!win) {
            printBoard();
            System.out.print("Check cell? ");
            String line = scanner.nextLine().toUpperCase();
            int row = line.charAt(0)-'A';
            int column = line.charAt(1)-'1';
            if (minefield[row][column]) 
                break;
            else 
                checked[row][column] = true;
            win = true;
            for (int i = 0 ; i < rows && win; i++)
                for (int j = 0; j < columns && win; j++)
                if (!checked[i][j] && !minefield[i][j])
                win = false; 
        }
        
        scanner.close();
        for (int i = 0 ; i < rows; i++)
            for (int j = 0; j < columns; j++)
            checked[i][j] = true;
        printBoard();
        if (win)
            System.out.println("\nYou win!");
        else
            System.out.println("\nYou're not very good at this are you?");
    }
    
    
    public static void main(String[] args) {
        Minesweeper m = new Minesweeper(5, 5, 5);
        m.start();
    }
}
    
    
