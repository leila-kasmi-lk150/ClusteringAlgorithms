package kmeans;

import javax.swing.*;
import java.awt.*;
import dataminingproject.CustomButton;
import dataminingproject.CustomColors;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
               
                int numClasses = Integer.parseInt(JOptionPane.showInputDialog("Enter number of classes:"));
                
                ArrayList<ClassData> classList = new ArrayList<>();
                
                for (int i = 0; i < numClasses; i++) {
                    // Create panel for class input
                    JPanel classPanel = new JPanel(new GridLayout(3, 2));
                    classPanel.add(new JLabel("Class Name:"));
                    JTextField classNameField = new JTextField(10);
                    classPanel.add(classNameField);
                    classPanel.add(new JLabel("X Value:"));
                    JTextField xValueField = new JTextField(10);
                    classPanel.add(xValueField);
                    classPanel.add(new JLabel("Y Value:"));
                    JTextField yValueField = new JTextField(10);
                    classPanel.add(yValueField);

                    int option = JOptionPane.showConfirmDialog(null, classPanel, "Enter details for class " + (i + 1), JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        // Store class info in list
                        String name = classNameField.getText();
                        double x = Double.parseDouble(xValueField.getText());
                        double y = Double.parseDouble(yValueField.getText());

                        classList.add(new ClassData(name, x, y));
                    } else {
                        JOptionPane.showMessageDialog(null, "Cancelled or Closed dialog. Exiting.");
                        return;
                    }
                }
                int numClusters = Integer.parseInt(JOptionPane.showInputDialog("Enter number of clusters:"));
               
                // Create map to store cluster data
                ArrayList<ClusterData> clusterData = new ArrayList<>();

                // Create a new list and copy elements from classList
                ArrayList<ClassData> myClasses = new ArrayList<>(classList);

                for (int i = 0; i < numClusters; i++) {
                    JPanel clusterPanel = new JPanel(new GridLayout(2, 1));
                    clusterPanel.add(new JLabel("Select  cluster " + (i + 1) + ":"));
                    JComboBox<String> classComboBox = new JComboBox<>();
                    for (ClassData classInfo : myClasses) {
                        classComboBox.addItem(classInfo.getName());
                    }
                    clusterPanel.add(classComboBox);
    
                    // Show popup
                    int option = JOptionPane.showConfirmDialog(null, clusterPanel, "Select cluster " + (i + 1), JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        // Get selected class 
                        int selectedIndex = classComboBox.getSelectedIndex();
                        if (selectedIndex != -1) {
                            ClassData selectedClass = myClasses.get(selectedIndex);
                            ArrayList<ClassData> selectedClasses = new ArrayList<>();
                            selectedClasses.add(selectedClass);
                            ClusterData cluster = new ClusterData("Cluster " + (i + 1), selectedClasses);
                            clusterData.add(cluster);
                            myClasses.remove(selectedIndex);
                        } else {
                            JOptionPane.showMessageDialog(null, "No class selected for cluster " + (i + 1) + ". Exiting.");
                            return;
                        }
                    } else {
                        // Handle cancellation or closing of the dialog
                        JOptionPane.showMessageDialog(null, "Cancelled or Closed dialog. Exiting.");
                        return;
                    }
                }

                // Print 
                System.out.println("==================Cluster Data==============");
                System.out.println(clusterData);
                System.out.println("================== Classes Data==============");
                System.out.println(classList);
                
                showKmeansProject(classList, numClasses, numClusters, clusterData);
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
    private void showKmeansProject(ArrayList<ClassData> classList, int numClasses, int numClusters, ArrayList<ClusterData> clusterData) {
        
        editorPane.removeAll(); 
        KmeansProject cahProject = new KmeansProject(classList,  numClasses,  numClusters, clusterData);
        editorPane.setText(cahProject.getScrollPane().toString());
        editorPane.revalidate(); // Revalidate container panel to reflect changes
        editorPane.repaint(); // Repaint container panel
    }
    public static void main(String[] args) {
        new Kmeans();
    }
}
