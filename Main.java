// NAME: LUCY EVEDZI
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("3x3 Tic-tac-toe"); // Introductory message
        Scanner sc = new Scanner(System.in);

        boolean game = true;
        while (game) { // While loop to ensure the game is replayable
            System.out.println("Choose an option:\n [1] Player 1 vs Player 2 \n [2] Player vs AI");
            int option = 0;
            while (option == 0) { // Handles incorrect input
                option = sc.nextInt();
                if (option != 1 && option != 2) {
                    option = 0;
                    System.out.println("Error: Input the number 1 or 2");
                }
            }

            String otherPlayer; // Shortcut for printing
            if (option == 1)
                otherPlayer = "Player 2";
            else
                otherPlayer = "Computer";

            boolean AITurn = false;

            // Assigning the player X or O...
            int xoAssignment = (int)(2*Math.random());
            if (xoAssignment == 0) { // O looks like an ⭕, so player is O
                System.out.println("Player 1: ⭕");
                System.out.println(otherPlayer + ": ❌");
            }
            else if (xoAssignment == 1){
                System.out.println("Player 1: ❌");
                System.out.println(otherPlayer + ": ⭕");
            }

            char currentTurn = 'X'; // X always goes first
            System.out.println("X goes first.");

            if (xoAssignment == 0 && option == 2) { // If P1 goes second and versus computer,
                AITurn = true; // then the AI starts the game.
            }

            // the current board - empty rn
            char[] board = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
            // the 3-in-a-rows represented as 3-digits (used for AI strat)
            int[] wins = {12, 345, 678, 36, 147, 258, 48, 246};
            // tie/win/etc
            int victory = 0; 

            // Repeats 9 times for 9 turns before defaulting to a tie
            // The body of the game!!
            for (int i = 1; i <= 9; i++) {
                // Prints who's turn it is
                System.out.print(currentTurn + " - ");
                if (currentTurn == 'X' && xoAssignment == 1) {
                    //It's P1's turn!
                    System.out.println("Player 1's turn:\n");
                }
                else if (currentTurn == 'X' && xoAssignment == 0) {
                    System.out.println(otherPlayer + "'s turn:\n");
                }
                else if (xoAssignment == 0) {
                    System.out.println("Player 1's turn:\n");
                }
                else if (xoAssignment == 1) {
                    System.out.println(otherPlayer + "'s turn:\n");
                }

                // Print Board
                System.out.println("|---|---|---|");
                int arrayCounter = 0;
                for (int n = 0; n < 3; n++) {
                    System.out.print("|");
                    for (int j = 0; j < 3; j++) {
                        System.out.print(" " + board[arrayCounter] + " |");
                        arrayCounter++;
                    }
                    System.out.println("\n|---|---|---|");
                }
                System.out.println();

                if (!AITurn){ // PLAYER TURN
                    System.out.println("Enter slot number to place " + currentTurn + ":");
                    int slot = 0;
                    while (slot == 0) { // Handles incorrect input
                        slot = sc.nextInt();
                        if (slot >= 1 && slot <= 9) {
                            if ("XO".contains(String.valueOf(board[slot-1]))){
                                System.out.println("Space already taken! Choose another.");
                                slot = 0;
                            }
                        }
                        else {
                            slot = 0;
                            System.out.println("Error: choose a number between 1 and 9.");
                        }
                    }
                    board[slot-1] = currentTurn;
                }

                else { // COMPUTER TURN:
                    boolean turn = false;
                    if (i == 4 && board[4] == 'O') {
                        if (board[0] == 'X' && board[8] == 'X') {
                            board[3] = 'O';
                            turn = true;
                        } else if (board[2] == 'X' && board[6] == 'X') {
                            board[3] = 'O';
                            turn = true;
                        }
                    }
                    if (i == 1) { // Turn 1
                        board[0] = currentTurn;
                    } else if (i == 2) {
                        if (board[4] == '5')
                            board[4] = 'O';
                        else
                            board[0] = 'O';
                    } else if (i == 3) { // Turn 2
                        if (board[8] == '9')
                            board[8] = currentTurn;
                        else
                            board[2] = currentTurn;
                    } else if (!turn) {
                        char checkFor = currentTurn;
                        for (int repeatTwice = 0; repeatTwice < 2; repeatTwice++) {
                            for (int repeat8 = 0; repeat8 < 8; repeat8++) {
                                if (!turn) {
                                    turn = true;
                                    int[] digits = new int[3];
                                    digits[0] = wins[repeat8] % 10;
                                    digits[1] = (wins[repeat8]/10)%10;
                                    digits[2] = wins[repeat8]/100;
                                    if (board[digits[0]] == checkFor && board[digits[1]] == checkFor && board[digits[2]] != 'O' && board[digits[2]] != 'X')
                                        board[digits[2]] = currentTurn;
                                    else if (board[digits[0]] == checkFor && board[digits[2]] == checkFor && board[digits[1]] != 'O' && board[digits[1]] != 'X')
                                        board[digits[1]] = currentTurn;
                                    else if (board[digits[2]] == checkFor && board[digits[1]] == checkFor && board[digits[0]] != 'O' && board[digits[0]] != 'X')
                                        board[digits[0]] = currentTurn;
                                    else {
                                        turn = false;
                                    }
                                }
                                if (turn)
                                    repeat8 = 8;
                            }
                            if (checkFor == 'X')
                                checkFor = 'O';
                            else
                                checkFor = 'X';
                            if (turn)
                                repeatTwice = 2;
                        }
                    }
                    if (!turn && i > 3) {
                        // If the player has 2 edges, play in the corresponding corner
                        if (board[1] != currentTurn && board[1] != '2') {
                            // 1 - Belongs to the other player!!
                            if (board[3] != currentTurn && board[3] != '4') {
                                // 1 and 3 belong to other player
                                if (board[0] == '1' && !turn) {
                                    board[0] = currentTurn;
                                    turn = true;
                                }
                            }
                            if (board[5] != currentTurn && board[5] != '6') {
                                // 1 and 5 belong to other player
                                if (board[2] == '1' && !turn) {
                                    board[2] = currentTurn;
                                    turn = true;
                                }
                            }
                        }
                        if (board[7] != currentTurn && board[7] != '8') {
                            // 1 - Belongs to the other player!!
                            if (board[3] != currentTurn && board[3] != '4') {
                                // 1 and 3 belong to other player
                                if (board[6] == '7' && !turn) {
                                    board[6] = currentTurn;
                                    turn = true;
                                }
                            }
                            if (board[5] != currentTurn && board[5] != '6') {
                                // 1 and 5 belong to other player
                                if (board[8] == '9' && !turn) {
                                    board[8] = currentTurn;
                                    turn = true;
                                }
                            }
                        }
                        int[] strategyOrder = {4, 0, 6, 2, 8, 1, 3, 5, 7};
                        for (int n : strategyOrder) {
                            if (board[n] != 'O' && board[n] != 'X' && !turn) {
                                board[n] = currentTurn;
                                turn = true; 
                            } 
                        }
                    }
                }

                victory = 0; // 0 = tie, 1 = p1 win, 2 = p2 win
                // Change i to 10 on the case of victory
                // Checks if game's been won

                // Check original array to see which have been replaced with the current
                // You can only win on your turn!
                if (board[0] == currentTurn) {
                    if (board[1] == currentTurn && board[2] == currentTurn) {
                        victory = -1;
                    }
                    else if (board[3] == currentTurn && board[6] == currentTurn) {
                        victory = -1;
                    }
                }
                if (board[4] == currentTurn) {
                    if (board[1] == currentTurn && board[7] == currentTurn) {
                        victory = -1;
                    }
                    else if (board[3] == currentTurn && board[5] == currentTurn) {
                        victory = -1;
                    }
                    else if (board[2] == currentTurn && board[6] == currentTurn) {
                        victory = -1;
                    }
                }
                if (board[8] == currentTurn) {
                    if (board[2] == currentTurn && board[5] == currentTurn) {
                        victory = -1;
                    }
                    else if (board[0] == currentTurn && board[4] == currentTurn) {
                        victory = -1;
                    }
                    else if (board[6] == currentTurn && board[7] == currentTurn) { // 6 7
                        victory = -1;
                    }
                }

                if (victory == -1) {
                    System.out.println("|---|---|---|");
                    arrayCounter = 0;
                    for (int n = 0; n < 3; n++) {
                        System.out.print("|");
                        for (int j = 0; j < 3; j++) {
                            System.out.print(" " + board[arrayCounter] + " |");
                            arrayCounter++;
                        }
                        System.out.println("\n|---|---|---|");
                    }

                    System.out.println(currentTurn + " wins!");
                    if (xoAssignment == 1 && currentTurn == 'X') // If P1 is X and X wins
                        victory = 1;
                    else if (xoAssignment == 1 || currentTurn == 'X') // If P1 is X and X loses, or if P1 is O and X wins
                        victory = 2;
                    else
                        victory = 1; // the other remaining case!
                }

                // Alternate the turns
                if (option == 2 && !AITurn) {
                    AITurn = true;
                }
                else if (option == 2) { // Implied that it is the AI turn
                    AITurn = false;
                }
                if (currentTurn == 'X')
                    currentTurn = 'O';
                else
                    currentTurn = 'X';

                if (victory != 0)
                    i = 10;

            } // End of game
            if (victory == 0) { // If no one has made the winning move... it's a tie
                System.out.println("|---|---|---|");
                int arrayCounter = 0;
                for (int n = 0; n < 3; n++) {
                    System.out.print("|");
                    for (int j = 0; j < 3; j++) {
                        System.out.print(" " + board[arrayCounter] + " |");
                        arrayCounter++;
                    }
                    System.out.println("\n|---|---|---|");
                }
                System.out.println();
                System.out.println("You tied!");
            }
            else if (victory == 1) { 
                System.out.println("Congratulations, Player 1!");
            }
            else {
                System.out.println("Congratulations, " + otherPlayer + "!");
            }

            System.out.println("To replay, type \"replay\".");
            sc.nextLine();
            String replay = sc.nextLine().toLowerCase();
            game = replay.equals("replay");
        }
        System.out.println("See you next time!");
        sc.close();
    }
}
