import java.awt.*;
import javax.swing.*;

public class PaintHangman extends JPanel{

    public int count;

    public PaintHangman(){
        super.setPreferredSize(new Dimension(300, 300));
        this.count = 0;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(Color.white);
        g.fillRect(40,400,150,20);
        g.fillRect(90,100,20,300);
        g.fillRect(70,80,250,20);
        g.drawLine(250,100,250,200);

        switch(count)
        {
            case 1: 
                g.fillOval(225,200,50,50);
                String msg = "The Count is: " + this.count;
                g.drawString(msg, 150, 50);
                break;
            case 2: 
                g.fillOval(225,200,50,50);
                g.drawLine(250,250,250,340);
                String msg1 = "The Count is: " + this.count;
                g.drawString(msg1, 150, 50);
                break;
            case 3:
                g.fillOval(225,200,50,50);
                g.drawLine(250,250,250,340); 
                g.drawLine(250,250,225,280);
                String msg2 = "The Count is: " + this.count;
                g.drawString(msg2, 150, 50);
                break;
            case 4: 
                g.fillOval(225,200,50,50);
                g.drawLine(250,250,250,340); 
                g.drawLine(250,250,225,280);
                g.drawLine(250,250,275,280);
                String msg3 = "The Count is: " + this.count;
                g.drawString(msg3, 150, 50);
                break;
            case 5: 
                g.fillOval(225,200,50,50);
                g.drawLine(250,250,250,340); 
                g.drawLine(250,250,225,280);
                g.drawLine(250,250,275,280);
                g.drawLine(250,340,225,380);
                String msg4 = "The Count is: " + this.count;
                g.drawString(msg4, 150, 50);
                break;
            case 6:
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