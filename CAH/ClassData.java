package CAH;

import java.util.List;

/**
 *
 * @author Leila
 */
public class ClassData {
    public int nc = 1; //nc ==> number of class 
    public double fv; // first value
    public double lv; // last value
    public String val; // valeur

    public ClassData(int nc, double fv, double lv, String val) {
        this.nc = nc;
        this.fv = fv;
        this.lv = lv;
        this.val = val;
    }

    // Getters and setters
    public int getNc() {
        return nc;
    }

    public void setNc(int nc) {
        this.nc = nc;
    }

    public void setFv(double fv) {
        this.fv = fv;
    }

    public void setLv(double lv) {
        this.lv = lv;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public double getFv() {
        return fv;
    }

    public double getLv() {
        return lv;
    }

    public String getVal() {
        return val;
    }
    
    public void updateWithMinimumDistance(List<ClassData> dataList) {
        double minDistance = Double.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < dataList.size(); i++) {
            double distance = Math.abs(this.lv - dataList.get(i).fv);
            if (distance < minDistance && distance > 0) {
                minDistance = distance;
                minIndex = i;
            }
        }

        if (minIndex != -1) {
            // Update current ClassData with the minimum distance found
            this.lv = dataList.get(minIndex).lv;
            this.nc++;
            this.val += " * " + dataList.get(minIndex).lv;

            // Remove the ClassData with which the minimum distance was found
            dataList.remove(minIndex);
        }
    }
}

