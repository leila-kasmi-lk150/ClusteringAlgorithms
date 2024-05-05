package kmeans;

/**
 *
 * @author Leila
 */
public class ClassData {
     private String name;
    private int x;
    private int y;

    public ClassData(String name, int x, int y) {
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Class Name: " + name + ", X: " + x + ", Y: " + y;
    }
}
