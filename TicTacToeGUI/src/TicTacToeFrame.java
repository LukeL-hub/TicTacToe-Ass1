import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {
    private TicTacToeTile[][] buttons = new TicTacToeTile[3][3];
    private GameLogic game;
    private JLabel statusLabel;
    private JButton quitButton;

    public TicTacToeFrame() {
        super("Tic Tac Toe");
        game = new GameLogic();
        setupUI();
    }

    private void setupUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Game Board Panel
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        ButtonListener listener = new ButtonListener();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new TicTacToeTile(row, col);
                buttons[row][col].setText(" ");
                buttons[row][col].addActionListener(listener);
                boardPanel.add(buttons[row][col]);
            }
        }

        // Control Panel
        JPanel controlPanel = new JPanel();
        statusLabel = new JLabel("Player X's Turn");
        quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> promptQuit());
        controlPanel.add(statusLabel);
        controlPanel.add(quitButton);

        add(boardPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        pack();
    }

    private void promptQuit() {
        int choice = JOptionPane.showConfirmDialog(this, "Quit the game?", "Quit", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TicTacToeTile tile = (TicTacToeTile) e.getSource();
            int row = tile.getRow();
            int col = tile.getCol();

            if (!game.isValidMove(row, col)) {
                JOptionPane.showMessageDialog(null, "Invalid move! Try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            game.makeMove(row, col);
            tile.setText(game.getCurrentPlayer());
            checkGameState();
            game.switchPlayer();
            statusLabel.setText("Player " + game.getCurrentPlayer() + "'s Turn");
        }

        private void checkGameState() {
            if (game.checkWin()) {
                String winner = game.getCurrentPlayer();
                int choice = JOptionPane.showConfirmDialog(null, "Player " + winner + " wins! Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                handleGameEnd(choice);
            } else if (game.checkTie()) {
                int choice = JOptionPane.showConfirmDialog(null, "It's a tie! Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                handleGameEnd(choice);
            }
        }

        private void handleGameEnd(int choice) {
            if (choice == JOptionPane.YES_OPTION) {
                game.reset();
                resetBoard();
            } else {
                System.exit(0);
            }
        }

        private void resetBoard() {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    buttons[row][col].setText(" ");
                }
            }
            statusLabel.setText("Player X's Turn");
        }
    }
}