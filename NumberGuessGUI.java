import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessGUI extends JFrame implements ActionListener {

    // Declare global variables to be used across multiple methods
    static final int MAX_RANGE_EASY = 50;
    static final int MAX_RANGE_NORMAL = 100;
    static final int MAX_RANGE_HARD = 200;

    private JComboBox<String> difficultyComboBox;
    private JButton startButton;
    private JTextField customRangeField;
    private JTextField customGuessCountField;

    public NumberGuessGUI() {
        // Set up the frame
        setTitle("Number Guess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new FlowLayout());

        // Add components
        add(new JLabel("Choose difficulty: "));
        String[] difficulties = {"Easy", "Normal", "Hard", "Insane", "Reverse Russian Roulette", "Custom"};
        difficultyComboBox = new JComboBox<>(difficulties);
        difficultyComboBox.addActionListener(this);
        add(difficultyComboBox);

        startButton = new JButton("Start");
        startButton.addActionListener(this);
        add(startButton);

        // Custom difficulty fields
        customRangeField = new JTextField(5);
        customRangeField.setEnabled(false); // Initially disabled
        customGuessCountField = new JTextField(5);
        customGuessCountField.setEnabled(false); // Initially disabled
        add(new JLabel("Custom Range: "));
        add(customRangeField);
        add(new JLabel("Custom Guess Count: "));
        add(customGuessCountField);

        // Set visibility
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NumberGuessGUI::new);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == difficultyComboBox) {
            // Enable or disable custom fields based on the selected difficulty
            int selectedDifficultyIndex = difficultyComboBox.getSelectedIndex();
            customRangeField.setEnabled(selectedDifficultyIndex == 5); // Custom index is 5
            customGuessCountField.setEnabled(selectedDifficultyIndex == 5); // Custom index is 5
        } else if (e.getSource() == startButton) {
            int difficulty = difficultyComboBox.getSelectedIndex();
            int maxRange;
            int guessCount;

            switch (difficulty) {
                case 0:
                    maxRange = MAX_RANGE_EASY;
                    guessCount = 10;
                    break;
                case 1:
                    maxRange = MAX_RANGE_NORMAL;
                    guessCount = 5;
                    break;
                case 2:
                    maxRange = MAX_RANGE_HARD;
                    guessCount = 3;
                    break;
                case 3:
                    maxRange = 500; // Insane mode
                    guessCount = 3;
                    break;
                case 4:
                    maxRange = 1000; // Reverse Russian Roulette
                    guessCount = 1;
                    break;
                case 5:
                    // Custom mode
                    maxRange = Integer.parseInt(customRangeField.getText());
                    guessCount = Integer.parseInt(customGuessCountField.getText());
                    break;
                default:
                    maxRange = 0;
                    guessCount = 0;
                    break;
            }

            // Start the game with the chosen difficulty
            playGame(maxRange, guessCount);
        }
    }

    static void playGame(int maxRange, int attempts) {
        Random random = new Random();
        int randomNumber = random.nextInt(maxRange) + 1;
        int guessedNumber;
        int attemptedGuesses = 0;

        do {
            String input = JOptionPane.showInputDialog("Enter your guess (1-" + maxRange + "):");
            if (input == null) // User clicked cancel
                return;
            guessedNumber = Integer.parseInt(input);
            attemptedGuesses++;

            if (guessedNumber == randomNumber) {
                JOptionPane.showMessageDialog(null, "Congratulations! You guessed the number!");
                JOptionPane.showMessageDialog(null, "You had " + attempts + " guesses left and you guessed " + attemptedGuesses + " times!");
                return;
            } else if (guessedNumber < randomNumber) {
                JOptionPane.showMessageDialog(null, "Too low! Try again.");
            } else {
                JOptionPane.showMessageDialog(null, "Too high! Try again.");
            }
            attempts--;
        } while (attempts > 0);

        JOptionPane.showMessageDialog(null, "Sorry, you're out of guesses. The number was " + randomNumber + ".");
    }
}
