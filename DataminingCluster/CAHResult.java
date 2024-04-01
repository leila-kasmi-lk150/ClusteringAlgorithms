/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataminingCluster;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Maryouma
 */
public class CAHResult {

    private int width, height;

    public CAHResult(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    
    
     public JPanel createScrollPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.white);
        
        // line
        JPanel linePanel = new JPanel();
        linePanel.setBackground(CustomColors.LightGray);
        linePanel.setPreferredSize(new Dimension(width, 2));
        contentPanel.add(linePanel, BorderLayout.NORTH);
        
        // inter
        JLabel interLabel = new JLabel("<html><u>Inter Class</u></html>");
        interLabel.setFont(new Font("Canva Sans", Font.BOLD, 17));
        interLabel.setForeground(CustomColors.BitterSweet);
        interLabel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 0));

        contentPanel.add(interLabel, BorderLayout.CENTER);
        // Add table
        //String[] columnNames = {"Name", "Age", "Gender"};
        //Object[][] data = {{"John", 30, "Male"}, {"Alice", 25, "Female"}, {"Bob", 35, "Male"}};
        //JTable table = new JTable(data, columnNames);
         DefaultTableModel model = new DefaultTableModel();
         int[] data = {2, 5, 9, 15, 16, 18, 25, 33, 33, 36};
         
        for (int i = 0; i < data.length; i++) {
            model.addColumn("Class " + (i + 1));
        }
        Object[] rowData = new Object[data.length];
        for (int i = 0; i < data.length; i++) {
            rowData[i] = data[i];
        }
        model.addRow(rowData);


         //model.addColumn("Name");
         //model.addColumn("Age");
         //model.addColumn("Gender");
         //model.addRow(new Object[]{"John", 30, "Male"});
         //model.addRow(new Object[]{"Alice", 25, "Female"});
         //model.addRow(new Object[]{"Bob", 35, "Male"});
         JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.SOUTH);
        
        scrollPane.setPreferredSize(new Dimension(width, height));
        scrollPane.getViewport().setBackground(Color.WHITE); 
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        JPanel scrollPanel = new JPanel(new BorderLayout());
        scrollPanel.add(contentPanel, BorderLayout.CENTER);
        
        return scrollPanel;
    }
}
