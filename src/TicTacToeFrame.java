import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {
    private static final int ROW = 3;
    private static final int COL = 3;
    private TicTacToeButton[][] board;
    private char player;
    private int moveCnt;
    private JButton quitButton;
    private JLabel statusLabel;

    public TicTacToeFrame() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLayout(new BorderLayout());

        initializeBoard();
        player = 'X';
        moveCnt = 0;

        quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Quit Game", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        statusLabel = new JLabel("Player " + player + "'s turn");
        statusLabel.setHorizontalAlignment(JLabel.CENTER);

        add(createBoardPanel(), BorderLayout.CENTER);
        add(quitButton, BorderLayout.SOUTH);
        add(statusLabel, BorderLayout.NORTH);
    }

    private void initializeBoard() {
        board = new TicTacToeButton[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = new TicTacToeButton(i, j);
                board[i][j].addActionListener(new ButtonClickListener());
            }
        }
    }

    private JPanel createBoardPanel() {
        JPanel panel = new JPanel(new GridLayout(ROW, COL));
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                panel.add(board[i][j]);
            }
        }
        return panel;
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TicTacToeButton button = (TicTacToeButton) e.getSource();
            int row = button.getRow();
            int col = button.getCol();

            if (isValidMove(row, col)) {
                board[row][col].setValue(player);
                moveCnt++;

                if (isWin(player)) {
                    JOptionPane.showMessageDialog(TicTacToeFrame.this, "Player " + player + " wins!");
                    resetGame();
                } else if (isTie()) {
                    JOptionPane.showMessageDialog(TicTacToeFrame.this, "It's a Tie!");
                    resetGame();
                } else {
                    player = (player == 'X') ? 'O' : 'X';
                    statusLabel.setText("Player " + player + "'s turn");
                }
            } else {
                JOptionPane.showMessageDialog(TicTacToeFrame.this, "Invalid move. Try again.");
            }
        }
    }

    private boolean isValidMove(int row, int col) {
        return board[row][col].getValue() == ' ';
    }

    private boolean isWin(char player) {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    private boolean isColWin(char player) {
        for (int col = 0; col < COL; col++) {
            if (board[0][col].getValue() == player &&
                    board[1][col].getValue() == player &&
                    board[2][col].getValue() == player) {
                return true;
            }
        }
        return false;
    }

    private boolean isRowWin(char player) {
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].getValue() == player &&
                    board[row][1].getValue() == player &&
                    board[row][2].getValue() == player) {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonalWin(char player) {
        if (board[0][0].getValue() == player &&
                board[1][1].getValue() == player &&
                board[2][2].getValue() == player) {
            return true;
        }
        if (board[0][2].getValue() == player &&
                board[1][1].getValue() == player &&
                board[2][0].getValue() == player) {
            return true;
        }
        return false;
    }

    private boolean isTie() {
        if (moveCnt < 9) {
            return false;
        }
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (board[row][col].getValue() == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        int choice = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    board[i][j].reset();
                }
            }
            player = 'X';
            moveCnt = 0;
            statusLabel.setText("Player " + player + "'s turn");
        } else {
            System.exit(0);
        }
    }
}