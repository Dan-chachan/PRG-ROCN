class Cell {

    private String wall = "##";
    private String empty= "..";
    private String start = "||";
    private String exit = "/\\";
    private String player = "oo";


    Content content = Content.EMPTY;
    //Creature creature = null; // TODO
    //Loot loot = null;
    int x, y;

    
    public Cell(int x, int y) {
        if (x < 0 || y < 0) throw new IllegalArgumentException();

        this.x = x;
        this.y = y;
    }

    public Cell(int x, int y, Content c) {
        if (x < 0 || y < 0) throw new IllegalArgumentException();

        this.x = x;
        this.y = y;
        this.setContent(c);        
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setContent(Content c) {
        this.content = c;
    }

    // void setCreature(Creature c) {
    //     if (c instanceof Enemy) 
    //         this.content = Content.ENEMY;
    //     else if (c instanceof Player)
    //         this.content = Content.PLAYER;
    //     this.creature = c;
    // }



    @Override
    public String toString() {
        switch (this.content) { // TODO
            case EMPTY: return this.empty;
            case WALL: return this.wall;
            case LOOT: return this.wall;
            case ENEMY: return this.wall;
            case PLAYER: return this.player;
            case EXIT: return this.exit;
            case START: return this.start;
            // ... more caseseses.... 
            default: return "";
        }
    }
}

enum Content {
    BASE,
    EMPTY, 
    WALL, 
    LOOT, 
    ENEMY, 
    PLAYER,
    EXIT, 
    START
}