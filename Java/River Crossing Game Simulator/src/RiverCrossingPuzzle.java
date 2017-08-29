
import java.util.Scanner;
public class RiverCrossingPuzzle {
    
    private static int numEach;
    private static int boatSize;
    private int numCannibalsToMove;
    private int numMissionariesToMove;
    private static int leftMissionaries;
    private static int leftCannibals;
    private static int rightMissionaries;
    private static int rightCannibals;
    private static int moves;
    private static int counter;
    private static int status = 0;
    private int boat;
    
    
    public RiverCrossingPuzzle(int numEach, int boatSize) {
        this.numEach = numEach;
        RiverCrossingPuzzle.reset();
        if (boatSize <= 4) {
            this.boatSize = boatSize;
        }  
    }
    
    
    
    public String availableActions() {
        return "";
            }
 
    
    
    
    
    
    
    public static void checkStatus(){
        
        if (leftCannibals < 1) {
            if (leftMissionaries < 1) {
                System.out.println("You Won!!!");
                status = 1;
                return;
            }
            return;
            
        }
        if (rightMissionaries > 0) {
            if (rightMissionaries < leftCannibals) {
                System.out.println("You Failed. Right bank missionaries eaten");
                status = -1;
                return;           
            }
            return;
        }
        
        if (leftMissionaries > 0) {
            if (leftCannibals > leftMissionaries) {
                System.out.println("You Failed. Left bank missionaries eaten");
                status = -1;
                return;  
            }
            return;
        }
        
        
        
        
    }
    
    
    public void move(int numCannibalsToMove, int numMissionariesToMove) {
        if ((numCannibalsToMove + numMissionariesToMove) > 0) {
            moves ++;
            if (numCannibalsToMove > 0) {
                if (numCannibalsToMove <= boatSize) {
                    if (boat == 0) {
                        if (leftCannibals != 0) {
                            for (int i = 0; i < numCannibalsToMove; i++) {
                                leftCannibals --;
                                rightCannibals ++;
                                boat = 1;
                                this.checkStatus();
                                System.out.println(this.puzzleState());
                                
                            }
                            return;
                        }
                        System.out.println("Not enough cannibals on left bank to move");
                        return;
                    }
                    if (rightCannibals >= numCannibalsToMove) {
                        
                        for (int i = 0; i < numCannibalsToMove ; i++) {
                            
                            rightCannibals --;
                            leftCannibals ++;
                            boat = 0;
                            this.checkStatus();
                            System.out.println(this.puzzleState());
                        }
                        return;
                        
                    }
                    
                    System.out.println("Not enough cannibals on right bank to move");
                    return;
                }
                System.out.println("Boat not large enough");
                return;
            }
            if (numMissionariesToMove > 0) {
                if (numMissionariesToMove <= boatSize) {
                    
                    if (boat == 0) {
                        if (leftMissionaries != 0) {
                            for (int i = 0; i < numMissionariesToMove; i++) {
                                
                                leftMissionaries --;
                                rightMissionaries ++;
                                boat = 1;
                                this.checkStatus();
                                System.out.println(this.puzzleState());
                                
                            }
                            return;
                            
                        }
                        System.out.println("Not enough missionaries on left bank to move");
                        return;
                    }
                    if (leftMissionaries >= numMissionariesToMove) {
                        for (int i = 0; i < numMissionariesToMove; i++) {
                            
                            rightMissionaries --;
                            leftMissionaries ++;
                            boat = 0;
                            this.checkStatus();
                            System.out.println(this.puzzleState());
                            
                            
                        }
                        return;
                        
                    }
                    System.out.println("Not enough missionaries on right bank to move");
                    return;
                }
                System.out.println("Boat not large enough");
                return;
            }
        }
        System.out.println("No one in boat.");
        return;
    }
    
    
    
    
    
    public int numMissionariesOnLeftBank() {
        return leftMissionaries;
    }
    
    public int numMissionariesOnRightBank() {
        return rightMissionaries;
    }
    
