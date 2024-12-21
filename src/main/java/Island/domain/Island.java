package Island.domain;

public class Island {

    private final Location[][] locations;

    public Island(int y, int x) {

        locations = new Location[y][x];
        fillIsland();

    }

    private void fillIsland() {
        for (int y = 0; y < locations.length; y++) {
            for (int x = 0; x < locations[y].length; x++) {
                Coordinates coordinates = new Coordinates(y, x);
                locations[y][x] = new Location(coordinates);
            }
        }
    }
}
