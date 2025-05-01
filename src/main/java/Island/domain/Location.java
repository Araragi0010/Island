package Island.domain;

import Island.domain.animals.Animal;
import Island.domain.animals.herbivores.Duck;
import Island.domain.animals.herbivores.Horse;
import Island.domain.animals.predators.Wolf;
import Island.domain.plants.Grass;
import Island.domain.plants.Plant;
import Island.domain.service.AnimalFactory;
import Island.domain.service.PlantFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Location {

    private static final HashMap< Class<? extends Animal> , Integer> mapAnimals = new HashMap<>();
    private static final HashMap< Class<? extends Plant> , Integer> mapPlants = new HashMap<>();

    static {
        //Predators
        mapAnimals.put(Wolf.class, 30);
        mapAnimals.put(Horse.class, 20);

        //Herbivores
        mapAnimals.put(Duck.class, 200);

        //Plants
        mapPlants.put(Grass.class, 200);
    }

    private final int maxCoordinateY;
    private final int maxCoordinateX;
    private final Coordinates coordinates;

    private final AnimalFactory animalFactory = new AnimalFactory();
    private final PlantFactory plantFactory = new PlantFactory();

    HashMap<Class<? extends Animal>, ArrayList<Animal>> animals = new HashMap<>();
    HashMap<Class<? extends Plant>, ArrayList<Plant>> plants = new HashMap<>();

    public Location(Coordinates coordinates, int maxCoordinateY, int maxCoordinateX) {
        this.maxCoordinateY = maxCoordinateY;
        this.maxCoordinateX = maxCoordinateX;
        this.coordinates = coordinates;
        fillLocation();
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

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
