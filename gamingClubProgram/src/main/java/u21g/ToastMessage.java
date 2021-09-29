
package u21g;

// Java program that creates the toast message
//(which is a selectively translucent JWindow)
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.Font;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;


class ToastMessage extends JFrame {
      public ToastMessage(final String message, FrameandCardHolder mainFrame) {
      setUndecorated(true);
      setLayout(new GridBagLayout());
      setBackground(new Color(240,240,240,250));

      setSize(300, 50);
      JLabel msg = new JLabel(message);
      msg.setFont(new Font("Veranda", Font.PLAIN, 20));
      msg.setForeground(Color.red);
      msg.setSize(new Dimension(300, 50));
      add(msg);
       
      addComponentListener(new ComponentAdapter() {
         @Override
         public void componentResized(ComponentEvent e) {
            setShape(new  RoundRectangle2D.Double(0,0,getWidth(),
            getHeight(), 20, 20));                      
         }
      });        
   }

   public void display() {
      try {
         setOpacity(1);
         setLocationRelativeTo(null);
         setVisible(true);
         Thread.sleep(2000);

         //hide the toast message in slow motion
         for (double d = 1.0; d > 0.2; d -= 0.1) {
            Thread.sleep(100);
            setOpacity((float)d);
         }

         // set the visibility to false
         setVisible(false);
      }catch (Exception e) {
         System.out.println(e.getMessage());
      }
   }
}