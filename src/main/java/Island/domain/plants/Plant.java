package Island.domain.plants;

public abstract class Plant {
    private double weight;
    private int maxNumberOnOneCell;

    public void grow(){

    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getMaxNumberOnOneCell() {
        return maxNumberOnOneCell;
    }

    public void setMaxNumberOnOneCell(int maxNumberOnOneCell) {
        this.maxNumberOnOneCell = maxNumberOnOneCell;
    }
}
