package kmeans;

import javax.swing.*;
import java.awt.*;
import dataminingproject.CustomButton;
import dataminingproject.CustomColors;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Leila
 */
public class Kmeans extends JFrame {

    CustomButton Download, Nouveau, cah;
    private JEditorPane editorPane;
    public Kmeans() {
        getContentPane().setBackground(Color.WHITE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/icon.png"));
        setIconImage(icon.getImage());
        
        // hrader Panel textLabel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(CustomColors.LightSalmon);
        headerPanel.setPreferredSize(new Dimension(getWidth(), 80));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        
        JLabel textLabel = new JLabel("K-means Algorithme");
        textLabel.setFont(new Font("Canva Sans", Font.BOLD, 25));
        
        headerPanel.add(textLabel);
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setPreferredSize(new Dimension(getWidth(), 80));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        Nouveau = new CustomButton("Nouveau", 140, 40);
        Nouveau.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                showKmeansProject();
            }
        });
        
        
        Download = new CustomButton("Exporter", 140, 40);
        
        cah = new CustomButton("CAH", 140, 40);
        cah.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                new CAH.CAH();
            }
        });
        
        buttonPanel.add(cah);
        buttonPanel.add(Nouveau);
        buttonPanel.add(Download);
        
        JPanel myButtonPanel = new JPanel(new BorderLayout());
        myButtonPanel.setBackground(Color.WHITE);
        // line
        JPanel linePanel = new JPanel();
        linePanel.setBackground(CustomColors.LightGray);
        linePanel.setPreferredSize(new Dimension(getWidth(), 2));
        
        myButtonPanel.add(buttonPanel, BorderLayout.NORTH);
        myButtonPanel.add(linePanel, BorderLayout.CENTER);
        
        

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // Create a JEditorPane to render HTML content
        editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setEditable(false); // To make it read-only
        editorPane.setPreferredSize(new Dimension(800, 490));
        scrollPane.setViewportView(editorPane);

        // container panel header panel + button panel + scrollPane

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());
        containerPanel.add(headerPanel, BorderLayout.NORTH);
        containerPanel.add(myButtonPanel, BorderLayout.CENTER);
        containerPanel.add(scrollPane, BorderLayout.SOUTH);


        getContentPane().add(containerPanel, BorderLayout.NORTH);


        setVisible(true);
    }
    private void showKmeansProject() {
        
        editorPane.removeAll(); // Remove previous components
        KmeansProject cahProject = new KmeansProject();
        editorPane.setText(cahProject.getScrollPane().toString());
        //scrollPanel.add(cahProject.getScrollPane(), BorderLayout.CENTER);
        editorPane.revalidate(); // Revalidate container panel to reflect changes
        editorPane.repaint(); // Repaint container panel
    }
    public static void main(String[] args) {
        new Kmeans();
    }
}
