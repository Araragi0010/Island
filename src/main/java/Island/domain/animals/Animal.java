package Island.domain.animals;

public abstract class Animal {
    private double weight;
    private int maxNumberOnOneCell;
    private int maxNumberOfCellsPassedPerMove;
    private double weightOfFoodForFullSaturation;

    public abstract void eat();

    public abstract void move();

    public abstract void reproduction();

}
