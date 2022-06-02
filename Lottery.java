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
        do {
            mainMenu(); 
            userChoice = getUserChoice(); 
            
            switch (userChoice) {
                case BUY: 
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
        Arrays.fill(userPicks, MIN, MAX, 0); 
        
        ticketMenu(); 
        userChoice = getUserChoice(); 
        
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
    
    static void checkMoney() {
        String amountWon; 
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
        
        if (choice == USER_NUM) {
            System.out.println("\nYou have chosen to pick your own numbers!"); 
            System.out.println("\nPlease choose between 5 unique numbers between 1-69: ");
            for (int i = 0; i < userPicks.length; ) {
                System.out.print("Number " + (i + 1) + ": "); 
                pick = keyboard.nextInt(); 
                
                if ((pick >= plays[POWERBALL].getMinPick()) && (pick < plays[POWERBALL].getMaxPick())) {
                    if (isRepeat(pick) == false) {
                        userPicks[i] = pick; 
                        i++; 
                    } else System.out.println(repeatedMessage); 
                } else System.out.println(invalidMessage); 
            }
            
            System.out.println("\nNow choose a one number between 1-26, this will be your special pick: "); 
            while (isCorrectRange == false) {
                System.out.println("\nPowerball Number: "); 
                specialPick = keyboard.nextInt(); 
                
                if ((specialPick >= plays[POWERBALL].getMinSpecialPick()) && (specialPick <= plays[POWERBALL].getMaxSpecialPick())) {
                    isCorrectRange = true; 
                } else System.out.println(invalidMessage); 
            }
            userTicket = plays[POWERBALL].userPickTicket(userPicks, specialPick); 
        }
        else if (choice == GENERATED_NUM) {
            System.out.println("You have chosen computer generated numbers"); 
            userTicket = plays[POWERBALL].quickPickTicket(); 
        }
        
        System.out.println("\n" + purchaseMessage); 
    } //end of powerballNumbers() method
    
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
    
    static void mainMenu() {
        System.out.println("\n====================================================================");
        System.out.println("\t" + BUY + "\tBuy a ticket for Powerball or MegaMillions\n"); 
        System.out.println("\t" + CHECK_MONEY + "\tCheck if you won!\n"); 
        System.out.println("\t" + QUIT + "\tTo exit"); 
        System.out.println("====================================================================\n");
    } //end of printMenu() method 
    
    static void ticketMenu() {
        System.out.println("\n====================================================================");
        System.out.println("\t" + POWERBALL + "\tBuy a Powerball ticket\n"); 
        System.out.println("\t" + MEGAMILLION + "\tBuy a MegaMillion ticket");  
        System.out.println("====================================================================\n");
    } //end of ticketMenu() method
    
    static void numberMenu() {
        System.out.println("\n====================================================================");
        System.out.println("\t" + USER_NUM + "\tChoose your own numbers\n"); 
        System.out.println("\t" + GENERATED_NUM + "\tGet random generated numbers");  
        System.out.println("====================================================================\n");
    } //end of numberMenu() method
    
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
    
    static boolean isRepeat(int number) {
        boolean isRepeat = false; 
        
        for (int i = 0; i < userPicks.length; i++) {
            if (userPicks[i] == number) isRepeat = true; 
        }
        
        return isRepeat; 
    } //end of isRepeat() method
}




























