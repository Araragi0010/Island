package Island.domain;

import Island.domain.animals.Animal;
import Island.domain.animals.herbivores.Duck;
import Island.domain.animals.herbivores.Horse;
import Island.domain.animals.predators.Wolf;
import Island.domain.plants.Grass;
import Island.domain.plants.Plant;
import Island.domain.service.AnimalFactory;
import Island.domain.service.PlantFactory;
import Island.domain.service.PropertyReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Location {

    private static final HashMap< Class<? extends Animal> , Integer> mapAnimals = new HashMap<>();
    private static final HashMap< Class<? extends Plant> , Integer> mapPlants = new HashMap<>();

    static {
        //Predators
        mapAnimals.put(Wolf.class, 30);

        //Herbivores
        mapAnimals.put(Horse.class, 20);
        mapAnimals.put(Duck.class, 200);

        //Plants
        mapPlants.put(Grass.class, 200);
    }

    private final int maxCoordinateY;
    private final int maxCoordinateX;
    private final Coordinates coordinates;
    private final Location[][] listOfAllLocations;

    private final PropertyReader propertyReader = new PropertyReader();

    private final AnimalFactory animalFactory = new AnimalFactory();
    private final PlantFactory plantFactory = new PlantFactory();

    private HashMap<Class<? extends Animal>, ArrayList<Animal>> animals = new HashMap<>();
    private HashMap<Class<? extends Plant>, ArrayList<Plant>> plants = new HashMap<>();

    public Location(Location[][] listOfAllLocations, Coordinates coordinates, int maxCoordinateY, int maxCoordinateX) {
        this.maxCoordinateY = maxCoordinateY;
        this.maxCoordinateX = maxCoordinateX;
        this.coordinates = coordinates;
        this.listOfAllLocations = listOfAllLocations;
        fillLocation();
    }

    private void eat(){
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for(Class<? extends Animal> animalClass : animals.keySet()){

            List<Animal> animalList = animals.get(animalClass);

            String fileName = animalClass.getName() + "Chances";
            Map<String, Double> chancesToEat = propertyReader.readProperty(fileName);

            for (Animal animal : animalList) {
                double currentSaturation = animal.getCurrentSaturation();

                if(currentSaturation >= animal.getWeightOfFoodForFullSaturation()){
                    animal.setCurrentSaturation(animal.getWeightOfFoodForFullSaturation());
                    continue;
                }

                for(Class<? extends Animal> animalToEat: mapAnimals.keySet()){
                    double chanceFromProperty = chancesToEat.get(animalToEat.getName());

                    if(currentSaturation >= animal.getWeightOfFoodForFullSaturation()){
                        return;
                    }

                    if(chanceFromProperty == 0.0){
                        continue;
                    }

                    double chance = 100.0 - chanceFromProperty;

                    //Calculate the random number that is less or equals than 100.
                    // It shows whether you have a chance to eat an animal or a plant.
                    double currentChance =random.nextDouble(0,100);

                    ArrayList<Animal> animalsToEatList = animals.get(animalToEat);

                    if(currentChance >= chance && !animalsToEatList.isEmpty()){
                        animal.eat(animalsToEatList.get(animalsToEatList.size()-1));
                        animalsToEatList.remove(animalsToEatList.size()-1);
                    }
                }

                for(Class<? extends Plant> plantToEat: mapPlants.keySet()){
                    double chanceFromProperty = chancesToEat.get(plantToEat.getName());

                    if(currentSaturation >= animal.getWeightOfFoodForFullSaturation()){
                        return;
                    }

                    if(chanceFromProperty == 0.0){
                        continue;
                    }

                    double chance = 100.0 - chanceFromProperty;

                    //Calculate the random number that is less or equals than 100.
                    // It shows whether you have a chance to eat an animal or a plant.
                    double currentChance =random.nextDouble(0,100);

                    ArrayList<Plant> plantsToEatList = plants.get(plantToEat);

                    if(currentChance >= chance && !plantsToEatList.isEmpty()){
                        animal.eat(plantsToEatList.get(plantsToEatList.size()-1));
                        plantsToEatList.remove(plantsToEatList.size()-1);
                    }
                }
            }
        }
    }

    private void reproduction(){
        for(Class<? extends Animal> animalKey : animals.keySet()){
            ArrayList<Animal> animalsList = animals.get(animalKey);
            int numberOfCouples = (int) Math.floor(animalsList.size() / 2);

            if(numberOfCouples > 0){
                for(int i = 0; i < numberOfCouples; i++){
                    Collections.addAll(animalsList, animalsList.get(i).reproduction(animalKey, animalFactory));
                }
            }
        }
    }

    private void move(){
        for(Class<? extends Animal> animalKey : animals.keySet()){
            ArrayList<Animal> animalsList = animals.get(animalKey);

            for(Animal animal : animalsList){
                Coordinates newCoordinates = animal.move(coordinates, maxCoordinateY, maxCoordinateX);

                if (coordinates.equals(newCoordinates)){
                    continue;
                }

                ArrayList<Animal> listOfNewLocation = listOfAllLocations[newCoordinates.getY()][newCoordinates.getX()]
                        .getMapAnimals().get(animalKey);

                if(listOfNewLocation.size() < animal.getMaxNumberOnOneCell()){
                    listOfNewLocation.add(animal);
                    animalsList.remove(animal);
                }
            }
        }
    }

    private void fillLocation(){
        mapAnimals.forEach((aClass, value) -> processAnimal(aClass, randomNumber(value)));
        mapPlants.forEach((pClass, value) -> processPlant(pClass, randomNumber(value)));
    }

    private void processAnimal(Class<? extends Animal> aClass, Integer value) {
        ArrayList<Animal> animalsList = new ArrayList<>();

        for (int i = 0; i < value; i++) {
            animalsList.add(animalFactory.create(aClass));
        }

        animals.put(aClass, animalsList);
    }

    private void processPlant(Class<? extends Plant> pClass, Integer value) {
        ArrayList<Plant> plantsList = new ArrayList<>();

        for (int i = 0; i < value; i++) {
            plantsList.add(plantFactory.create(pClass));
        }

        plants.put(pClass, plantsList);
    }

    private Integer randomNumber(Integer number){
        return new Random().nextInt(number);
    }

    public Coordinates getCoordinates() {return coordinates;}
    public HashMap<Class<? extends Animal>, ArrayList<Animal>> getMapAnimals() {return animals;}
    public HashMap<Class<? extends Plant>, ArrayList<Plant>> getMapPlants() {return plants;}
}
