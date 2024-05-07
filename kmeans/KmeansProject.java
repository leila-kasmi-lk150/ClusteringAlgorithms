package kmeans;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 *
 * @author Leila
 */
public class KmeansProject extends JFrame {
    private StringBuilder htmlContent;

    public KmeansProject(ArrayList<ClassData> classList, int numClasses, int numClusters, ArrayList<ClusterData> clusterData) {
        
        htmlContent = new StringBuilder();
        htmlContent.append("<html><body>");
        
        double IA=0;
        double IAec;
        int itr = 1;
        do { 
        IAec=IA;
        IA =0.0;
        
        System.out.println(itr);
        htmlContent.append("<h1>Iteration " + itr+ "</h1>");
        htmlContent.append("<table border='1' width='100%'>");
        htmlContent.append("<tr>"
                + "<th>Axes</th>" );
        for (ClusterData clusterInfo : clusterData) {
            htmlContent.append("<th>" + clusterInfo.getName() + "</th>");
        }
        for (ClassData classInfo : classList) {
            htmlContent.append("<th>" + classInfo.getName() + "</th>");
        }
                
        htmlContent.append("</tr>");
        htmlContent.append("<tr>"
                + "<td> X </td>");
        for (ClusterData clusterInfo : clusterData) {
            htmlContent.append("<td>" + clusterInfo.getX() + "</td>");
        }
        for (ClassData classInfo : classList) {
            htmlContent.append("<td>" + classInfo.getX() + "</td>");
        }
        
        htmlContent.append("</tr>");
        
        
        htmlContent.append("<tr>"
                + "<td> Y </td>");
         for (ClusterData clusterInfo : clusterData) {
            htmlContent.append("<td>" + clusterInfo.getY() + "</td>");
        }
        for (ClassData classInfo : classList) {
            htmlContent.append("<td>" + classInfo.getY() + "</td>");
        }
        htmlContent.append("</tr>");
        double[][] distances = new double[numClusters][numClasses];      
        int colspan = numClusters + 1;
        
        
        for (int i = 0; i < numClusters; i++) {
            htmlContent.append("<tr>"
                + "<td colspan='" + colspan + "'> distance(P,C" + (i+1) + ") </td>");
            for (int j = 0; j < numClasses; j++) {
                distances[i][j] = calculateDistance(classList.get(j), clusterData.get(i));
                htmlContent.append("<td>"+distances[i][j] +"</td>");
            }
            htmlContent.append("</tr>");
        }
        
        ArrayList<Integer> oneDis = new ArrayList<>();
        boolean emptyCluster; 
        boolean[] classAssigned = new boolean[numClasses]; 
        for (int i = 0; i < numClusters; i++) {
            emptyCluster = false; // we need to empty the cluster before filling it !
            htmlContent.append("<tr>"
                + "<td colspan='" + colspan + "'> distMin(P,C" + i + ") </td>" );
            for (int j = 0; j < numClasses; j++) {
                if (distances[i][j] == minDes( j, numClusters, distances)  && !classAssigned[j]) {
                    htmlContent.append("<td>"+distances[i][j] +"</td>");
                    classAssigned[j] = true; // Mark the class as assigned to a cluster
                    if(!emptyCluster){
                        clusterData.get(i).getClasses().removeAll(clusterData.get(i).getClasses()); // empty cluster
                        clusterData.get(i).getClasses().add(classList.get(j));
                        clusterData.get(i).calculateCoordinates();
                        emptyCluster = true;
                    }else{
                        clusterData.get(i).getClasses().add(classList.get(j));
                        clusterData.get(i).calculateCoordinates();
                    }
                } else {
                    htmlContent.append("<td>  </td>");
                }
            }
            htmlContent.append("</tr>");
        }
        htmlContent.append("</table>");
        htmlContent.append("");
        for (ClusterData clusterInfo : clusterData) {
            htmlContent.append("<h3>" + clusterInfo.getName()+ " ( " + clusterInfo.getX() + " , "+ clusterInfo.getY() + " )</td>");
        }
        // Print
        for (ClusterData cluster : clusterData) {
            String leila = cluster.getName() + " : ";
            for (ClassData classData : cluster.getClasses()) {
               leila += classData.getName() + " ";
            }
            htmlContent.append("<h4>"+ leila +"</h4>");
        }
        
        // ===== Inertie Intra-Class IA ====
        htmlContent.append("<h3>Inertie Intra-Class IA</h3>");
        
        
        htmlContent.append("<table border='1' width='100%'>");
        
        htmlContent.append("<tr>"
                + "<th colspan='3'> </th>" );
        for (ClassData classInfo : classList) {
            htmlContent.append("<th>" + classInfo.getName() + "</th>");
        }        
        htmlContent.append("</tr>");
        
        
        double I;
        boolean[] classAssignedIA = new boolean[numClasses]; 
        for (int i = 0; i < numClusters; i++) {
            htmlContent.append("<tr>");
            StringBuilder dis2 = new StringBuilder();
            double distance2 = 0;
            for (int j = 0; j < numClasses; j++) {
                if ( clusterData.get(i).getClasses().contains(classList.get(j))) {
                    distance2 += distanceIA(classList.get(j), clusterData.get(i));
                    dis2.append("<td>"+ distanceIA(classList.get(j), clusterData.get(i)) +"</td>");
                    classAssignedIA[j] = true; // Mark the class as assigned to a cluster
                } else{
                    dis2.append("<td> </td>");
                }
            }
            I = distance2 / clusterData.get(i).getClasses().size();
            IA += I;
            htmlContent.append("<td> I" + i+1 + "</td>");
            htmlContent.append("<td> " + I + "</td>");
            htmlContent.append("<td> dis^2(P,C" + i+1 + ") </td>");
            htmlContent.append(dis2);
            htmlContent.append("</tr>");
        }
        htmlContent.append("</table>");
        htmlContent.append("<h3> IA = "+ IA +"</h3>");
        
        itr++;
        System.out.println("IA>=IAec " + (IA>IAec));
        System.out.println("IA " +IA);
        System.out.println("IAec " +IAec);
        System.out.println("====================");
        } while (IA > IAec);
        
        
        
        htmlContent.append("</body></html>");
    }
    
    private double calculateDistance(ClassData classInfo, ClusterData clusterInfo) {
        double x = classInfo.getX() - clusterInfo.getX();
        double y = classInfo.getY() - clusterInfo.getY();
        double distance = Math.sqrt(x * x + y * y);
        DecimalFormat df = new DecimalFormat("#.##"); 
        return Double.parseDouble(df.format(distance)); 
    }
    private double distanceIA(ClassData classInfo, ClusterData clusterInfo) {
        double x = classInfo.getX() - clusterInfo.getX();
        double y = classInfo.getY() - clusterInfo.getY();
        double distance = x * x + y * y;
        DecimalFormat df = new DecimalFormat("#.##"); 
        return Double.parseDouble(df.format(distance)); 
    }
    private double minDes( int j, int numbCluster, double[][] distances){
        double minDis = Double.MAX_VALUE;
        for (int i = 0; i < numbCluster; i++) {
                minDis = Math.min(minDis, distances[i][j]);
        }    
        return minDis;
    }
    public StringBuilder getScrollPane() {
        return htmlContent;
    }
}
