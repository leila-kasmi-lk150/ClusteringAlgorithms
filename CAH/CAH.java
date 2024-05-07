
package CAH;

import dataminingproject.CustomButton;
import dataminingproject.CustomColors;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Destination;
import javax.swing.text.JTextComponent;
/**
 *
 * @author Leila
 */
public class CAH extends JFrame {
    CustomButton Download, Nouveau, Kmeans;
    private JEditorPane editorPane;
    private JPanel scrollPanel;
    private JScrollPane scrollPane;
    public CAH() {
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
        
        JLabel textLabel = new JLabel("CAH Algorithme");
        textLabel.setFont(new Font("Canva Sans", Font.BOLD, 25));
        
        headerPanel.add(textLabel);
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setPreferredSize(new Dimension(getWidth(), 80));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        Nouveau = new CustomButton("Nouveau", 140, 40);
        //Nouveau.addActionListener(this);
        Nouveau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberOfClasses = Integer.parseInt(JOptionPane.showInputDialog("Enter number of classes:"));

                // Prompt user to enter values for each class
                List<Double> classValues = new ArrayList<>();
                for (int i = 0; i < numberOfClasses; i++) {
                    double value = Double.parseDouble(JOptionPane.showInputDialog("Enter value for class " + (i + 1) + ":"));
                    classValues.add(value);
                }

                // Sort the classValues list
                Collections.sort(classValues);

                // Pass the sorted data to CAHProject
                showCAHProject(classValues, numberOfClasses);
            }
        });
        Download = new CustomButton("Exporter", 140, 40);
        Download.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                download();
            }
        });
        
        Kmeans = new CustomButton("K-means", 140, 40);
        Kmeans.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                new kmeans.Kmeans();
            }
        });
        
        buttonPanel.add(Kmeans);
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
        
        

        scrollPane = new JScrollPane();
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
    
   
   private void showCAHProject(List<Double> classValues, int numberOfClasses) {
        
        editorPane.removeAll(); // Remove previous components
        CAHProject cahProject = new CAHProject(classValues, numberOfClasses);
        editorPane.setText(cahProject.getScrollPane().toString());
        //scrollPanel.add(cahProject.getScrollPane(), BorderLayout.CENTER);
        editorPane.revalidate(); // Revalidate container panel to reflect changes
        editorPane.repaint(); // Repaint container panel
    }
   private void download() {
    Component view = scrollPane.getViewport().getView();

    if (view instanceof JTextComponent) {
        
        
        JTextComponent textComponent = (JTextComponent) view;

        // Print content to PDF
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("ScrollPane Content PDF");

        PrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
        File outputFile = new File("scrollpane_content.pdf");
        attr.add(new Destination(outputFile.toURI()));

        job.setPrintable((graphics, pageFormat, pageIndex) -> {
            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }

            graphics.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

            textComponent.printAll(graphics);

            return Printable.PAGE_EXISTS;
        });

        if (job.printDialog(attr)) {
            try {
                job.print(attr);
                JOptionPane.showMessageDialog(this, "Content downloaded successfully as PDF!");
            } catch (PrinterException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error downloading content as PDF!");
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "ScrollPane content type not supported for download!");
    }
}
    
    public static void main(String[] args) {
        new CAH();
    }
}