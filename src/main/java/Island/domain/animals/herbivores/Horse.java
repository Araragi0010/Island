package Island.domain.animals.herbivores;

import Island.domain.animals.Animal;
import Island.domain.plants.Plant;

public class Horse extends Animal implements Herbivore {

    public Horse() {
    }

    public void eat(Plant plant) {
        if (getCurrentSaturation() < getWeightOfFoodForFullSaturation()) {
            double saturation = (plant.getWeight() / 2.0) + getCurrentSaturation();

            if (saturation >= getWeightOfFoodForFullSaturation()) {
                setCurrentSaturation(getWeightOfFoodForFullSaturation());
            } else {
                setCurrentSaturation(saturation);
            }
        }
    }
}
