/**
 * This is the Maze class. It has all of the methods and information needed
 * to build a specific maze and have a MazeRunner get from the start to the end.
 * There are pits and walls that block the path of the user. If the user attempts to
 * move through a wall or a pit Maze throws an exception and terminates the program.
 * The MazeRunner class should never throw an exception.
 */
import java.util.*;

public class MazeRunner {
    public static Maze myMap = new Maze();

    public static void main(String[] args) {
        intro();
    }

    public static void intro() {
        System.out.println("Welcome to Maze Runner!");
        System.out.println("Here is your current position:");
        myMap.printMap();
        game();
    }

    public static void game() {
        int moves = 0;
        while (!myMap.didIWin() && moves <= 100) {
            movesMessage(moves);
            userMove();
            moves += 1;
            if (myMap.didIWin()) {
                System.out.println("Congratulations, you made it out alive in " + moves + " moves!");
            }
            if (moves % 10 == 0){
                System.out.println(moves + " moves!");
            }
        }
        if (!myMap.didIWin()) {
            System.out.println("Sorry, but you couldn't escape :(");
        }
    }

    public static void movesMessage(int moves) {
        String message = "";
        switch (moves) {
            case 50:
                message = "Warning: You have made 50 moves, you have 50 remaining before the maze exit closes";
                break;
            case 75:
                message = "Alert! You have made 75 moves, you only have 25 moves left to escape.";
                break;
            case 90:
                message = "DANGER! You have made 90 moves, you only have 10 moves left to escape!!";
                break;
            case 100:
                message = "Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[";
                break;
            default:
                break;
        }
        System.out.println(message);
    }

    public static void userMove() {
        Scanner input = new Scanner(System.in);
        String move = "";
        do {
            System.out.println("Where would you like to move? (D(right), A(left), W(up), S(down))");
            move = input.next();
            move = move.toUpperCase();
        } while (!(move.equals("D") || move.equals("A") || move.equals("W") || move.equals("S")));

        boolean flag = true;
        while (flag) {
            if (myMap.isThereAPit(move)) {
                navigatePit(move);
                flag = false;
            } else if (move.equals("D") && myMap.canIMoveRight()) {
                myMap.moveRight();
                myMap.printMap();
                flag = false;
            } else if (move.equals("A") && myMap.canIMoveLeft()) {
                myMap.moveLeft();
                myMap.printMap();
                flag = false;
            } else if (move.equals("W") && myMap.canIMoveUp()) {
                myMap.moveUp();
                myMap.printMap();
                flag = false;
            } else if (move.equals("S") && myMap.canIMoveDown()) {
                myMap.moveDown();
                myMap.printMap();
                flag = false;
            } else {
                System.out.println("Sorry, you've hit the wall");
                myMap.printMap();
                flag = false;
            }
        }
    }

    public static void navigatePit(String move) {
        Scanner input = new Scanner(System.in);
        if (myMap.isThereAPit(move)) {
            System.out.print("Watch out! There's a pit ahead, jump it? ");
            String answer = input.next();
            answer = answer.toUpperCase();
            if (answer.startsWith("Y")) {
                myMap.jumpOverPit(move);
                myMap.printMap();
            }
        }

    }
}
