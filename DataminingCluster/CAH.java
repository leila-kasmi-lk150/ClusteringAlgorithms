package DataminingCluster;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
/**
 *
 * @author Leila
 */
public class CAH extends JFrame {
    
    CustomButton Download, Nouveau;
    
    CAH() {
        getContentPane().setBackground(Color.WHITE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        // hrader Panet icon + textLabel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(CustomColors.LightSalmon);
        headerPanel.setPreferredSize(new Dimension(getWidth(), 80));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        
        JLabel textLabel = new JLabel("CAH Algorithme");
        textLabel.setFont(new Font("Canva Sans", Font.BOLD, 25));
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/icon.png"));
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        JLabel iconLabel = new JLabel(scaledIcon);
        
       //JOptionPane.showMessageDialog(null, "Returning to home page...");
        iconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                new HomePage();
            }
        });

        // headerPanel.add(iconLabel);
        headerPanel.add(textLabel);
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setPreferredSize(new Dimension(getWidth(), 80));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        
        Nouveau = new CustomButton("Nouveau", 140, 40);
        
        Download = new CustomButton("Exporter", 140, 40);
        
        CustomButton leila = new CustomButton("K-means", 140, 40);
        
        buttonPanel.add(leila);
        buttonPanel.add(Nouveau);
        buttonPanel.add(Download);
        
        int scrollPaneHeight = getHeight() - (headerPanel.getHeight() + buttonPanel.getHeight() + 2);
        CAHResult NewScrollPanel = new CAHResult(getWidth(), scrollPaneHeight);
        JPanel scrollPanel = NewScrollPanel.createScrollPanel();

        
        // container panel header panel + button panel + line
        
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());
        containerPanel.add(headerPanel, BorderLayout.NORTH);
        containerPanel.add(buttonPanel, BorderLayout.CENTER);
        containerPanel.add(scrollPanel, BorderLayout.SOUTH);
         
        getContentPane().add(containerPanel, BorderLayout.NORTH);
        
        
        setVisible(true);
    }
    
   
    
    public static void main(String[] args) {
        new CAH();
    }
}
