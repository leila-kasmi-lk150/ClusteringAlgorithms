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

    public KmeansProject(ArrayList<ClassData> classList, int numClasses, int numClusters, ArrayList<ClassData> clusterData) {
        
        htmlContent = new StringBuilder();
        htmlContent.append("<html><body>");
        
        htmlContent.append("<h1>Iteration 1</h1>");
        htmlContent.append("<table border='1' width='100%'>");
        htmlContent.append("<tr>"
                + "<th>Axes</th>" );
        for (ClassData classInfo : clusterData) {
            htmlContent.append("<th>" + classInfo.getName() + "</th>");
        }
        for (ClassData classInfo : classList) {
            htmlContent.append("<th>" + classInfo.getName() + "</th>");
        }
                
        htmlContent.append("</tr>");
        htmlContent.append("<tr>"
                + "<td> X </td>");
        for (ClassData classInfo : clusterData) {
            htmlContent.append("<td>" + classInfo.getX() + "</td>");
        }
        for (ClassData classInfo : classList) {
            htmlContent.append("<td>" + classInfo.getX() + "</td>");
        }
        
        htmlContent.append("</tr>");
        
        
        htmlContent.append("<tr>"
                + "<td> Y </td>");
         for (ClassData classInfo : clusterData) {
            htmlContent.append("<td>" + classInfo.getY() + "</td>");
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
        
        
        
        for (int i = 0; i < numClusters; i++) {
            htmlContent.append("<tr>"
                + "<td colspan='" + colspan + "'> distMin(P,C" + i + ") </td>" );
            for (int j = 0; j < numClasses; j++) {
                if (distances[i][j] == minDes(clusterData, j, numClusters, distances)) {
                    htmlContent.append("<td>"+distances[i][j] +"</td>");
                } else {
                    htmlContent.append("<td>  </td>");
                }
            }
            htmlContent.append("</tr>");
        }
        
        
        htmlContent.append("</table>");
        htmlContent.append("</body></html>");
    }
    private double calculateDistance(ClassData classInfo, ClassData clusterInfo) {
        int x = classInfo.getX() - clusterInfo.getX();
        int y = classInfo.getY() - clusterInfo.getY();
        double distance = Math.sqrt(x * x + y * y);
        DecimalFormat df = new DecimalFormat("#.##"); 
        return Double.parseDouble(df.format(distance)); 
    }
    private double minDes(ArrayList<ClassData> clusterInfo, int j, int numbCluster, double[][] distances){
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
