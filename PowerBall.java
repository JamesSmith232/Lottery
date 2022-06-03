/** 
Class PowerBall represents purchasing a PowerBall ticket
    -Subclass to LotteryGame
    
Author: James Smith
Last modified: Feburary 25, 2019
Email: james.smith048@topper.wku.edu    
**/

import java.util.Arrays; 

public class PowerBall extends LotteryGame {
    
    public static final int NUM_OF_PICKS = 5; 
    public static final int MIN_PICK = 1; 
    public static final int MAX_PICK = 69;
    public static final int MIN_SPECIAL = 1; 
    public static final int MAX_SPECIAL = 26; 
    
    protected LotteryTicket winningPB; 
    
    public PowerBall() {
        super(NUM_OF_PICKS, MIN_PICK, MAX_PICK); 
        this.winningTicket = new LotteryTicket(getPicks(MAX_PICK), getSpecialPick());
        
        //fills the prize array
        this.prize = new String[11]; 
        this.prize[0] = "$4";
        this.prize[1] = "$4";
        this.prize[2] = "$7";
        this.prize[3] = "$7";
        this.prize[4] = "$100";
        this.prize[5] = "$100";
        this.prize[6] = "$50,000";
        this.prize[7] = "$50,000";
        this.prize[8] = "$1,000,000";
        this.prize[9] = "GRAND PRIZE";
        this.prize[10] = "LOSER";        
    } //end of PowerBall() method
    
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
    
    //getSpecialPick() uses the min and max that follows the powerball ruleset
    @Override 
    protected int getSpecialPick() {
       int range = (MAX_SPECIAL - MIN_SPECIAL + 1); 
       
       return (int)(Math.random() * range) + MIN_SPECIAL;
    } //end of getSpecialPick() method
    
    @Override
    protected String getAmountWon(int ticketNum) {
        LotteryTicket yourTicket;
        
        boolean isSpecialWin = false; 
        boolean ticketFound = false; 
        String amountWin = null; 
        String[] winnings = new String[this.prize.length];
        int countMatches = countMatches(ticketNum); 
        int specialPick = winningTicket.getSpecialPick();
        int ticketCount = 0; 
        
        //initializes the winnings[] with the prize values from earlier
        winnings = this.prize; 
        
        for (int i = 0; i < this.ticket.length; i++) {
            if (this.ticket[i] != null) {
                if (this.ticket[i].getTicket() == ticketNum) ticketCount = i;              
            }
        }
        
        yourTicket = this.ticket[ticketCount]; 
        
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
                    amountWin = winnings[7];
                    break;
                case 5:
                    amountWin = winnings[9];
                    break;
                default: 
                    break; 
            } 
        } else {
            switch (countMatches) {
                case 0: 
                    amountWin = winnings[10]; 
                    break; 
                case 1:
                    amountWin = winnings[10];
                    break;
                case 2:
                    amountWin = winnings[10];
                    break;
                case 3:
                    amountWin = winnings[3];
                    break;
                case 4:
                    amountWin = winnings[4];
                    break;
                case 5:
                    amountWin = winnings[8];
                    break;
                default: 
                    break;
            }
        }
        
        return amountWin; 
    }
    
    @Override
    protected String quickPickTicket() {
        LotteryTicket yourTicket; 
        
        int picks[] = new int[NUM_OF_PICKS];
        int specialPick = getSpecialPick(); 
        String yourTicketDetails; 
        
        picks = getPicks(MAX_PICK); 
        yourTicket = new LotteryTicket(picks, specialPick);
        Arrays.fill(this.ticket, yourTicket);
        countTicket++; 
        
        yourTicketDetails = "PowerBall: " + "\n\tTicketNumber: " + yourTicket.getTicket() + 
                                            "\n\tYour Numbers: " + Arrays.toString(picks) + 
                                            "\n\tYour PowerBall Number: " + specialPick; 
        return yourTicketDetails; 
    } //end of getQuickPickTicket() method
    
    @Override
    protected String userPickTicket(int[] picks, int specialPick) {
        Arrays.sort(picks);
        
        LotteryTicket yourTicket = new LotteryTicket(picks, specialPick); 
        String yourTicketDetails; 
        
        Arrays.fill(this.ticket, countTicket, (countTicket + 1), yourTicket); 
        countTicket++; 
        
        yourTicketDetails = "PowerBall: " + "\n\tTicket Number: " + yourTicket.getTicket() + 
                                            "\n\tYour Numbers: " + Arrays.toString(picks) + 
                                            "\n\tYour PowerBall Number: " + specialPick; 
        return yourTicketDetails;        
    } //end of userPIckTicket() method    
}
