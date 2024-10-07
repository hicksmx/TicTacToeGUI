import javax.swing.JButton;

public class TicTacToeButton extends JButton {
    private int row;
    private int col;
    private char value;

    public TicTacToeButton(int row, int col) {
        this.row = row;
        this.col = col;
        this.value = ' ';
        this.setFont(this.getFont().deriveFont(24.0f));
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
        this.setText(String.valueOf(value));
    }

    public void reset() {
        this.value = ' ';
        this.setText("");
    }
}
