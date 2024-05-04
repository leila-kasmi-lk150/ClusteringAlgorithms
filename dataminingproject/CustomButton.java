
package dataminingproject;

/**
 *
 * @author Leila
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.awt.geom.RoundRectangle2D;

public class CustomButton extends JButton {
    
    public CustomButton(String text, int width, int height) {
        super(text);
        
        Font font = new Font("Canva Sans", Font.BOLD, 20);
        setForeground(Color.WHITE);
        setBackground(CustomColors.BitterSweet);
        setFont(font);
        setFocusable(false);
        //setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        setOpaque(false);
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(width, height));
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(CustomColors.LightSalmon); 
                setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(CustomColors.BitterSweet);
                setForeground(Color.WHITE);
            }
        });
    }
     @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 10, 10));
        super.paintComponent(g2);
        g2.dispose();
    }
    @Override
    public Insets getInsets() {
        return new Insets(10, 10, 10, 10);
    }
}
