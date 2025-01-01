package Island.domain.animals;

import Island.domain.plants.Plant;

public abstract class Animal {
    private double weight;
    private int maxNumberOnOneCell;
    private int maxNumberOfCellsPassedPerMove;
    private double weightOfFoodForFullSaturation;
    private double currentSaturation = 0.0;

    public void move(){

    }

    public void reproduction(){

    }

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

    public double getCurrentSaturation() {
        return currentSaturation;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setMaxNumberOnOneCell(int maxNumberOnOneCell) {
        this.maxNumberOnOneCell = maxNumberOnOneCell;
    }

    public void setMaxNumberOfCellsPassedPerMove(int maxNumberOfCellsPassedPerMove) {
        this.maxNumberOfCellsPassedPerMove = maxNumberOfCellsPassedPerMove;
    }

    public void setWeightOfFoodForFullSaturation(double weightOfFoodForFullSaturation) {
        this.weightOfFoodForFullSaturation = weightOfFoodForFullSaturation;
    }

    public void setCurrentSaturation(double currentSaturation) {
        this.currentSaturation = currentSaturation;
    }
}
