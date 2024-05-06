package kmeans;

/**
 *
 * @author Leila
 */
public class ClassData {
     private String name;
    private double x;
    private double y;

    public ClassData(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "========================================== \n" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '\n';
    }
}
