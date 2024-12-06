import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TicTacToe extends JFrame implements ActionListener 
{
    private String player1;
    private String player2;
    private JLabel turnLabel;
    private static JButton[][] buttons = new JButton[3][3];
    private boolean player1Turn = true;

    public TicTacToe() 
    {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 350);
        setLayout(new BorderLayout());

        turnLabel = new JLabel();
        JPanel labelPanel = new JPanel();
        labelPanel.add(turnLabel);
        add(labelPanel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 3; j++) 
            {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(buttons[i][j].getFont().deriveFont(50.0f));
                buttons[i][j].addActionListener(this);
                boardPanel.add(buttons[i][j]);
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        setVisible(true);

        player1 = JOptionPane.showInputDialog(this, "Enter name for Player 1 (X):");
        if (player1 == null || player1.trim().equals("")) 
        {
            player1 = "Player 1";
        }
        player2 = JOptionPane.showInputDialog(this, "Enter name for Player 2 (O):");
        if (player2 == null || player2.trim().equals("")) 
        {
            player2 = "Player 2";
        }

        turnLabel.setText(player1 + "'s turn (X)");
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        JButton clickedButton = (JButton) e.getSource();

        if (!clickedButton.getText().equals("")) 
        {
            return;
        }

        String currentPlayerSymbol = player1Turn ? "X" : "O";
        String currentPlayerName = player1Turn ? player1 : player2;

        clickedButton.setText(currentPlayerSymbol);

        if (checkWin(currentPlayerSymbol)) 
        {
            JOptionPane.showMessageDialog(this, currentPlayerName + " wins!");
            resetGame();
            return;
        }

        if (checkDraw()) 
        {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetGame();
            return;
        }

        player1Turn = !player1Turn;
        currentPlayerName = player1Turn ? player1 : player2;
        currentPlayerSymbol = player1Turn ? "X" : "O";
        turnLabel.setText(currentPlayerName + "'s turn (" + currentPlayerSymbol + ")");
    }

    public boolean checkWin(String playerSymbol) 
    {
        for (int i = 0; i < 3; i++) 
        {
            if (buttons[i][0].getText().equals(playerSymbol) &&
                buttons[i][1].getText().equals(playerSymbol) &&
                buttons[i][2].getText().equals(playerSymbol)) 
            {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) 
        {
            if (buttons[0][i].getText().equals(playerSymbol) &&
                buttons[1][i].getText().equals(playerSymbol) &&
                buttons[2][i].getText().equals(playerSymbol)) 
            {
                return true;
            }
        }
        if (buttons[0][0].getText().equals(playerSymbol) &&
            buttons[1][1].getText().equals(playerSymbol) &&
            buttons[2][2].getText().equals(playerSymbol)) 
        {
            return true;
        }
        if (buttons[0][2].getText().equals(playerSymbol) &&
            buttons[1][1].getText().equals(playerSymbol) &&
            buttons[2][0].getText().equals(playerSymbol)) 
        {
            return true;
        }
        return false;
    }

    public boolean checkDraw() 
    {
        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 3; j++) 
            {
                if (buttons[i][j].getText().equals("")) 
                {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() 
    {
        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 3; j++) 
            {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        player1Turn = true;
        turnLabel.setText(player1 + "'s turn (X)");
    }

    public static void main(String[] args) 
    {
        new TicTacToe();
    }
}
