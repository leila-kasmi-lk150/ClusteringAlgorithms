package kmeans;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Leila
 */
public class KmeansProject extends JFrame {
    private StringBuilder htmlContent;

    public KmeansProject() {
        
        htmlContent = new StringBuilder();
        htmlContent.append("<html><body>");
        
        htmlContent.append("<h1>LEILA KASMI</h1>");
        htmlContent.append("</body></html>");
    }
    public StringBuilder getScrollPane() {
        return htmlContent;
    }
}
