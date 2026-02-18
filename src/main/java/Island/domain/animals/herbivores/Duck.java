package Island.domain.animals.herbivores;

import Island.domain.animals.Animal;
import Island.domain.animals.predators.Predator;
import Island.domain.plants.Plant;

public class Duck extends Animal implements Herbivore, Predator {

    public Duck() {
    }

    @Override
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

    public void eat(Animal animal) {
    }

    public void move() {
    }

    public void reproduction() {
    }
}
