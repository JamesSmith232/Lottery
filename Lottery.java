/** 
This class will be the application of the LotteryGame subclasses.
    - Allows the user to buy Powerball or MegaMillions
    - Check how much they have won 
    
Author: James Smith
Last modified: Feburary 25, 2019
Email: james.smith048@topper.wku.edu    
**/

import java.util.Scanner; 
import java.util.Arrays; 

public class Lottery {
    
    //globally decalred varaibles to be used throughout program 
    static LotteryGame[] plays = new LotteryGame[2]; 
    static int[] userPicks; 
    
    public static final int BUY = 0; 
    public static final int CHECK_MONEY = 1; 
    public static final int QUIT = 2; 
    
    public static final int POWERBALL = 0; 
    public static final int MEGAMILLION = 1; 
    
    public static final int USER_NUM = 0; 
    public static final int GENERATED_NUM = 1; 
    
    static Scanner keyboard = new Scanner(System.in); 
    static String quitMessage = "\nThank you for playing the Lottery!\n";
    static String invalidMessage = "\nInvalid option, please try again.\n";
    static String repeatedMessage = "\nRepeated number, please try again.\n";
    static String purchaseMessage = "\nThank you for your purchase! Check to see if you won.\n";
    static String userTicket; 
    static int ticketCount = 100005; 
    static int ticketType; 
    static int userChoice = QUIT; 
    
    public static void main(String[] args) { 
        //creates the menu until user quits
        do {
            mainMenu(); 
            userChoice = getUserChoice(); 
            
            switch (userChoice) {
                case BUY: 
                    //fillLotter() changes the winning ticket everytime the buy case is selected
                    fillLottery();
                    buyTickets(); 
                    break; 
                case CHECK_MONEY: 
                    checkMoney(); 
                    break;
                case QUIT:
                    System.out.println(quitMessage); 
                    break; 
                default: 
                    System.out.println(invalidMessage); 
            }
        } while(userChoice != QUIT); 
    } //end of main() method
    
    static void buyTickets() {
        final int MAX = 5;
        final int MIN = 0; 
        //clears the userPicks[] with 0's, which allows for multiple tickets to be played
        Arrays.fill(userPicks, MIN, MAX, 0); 
        
        //choosing between powerball or megamillion
        ticketMenu(); 
        userChoice = getUserChoice(); 
        
        //Goes through both options for each ticket, asks the user if they would like to manually
        //enter in or have randomly generated numbers. Assigns ticketType to which ever ticket is 
        //chosen, this varaible is globally declared and will be used in the next method 
        switch (userChoice) {
            case POWERBALL: 
                numberMenu(); 
                userChoice = getUserChoice();
                powerballNum(userChoice); 
                ticketType = POWERBALL; 
                break; 
            case MEGAMILLION: 
                numberMenu(); 
                userChoice = getUserChoice();
                megamillionNum(userChoice); 
                ticketType = MEGAMILLION; 
                break; 
            default: 
                System.out.println(invalidMessage); 
        }
    } //end of buyTickets() method
    
    //calls the LotteryGame class and finds the amount won with the ticket number
    static void checkMoney() {
        String amountWon; 
        //getAmountWon() is an abstract method that both subclasses have overriden, 
        //which means that they do similar things but receive different results based
        //on the different rules of Powerball and Megamillions 
        switch (ticketType) {
            case POWERBALL: 
                amountWon = plays[POWERBALL].getAmountWon(ticketCount); 
                ticketCount++; 
                System.out.println("\n" + userTicket); 
                System.out.println("\n" + amountWon); 
                break; 
            case MEGAMILLION: 
                amountWon = plays[MEGAMILLION].getAmountWon(ticketCount); 
                System.out.println("\n" + userTicket); 
                System.out.println("\n" + amountWon); 
                break;
            default: 
                System.out.println(invalidMessage); 
        }
    } //end of checkMoney() method
    
    static void powerballNum(int choice) {
        int pick; 
        int specialPick = 0; 
        boolean isCorrectRange = false; 
        
        //checks if the user wants to pick his own numbers
        if (choice == USER_NUM) {
            System.out.println("\nYou have chosen to pick your own numbers!"); 
            System.out.println("\nPlease choose between 5 unique numbers between 1-69: ");
            //loop to get the users picks, only incremented if user chooses right 
            for (int i = 0; i < userPicks.length; ) {
                System.out.print("Number " + (i + 1) + ": "); 
                pick = keyboard.nextInt(); 
                
                //checks to see if each pick is in correct range and not chosen before 
                if ((pick >= plays[POWERBALL].getMinPick()) && (pick < plays[POWERBALL].getMaxPick())) {
                    if (isRepeat(pick) == false) {
                        userPicks[i] = pick;
                        i++; 
                    } else System.out.println(repeatedMessage); 
                } else System.out.println(invalidMessage); 
            }
            
            //asks user for special pick, and checks if it is in the correct range
            System.out.println("\nNow choose a one number between 1-26, this will be your special pick: "); 
            while (isCorrectRange == false) {
                System.out.println("\nPowerball Number: "); 
                specialPick = keyboard.nextInt(); 
                
                if ((specialPick >= plays[POWERBALL].getMinSpecialPick()) && (specialPick <= plays[POWERBALL].getMaxSpecialPick())) {
                    isCorrectRange = true; 
                } else System.out.println(invalidMessage); 
            }
            //creates powerball ticket with userPicks[] and specialPick variable 
            userTicket = plays[POWERBALL].userPickTicket(userPicks, specialPick); 
        }
        //if user chose to have his numbers randomly generated, calls the quickPickTicket() 
        //which chooses ticket randomly and assigns it to the userTicket varaible 
        else if (choice == GENERATED_NUM) {
            System.out.println("You have chosen computer generated numbers"); 
            userTicket = plays[POWERBALL].quickPickTicket(); 
        }
        
        System.out.println("\n" + purchaseMessage); 
    } //end of powerballNumbers() method
    
