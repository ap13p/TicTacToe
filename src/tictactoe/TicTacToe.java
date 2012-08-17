
package tictactoe;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TicTacToe {

    private static String table;
    private static String[] isiTable;
    private BufferedReader dataIn;
    private Random randInt;

    public TicTacToe () {
        isiTable = new String[9];
        dataIn = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < isiTable.length; i++) {
            isiTable[i] = " ";
        }
        randInt = new Random();
    }

    private void printTable () {
        table = "+---+---+---+\n"
                + "| " + isiTable[0] + " | " + isiTable[1] + " | " + isiTable[2] + " |\n"
                + "+---+---+---+\n"
                + "| " + isiTable[3] + " | " + isiTable[4] + " | " + isiTable[5] + " |\n"
                + "+---+---+---+\n"
                + "| " + isiTable[6] + " | " + isiTable[7] + " | " + isiTable[8] + " |\n"
                + "+---+---+---+\n";
        System.out.println(table);
    }

    private int getRandomNumber () {
        int start = 1, end = 9;
        long range = (long) end - (long) start + 1;
        long fraction = (long) (range * randInt.nextDouble());
        int randNumber = (int) (fraction + start);
        return randNumber;
    }

    private boolean isEmpty (int index) {
        return isiTable[index - 1].equals(" ");
    }

    private void getUserInput () {
        System.out.print(">> ");
        String input = "";
        try {
            input = dataIn.readLine();
        } catch (IOException ex) {
            Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
        }
        int inputInt = 0;
        try {
            inputInt = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            getUserInput();
        }
        if (0 < inputInt && inputInt < 9) {
            if (isEmpty(inputInt)) {
                isiTable[inputInt - 1] = "x";
            } else {
                getUserInput();
            }
        }
    }

    private void compInput () {
        int input = getRandomNumber();
        System.out.println(input);
        if (isEmpty(input)) {
            try {
                isiTable[input - 1] = "o";
            } catch (ArrayIndexOutOfBoundsException e) {
                compInput();
            }
        } else {
            compInput();
        }
    }

    private boolean check(){
        // horizontal
        if(isiTable[0].equals("o") && isiTable[1].equals("o") && isiTable[2].equals("o")
                || isiTable[0].equals("x") && isiTable[1].equals("x") && isiTable[2].equals("x"))
            return true;
        if(isiTable[3].equals("o") && isiTable[4].equals("o") && isiTable[5].equals("o")
                || isiTable[3].equals("x") && isiTable[4].equals("x") && isiTable[5].equals("x"))
            return true;
        if(isiTable[6].equals("o") && isiTable[7].equals("o") && isiTable[8].equals("o")
                || isiTable[6].equals("x") && isiTable[7].equals("x") && isiTable[8].equals("x"))
            return true;

        // diagonal
        if(isiTable[0].equals("o") && isiTable[4].equals("o") && isiTable[8].equals("o")
                || isiTable[0].equals("x") && isiTable[4].equals("x") && isiTable[8].equals("x"))
            return true;
        if(isiTable[3].equals("o") && isiTable[4].equals("o") && isiTable[6].equals("o")
                || isiTable[3].equals("x") && isiTable[4].equals("x") && isiTable[6].equals("x"))
            return true;

        // vertical
        if(isiTable[0].equals("o") && isiTable[3].equals("o") && isiTable[6].equals("o")
                || isiTable[0].equals("x") && isiTable[3].equals("x") && isiTable[6].equals("x"))
            return true;
        if(isiTable[1].equals("o") && isiTable[4].equals("o") && isiTable[7].equals("o")
                || isiTable[1].equals("x") && isiTable[4].equals("x") && isiTable[7].equals("x"))
            return true;
        if(isiTable[2].equals("o") && isiTable[5].equals("o") && isiTable[8].equals("o")
                || isiTable[2].equals("x") && isiTable[5].equals("x") && isiTable[8].equals("x"))
            return true;
        return false;
    }

    public void update () {
        if(check()){
            System.out.println("Someone is win!");
            System.exit(0);
        }
        getUserInput();
        compInput();
    }

    public void render () {
        try{
            Thread.sleep(10);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        printTable();
    }

    public static void main (String[] args) {
        TicTacToe game = new TicTacToe();
        while (true) {
            game.render();
            game.update();
        }
    }
}
