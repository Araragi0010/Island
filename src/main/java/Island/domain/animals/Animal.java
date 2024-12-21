package Island.domain.animals;

public abstract class Animal {
    private double weight;
    private int maxNumberOnOneCell;
    private int maxNumberOfCellsPassedPerMove;
    private double weightOfFoodForFullSaturation;

    public abstract void eat();

    public abstract void move();

    public abstract void reproduction();

    public double getWeight() {
        return weight;
    }

    public int getMaxNumberOnOneCell() {
        return maxNumberOnOneCell;
    }

    public int getMaxNumberOfCellsPassedPerMove() {
        return maxNumberOfCellsPassedPerMove;
    }

    public double getWeightOfFoodForFullSaturation() {
        return weightOfFoodForFullSaturation;
    }
}
