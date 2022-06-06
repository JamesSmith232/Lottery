/** 
Class MegaMillions represents purchasing a MegaMillions ticket
    -Subclass to LotteryGame
    
Author: James Smith
Last modified: Feburary 25, 2019
Email: james.smith048@topper.wku.edu    
**/

import java.util.Random; 
import java.util.Arrays; 

public class MegaMillions extends LotteryGame {
    
    public static final int NUM_OF_PICKS = 5;
    public static final int MIN_PICK = 1; 
    public static final int MAX_PICK = 75; 
    public static final int MIN_SPECIAL = 1; 
    public static final int MAX_SPECIAL = 15; 
    
    protected LotteryTicket winningPowerball; 
    
    public MegaMillions() {
        super(NUM_OF_PICKS, MIN_PICK, MAX_PICK); 
        
        this.winningTicket = new LotteryTicket(getPicks(MAX_PICK), getSpecialPick()); 
        
        this.prize = new String[10]; 
        this.prize[0] = "$1";
        this.prize[1] = "$2";
        this.prize[2] = "$5";
        this.prize[3] = "$5";
        this.prize[4] = "$50";
        this.prize[5] = "$500";
        this.prize[6] = "$5,000";
        this.prize[7] = "$1,000,000";
        this.prize[8] = "JACKPOT";
        this.prize[9] = "LOSER";        
    } //end of MegaMillions() method
    
    //abstract methods that override the LotteryGame class
    @Override
    protected int getMinPick() { return MIN_PICK; } 
    
    @Override
    protected int getMaxPick() { return MAX_PICK; } 
    
    @Override
    protected int getMinSpecialPick() { return MIN_SPECIAL; } 
    
    @Override
    protected int getMaxSpecialPick() { return MAX_SPECIAL; }
    //end of the abstract methods
    
    //uses the min and max of the powerball ruleset to get the special pick in the range of 1-15
    @Override 
    protected int getSpecialPick() {
       int range = (MAX_SPECIAL - MIN_SPECIAL + 1); 
       
       return (int)(Math.random() * range) + MIN_SPECIAL;
    } //end of getSpecialPick() method
    
    @Override
    protected String getAmountWon(int ticketNum) {
        LotteryTicket yourTicket; 
        
        boolean isSpecialWin = false; 
        String amountWin = null; 
        String[] winnings = new String[this.prize.length];
        int countMatches = countMatches(ticketNum); 
        int specialPick = winningTicket.getSpecialPick(); 
        int ticketCount = 0; 
        
        //initializes the winnings[] with the prize values from earlier
        winnings = this.prize; 
        
        //uses the getTicket() to find the correct ticket which initializes ticketCount 
        //with how many loops it took to get there
        for (int i = 0; i < this.ticket.length; i++) {
            if (this.ticket[i] != null) {
                if (this.ticket[i].getTicket() == ticketNum) ticketCount = i; 
            }
        }
        
        //initializes yourTicket to the current object with the ticketCount number
        yourTicket = this.ticket[ticketCount]; 
        //if specialWin is true, winnings will be different then what is to follow
        if (yourTicket.getSpecialPick() == specialPick) isSpecialWin = true; 
        if (isSpecialWin == true) {
            switch (countMatches) {
                case 0: 
                    amountWin = winnings[0]; 
                    break;
                case 1: 
                    amountWin = winnings[1]; 
                    break;
                case 2: 
                    amountWin = winnings[2]; 
                    break;
                case 3: 
                    amountWin = winnings[4]; 
                    break;
                case 4: 
                    amountWin = winnings[6]; 
                    break;
                case 5: 
                    amountWin = winnings[8]; 
                    break;
                default: 
                    break; 
            }
        //gets winnings when isSpecialWin is false
        } else {
            switch (countMatches) {
                case 0: 
                    amountWin = winnings[9]; 
                    break;
                case 1: 
                    amountWin = winnings[9]; 
                    break;
                case 2: 
                    amountWin = winnings[9]; 
                    break;
                case 3: 
                    amountWin = winnings[3]; 
                    break;
                case 4: 
                    amountWin = winnings[5]; 
                    break;
                case 5: 
                    amountWin = winnings[7]; 
                    break;
                default: 
                    break;
            }
        }
        
        return amountWin; 
    } //end of getAmountWon() method
    
    //generates a ticket and its numbers to give to the user who does not want to give their own picks 
    //follows the range using the rules given by megamillions ruleset
    @Override 
    protected String quickPickTicket() { 
        String yourTicketDetails;
        int picks[] = new int[NUM_OF_PICKS];
        int specialPick = getSpecialPick();
        
        picks = getPicks(MAX_PICK);   
        LotteryTicket yourTicket = new LotteryTicket(picks, specialPick);
        Arrays.fill(this.ticket, countTicket, (countTicket + 1), yourTicket); 
        countTicket++; 
        
        yourTicketDetails = "MegaMillions: " + "\n\tTicket Number: " + yourTicket.getTicket() + 
                                               "\n\tYour Numbers: " + Arrays.toString(picks) + 
                                               "\n\tYour MegaMillion Number: " + specialPick; 
        return yourTicketDetails;    
    } //end of quickPickTicket() method
    
    //creates a megamillion ticket using the numbers given by user 
    @Override 
    protected String userPickTicket(int[] picks, int specialPick) {
        String yourTicketDetails; 
        Arrays.sort(picks); 
        
        LotteryTicket yourTicket = new LotteryTicket(picks, specialPick); 
        Arrays.fill(this.ticket, countTicket, (countTicket + 1), yourTicket); 
        countTicket++; 
        
        yourTicketDetails = "MegaMillions: " + "\n\tTicket Number: " + yourTicket.getTicket() + 
                                               "\n\tYour Numbers: " + Arrays.toString(picks) + 
                                               "\n\tYour MegaMillion Number: " + specialPick;
        return yourTicketDetails; 
    } //end of userPickTicket() method
}
