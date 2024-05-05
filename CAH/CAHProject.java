package CAH;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CAHProject extends JFrame {

    private JScrollPane scrollPane;
    private StringBuilder htmlContent;

    List<ClassData> classDataList;
    public CAHProject(List<Double> classValues, int nbrClass) {
        setTitle("CAHProject");
        setSize(400, 300);
        setLocationRelativeTo(null);

        classDataList = new ArrayList<>();
         for (double value : classValues) {
            String s = String.valueOf(value);
            classDataList.add(new ClassData(1, value, value, s));
        }
        
        htmlContent = new StringBuilder();
        htmlContent.append("<html><body>");
        
        // ======================== "Inter Class" ====================================
         cahAlgo("Inter Class", classValues, nbrClass);

        
        // ==================== Intra Class ===========================
        
        cahAlgo("Intra Class", classValues, nbrClass);
        
        htmlContent.append("</body></html>");
        JEditorPane editorPane = new JEditorPane("text/html", htmlContent.toString());
        editorPane.setEditable(false);

       
        scrollPane = new JScrollPane(editorPane);
    }

    private void cahAlgo(String algo, List<Double> classValues, int nbrClass) {
        StringBuilder myHtml = new StringBuilder();
        
        classDataList = new ArrayList<>();
         for (double value : classValues) {
            String s = String.valueOf(value);
            classDataList.add(new ClassData(1, value, value, s));
        }
        htmlContent.append("<h1>" + algo + "</h1><table border='1' width='100%'>");
        htmlContent.append("<tr><th colspan='" + nbrClass + "'>Classes</th></tr><tr>");
         
        for (ClassData classData : classDataList) {
            htmlContent.append("<td>").append(classData.getVal()).append("</td>");
        }
         htmlContent.append("</tr>");
        
         
        int numClass = classDataList.size();
       while (numClass > 1) {
           double minDistance = Double.MAX_VALUE;
           int minIndex1 = -1;
           int minIndex2 = -1;

            for (int l = 0; l < classDataList.size(); l++) {
                for (int k = l + 1; k < classDataList.size(); k++) { 
                    ClassData class1 = classDataList.get(l);
                    ClassData class2 = classDataList.get(k);
                    double distance = 0;
                    if (algo == "Inter Class") {
                        distance = Math.abs(class1.lv - class2.fv);
                    } else if (algo == "Intra Class") {
                        distance = Math.abs(class1.fv - class2.lv);
                    }
                    
                    if (distance < minDistance) {
                        minDistance = distance;
                        minIndex1 = l;
                        minIndex2 = k;
                    }
                }
            }
    
            ClassData class1 = classDataList.get(minIndex1);
            ClassData class2 = classDataList.get(minIndex2);
    
            class1.nc += class2.nc;
            class1.lv = class2.lv;
            class1.val += " | " + class2.val;
            classDataList.remove(minIndex2);
            numClass--;

            htmlContent.append("<tr>");
            for (ClassData cd : classDataList) {
                htmlContent.append("<td colspan='").append(cd.getNc()).append("'>").append(cd.getVal()).append("</td>");
            }
            htmlContent.append("</tr>");
        }         
        htmlContent.append("</table>");
    }
     
    public StringBuilder getScrollPane() {
        return htmlContent;
    }
}