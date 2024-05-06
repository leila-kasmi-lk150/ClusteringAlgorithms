package kmeans;

/**
 *
 * @author Leila
 */
import java.util.ArrayList;

public class ClusterData {
    private String name;
    private ArrayList<ClassData> classes;
    private double x;
    private double y;

    public ClusterData(String name, ArrayList<ClassData> classes) {
        this.name = name;
        this.classes = classes;
        calculateCoordinates();
    }

    public void calculateCoordinates() {
        if (classes != null && !classes.isEmpty()) {
            double totalX = 0;
            double totalY = 0;
            for (ClassData classData : classes) {
                totalX += classData.getX();
                totalY += classData.getY();
            }
            x = totalX / classes.size();
            y = totalY / classes.size();
        } else {
            x = 0;
            y = 0;
        }
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ClassData> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<ClassData> classes) {
        this.classes = classes;
        calculateCoordinates(); 
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(" ========================================== \n name='").append(name).append('\'');
        s.append(", X=").append(x);
        s.append(", Y=").append(y);
        s.append(", classes=[");
        for (ClassData classData : classes) {
            s.append(classData).append(", ");
        }
        s.append("] \n");
        return s.toString();
    }
}
