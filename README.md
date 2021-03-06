<h1 align="center">Lottery</h1>

## Description
This program simulates a person playing the lottery. Allows the user to purchase either a powerball or megamillions ticket. This program 
uses inheritance to increase the reusability of code from other classes. I will include detailed notes inside each file explaining what 
each part does. 

This program has five different classes: Lottery, LotteryTicket, LotteryGame, Powerball, and Megamillions.
 - **_Lottery:_** this class is the application of the Lottery subclasses. This will create all of the menus, allow the 
user to purchase their lottery tickets, and generate the winning numbers. When a user "purchases" a ticket, this class will 
use the respect subclass and set up the ticket using the rules defined by either powerball or megamillions. Checks to 
see if the users ticket is a winner.
 - **_LotteryTicket:_** this class represents purchasing a lottery ticket. Inside this class, it refrences the current object 
to create a powerball or megamillion ticket. It takes the users picks, sorts them and converts them to a string varaible. 
- **_LotteryGame:_** is an abstract superclass for the the two following classes PowerBall and MegaMillions. The reason we use
an abstract class like this is because both powerball and megamillions have different aspects about each, but both fall under
a lottery ticket. A powerball's max pick is 69 and the megamillions max pick is 75, but they each have a max pick. The purpose 
of a abstract class is to be used as a base for their subclasses. This class does use a getPicks(), isRepeat(), and countMatches()
methods since this can be used for each subclass.
- **_PowerBall:_** one of the subclasses for abstract class LotteryGame. Using inheritance, this allows reusing of fields and methods 
without having to write extra code. The class Powerball uses getAmountWon(), quickPickTicket(), and userPickTicket() methods. These 
three are also found in the next subclass, but since Powerball and Megamillions has different rules they abide under. These methods 
will differ slightly. 
- **_MegaMillions:_** the last subclass for abstract class LotteryGame. Similar to Powerball, it uses the same methods but changes 
them to fit the criteria of the Megamillions rules. 

## Outcome
<p align="center">
  <img src="https://user-images.githubusercontent.com/80684500/171483687-1bd3d43e-2bfe-43e4-8899-c6465e33d69f.JPG" alt="Sublime's custom image"/>
  <img src="https://user-images.githubusercontent.com/80684500/171483689-915128d6-7bcc-4e3b-be2d-6619d0bff69a.JPG" alt="Sublime's custom image"/>
  <img src="https://user-images.githubusercontent.com/80684500/171483691-10454eb1-6cc3-41b4-9f17-3733df734d76.JPG" alt="Sublime's custom image"/>
</p>

## Projects
|  Num  | Project                                                                                                 | Author                                            |
| ----- | ------------------------------------------------------------------------------------------------------- | --------------------------------------------------|
|   1   | [Bookstore](https://github.com/JamesSmith232/BookStore)                                                 | [James Smith](https://github.com/JamesSmith232)   |
|   2   | [Lottery](https://github.com/JamesSmith232/Lottery)                                                     | [James Smith](https://github.com/JamesSmith232)   |
|   3   | [FizzBuzz](https://github.com/JamesSmith232/FizzBuzz)                                                   | [James Smith](https://github.com/JamesSmith232)   |
|   4   | [RockPaperScissors](https://github.com/JamesSmith232/RockPaperScissors)                                 | [James Smith](https://github.com/JamesSmith232)   |
|   5   | [Grader](https://github.com/JamesSmith232/Grader)                                                       | [James Smith](https://github.com/JamesSmith232)   |
|   6   | [StoreManager](https://github.com/JamesSmith232/StoreManager)                                           | [James Smith](https://github.com/JamesSmith232)   |
|   7   | [TicTacToe](https://github.com/JamesSmith232/TicTacToe)                                                 | [James Smith](https://github.com/JamesSmith232)   |

