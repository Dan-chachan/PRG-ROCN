
class Player {
    int health = 10;
    String name;
    Coords position;
    Inventory inv;
    Dungeon dung;

    public Player(String name, Dungeon dung) {
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

    // TODO deny the player movement through walls n shit
    public void move(Direction dir) {
        int x = this.position.x;
        int y = this.position.y;

        switch (dir) {
            case UP:
                y -= 1;
                break;
            case DOWN:
                y += 1;
                break;
            case LEFT:
                x -= 1;
                break;
            case RIGHT:
                x += 1;
                break;
            default:
                break;
        }
        dung.currentLevel.data[position.y][position.x].setContent(Content.EMPTY);
        dung.currentLevel.data[y][x].setContent(Content.PLAYER);
        this.setPosition(new Coords(x, y));
    }
}

enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
}