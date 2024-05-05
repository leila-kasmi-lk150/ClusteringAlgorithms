package kmeans;

import javax.swing.*;
import java.awt.*;
import dataminingproject.CustomButton;
import dataminingproject.CustomColors;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
                        int x = Integer.parseInt(xValueField.getText());
                        int y = Integer.parseInt(yValueField.getText());
                        classList.add(new ClassData(name, x, y));
                    } else {
                        JOptionPane.showMessageDialog(null, "Cancelled or Closed dialog. Exiting.");
                        return;
                    }
                }
                int numClusters = Integer.parseInt(JOptionPane.showInputDialog("Enter number of clusters:"));
               
                System.out.println("---------------Class Data:-------------------");
                for (int i = 0; i < classList.size(); i++) {
                    System.out.println("class " + (i + 1) + ":");
                    ClassData classData = classList.get(i);
                    System.out.println("Name: " + classData.getName() + ", X: " + classData.getX() + ", Y: " + classData.getY());
                }
                // Create map to store cluster data
                ArrayList<ClassData> clusterData = new ArrayList<>();

                // Create a new list and copy elements from classList
                ArrayList<ClassData> myClasses = new ArrayList<>(classList);

                // Select classes for each cluster
                for (int i = 0; i < numClusters; i++) {
                    // Create panel for cluster selection
                    JPanel clusterPanel = new JPanel(new GridLayout(2, 1));
                    clusterPanel.add(new JLabel("Select  cluster " + (i + 1) + ":"));
                    JComboBox<String> classComboBox = new JComboBox<>();
                    for (ClassData classInfo : myClasses) {
                        classComboBox.addItem(classInfo.getName());
                    }
                    clusterPanel.add(classComboBox);

                    // Show popup with cluster selection
                    int option = JOptionPane.showConfirmDialog(null, clusterPanel, "Select cluster " + (i + 1), JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                         // Get selected class from myClasses
                        ClassData selectedClass = myClasses.get(classComboBox.getSelectedIndex());
                        selectedClass.setName("Cluster " + (i + 1));
                        clusterData.add(selectedClass);
                        // Remove the selected class from myClasses
                        myClasses.remove(classComboBox.getSelectedIndex());
                    }else {
                        // Handle cancellation or closing of the dialog
                        JOptionPane.showMessageDialog(null, "Cancelled or Closed dialog. Exiting.");
                        return;
                    }
                }
                // Show the data stored in clusterData
                // Show the data stored in clusterData
                System.out.println("-----------Cluster Data:-------------");
                for (int i = 0; i < clusterData.size(); i++) {
                    System.out.println("Cluster " + (i + 1) + ":");
                    ClassData classData = clusterData.get(i);
                    System.out.println("Name: " + classData.getName() + ", X: " + classData.getX() + ", Y: " + classData.getY());
                }
                System.out.println("-----------------Class Data:");
                for (int i = 0; i < classList.size(); i++) {
                    System.out.println("class " + (i + 1) + ":");
                    ClassData classData = classList.get(i);
                    System.out.println("Name: " + classData.getName() + ", X: " + classData.getX() + ", Y: " + classData.getY());
                }


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
    private void showKmeansProject(ArrayList<ClassData> classList, int numClasses, int numClusters, ArrayList<ClassData> clusterData) {
        
        editorPane.removeAll(); // Remove previous components
        KmeansProject cahProject = new KmeansProject(classList,  numClasses,  numClusters, clusterData);
        editorPane.setText(cahProject.getScrollPane().toString());
        //scrollPanel.add(cahProject.getScrollPane(), BorderLayout.CENTER);
        editorPane.revalidate(); // Revalidate container panel to reflect changes
        editorPane.repaint(); // Repaint container panel
    }
    public static void main(String[] args) {
        new Kmeans();
    }
}
