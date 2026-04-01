package Island.domain.service;

import Island.domain.animals.Animal;
import Island.domain.animals.herbivores.Duck;
import Island.domain.animals.herbivores.Horse;
import Island.domain.animals.predators.Wolf;

import java.util.HashMap;
import java.util.function.Supplier;

public class AnimalFactory {

    private final HashMap<Class<? extends Animal>, Supplier<? extends Animal>> map = new HashMap<>();

    {
        //Predators
        map.put(Wolf.class, () -> new Wolf());
        map.put(Horse.class, () -> new Horse());

        //Herbivores
        map.put(Duck.class, () -> new Duck());

    }

    public Animal create(Class<? extends Animal> aClass) {
        return map.get(aClass).get();
    }
}
