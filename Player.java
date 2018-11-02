
class Player {
    int health = 10;
    String name;
    Coords position;

    public Player(String name) {
        this.name = name;
    }

    public void setPosition(Coords position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return name + " is at " + position;
    }
}