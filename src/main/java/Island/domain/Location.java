package Island.domain;

public class Location {
    private final Coordinates coordinates;

    public Location(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
