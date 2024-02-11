import java.util.Scanner;
import java.util.Random;

class Main {
    static final String GAME_START = "Welcome to dice game 12. You must roll 1-3 dice and try to get the sum of 12 ...\n";
    static final String CHOOSE_DICE = "Enter which dice you want to roll [1,2,3] (exit with q): ";
    static final String ROUND_WON = "You won!!";
    static final String ROUND_LOST = "You lost!!";
    static final String ROUND_TIE = "You neither won nor lost the round.";
    static final String NEXT_ROUND = "Next round!";
    static final String GAME_OVER = "Game Over!";
    static final String ALREADY_SELECTED_DICE = "Sorry, you have already rolled that dice. Try again";
    static final String INVALID_ENTRY = "Sorry, that is an invalid entry. Try again. Valid entries are 1, 2, 3, and q\n";
    static final String AMOUNT_WIN_STRING = "#win: ";
    static final String AMOUNT_LOST_STRING = " #loss: ";
    static final String SUM_STRING = " sum: ";
    static final int MAX_DICE_VALUE = 6;
    static final int MIN_DICE_VALUE = 1;
    static final int DICE_SUM_TARGET_VALUE = 12;

    public static void main(final String[] args) {

        int winCounter = 0;
        int lossCounter = 0;

        Scanner userInput = new Scanner(System.in);
        Random rand = new Random();

        System.out.println(GAME_START);

        while (true) {
            int dice1Value = 0, dice2Value = 0, dice3Value = 0;
            boolean isDice1Rolled = false, isDice2Rolled = false, isDice3Rolled = false;
            int sum = 0;

            for (int i = 0; i < 3; i++) {
                System.out.print(CHOOSE_DICE);
                String input = userInput.nextLine().trim();

                if ("q".equalsIgnoreCase(input)) {
                    System.out.println(AMOUNT_WIN_STRING + winCounter + AMOUNT_LOST_STRING + lossCounter);
                    System.out.println(GAME_OVER);
                    userInput.close();
                    return;
                }

                int diceChoice;
                try {
                    diceChoice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println(INVALID_ENTRY);
                    i--; // to retry the current iteration
                    continue;
                }

                if (diceChoice < 1 || diceChoice > 3 ||
                        (diceChoice == 1 && isDice1Rolled) ||
                        (diceChoice == 2 && isDice2Rolled) ||
                        (diceChoice == 3 && isDice3Rolled)) {
                    System.out.println(ALREADY_SELECTED_DICE);
                    i--; // to retry the current iteration
                    continue;
                }

                // Roll the selected dice
                int roll = rand.nextInt(MAX_DICE_VALUE) + MIN_DICE_VALUE;
                sum += roll;

                if (diceChoice == 1) { dice1Value = roll; isDice1Rolled = true; }
                if (diceChoice == 2) { dice2Value = roll; isDice2Rolled = true; }
                if (diceChoice == 3) { dice3Value = roll; isDice3Rolled = true; }

                System.out.println(dice1Value + " " + dice2Value + " " + dice3Value + SUM_STRING + sum);

                // Check win/loss condition after each roll
                if (sum >= DICE_SUM_TARGET_VALUE) break;
            }

            if (sum == DICE_SUM_TARGET_VALUE) {
                winCounter++;
                System.out.println(ROUND_WON);
            } else if (sum > DICE_SUM_TARGET_VALUE) {
                lossCounter++;
                System.out.println(ROUND_LOST);
            } else {
                System.out.println(ROUND_TIE);
            }

            System.out.println(NEXT_ROUND);
        }
    }
}
