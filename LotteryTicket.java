/** 
Class LotteryTicket represents a lottery ticket purchased by the customer
    -ID Number for ticket
    -Numbers the customer chose
    -Numbers generated by the computer
    
Author: James Smith
Last modified: Feburary 25, 2019
Email: james.smith048@topper.wku.edu    
**/
import java.util.Arrays; 

public class LotteryTicket {
    
    static int nextTicket = 100001; 
    
    protected int ticket; 
    protected int[] pick = new int[5]; 
    protected int specialPick; 
    
    public LotteryTicket(int [] pick, int specialPick) {
        //.this is a reference varaible for the current object 
        this.pick = new int[pick.length]; 
        //fills the current object with user picks 
        for (int i = 0; i < pick.length; i++) {
            this.pick[i] = pick[i]; 
        }
        
        if (specialPick > 0) this.specialPick = specialPick; 
        
        //increments to the next ticket 
        ticket = nextTicket; 
        nextTicket++; 
    } //end of LotteryTicket() method 
    
    //returns the current object's ticket number
    public int getTicket() {
        return this.ticket; 
    } //end of getTicket() method
    
    public int[] getPicks() {
        int[] copyPicks = new int[this.pick.length];
        
        //fills copyPicks array with current objects values
        for (int i = 0; i < this.pick.length; i++) {
            copyPicks[i] = this.pick[i]; 
        }
        
        //sorts array using .sort() function
        Arrays.sort(copyPicks); 
        return copyPicks; 
    } //end of getPicks() method
    
    public int getSpecialPick() {
        return this.specialPick; 
    } //end of getSpecialPick() method
    
    //converts the picks to a string, which will be displayed to the user
    public String toString() {
        int[] sortedPick = new int[this.pick.length];
        sortedPick = this.pick; 
        Arrays.sort(sortedPick);
        String ticket = "Ticket Number: " + this.ticket + "\nPicks: " + Arrays.toString(sortedPick) + "\nSpecial Pick: " + this.specialPick;
        
        return ticket;      
    } //end of toString() method
    
    //uses the .equals() function to check if the current object equals the ticket variable 
    public boolean equals(int[] ticket) {
        if (Arrays.equals(this.pick, ticket)) return true; 
        else return false; 
    } //end of equals() method 
}
