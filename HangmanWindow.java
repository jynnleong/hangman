import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class HangmanWindow extends JFrame implements ActionListener {

    public JSplitPane split, split1;
    public JButton hint;
    public JPanel buttonpanel, gamepanel, rightpanel, panel;
    public PaintHangman drawpanel;
    public JLabel answer;
    public String button, wrd, space;
    public GetWord g;
    public ArrayList<String> newwordlist;
    public ArrayList<Integer> randomindex;
    public ArrayList<JButton> buttonlist;
    public int hintcount;

    public HangmanWindow() {
        super("Hangman");
        setSize(500, 500);

        this.space = " ";
        this.newwordlist = new ArrayList<>();
        this.randomindex = new ArrayList<>();
        this.buttonlist = new ArrayList<>();

        this.hintcount = 0;

        //Initialise GUI 
        gamepanel = new JPanel();
        //Set drawpanel as an instance of PaintHangman to draw 
        drawpanel = new PaintHangman();
        drawpanel.setBackground(Color.black);
        rightpanel = new JPanel();
        buttonpanel = new JPanel();
        buttonpanel.setLayout(new GridLayout(14, 2, 0, 0));
        buttonpanel.setPreferredSize(new Dimension(50, 500));
        panel = new JPanel();
        answer = new JLabel();
        answer.setPreferredSize(new Dimension(300, 50));
        hint = new JButton("Hint");
        hint.addActionListener(this);
        this.buttonlist.add(hint);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(drawpanel);
        panel.add(answer);

        gamepanel.setLayout(new BoxLayout(gamepanel, BoxLayout.X_AXIS));
        gamepanel.add(buttonpanel);
        gamepanel.add(panel);

        this.button = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        this.g = new GetWord();
        this.newwordlist = this.g.getword();


        this.answer.setText(g.getwordtoguess());
        this.answer.setFont(new Font("HELVETICA", Font.BOLD, 30));
        this.answer.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        this.answer.setHorizontalAlignment(SwingConstants.CENTER);

        // Simple for loop to set each character from the string variable Button as a button
        for (int i = 0; i < button.length(); i++) {
            JButton b = new JButton(Character.toString(button.charAt(i)));
            this.buttonlist.add(b);
            b.addActionListener(this);
            buttonpanel.add(b);
        }

        buttonpanel.add(hint);

        getContentPane().add(gamepanel);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent evt)
    {
        String selected = evt.getActionCommand();
        JButton click = (JButton)evt.getSource();

        //If user chooses the wrong character, it will go through this if statement
        if(!this.g.word.contains(selected) && !selected.equals("Hint"))
        {
            //Once the character button is clicked, it will prevent users from clicking it again
            click.setEnabled(false);
            drawpanel.count ++;

            // If the hang count reaches 3, the system gives the user another chance to use the hint
            if(drawpanel.count == 3){
                this.hintcount = 0;
                hint.setEnabled(true);
            }
            repaint();
            checkifguessed();
        }
        //This if statement is activated when user chooses "Hint"
        else if(selected.equals("Hint"))
        {
            this.hintcount += 1;
            Random rand = new Random();
            int index = rand.nextInt(g.word.length());
            while(randomindex.contains(index))
            {
                index = rand.nextInt(g.word.length());
            }
            randomindex.add(index);


            for(int i = 0; i < g.actualword.length(); i ++)
            {
                if(g.actualword.charAt(i) == (g.word.charAt(index)))
                {
                    this.newwordlist.set(i, Character.toString(g.word.charAt(index)));
                }
            }

        
            for(JButton j : buttonlist)
            {
                if(j.getText().equals(Character.toString(g.word.charAt(index))))
                {
                    j.setEnabled(false);
                }
            }
            


            StringBuilder s = new StringBuilder();

            for(int i = 0; i < this.newwordlist.size(); i++)
            {
                s.append(this.newwordlist.get(i));
            }

            answer.setText(s.toString());

            // If user clicks on hint twice, the hint button will be disabled
            if(this.hintcount == 2){
                click.setEnabled(false);
            }
            // If system detects that users are struggling, it will give them another hint
            else if(this.hintcount == 1 && drawpanel.count >= 3){
                click.setEnabled(false);
            }
            checkifguessed();
        }
        //If user chooses the right character, it will go through this if statement
        else
        {   
            //Simple for loop to iterate through the guessing word to match the character and updates the guessing board
            for(int i = 0; i < g.actualword.length(); i ++)
            {
                if(Character.toString(g.actualword.charAt(i)).equals(selected))
                {
                    this.newwordlist.set(i, selected);
                }
            }

            StringBuilder sb = new StringBuilder();
            
            for(int i = 0; i < this.newwordlist.size(); i++)
            {
                sb.append(this.newwordlist.get(i));
            }

            answer.setText(sb.toString());
            //Prevents user from choosing the same character again 
            click.setEnabled(false);
            checkifguessed();
        }
    }

    //This method is called after every button is pressed to check if the user has successfully guessed the word or not
    public void checkifguessed()
    {
        String check = answer.getText();
        //If the word does not contain any '_', it will activate this if statement, stating that the user has successfully guessed the word
        if(!check.contains("_"))
        {
            int replay = JOptionPane.showConfirmDialog(this.getContentPane(), "CONGRATULATIONS, You Win! Replay?", "Replay?", JOptionPane.YES_NO_OPTION);
            //If user chooses YES, the game will restart
            if(replay == JOptionPane.YES_OPTION)
            {
                this.newwordlist = g.getword();
                this.wrd = g.getwordtoguess();
                this.answer.setText(this.wrd);
                this.answer.setFont(new Font("HELVETICA", Font.BOLD, 30));
                this.answer.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                this.answer.setHorizontalAlignment(SwingConstants.CENTER);
                drawpanel.count = 0;
                this.randomindex.clear();
                this.hintcount = 0;
                for(JButton j : buttonlist)
                {
                    j.setEnabled(true);
                }
                repaint();
            }
            //If not, the game will be closed
            else
            {
                JOptionPane.showMessageDialog(this.getContentPane(), "Goodbye");
                System.exit(0);
            }
        }
        // If the word still contains '_' and the their 6 chances have been used up, it will show a popup indicating that they lost
        else if(check.contains("_") && drawpanel.count == 6)
        {
            int replay = JOptionPane.showConfirmDialog(this.getContentPane(), "You lose... Replay?", "You lose", JOptionPane.YES_NO_OPTION);
            if(replay == JOptionPane.YES_OPTION)
            {
                this.newwordlist = g.getword();
                this.wrd = g.getwordtoguess();
                this.answer.setText(this.wrd);
                this.answer.setFont(new Font("HELVETICA", Font.BOLD, 30));
                this.answer.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                this.answer.setHorizontalAlignment(SwingConstants.CENTER);
                drawpanel.count = 0;
                this.hintcount = 0;
                this.randomindex.clear();
                for(JButton j : buttonlist)
                {
                    j.setEnabled(true);
                }
                repaint();
            }
            else
            {
                JOptionPane.showMessageDialog(this.getContentPane(), "Goodbye");
                System.exit(0);
            }
        }
    } 
}
