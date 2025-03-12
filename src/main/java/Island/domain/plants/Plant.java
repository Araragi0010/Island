package Island.domain.plants;

import Island.domain.service.PropertyReader;
import java.util.Map;

public abstract class Plant {
    private double weight;
    private double maxNumberOnOneCell;

    public Plant(){
        PropertyReader propertyReader = new PropertyReader();
        Map<String, Double> plantCharacteristics = propertyReader.readProperty(createFileName());

        this.weight = plantCharacteristics.get("weight");
        this.maxNumberOnOneCell = plantCharacteristics.get("maxNumberOnOneCell");
    }

    private String createFileName(){
        Class clazz = this.getClass();

        return clazz.getName();
    }

    public void grow(){

    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getMaxNumberOnOneCell() {
        return maxNumberOnOneCell;
    }

    public void setMaxNumberOnOneCell(double maxNumberOnOneCell) {
        this.maxNumberOnOneCell = maxNumberOnOneCell;
    }
}
