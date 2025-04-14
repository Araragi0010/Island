package Island.domain.service;

import Island.domain.plants.Grass;
import Island.domain.plants.Plant;

import java.util.HashMap;
import java.util.function.Supplier;

public class PlantFactory {

    HashMap<Class<? extends Plant>, Supplier<? extends Plant>> map = new HashMap<>();

    {
        map.put(Grass.class, () -> new Grass());
    }

    public Plant create(Class<? extends Plant> pClass) {
        return map.get(pClass).get();
    }
}