    public int numCannibalsOnLeftBank() {
        return leftCannibals;
    }
    public int numCannibalsOnRightBank() {
        return rightCannibals;
    }
    public boolean boatOnLeftBank() {
        if (boat == 0) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean boatOnRightBank() {
        if (boat == 1) {
            return true;
        }
        else {
            return false;
        }
    }
    
    
    
    
    
    public int status() {
        return status;
    }
    
    public int totalMoves() {
        return moves;
    }
    
    public static void reset() {
        leftMissionaries = numEach;
        leftCannibals = numEach;
        rightMissionaries = 0;
        rightCannibals = 0;
    }
    
    
    
    
    //NO EDIT ZONE!!!!!!!!!!!!!
    //-------------------------
    
    public String prompt() {
        String str = "";
        str += "Available Actions\n";
        str += availableActions();
        str += "Action: ";
        return str;
    }
    
    
    
    /**
     * ***DO NOT CHANGE THIS FUNCTION.***
     * @return state of left (starting) bank as a String
     */
    private String leftBank() {
        String str = "";
        for (int i = 0; i < numCannibalsOnLeftBank(); i++)
            str += "C";
        str += " ";
        for (int i = 0; i < numMissionariesOnLeftBank(); i++)
            str += "M";
        str += " ";
        if (boatOnLeftBank())
            str += "B";
        return str;
    }
    
    /**
     *  ***DO NOT CHANGE THIS FUNCTION.***
     * @return state of right (ending) bank as a String
     */
    private String rightBank() {
        String str = "";
        if (boatOnRightBank())
            str += "B ";
        for (int i = 0; i < numCannibalsOnRightBank(); i++)
            str += "C";
        str += " ";
        for (int i = 0; i < numMissionariesOnRightBank(); i++)
            str += "M";
        return str;
    }
    
    public String toString() {
        return leftBank() + " | " + rightBank();
    }
    
    public void play() {
        Scanner s = new Scanner(System.in);
        System.out.println(this.puzzleState());
             
    }
    
    
    
    /**
     *  ***DO NOT CHANGE THIS FUNCTION.***
     * @return String representation of current state of puzzle
     */
    
    public String puzzleState() {
        String lb = leftBank();
        String rb = rightBank();
        String str = "\n";
        str += "Left Bank";
        for (int i = 9; i < lb.length(); i++)
            str += " ";
        str += " | ";
        for (int i = lb.length() + 3; i < lb.length() + rb.length() + 3 - 10; i++)
            str += " ";
        str += "Right Bank";
        str += "\n";
        str += lb;
        for (int i = lb.length(); i < 9; i++)
            str += " ";
        str += " | ";
        for (int i = rb.length() - 10; i < 0; i++)
            str += " ";
        str += rb;
        str += "\n";
        str += "\n";
        str += "   Cannibals on left,right banks: ";
        str += String.format("%3d,%3d", numCannibalsOnLeftBank(), numCannibalsOnRightBank());
        str += "\n";
        str += "Missionaries on left,right banks: ";
        str += String.format("%3d,%3d", numMissionariesOnLeftBank(), numMissionariesOnRightBank());
        str += "\n";
        str += "\n";
        str += "Number of moves: " + totalMoves();
        str += "\n";
        return str;
    }
    
//END OF NO EDIT ZONE!!!
    
  
    public static void main(String[] args){
        
        RiverCrossingPuzzle r = new RiverCrossingPuzzle(3,2);
       
        if(args.length == 0){
            System.out.println("No arguments present");
            return;
        }
        
        else if(args[0].equals("-n")){   
            
            numEach = Integer.parseInt(args[1]);
            return;
        }
        
        
        else if(args[0].equals("-b")) { 
            
            boatSize = Integer.parseInt(args[1]);
            return;
        }
        else if(args[0].matches(".*\\d.*")) {
            r.move(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            return; 
        }
        r.play();
                     
    }   
}


