package Island.domain.animals;

import Island.domain.service.PropertyReader;
import java.util.Map;

public abstract class Animal {
    private double weight;
    private final double maxNumberOnOneCell;
    private final double maxNumberOfCellsPassedPerMove;
    private final double weightOfFoodForFullSaturation;
    private double currentSaturation = 0.0;

    public Animal(){
        PropertyReader propertyReader = new PropertyReader();
        Map<String, Double> animalCharacteristics = propertyReader.readProperty(createFileName());

        this.weight = animalCharacteristics.get("weight");
        this.maxNumberOnOneCell = animalCharacteristics.get("maxNumberOnOneCell");
        this.maxNumberOfCellsPassedPerMove = animalCharacteristics.get("maxNumberOfCellsPassedPerMove");
        this.weightOfFoodForFullSaturation = animalCharacteristics.get("weightOfFoodForFullSaturation");
    }

    public abstract void eat();

    public void reproduction(){

    }

    public void move(){

    }

    private String createFileName(){
        Class clazz = this.getClass();

        return clazz.getName();
    }

    public double getWeight() {
        return weight;
    }

    public double getMaxNumberOnOneCell() {
        return maxNumberOnOneCell;
    }

    public double getMaxNumberOfCellsPassedPerMove() {
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

    public void setCurrentSaturation(double currentSaturation) {
        this.currentSaturation = currentSaturation;
    }
}
