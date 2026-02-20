package Island.domain.animals.herbivores;

import Island.domain.animals.Animal;
import Island.domain.plants.Plant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Horse extends Animal implements Herbivore {

    public Horse() {
    }

    //Eat plant
    public void eat(HashMap<Class<? extends Plant>, ArrayList<Plant>> plantsMap, Map<String, Double> chancesToEat) {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for(Class<? extends Plant> plantToEat: plantsMap.keySet()){
            double chanceFromProperty = chancesToEat.get(plantToEat.getName());

            if(this.getCurrentSaturation() >= this.getWeightOfFoodForFullSaturation()){
                return;
            }

            if(chanceFromProperty == 0.0){
                continue;
            }

            double chance = 100.0 - chanceFromProperty;

            //Calculate the random number that is less or equals than 100.
            // It shows whether you have a chance to eat an animal or a plant.
            double currentChance =random.nextDouble(0,100);

            ArrayList<Plant> plantsToEatList = plantsMap.get(plantToEat);

            if(currentChance >= chance && !plantsToEatList.isEmpty()){
                this.setCurrentSaturation(plantsToEatList.get(plantsToEatList.size()-1).getWeight());
                plantsToEatList.remove(plantsToEatList.size()-1);
            }
        }
    }

    //Eat animal
    public void eat(Map<String, Double> chancesToEat, HashMap<Class<? extends Animal>, ArrayList<Animal>> animalsMap) {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for(Class<? extends Animal> animalToEat: animalsMap.keySet()){
            double chanceFromProperty = chancesToEat.get(animalToEat.getName());

            if(this.getCurrentSaturation() >= this.getWeightOfFoodForFullSaturation()){
                return;
            }

            if(chanceFromProperty == 0.0){
                continue;
            }

            double chance = 100.0 - chanceFromProperty;

            //Calculate the random number that is less or equals than 100.
            // It shows whether you have a chance to eat an animal or a plant.
            double currentChance =random.nextDouble(0,100);

            ArrayList<Animal> animalsToEatList = animalsMap.get(animalToEat);

            //weight of food eaten = half (0.5) the weight of the animal eaten
            if(currentChance >= chance && !animalsToEatList.isEmpty()){
                this.setCurrentSaturation(animalsToEatList.get(animalsToEatList.size()-1).getWeight() * 0.5);
                animalsToEatList.remove(animalsToEatList.size()-1);
            }
        }
    }
}
