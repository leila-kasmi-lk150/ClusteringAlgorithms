package dataminingproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Leila
 */
public class DataminingProject extends JFrame implements ActionListener{

    CustomButton CAH, Kmeans;
    public DataminingProject(){
        
        getContentPane().setBackground(Color.WHITE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/icon.png"));
        setIconImage(icon.getImage());
        
        ImageIcon imgIcon = new ImageIcon(ClassLoader.getSystemResource("img/dm.png"));
        JLabel image = new JLabel(imgIcon);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        
        CAH = new CustomButton("HCA", 160, 60);
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
           new CAH.CAH();
        } else if (ae.getSource() == Kmeans) {
            setVisible(false);
            new kmeans.Kmeans();
        }
    }
    
    public static void main(String[] args) {
        new DataminingProject();
    }
    
}
