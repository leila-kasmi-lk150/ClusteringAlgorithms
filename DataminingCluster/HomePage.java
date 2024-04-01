package DataminingCluster;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Leila
 */
public class HomePage extends JFrame implements ActionListener{

    CustomButton CAH, Kmeans;
   
    
    public HomePage(){
        
        
        getContentPane().setBackground(Color.WHITE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        ImageIcon imgIcon = new ImageIcon(ClassLoader.getSystemResource("img/dm.png"));
        JLabel image = new JLabel(imgIcon);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        
        CAH = new CustomButton("CAH", 160, 60);
        CAH.addActionListener(this);
        buttonPanel.add(CAH);
        
        Kmeans = new CustomButton("K-means", 160,60);
        Kmeans.addActionListener(this);
        buttonPanel.add(Kmeans);
        
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(image, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == CAH) {
            setVisible(false);
            new CAH();
        } else if (ae.getSource() == Kmeans) {
            setVisible(false);
        }
    }
    
     public static void main(String[] args) {
        new HomePage();
    }
}
