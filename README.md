# ISU-2025-26
a tic tac toe :D

**boolean game**: used in the while loop - game is changed to false if user does not desire to replay the game and exits the loop

**int option**: user input of whether they want Player vs Player or Player vs AI

**String otherPlayer**: On the other player’s turn, this string is printed: if P1 is against the AI, the value is “Computer”

_^ the above are interchangeable - later in my code I put (otherPlayer == “Computer”) in the if statement instead of (option == 2). the latter is for printing and the former was used to get user input_

**boolean AITurn**: whether or not it’s the AI’s turn - every turn, checks whether to let player input or tell AI to input

**int xoAssignment**: assigns Player 1 X or O using the random math method. 

**char currentTurn**: whether the current turn is X or O.

**char[] board**: the current board, holds numbers 1 to 9 at initialization but will be changed each turn when someone plays. this is used for both printing and internal processing of the state of the game

**int[] wins**: each digit is the index on the board needed to make a 3-in-a-row. Note that 2-digits like “12” represent “012”. used to let the AI check for 2-in-a-rows.
Note: would’ve been easier to also code checking for victories using this array, but I created the array while making the AI and therefore after I made the game winnable

**int victory**: who wins: when victory == 0, the default value, it means there’s been a tie. victory == 1 means Player 1 wins, and 2 means Player 2 won. the value is changed to -1 when anyone wins, though later an if statement changes it to 1 or 2 based on the currentTurn of the winning turn.

**int i**: the loop control variable of the game’s for loop, or the current turn number (the ith turn)

**int arrayCounter**: this variable is declared/used many times (inside the for loop and again outside), though it is always used to print the board: it holds a certain board[] index, then moves to the next one once it’s been printed
Line 78 - int j: prints each row repeating “ ? |” thrice
Line 76 - int n: repeats the printing of each row 3 times

**int slot**: the user input of which square to place their X or O in: note that this is the “display value” of the board, which is 1 greater than the corresponding index

**boolean turn**: whether or not the AI has taken a turn. since the AI has priorities on where to place X or O, once passing one condition it will move to the next. having this turn variable ensures the AI doesn’t act twice

**char checkFor**: how the AI checks for 2-in-a-rows. It first checks for its own 2-in-a-rows (checkFor = currentTurn), as if it can win the game in 1 turn no further consideration is needed. If it has none, it checks for the opponent’s 2-in-a-rows, so checkFor is changed to the “opposite” value with an if loop

**int[] digits**: an array of 3 integers used to hold the digits of each value in wins[]. As stated earlier, these integers are the indices of possible 3-in-a-rows, thus digits[] is a line.

**int repeatTwice**: the control variable of a for loop allowing both values of checkFor to be checked; at the end of the first repeat, checkFor is changed

**int repeat8**: the control variable of the for loop allowing each value of wins[] to be checked. If the turn has already been taken, repeat8 is changed to 8 to terminate the loop

**int[] strategyOrder**: if all other strategies are inapplicable, the AI cycles through the values in this array using a for each loop to decide where to take the turn.
