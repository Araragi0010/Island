package Island.domain.animals;

import Island.domain.Coordinates;
import Island.domain.service.Direction;
import Island.domain.service.PropertyReader;
import Island.domain.service.RandomDirection;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {
    private double weight;
    private final double maxNumberOnOneCell;
    private final double maxNumberOfCellsPassedPerMove;
    private final double weightOfFoodForFullSaturation;
    private double currentSaturation = 0.0;

    private final RandomDirection randomDirection = new RandomDirection();

    public Animal(){
        PropertyReader propertyReader = new PropertyReader();
        Map<String, Double> animalCharacteristics = propertyReader.readProperty(createFileName());

        this.weight = animalCharacteristics.get("weight");
        this.maxNumberOnOneCell = animalCharacteristics.get("maxNumberOnOneCell");
        this.maxNumberOfCellsPassedPerMove = animalCharacteristics.get("maxNumberOfCellsPassedPerMove");
        this.weightOfFoodForFullSaturation = animalCharacteristics.get("weightOfFoodForFullSaturation");
    }

    public void reproduction(){

    }

    public Coordinates move(Coordinates currentCoordinates, int maxCoordinateY, int maxCoordinateX){
        int currentY = currentCoordinates.getY();
        int currentX = currentCoordinates.getX();

        int newY = currentY;
        int newX = currentX;

        if(randomDirection.getDirection() == Direction.RIGHT){
            if(currentX == maxCoordinateX){
                return currentCoordinates;
            }

            newX = currentX + ThreadLocalRandom.current().nextInt(0, (int)maxNumberOfCellsPassedPerMove);

            if(newX == currentX){
                return currentCoordinates;
            }

            if (newX > maxCoordinateX) {
                newX = ThreadLocalRandom.current().nextInt(currentX+1, maxCoordinateX);
            }

        } else if (randomDirection.getDirection() == Direction.LEFT){
            if(currentX == 0){
                return currentCoordinates;
            }

            newX = currentX - ThreadLocalRandom.current().nextInt(0, (int)maxNumberOfCellsPassedPerMove);

            if(newX == currentX){
                return currentCoordinates;
            }

            if (newX < 0) {
                newX = ThreadLocalRandom.current().nextInt(0, currentX-1);
            }

        } else if (randomDirection.getDirection() == Direction.UP){
            if(currentY == 0){
                return currentCoordinates;
            }

            newY = currentY - ThreadLocalRandom.current().nextInt(0, (int)maxNumberOfCellsPassedPerMove);

            if(newY == currentY){
                return currentCoordinates;
            }

            if (newY < maxCoordinateY) {
                newY = ThreadLocalRandom.current().nextInt(0, currentY-1);
            }
        } else if (randomDirection.getDirection() == Direction.DOWN){
            if(currentY == maxCoordinateY){
                return currentCoordinates;
            }

            newY = currentY + ThreadLocalRandom.current().nextInt(0, (int)maxNumberOfCellsPassedPerMove);
            if(newY == currentY){
                return currentCoordinates;
            }

            if (newY > maxCoordinateY) {
                newY = ThreadLocalRandom.current().nextInt(currentY+1, maxCoordinateY);
            }
        } else {
            return currentCoordinates;
        }

        return new Coordinates(newY, newX);
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
