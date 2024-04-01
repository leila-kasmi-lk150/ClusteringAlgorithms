
package DataminingCluster;


import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class AHCInterCluster extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    public AHCInterCluster() {
        setTitle("AHC Inter-Cluster Distance");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Initialize data
        int[] data = {2, 5, 9, 15, 16, 18, 25, 33, 33, 36};
        List<List<Integer>> clusters = new ArrayList<>();
        for (int value : data) {
            List<Integer> cluster = new ArrayList<>();
            cluster.add(value);
            clusters.add(cluster);
        }

        // Create JTable
        model = new DefaultTableModel();
        table = new JTable(model);
        

        // Update JTable with initial clusters
        updateTable(clusters);

        while (clusters.size() > 1) {
            int minDistance = Integer.MAX_VALUE;
            int minClusterIndex1 = -1;
            int minClusterIndex2 = -1;

            for (int i = 0; i < clusters.size(); i++) {
                for (int j = i + 1; j < clusters.size(); j++) {
                    int distance = findMinDistance(clusters.get(i), clusters.get(j));
                    if (distance < minDistance) {
                        minDistance = distance;
                        minClusterIndex1 = i;
                        minClusterIndex2 = j;
                    }
                }
            }

            List<Integer> mergedCluster = new ArrayList<>(clusters.get(minClusterIndex1));
            mergedCluster.addAll(clusters.get(minClusterIndex2));

            clusters.remove(minClusterIndex2);
            clusters.remove(minClusterIndex1);
            clusters.add(mergedCluster);

            updateTable(clusters);
        }
        
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private int findMinDistance(List<Integer> cluster1, List<Integer> cluster2) {
        int minDistance = Integer.MAX_VALUE;
        for (int value1 : cluster1) {
            for (int value2 : cluster2) {
                int distance = Math.abs(value1 - value2);
                if (distance < minDistance) {
                    minDistance = distance;
                }
            }
        }
        return minDistance;
    }

    private void updateTable(List<List<Integer>> clusters) {
        model.setRowCount(0);
        List<Object> row = new ArrayList<>();
        for (List<Integer> cluster : clusters) {
            StringBuilder sb = new StringBuilder();
            for (int value : cluster) {
                sb.append(value).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            row.add(sb.toString());
        }
        model.addRow(row.toArray());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AHCInterCluster().setVisible(true);
            }
        });
    }
}