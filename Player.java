
class Player {
    int health = 10;
    String name;
    Coords position;
    Inventory inv;

    public Player(String name) {
        this.name = name;

        this.inv = new Inventory();
    }

    public void setPosition(Coords position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return name + " is at " + position;
    }

    public void viewInventory() {
        System.out.println(this.inv);
    }
}