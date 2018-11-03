

class Item {
    public String name;

    public Item(String name) {
        this.name = name;
    }

    public Item() {
        this("....");
    }

    @Override
    public String toString() {
        return this.name;
    }
}