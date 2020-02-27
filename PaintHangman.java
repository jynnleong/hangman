import java.awt.*;
import javax.swing.*;

public class PaintHangman extends JPanel{

    public int count; //Keep track of the amount of chances(6) that the user gets while trying to guess for the word

    public PaintHangman(){
        super.setPreferredSize(new Dimension(300, 300));
        this.count = 0;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        //Drawing the podium for the game
        g.setColor(Color.white);
        g.fillRect(40,400,150,20);
        g.fillRect(90,100,20,300);
        g.fillRect(70,80,250,20);
        g.drawLine(250,100,250,200);

        //A swtich-case loop with the variable "count"
        switch(count)
        {
            case 1: //Draw the head
                g.fillOval(225,200,50,50);
                String msg = "The Count is: " + this.count;
                g.drawString(msg, 150, 50);
                break;
            case 2: //Add the body
                g.fillOval(225,200,50,50);
                g.drawLine(250,250,250,340);
                String msg1 = "The Count is: " + this.count;
                g.drawString(msg1, 150, 50);
                break;
            case 3: //Add the left arm
                g.fillOval(225,200,50,50);
                g.drawLine(250,250,250,340); 
                g.drawLine(250,250,225,280);
                String msg2 = "The Count is: " + this.count;
                g.drawString(msg2, 150, 50);
                break;
            case 4: //Add the right arm
                g.fillOval(225,200,50,50);
                g.drawLine(250,250,250,340); 
                g.drawLine(250,250,225,280);
                g.drawLine(250,250,275,280);
                String msg3 = "The Count is: " + this.count;
                g.drawString(msg3, 150, 50);
                break;
            case 5: //Add the left leg
                g.fillOval(225,200,50,50);
                g.drawLine(250,250,250,340); 
                g.drawLine(250,250,225,280);
                g.drawLine(250,250,275,280);
                g.drawLine(250,340,225,380);
                String msg4 = "The Count is: " + this.count;
                g.drawString(msg4, 150, 50);
                break;
            case 6: //Add the right leg
                g.fillOval(225,200,50,50);
                g.drawLine(250,250,250,340); 
                g.drawLine(250,250,225,280);
                g.drawLine(250,250,275,280);
                g.drawLine(250,340,225,380); 
                g.drawLine(250,340,275,380);
                g.drawString("Oh no the man died", 150, 50);
                break;
            default: break;
        }
        }
        
    
    
    
}
