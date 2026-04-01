package Island.domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDirection {
    private final ArrayList<Direction> directions = new ArrayList<>();
    {
        Collections.addAll(directions, Direction.values());
    }

    public Direction getDirection(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int index = random.nextInt(0, directions.size()-1);

        return directions.get(index);
    }
}
