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

    public HangmanWindow() {
        super("Hangman");
        setSize(500, 500);

        this.space = " ";
        this.newwordlist = new ArrayList<>();
        this.randomindex = new ArrayList<>();
        this.buttonlist = new ArrayList<>();

        gamepanel = new JPanel();
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

        if(!this.g.word.contains(selected) && !selected.equals("Hint"))
        {
            click.setEnabled(false);
            drawpanel.count ++;
            repaint();
            checkifguessed();
        }
        else if(selected.equals("Hint"))
        {
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
            checkifguessed();
        }
        else
        {   
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

            checkifguessed();
        }
    }

    public void checkifguessed()
    {
        String check = answer.getText();
        if(!check.contains("_"))
        {
            int replay = JOptionPane.showConfirmDialog(this.getContentPane(), "CONGRATULATIONS, You Win! Replay?", "Replay?", JOptionPane.YES_NO_OPTION);
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