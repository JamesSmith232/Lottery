/** 
Class LotteryGame is an abstract superclass for the lottery 
    Subclasses: PowerBall, MegaMillions
    
Author: James Smith
Last modified: Feburary 25, 2019
Email: james.smith048@topper.wku.edu    
**/

import java.util.Random; 
import java.util.Arrays; 

public abstract class LotteryGame {
    
    //instance varaibles
    protected int numOfPicks;
    protected int minPick; 
    protected int maxPick; 
    protected int countTicket; 
    protected int[] pick; 
    protected LotteryTicket[] ticket; 
    protected LotteryTicket winningTicket; 
    protected String[] prize; 
    
    static final int MILLION = 1000000; 
    static int[] generatedPicks = new int[5]; 
    
    //constructor 
    public LotteryGame(int numOfPicks, int minPick, int maxPick) {
        if (numOfPicks > 0 && minPick > 0 && maxPick > 0) {
            this.numOfPicks = numOfPicks; 
            this.minPick = minPick; 
            this.maxPick = maxPick; 
        }
        
        this.pick = new int[numOfPicks];
        this.ticket = new LotteryTicket[MILLION]; 
        this.winningTicket = new LotteryTicket(getPicks(this.maxPick), getSpecialPick());
    } //end of LotteryGame() method
    
    public int getNumOfPicks() {
        return this.numOfPicks;
    } //end of getNumOfPicks() method
    
    //abstract methods that will be created in both subclasses
    protected abstract int getMinPick();
    protected abstract int getMaxPick(); 
    protected abstract int getMinSpecialPick(); 
    protected abstract int getMaxSpecialPick(); 
    protected abstract int getSpecialPick(); 
    protected abstract String quickPickTicket(); 
    protected abstract String userPickTicket(int[] picks, int specialPick); 
    protected abstract String getAmountWon(int ticket); 
    //end of the abstract methods
    
    protected int[] getPicks(int max) {
       int number; 
       final int MIN = 1; 
       int range = (max - MIN + 1); 
       
       for (int i = 0; i < generatedPicks.length; ) {
           number = (int)(Math.random() * range) + MIN; 
           if (isRepeat(number) == false) {
               generatedPicks[i] = number; 
               i++;
           }
       }
       
       Arrays.sort(generatedPicks); 
       return generatedPicks; 
    } //end of getPicks() method
    
    protected boolean isRepeat(int number) {
        boolean isRepeat = false; 
        for (int i = 0; i < generatedPicks.length; i++) {
            if (generatedPicks[i] == number) isRepeat = true; 
        }
        
        return isRepeat; 
    } //end of isRepeat() method
    
    protected int countMatches(int ticketNumber) {
        LotteryTicket yourTicket; 
        int ticketCount = 0; 
        int count = 0; 
        
        for (int i = 0; i < this.ticket.length; i++) {
            if (this.ticket[i] != null) {
                if (this.ticket[i].getTicket() == ticketNumber) ticketCount = i; 
            }
        }
        
        yourTicket = this.ticket[ticketCount];
        for (int i = 0; i < yourTicket.getPicks().length; i++) {
            for (int j = 0; j < winningTicket.getPicks().length; j++) {
                if (yourTicket.getPicks()[i] == winningTicket.getPicks()[j]) count++; 
            }
        }
        
        System.out.println("\nWinning Ticket: " + "\n\tWinning Numbers: " + Arrays.toString(winningTicket.getPicks()) + 
                                                  "\n\tSpecial Number: " + winningTicket.getSpecialPick());
        System.out.println(); 
        return count; 
    } //end of countMatches
}