    //Does the same thing as the powerballNum() method but uses the megaMillions format rather than powerball 
    static void megamillionNum(int choice) {
        int pick; 
        int specialPick = 0; 
        boolean isCorrectRange = false; 
        
        if (choice == USER_NUM) {
            System.out.println("\nYou have chosen to pick your own numbers!"); 
            System.out.println("\nPlease choose between 5 unique numbers between 1-75: ");
            for (int i = 0; i < userPicks.length; ) {
                System.out.print("Number " + (i + 1) + ": "); 
                pick = keyboard.nextInt(); 
                
                if ((pick >= plays[MEGAMILLION].getMinPick()) && (pick < plays[MEGAMILLION].getMaxPick())) {
                    if (isRepeat(pick) == false) {
                        userPicks[i] = pick; 
                        i++; 
                    } else System.out.println(repeatedMessage); 
                } else System.out.println(invalidMessage); 
            }
            
            System.out.println("\nNow choose a one number between 1-15, this will be your special pick: "); 
            while (isCorrectRange == false) {
                System.out.println("\nMegaMillion Number: "); 
                specialPick = keyboard.nextInt(); 
                
                if ((specialPick >= plays[MEGAMILLION].getMinSpecialPick()) && (specialPick <= plays[MEGAMILLION].getMaxSpecialPick())) {
                    isCorrectRange = true; 
                } else System.out.println(invalidMessage); 
            }
            userTicket = plays[MEGAMILLION].userPickTicket(userPicks, specialPick); 
        }
        else if (choice == GENERATED_NUM) {
            System.out.println("You have chosen computer generated numbers"); 
            userTicket = plays[MEGAMILLION].quickPickTicket(); 
        }
        
        System.out.println("\n" + purchaseMessage); 
    } //end of megaMillions() method
    
    //creates the main menu which allows user to buy tickets, check winnings, and quit 
    static void mainMenu() {
        System.out.println("\n====================================================================");
        System.out.println("\t" + BUY + "\tBuy a ticket for Powerball or MegaMillions\n"); 
        System.out.println("\t" + CHECK_MONEY + "\tCheck if you won!\n"); 
        System.out.println("\t" + QUIT + "\tTo exit"); 
        System.out.println("====================================================================\n");
    } //end of printMenu() method 
    
    //creates the ticket menu which allows the user to buy a powerball or megamillion ticket
    static void ticketMenu() {
        System.out.println("\n====================================================================");
        System.out.println("\t" + POWERBALL + "\tBuy a Powerball ticket\n"); 
        System.out.println("\t" + MEGAMILLION + "\tBuy a MegaMillion ticket");  
        System.out.println("====================================================================\n");
    } //end of ticketMenu() method
    
    //creates the number menu which allows the user to choose between their own numbers or randomly generated numbers
    static void numberMenu() {
        System.out.println("\n====================================================================");
        System.out.println("\t" + USER_NUM + "\tChoose your own numbers\n"); 
        System.out.println("\t" + GENERATED_NUM + "\tGet random generated numbers");  
        System.out.println("====================================================================\n");
    } //end of numberMenu() method
    
    //used to get user's choice through out the program, helps with code redundancy 
    static int getUserChoice() {
        System.out.println("Please enter your choice: "); 
        int choice = keyboard.nextInt(); 
        
        return choice;
    } //end of getUserchoice() method
    
    static void fillLottery() {
        LotteryGame powerball = new PowerBall(); 
        LotteryGame megamillion = new MegaMillions(); 
        
        plays[0] = powerball; 
        plays[1] = megamillion; 
        userPicks = new int[plays[POWERBALL].getNumOfPicks()];
    } //end of fillLottery() method
    
    //checks if users number are all unique
    static boolean isRepeat(int number) {
        boolean isRepeat = false; 
        
        //goes through entire array and checks if the user's selection matches with a number in the array 
        //if it is, isRepeat = true which means the user will have to choose a different number
        for (int i = 0; i < userPicks.length; i++) {
            if (userPicks[i] == number) isRepeat = true; 
        }
        
        return isRepeat; 
    } //end of isRepeat() method
}
